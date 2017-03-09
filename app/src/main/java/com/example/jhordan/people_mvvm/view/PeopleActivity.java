/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.PeopleActivityBinding;
import com.example.jhordan.people_mvvm.viewmodel.PeopleViewModel;
import java.util.Observable;
import java.util.Observer;

public class PeopleActivity extends AppCompatActivity implements Observer {

  private PeopleActivityBinding peopleActivityBinding;
  private PeopleViewModel peopleViewModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initDataBinding();
    setSupportActionBar(peopleActivityBinding.toolbar);
    setupListPeopleView(peopleActivityBinding.listPeople);
    setupObserver(peopleViewModel);
  }

  private void initDataBinding() {
    peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_activity);
    peopleViewModel = new PeopleViewModel(this);
    peopleActivityBinding.setMainViewModel(peopleViewModel);
  }


  private void setupListPeopleView(RecyclerView listPeople) {
    PeopleAdapter adapter = new PeopleAdapter();
    listPeople.setAdapter(adapter);
    listPeople.setLayoutManager(new LinearLayoutManager(this));
  }

  public void setupObserver(Observable observable) {
    observable.addObserver(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    peopleViewModel.reset();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_github) {
      startActivityActionView();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void  startActivityActionView() {
    startActivity(
        new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/erikcaffrey/People-MVVM")));
  }

  @Override public void update(Observable observable, Object data) {
    if (observable instanceof PeopleViewModel) {
      PeopleAdapter peopleAdapter = (PeopleAdapter) peopleActivityBinding.listPeople.getAdapter();
      PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
      peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
    }
  }
}
