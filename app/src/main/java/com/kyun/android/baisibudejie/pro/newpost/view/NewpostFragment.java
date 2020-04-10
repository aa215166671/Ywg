package com.kyun.android.baisibudejie.pro.newpost.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;
import com.kyun.android.baisibudejie.pro.newpost.view.adapter.NewpostAdapter;

import java.util.Arrays;

public class NewpostFragment extends BaseFragment {

    private TabLayout tab_newpost;
    private ViewPager vp_newpost;

    @Override
    public int getContentView() {
        return R.layout.fragment_newpost;
    }

    @Override
    public void initContentView(View contentView) {
        this.tab_newpost=contentView.findViewById(R.id.tab_newpost);
        this.vp_newpost=contentView.findViewById(R.id.vp_newpost);
    }

    @Override
    public void initData() {
        String[] titles=getResources().getStringArray(R.array.newpost_video_tab);
        NewpostAdapter adapter=new NewpostAdapter(getFragmentManager(), Arrays.asList(titles));
        this.vp_newpost.setAdapter(adapter);
        this.tab_newpost.setupWithViewPager(this.vp_newpost);
    }
}
