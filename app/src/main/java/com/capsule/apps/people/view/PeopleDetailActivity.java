package com.capsule.apps.people.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.capsule.apps.people.R;
import com.capsule.apps.people.databinding.ActivityDetailPeopleBinding;
import com.capsule.apps.people.model.People;
import com.capsule.apps.people.viewmodel.PeopleDetailViewModel;
import com.capsule.apps.people.viewmodel.PeopleDetailViewModelContract;

public class PeopleDetailActivity extends AppCompatActivity implements PeopleDetailViewModelContract.DetailView {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";
    private ActivityDetailPeopleBinding mActivityDetailPeopleBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDetailPeopleBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_people);
        setSupportActionBar(mActivityDetailPeopleBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Context getContext() {
        return PeopleDetailActivity.this;
    }

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void getExtrasFromIntent() {
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
        mActivityDetailPeopleBinding.setPeopleDetailViewModel(peopleDetailViewModel);
        setTitle(people.name.title + "." + people.name.first + " " + people.name.last);
    }
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
