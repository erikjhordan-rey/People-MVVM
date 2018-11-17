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
import android.view.View;

import com.example.jhordan.people_mvvm.data.FakeRandomUserGeneratorAPI;
import com.example.jhordan.people_mvvm.data.PeopleService;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.viewmodel.PeopleViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class) public class PeopleViewModelTest {

    private static final String URL_TEST = "http://api.randomuser.me/?results=10&nat=en";

    @Mock private PeopleService peopleService;
    private Context mockContext = mock(Context.class);

    private PeopleViewModel peopleViewModel;

    @Before public void setUpMainViewModelTest() {
        peopleViewModel = new PeopleViewModel(mockContext);
    }

    @Test public void simulateGivenTheUserCallListFromApi() {
        List<People> peoples = FakeRandomUserGeneratorAPI.getPeopleList();
        doReturn(Observable.just(peoples)).when(peopleService).fetchPeople(URL_TEST);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() {
        peopleViewModel.initializeViews();
        assertEquals(View.GONE, peopleViewModel.peopleLabel.get());
        assertEquals(View.GONE, peopleViewModel.peopleRecycler.get());
        assertEquals(View.VISIBLE, peopleViewModel.peopleProgress.get());
    }
}
