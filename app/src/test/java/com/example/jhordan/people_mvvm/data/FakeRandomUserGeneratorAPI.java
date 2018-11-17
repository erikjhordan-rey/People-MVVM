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

package com.example.jhordan.people_mvvm.data;

import com.example.jhordan.people_mvvm.model.Location;
import com.example.jhordan.people_mvvm.model.Login;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomUserGeneratorAPI {

  private static final String PEOPLE_CELL_TEST = "0177-6155420";
  private static final String PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com";
  private static final String PEOPLE_PICTURE_TEST =
      "http://api.randomuser.me/portraits/women/39.jpg";
  private static final String PEOPLE_TITLE_TEST = "ms";
  private static final String PEOPLE_FIRST_TEST = "constance";
  private static final String PEOPLE_LAST_TEST = "fowler";
  private static final String PEOPLE_STREET_TEST = "9193 brock rd";
  private static final String PEOPLE_CITY_TEST = "flatrock";
  private static final String PEOPLE_STATE_TEST = "prince edward island";
  private static final String PEOPLE_USER_NAME_TEST = "organicgoose874";

  public static List<People> getPeopleList() {
    List<People> peoples = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      peoples.add(getPeople());
    }
    return peoples;
  }

  public static People getPeople() {
    People people = new People();
    people.picture = new Picture();
    people.location = new Location();
    people.login = new Login();
    people.login.userName = PEOPLE_USER_NAME_TEST;
    people.fullName = PEOPLE_TITLE_TEST + "." + PEOPLE_FIRST_TEST + " " + PEOPLE_LAST_TEST;
    people.cell = PEOPLE_CELL_TEST;
    people.mail = PEOPLE_MAIL_TEST;
    people.picture.large = PEOPLE_PICTURE_TEST;
    people.location.street = PEOPLE_STREET_TEST;
    people.location.city = PEOPLE_CITY_TEST;
    people.location.state = PEOPLE_STATE_TEST;
    return people;
  }
}
