package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Set_name extends Activity {
    private EditText User_name;
    Bb_post my_info_name;
    private Handler handler;
    Map<String, String> map = new HashMap<String, String>();
    private static String url_my_info = "http://loco.xinbinlong.com/login/Info/my_info_name.php";

    private static String url_my_info_set_name = "http://loco.xinbinlong.com/login/Info/my_info_set_name.php";
    Bb_post my_info_set_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_nicheng );

        load();
        tiaozhuan();
        initView();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        User_name.setHint(my_info_name.getUser_name());
                        break;
                    case 2:
                       Toast.makeText(Set_name.this,"设置成功！",Toast.LENGTH_SHORT).show();
                        startActivity( new Intent( Set_name.this, GRXX.class ) );
                        finish();
                        break;
                }
            }
        };
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("user_id", DL_3.user_id);
                    HttpUtil.post(url_my_info, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            my_info_name = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.obj = my_info_name; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView() {
        User_name=(EditText) findViewById(R.id.user_name);
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_nicheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.baocun_nicheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (User_name.getText().toString().equals("")) {
                    Toast.makeText(Set_name.this,"输入的名字为空，请输入需要更改的名字！",Toast.LENGTH_SHORT).show();
                } else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                map.put("user_id", DL_3.user_id);
                                map.put("user_name", User_name.getText().toString());
                                HttpUtil.post(url_my_info_set_name, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Log.e("DaiDai", "OnFaile:", e);
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String responseBody = response.body().string();
                                        Gson gson = new Gson();
                                        my_info_set_name = gson.fromJson(responseBody, Bb_post.class);
                                        //发送登录成功的消息
                                        Message msg = handler.obtainMessage();
                                        msg.what = 2;
                                        msg.obj = my_info_set_name; //把登录结果也发送过去
                                        handler.sendMessage(msg);
                                    }
                                }, map);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        } );
    }
}
