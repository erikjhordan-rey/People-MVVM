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

package com.example.jhordan.people_mvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;

import com.example.jhordan.people_mvvm.data.MockView;
import com.example.jhordan.people_mvvm.model.Name;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.model.Picture;
import com.example.jhordan.people_mvvm.viewmodel.ItemPeopleViewModel;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class ItemPeopleViewModelTest {

    private static final String PEOPLE_CELL_TEST = "0177-6155420";
    private static final String PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com";
    private static final String PEOPLE_PICTURE_TEST = "http://api.randomuser.me/portraits/women/39.jpg";
    private static final String PEOPLE_TITLE_TEST = "ms";
    private static final String PEOPLE_FIRST_TEST = "constance";
    private static final String PEOPLE_LAST_TEST = "fowler";

    private Context mockContext = mock(Context.class);

    @Test public void shouldGetPeopleCell() throws Exception {
        People people = new People();
        people.cell = PEOPLE_CELL_TEST;
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        assertEquals(people.cell, itemPeopleViewModel.getCell());
    }

    @Test public void shouldGetPeopleMail() throws Exception {
        People people = new People();
        people.mail = PEOPLE_MAIL_TEST;
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        assertEquals(people.mail, itemPeopleViewModel.getMail());
    }

    @Ignore public void shouldGetPeoplePicture() throws Exception {
        People people = new People();
        people.picture = Mockito.mock(Picture.class);
        people.picture.large = PEOPLE_PICTURE_TEST;
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        assertEquals(people.picture.large, itemPeopleViewModel.getPictureProfile());
    }

    @Test public void shouldGetPeopleFullName() throws Exception {
        People people = new People();
        people.name = Mockito.mock(Name.class);
        people.name.title = PEOPLE_TITLE_TEST;
        people.name.firts = PEOPLE_FIRST_TEST;
        people.name.last = PEOPLE_LAST_TEST;
        people.fullName = people.name.title + "." + people.name.firts + " " + people.name.last;
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        assertEquals(people.fullName, itemPeopleViewModel.getFullName());
    }

    @Test public void shouldStartPeopleDetailActivityOnItemClick() throws Exception {
        People people = new People();
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        itemPeopleViewModel.onItemClick(new MockView(mockContext));
        verify(mockContext).startActivity(any(Intent.class));
    }

    @Test public void shouldNotifyPropertyChangeWhenSetPeople() throws Exception {
        People people = new People();
        ItemPeopleViewModel itemPeopleViewModel = new ItemPeopleViewModel(people, mockContext);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        itemPeopleViewModel.addOnPropertyChangedCallback(mockCallback);
        itemPeopleViewModel.setPeople(people);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
