package com.capsule.apps.people.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.capsule.apps.people.PeopleApp;
import com.capsule.apps.people.R;
import com.capsule.apps.people.data.PeopleResponse;
import com.capsule.apps.people.data.PeopleService;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PeopleViewModel implements PeopleViewModelContract.ViewModel {

    public ObservableInt mPeopleProgress;
    public ObservableInt mPeopleList;
    public ObservableInt mPeopleLabel;
    public ObservableField<String> mMessageLabel;


    private PeopleViewModelContract.MainView mMainView;
    private Context mContext;
    private Subscription mSubscription;

    public PeopleViewModel(@NonNull PeopleViewModelContract.MainView mainView, @NonNull Context context) {
        this.mMainView = mainView;
        this.mContext = context;
        this.mPeopleProgress = new ObservableInt(View.GONE);
        this.mPeopleList = new ObservableInt(View.GONE);
        this.mPeopleLabel = new ObservableInt(View.VISIBLE);
        mMessageLabel = new ObservableField<>(mContext.getString(R.string.default_loading_people));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }

    public void initializeViews() {
        mPeopleLabel.set(View.GONE);
        mPeopleList.set(View.GONE);
        mPeopleProgress.set(View.VISIBLE);
    }

    private void fetchPeopleList() {
        final String URL = "http://api.randomuser.me/?results=10&nat=en";
        unSubscribeFromObservable();
        PeopleApp app = PeopleApp.create(mContext);
        PeopleService peopleService = app.getPeopleService();
        mSubscription = peopleService.fetchPeople(URL).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.subscribeScheduler()).subscribe(new Action1<PeopleResponse>() {
                    @Override
                    public void call(PeopleResponse peopleResponse) {
                        mPeopleProgress.set(View.GONE);
                        mPeopleLabel.set(View.GONE);
                        mPeopleList.set(View.VISIBLE);

                        if (mMainView != null) {
                            mMainView.loadData(peopleResponse.getPeopleList());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mMessageLabel.set(mContext.getString(R.string.error_loading_people));
                        mPeopleProgress.set(View.GONE);
                        mPeopleLabel.set(View.VISIBLE);
                        mPeopleList.set(View.GONE);
                    }
                });
    }

    @Override
    public void destroy() {
        reset();
    }

    private void unSubscribeFromObservable() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void reset() {
        unSubscribeFromObservable();
        mSubscription = null;
        mContext = null;
        mMainView = null;
    }

}
