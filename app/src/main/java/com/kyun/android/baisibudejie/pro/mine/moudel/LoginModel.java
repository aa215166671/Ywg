package com.kyun.android.baisibudejie.pro.mine.moudel;

import android.content.Context;

import com.kyun.android.baisibudejie.http.impl.RequestParam;
import com.kyun.android.baisibudejie.http.impl.SystemHttpCommand;
import com.kyun.android.baisibudejie.http.utils.HttpTask;
import com.kyun.android.baisibudejie.http.utils.HttpUtils;
import com.kyun.android.baisibudejie.pro.base.model.BaseModel;

public class LoginModel extends BaseModel {

    public LoginModel(Context context) {
        super( context );
    }

    public void login(String username, String password, HttpUtils.OnHttpResultListener onHttpResultListener){

        RequestParam requestParam=new RequestParam();
        requestParam.put("username",username);
        requestParam.put("password",password);
        HttpTask httpTask=new HttpTask("http://192.168.57.1:8080/Dream/LoginServlet",requestParam, onHttpResultListener, new SystemHttpCommand());
        httpTask.execute();
    }
}
