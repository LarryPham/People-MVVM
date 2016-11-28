package com.capsule.apps.people.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.capsule.apps.people.model.People;
import com.capsule.apps.people.view.PeopleDetailActivity;

public class ItemPeopleViewModel extends BaseObservable {
    private People mPeople;
    private Context mContext;

    public ItemPeopleViewModel(People people, Context context) {
        this.mPeople = people;
        this.mContext = context;
    }

    public String getFullName() {
        this.mPeople.fullName = mPeople.name.title + "." + mPeople.name.first + " " + mPeople.name.last;
        return mPeople.fullName;
    }

    public String getCell() {
        return mPeople.cell;
    }

    public String getMail() {
        return mPeople.email;
    }

    public String getPictureProfile() {
        return mPeople.picture.medium;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        mContext.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), mPeople));
    }

    public void setPeople(People people) {
        this.mPeople = people;
        notifyChange();
    }
}
