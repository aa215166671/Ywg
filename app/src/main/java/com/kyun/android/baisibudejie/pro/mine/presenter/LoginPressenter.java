package com.kyun.android.baisibudejie.pro.mine.presenter;

import android.content.Context;

import com.kyun.android.baisibudejie.bean.UserBean;
import com.kyun.android.baisibudejie.http.utils.HttpUtils;
import com.kyun.android.baisibudejie.pro.base.presenter.BasePresenter;
import com.kyun.android.baisibudejie.pro.mine.moudel.LoginModel;

public class LoginPressenter extends BasePresenter<LoginModel> {

    public LoginPressenter(Context context) {
        super(context);
    }

    @Override
    public LoginModel bindModel() {
        return new LoginModel(getContext());
    }

    public void login(String username, String password, final OnUIThreadListener<UserBean> onUIThreadListener){
        getModel().login( username, password, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                //解析model
                if(result==null){
                    onUIThreadListener.OnResult(null);
                }else{
                    UserBean userBean = getGson().fromJson( result, UserBean.class );
                    onUIThreadListener.OnResult(userBean);
                }
            }
        } );
    }
}
