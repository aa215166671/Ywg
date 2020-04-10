package com.kyun.android.baisibudejie.pro.publish.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.WangLuoError;
import com.kyun.android.baisibudejie.ZC_2;
import com.kyun.android.baisibudejie.mvp.presenter.impl.MvpBasePresenter;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.TD_file.TDmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PublishFragment extends BaseFragment implements View.OnClickListener {

private Button mZhuan;

    @Override
    public int getContentView() {
        return R.layout.f1;
    }

    @Override
    public void initContentView(View viewContent) {
        mZhuan = viewContent.findViewById(R.id.zhuan);
        this.mZhuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuan:
                Intent intent = new Intent(getActivity(), Publish.class);
                startActivity(intent);

                break;
        }
    }
}

