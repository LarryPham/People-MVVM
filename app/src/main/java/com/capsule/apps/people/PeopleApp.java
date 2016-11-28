package com.capsule.apps.people;

import android.app.Application;
import android.content.Context;

import com.capsule.apps.people.data.PeopleFactory;
import com.capsule.apps.people.data.PeopleService;

import rx.Scheduler;
import rx.schedulers.Schedulers;


public class PeopleApp extends Application {

    private PeopleService mPeopleService;
    private Scheduler mScheduler;

    private static PeopleApp get(Context context) {
        return (PeopleApp) context.getApplicationContext();
    }

    public static PeopleApp create(Context context) {
        return PeopleApp.get(context);
    }

    public PeopleService getPeopleService() {
        if (mPeopleService == null) mPeopleService = PeopleFactory.create();
        return mPeopleService;
    }


    public Scheduler subscribeScheduler() {
        if (mScheduler == null) mScheduler = Schedulers.io();
        return mScheduler;
    }

    public void setPeopleService(PeopleService peopleService) {
        this.mPeopleService = peopleService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.mScheduler = scheduler;
    }
}
