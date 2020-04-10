package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.TiaoZaoAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.view.LoadMoreRecyclerView;


public class FragmentTiaoZao extends Fragment {

    private static FragmentTiaoZao instance=null;
    public static FragmentTiaoZao newInstance() {
        if(instance==null){
            instance= new FragmentTiaoZao();
        }
        return instance;
    }
    public FragmentTiaoZao(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_tiaozao, container, false);
        final LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.TiaoZao_Rview);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]tz_ImgList = new int[]{R.drawable.youxiyizi,R.drawable.xiangce1,R.drawable.yizi,R.drawable.youxiyizi};
        String[] tz_neirong = { "专业大神专业椅子", "还没用完的相册", "摇到天荒地老的摇椅", "我想买这种游戏机"};
        String[] tz_chakan_sum = { "56", "42", "130", "520"};
        String[] tz_cleixing = { "椅子", "物品", "椅子", "游戏机"};
        String[] tz_maimai = { "售", "售", "售", "买"};
        String[] tz_jiage = { "￥120", "￥26", "￥100", "￥80"};
        mRecyclerView.setAdapter(new TiaoZaoAdapter(getActivity(), tz_ImgList,tz_neirong,tz_chakan_sum,tz_cleixing,tz_maimai,tz_jiage));
        return view;
    }
}
