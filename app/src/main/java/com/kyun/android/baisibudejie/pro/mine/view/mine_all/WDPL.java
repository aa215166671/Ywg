package com.kyun.android.baisibudejie.pro.mine.view.mine_all;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentXuanShang;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.XuanShang_comment;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.Adapter.Wdpl_adapter;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WDPL extends Activity {

    Map<String, String> map = new HashMap<String, String>();
    public ArrayList<Bb_post> comment_lister;
    private Bb_post my_comment;
    private Handler handler;
    private static String url_my_comment = "http://loco.xinbinlong.com/login/my_comment.php";

    private ListView comment_Listview;
    private TextView my_comment_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_wodepinglun);
        initview();
        //跳转事件
        tiaozhuan();
        laod();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
               comment_Listview.setAdapter(new Wdpl_adapter(WDPL.this, comment_lister));
                        my_comment_size.setText("全部评论("+comment_lister.size()+")");
                        break;
                }
            }
        };
    }

    private void initview() {
        comment_Listview=(ListView)findViewById(R.id.my_wdpl_Listview);
        my_comment_size=(TextView)findViewById(R.id.my_comment_size);
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_wodepinglun ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }

//查询我的评论
    private void laod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("user_id", DL_3.user_id);
                    HttpUtil.post(url_my_comment, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            JsonParser parser = new JsonParser();
                            JsonArray jsonArray = parser.parse(responseBody).getAsJsonArray();
                            Gson gson = new Gson();
                            comment_lister = new ArrayList<>();
                            for (JsonElement user : jsonArray) {
                                my_comment = gson.fromJson(user, Bb_post.class);
                                comment_lister.add(my_comment);
                            }
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.obj = my_comment; //把登录结果也发送过去
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
