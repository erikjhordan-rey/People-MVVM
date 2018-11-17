/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import com.example.jhordan.people_mvvm.PeopleApplication;
import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.data.PeopleFactory;
import com.example.jhordan.people_mvvm.data.PeopleResponse;
import com.example.jhordan.people_mvvm.data.PeopleService;
import com.example.jhordan.people_mvvm.model.People;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class PeopleViewModel extends Observable {

  public ObservableInt peopleProgress;
  public ObservableInt peopleRecycler;
  public ObservableInt peopleLabel;
  public ObservableField<String> messageLabel;

  private List<People> peopleList;
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public PeopleViewModel(@NonNull Context context) {

    this.context = context;
    this.peopleList = new ArrayList<>();
    peopleProgress = new ObservableInt(View.GONE);
    peopleRecycler = new ObservableInt(View.GONE);
    peopleLabel = new ObservableInt(View.VISIBLE);
    messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
  }

  public void onClickFabLoad(View view) {
    initializeViews();
    fetchPeopleList();
  }

  //It is "public" to show an example of test
  public void initializeViews() {
    peopleLabel.set(View.GONE);
    peopleRecycler.set(View.GONE);
    peopleProgress.set(View.VISIBLE);
  }

  // NOTE: This method can return the observer and just subscribe to it in the activity or fragment,
  // an Activity or Fragment needn't to implement from the Observer java class
  // (it was my first approach to avoid RX in UI now we can use LiveData instead)
  private void fetchPeopleList() {

    PeopleApplication peopleApplication = PeopleApplication.create(context);
    PeopleService peopleService = peopleApplication.getPeopleService();

    Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
        .subscribeOn(peopleApplication.subscribeScheduler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<PeopleResponse>() {
          @Override public void accept(PeopleResponse peopleResponse) {
            changePeopleDataSet(peopleResponse.getPeopleList());
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.GONE);
            peopleRecycler.set(View.VISIBLE);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) {
            messageLabel.set(context.getString(R.string.error_loading_people));
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.VISIBLE);
            peopleRecycler.set(View.GONE);
          }
        });

    compositeDisposable.add(disposable);
  }

  private void changePeopleDataSet(List<People> peoples) {
    peopleList.addAll(peoples);
    setChanged();
    notifyObservers();
  }

  public List<People> getPeopleList() {
    return peopleList;
  }

  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }
}
