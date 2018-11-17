/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhordan.people_mvvm.model.People;

public class PeopleDetailViewModel {

  private People people;

  public PeopleDetailViewModel(People people) {
    this.people = people;
  }

  public String getFullUserName() {
    people.fullName = people.name.title + "." + people.name.firts + " " + people.name.last;
    return people.fullName;
  }

  public String getUserName() {
    return people.login.userName;
  }

  public String getEmail() {
    return people.mail;
  }

  public int getEmailVisibility() {
    return people.hasEmail() ? View.VISIBLE : View.GONE;
  }

  public String getCell() {
    return people.cell;
  }

  public String getPictureProfile() {
    return people.picture.large;
  }

  public String getAddress() {
    return people.location.street
        + " "
        + people.location.city
        + " "
        + people.location.state;
  }

  public String getGender() {
    return people.gender;
  }

  @BindingAdapter({"imageUrl"})
  public static void loadImage(ImageView view, String imageUrl) {
    Glide.with(view.getContext()).load(imageUrl).into(view);
  }
}
