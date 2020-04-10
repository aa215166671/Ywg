package com.kyun.android.baisibudejie.pro.attention.view;

import android.view.View;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;

//订阅列表
public class AttentionSubscriptionFragment extends BaseFragment {

    private int mType=0;
    private String mTitle;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_attention_subscription;
    }

    @Override
    public void initContentView(View viewContent) {

    }

    @Override
    public void initData() {

    }

}
