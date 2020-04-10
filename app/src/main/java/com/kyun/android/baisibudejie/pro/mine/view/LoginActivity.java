package com.kyun.android.baisibudejie.pro.mine.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.UserBean;
import com.kyun.android.baisibudejie.mvp.presenter.impl.MvpBasePresenter;
import com.kyun.android.baisibudejie.pro.base.presenter.BasePresenter;
import com.kyun.android.baisibudejie.pro.base.view.BaseActivity;
import com.kyun.android.baisibudejie.pro.mine.presenter.LoginPressenter;
import com.kyun.android.baisibudejie.pro.mine.view.navigation.LoginNavigationBuilder;
import com.kyun.android.baisibudejie.utils.ToastUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LoginPressenter loginPressenter;
    private EditText et_phone;
    private EditText et_password;

    @Override
    public MvpBasePresenter bindPresenter() {
        loginPressenter=new LoginPressenter(this);
        return loginPressenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        initView();
        initToolBar();
        et_phone.getBackground().setAlpha(50);
        et_password.getBackground().setAlpha(50);
    }

    private void initToolBar(){
        LinearLayout ll_login=findViewById(R.id.ll_login);
        LoginNavigationBuilder builder=new LoginNavigationBuilder(this);
        builder.setLeftIcon(R.drawable.login_close_selector )
                .setLeftIconOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                } );
        builder.createAndBind(ll_login);
    }

    private void initView(){
        et_phone = findViewById( R.id.et_phone);
        et_password = findViewById( R.id.et_password);
        findViewById( R.id.bt_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        loginPressenter.login( et_phone.getText().toString(), et_password.getText().toString(), new BasePresenter.OnUIThreadListener <UserBean>() {
            @Override
            public void OnResult(UserBean result) {
                if (result==null){
                    ToastUtil.showToast(LoginActivity.this,"登录失败！");
                }else {
                    ToastUtil.showToast(LoginActivity.this,"登录成功！");
                }
            }
        } );

    }
}
