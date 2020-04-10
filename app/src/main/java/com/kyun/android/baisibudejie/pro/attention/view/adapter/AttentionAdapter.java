package com.kyun.android.baisibudejie.pro.attention.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kyun.android.baisibudejie.pro.attention.view.AttentionListFragment;
import com.kyun.android.baisibudejie.pro.attention.view.AttentionSubscriptionFragment;

import java.util.List;


public class AttentionAdapter extends FragmentStatePagerAdapter {

    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;

    public AttentionAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            AttentionSubscriptionFragment fragment = new AttentionSubscriptionFragment();
            String[] title = mTitles.get(position).split(TAB_TAG);
            fragment.setType(Integer.parseInt(title[1]));
            fragment.setTitle(title[0]);
            return fragment;
        }
        AttentionListFragment fragment = new AttentionListFragment();
        String[] title = mTitles.get(position).split(TAB_TAG);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}
