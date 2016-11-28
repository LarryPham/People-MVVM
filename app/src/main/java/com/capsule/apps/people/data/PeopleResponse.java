package com.capsule.apps.people.data;

import com.capsule.apps.people.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleResponse {

    @SerializedName("results")
    private List<People> mPeopleList;

    public List<People> getPeopleList() {
        return mPeopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        mPeopleList = peopleList;
    }
}
