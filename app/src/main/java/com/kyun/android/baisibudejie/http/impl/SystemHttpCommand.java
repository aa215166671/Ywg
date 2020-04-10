package com.kyun.android.baisibudejie.http.impl;

import com.kyun.android.baisibudejie.http.IRequestParam;
import com.kyun.android.baisibudejie.http.utils.HttpUtils;
import com.kyun.android.baisibudejie.http.IHttpCommand;

import java.util.HashMap;

//
public class SystemHttpCommand implements IHttpCommand<HashMap<String,Object>> {
    @Override
    public String execute(String url, IRequestParam<HashMap <String, Object>> requestParam) {
        try {
            return HttpUtils.post(url,requestParam.getRequestParam());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
