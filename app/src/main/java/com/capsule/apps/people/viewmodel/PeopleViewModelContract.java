package com.capsule.apps.people.viewmodel;

import android.content.Context;

import com.capsule.apps.people.model.People;

import java.util.List;

public interface PeopleViewModelContract {

    interface MainView {
        Context getContext();
        void loadData(List<People> peoples);
    }

    interface ViewModel {
        void destroy();
    }
}
