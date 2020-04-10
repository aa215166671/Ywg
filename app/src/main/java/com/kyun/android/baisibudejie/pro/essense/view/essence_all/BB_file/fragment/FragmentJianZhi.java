package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyun.android.baisibudejie.R;


public class FragmentJianZhi extends Fragment {
    private static FragmentJianZhi instance=null;
    public static FragmentJianZhi newInstance() {
        if(instance==null){
            instance= new FragmentJianZhi();
        }
        return instance;
    }
    public FragmentJianZhi(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_jianzhi, container, false);

        return view;


    }

}
