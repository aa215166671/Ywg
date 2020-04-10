package com.kyun.android.baisibudejie.pro.base.model;

import android.content.Context;

import com.kyun.android.baisibudejie.mvp.model.MvpModel;

public abstract class BaseModel implements MvpModel {

    private Context context;

    public BaseModel(Context context){
        this.context=context;
    }

    public String getServerUrl(){
        return  "http://api.budejie.com";
    }
}
