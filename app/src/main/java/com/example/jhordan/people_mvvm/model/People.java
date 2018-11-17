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

package com.example.jhordan.people_mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class People implements Serializable {

  @SerializedName("gender") public String gender;

  @SerializedName("name") public Name name;

  @SerializedName("location") public Location location;

  @SerializedName("email") public String mail;

  @SerializedName("login") public Login login;

  @SerializedName("phone") public String phone;

  @SerializedName("cell") public String cell;

  @SerializedName("picture") public Picture picture;

  public String fullName;

  public boolean hasEmail() {
    return mail != null && !mail.isEmpty();
  }
}
