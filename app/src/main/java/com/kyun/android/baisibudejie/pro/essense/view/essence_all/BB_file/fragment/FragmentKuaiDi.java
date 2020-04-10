package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.KuaiDiAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.view.LoadMoreRecyclerView;


public class FragmentKuaiDi extends Fragment {
    private static FragmentKuaiDi instance=null;
    public static FragmentKuaiDi newInstance() {
        if(instance==null){
            instance= new FragmentKuaiDi();
        }
        return instance;
    }
    public FragmentKuaiDi(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_kuaidi, container, false);
        LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.KuaiDi_Rview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]mImgList = new int[]{R.drawable.na_kuaidi1,R.drawable.na_kuaidi2,R.drawable.na_kuaidi1,R.drawable.na_kuaidi2};
        String[] Name = { "三剑客", "五点就开始", "发酵时间", "费尽口舌" };
        String[] Na_jiage = { "2元", "4元", "1元", "2元" };
        String[] Na_qujiandizhi = { "校内蜂巢", "校外蜂巢", "校外蜂巢", "校内蜂巢" };
        String[] Na_shoujiandizhi = { "2区3121", "4区506", "1区203", "11区202" };
        //寄
        int[]Ji_ImgList = new int[]{R.drawable.ji_kuaidi1,R.drawable.ji_kuaidi2,R.drawable.ji_kuaidi2,R.drawable.ji_kuaidi1};
        String[] Ji_neirong = { "技工", "鼎折覆餗", "王企鹅辅导", "似懂非懂非凡哥" };
        String[] Ji_jiage = { "2元", "2元", "3元", "4元" };
        String[] Ji_qujiandizhi = { "6区311", "2区312", "1区203", "1区512" };
        String[] Ji_jijiandizhi = { "校内蜂巢", "校外蜂巢", "校外蜂巢", "校内蜂巢" };
        mRecyclerView.setAdapter(new KuaiDiAdapter(getActivity(), mImgList,Name,Na_jiage,Na_qujiandizhi,Na_shoujiandizhi,Ji_ImgList,Ji_neirong,Ji_jiage,Ji_qujiandizhi,Ji_jijiandizhi));
        return view;
    }


}

