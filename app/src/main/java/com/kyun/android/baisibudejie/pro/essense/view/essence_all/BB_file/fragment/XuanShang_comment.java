package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.Adapter.AdapterComment;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.utils.picutils.Imageutils;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.view.PhotoView;
import com.kyun.android.baisibudejie.pro.newpost.view.views.CircleNetworkImageImage;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.DateUtils;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XuanShang_comment extends Activity implements View.OnClickListener {
    private ImageView comment;

    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;

    private LinearLayout rl_enroll;
    private RelativeLayout rl_comment;

    private ListView comment_list;

    private AdapterComment adapterComment;
    public ArrayList<Bb_post> listuser;


    Map<String, String> map = new HashMap<String, String>();
    private static String url = "http://loco.xinbinlong.com/login/xuanshang_comment_item.php";
    private static String url_read = "http://loco.xinbinlong.com/login/xuanshang_comment_item_read.php";
    private static String URL_1 = "http://loco.xinbinlong.com/login/xuanshang_comment.php";
    private static String url_zan_read = "http://loco.xinbinlong.com/login/xuanshang_post_zan_read.php";
    private static String url_zan_send = "http://loco.xinbinlong.com/login/xuanshang_post_zan_send.php";
    private static String url_zan_send_jian = "http://loco.xinbinlong.com/login/xuanshang_post_zan_send_jian.php";
    Bb_post m_result;
    private Handler handler;
    private RequestQueue mQueue;
    //回传帖子
    private CircleNetworkImageImage Xuanshang_userURL;
    private TextView Xuanshang_username;
    private TextView Xuanshang_xinyuzhi;
    private TextView Xuanshang_postText;
    private ImageView Xuanshang_postUrl;
    private TextView Xuanshang_commentnum;
    private TextView Xuanshang_zan;
    private TextView Xuanshang_time;
    //评论者
    private CircleNetworkImageImage Comment_userURL;
    private TextView Comment_username;
    private TextView Comment_time;
    private TextView Comment_zan;
    private TextView Comment_text;
    //点赞
    private ImageView Xs_comment_zan;
    private int comment_Zan;
    private String mStatus;
    private SharedPreferences sharedPreferences;
    private Bb_post result;
    private Bb_post bb_post;
    private int comment_sum;
    private String qqq;
    private String post_url;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.xuanshang_comment);
        initView();
        Log.i("qq", "onCreate: "+post_url);

        load_comment();
        load();

        mQueue = Volley.newRequestQueue(this);
        handler = new Handler() {



            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        //评论数
                        comment_sum = Integer.parseInt(m_result.getComment());
                        //点赞数
                        comment_Zan = Integer.parseInt(m_result.getZan());
                        loadImage(Xuanshang_userURL, m_result.getUser_url());
                        Xuanshang_username.setText(m_result.getUser_name());
                        Xuanshang_xinyuzhi.setText(m_result.getReputation());
                        Xuanshang_postText.setText(m_result.text);
                        post_url = m_result.getText_url();
                        loadImgByVolley(Xuanshang_postUrl, post_url);

                        Xuanshang_commentnum.setText(m_result.getComment());
                        Xuanshang_time.setText(m_result.getTime());
                        Xuanshang_zan.setText(String.valueOf(comment_Zan));
                        break;
                    case 2:
                        Toast.makeText(XuanShang_comment.this, "发布成功！", Toast.LENGTH_SHORT).show();
                        onCreate(null);
                        break;
                    case 3:
                        comment_list.setAdapter(new AdapterComment(XuanShang_comment.this, listuser));//调用list
                        break;
                    case 4:
                        mStatus = result.getStatus();
                        switch (result.getStatus()) {
                            case "1":
                                Xs_comment_zan.setImageResource(R.drawable.zan);
                                break;
                            case "0":
                                Xs_comment_zan.setImageResource(R.drawable.zan0);
                                break;
                        }
                        break;
                    case 5:
                        onCreate(null);
                        break;
                }
            }
        };

    }

   private void initView() {
        // 初始化评论列表
        comment_list = (ListView) findViewById(R.id.comment_list);
        //评论条
        comment = (ImageView) findViewById(R.id.comment);
        hide_down = (TextView) findViewById(R.id.hide_down);
        comment_content = (EditText) findViewById(R.id.comment_content);
        comment_send = (Button) findViewById(R.id.comment_send);


        //显示因此隐藏那个评论
        rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);

        Xuanshang_zan = (TextView) findViewById(R.id.Xs_zan);
        //list_item
        Comment_userURL = (CircleNetworkImageImage) findViewById(R.id.comment_item_touxiang);
        Comment_username = (TextView) findViewById(R.id.comment_item_fromname);
        Comment_time = (TextView) findViewById(R.id.comment_item_time);
        Comment_zan = (TextView) findViewById(R.id.comment_item_zan);
        Comment_text = (TextView) findViewById(R.id.comment_item_comment);

        //点赞
        Xs_comment_zan = (ImageView) findViewById(R.id.Xs_comment_zan);
        setListener();

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.xuanshang_comment_head, comment_list, false);//可设置头布局宽和高
        Xuanshang_userURL = (CircleNetworkImageImage) view.findViewById(R.id.Xs_userurl);
        Xuanshang_username = (TextView) view.findViewById(R.id.Xs_username);
        Xuanshang_xinyuzhi = (TextView) view.findViewById(R.id.Xs_xinyuzhi);
        Xuanshang_postText = (TextView) view.findViewById(R.id.Xs_posttext);
        Xuanshang_postUrl = (ImageView) view.findViewById(R.id.Xs_posturl);
       Xuanshang_postUrl.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
           @Override
           public void onClick(View v) { Imageutils.startImagePage(XuanShang_comment.this,post_url,Xuanshang_postUrl);


           }
       });

        Xuanshang_commentnum = (TextView) view.findViewById(R.id.Xs_pinglun);
        Xuanshang_time = (TextView) view.findViewById(R.id.Xs_shijian);
        comment_list.addHeaderView(view);//添加布局到ListView中、

    }

    private void load_comment() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("post_id", FragmentXuanShang.post_id);
                    HttpUtil.post(url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            m_result = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.obj = m_result; //把登录结果也发送过去
                            handler.sendMessage(msg);

                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void load() {
        //评论
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("post_id", FragmentXuanShang.post_id);
                    HttpUtil.post(url_read, new Callback() {
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
                            listuser = new ArrayList<>();
                            for (JsonElement user : jsonArray) {
                                bb_post = gson.fromJson(user, Bb_post.class);
                                listuser.add(bb_post);
                            }
                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.obj = bb_post; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("post_id", FragmentXuanShang.post_id);
                    map.put("from_id", DL_3.user_id);
                    Log.i("ssss", "run: "+DL_3.user_id);
                    HttpUtil.post(url_zan_read, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            result = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 4;
                            msg.obj = result; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 设置监听
     */
    public void setListener() {
        comment.setOnClickListener(this);
        //隐藏输入法
        hide_down.setOnClickListener(this);
        //发送评论
        comment_send.setOnClickListener(this);
        //点赞
        Xs_comment_zan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down:
                // 隐藏评论框
                rl_enroll.setVisibility(View.VISIBLE);
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("线程2", "run: " + 2);
                        int comm_sum = comment_sum + 1;
                        Log.i("zan", "run: " + comm_sum);
                        try {
                            map.put("user_id", DL_3.user_id);
                            map.put("text", comment_content.getText().toString());
                            map.put("time", DateUtils.getComment_NowDateTime());
                            map.put("post_id", FragmentXuanShang.post_id);
                            map.put("comment_sum", String.valueOf(comm_sum));
                            HttpUtil.post(URL_1, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("DaiDai", "OnFaile:", e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBody = response.body().string();
                                    //解析数据
                                    // 发送登录成功的消息
                                    Message msg = handler.obtainMessage();
                                    msg.what = 2;
//                                    msg.obj = m_result; //把登录结果也发送过去
                                    handler.sendMessage(msg);
                                }
                            }, map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.Xs_comment_zan:
                //没点过赞和第一次进来
                Log.i("status", "onClick: " + mStatus);
                switch (mStatus) {
                    case "0":
                        comment_Zan = comment_Zan + 1;
                        Xs_comment_zan.setImageResource(R.drawable.zan);
                        final String mStatus = "1";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    map.put("post_id", FragmentXuanShang.post_id);
                                    map.put("from_id", DL_3.user_id);
                                    map.put("commment_zan", String.valueOf(comment_Zan));
                                    map.put("status", mStatus);
                                    HttpUtil.post(url_zan_send, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.e("DaiDai", "OnFaile:", e);
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String responseBody = response.body().string();
                                            Gson gson = new Gson();
                                            m_result = gson.fromJson(responseBody, Bb_post.class);
                                            //发送登录成功的消息
                                            Message msg = handler.obtainMessage();
                                            msg.what = 5;
                                            handler.sendMessage(msg);
                                        }
                                    }, map);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        break;
                    case "1": {
                        comment_Zan = comment_Zan - 1;
                        Xs_comment_zan.setImageResource(R.drawable.zan0);
                        final String mStatus_0 = "0";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    map.put("post_id", FragmentXuanShang.post_id);
                                    map.put("from_id", DL_3.user_id);
                                    map.put("commment_zan_jian", String.valueOf(comment_Zan));
                                    map.put("status", mStatus_0);
                                    HttpUtil.post(url_zan_send_jian, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.e("DaiDai", "OnFaile:", e);
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String responseBody = response.body().string();
                                            Gson gson = new Gson();
                                            m_result = gson.fromJson(responseBody, Bb_post.class);
                                            //发送登录成功的消息
                                            Message msg = handler.obtainMessage();
                                            msg.what = 5;
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
        }
    }

    //加载头像
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(XuanShang_comment.this);
        com.android.volley.toolbox.ImageLoader imageLoader = new com.android.volley.toolbox.ImageLoader(queue, new com.android.volley.toolbox.ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        imageView.setImageUrl(url, imageLoader);
    }

    //加载图片
    public void loadImgByVolley(final ImageView imageView, final String imgUrl) {
        ImageRequest imgRequest = new ImageRequest(imgUrl,
                new com.android.volley.Response.Listener<Bitmap>() {
                    /**
                     * 加载成功
                     * @param arg0
                     */
                    @Override
                    public void onResponse(Bitmap arg0) {

                        imageView.setImageBitmap(arg0);

                    }
                }, 300, 200, Bitmap.Config.ARGB_8888,
                new com.android.volley.Response.ErrorListener() {
                    //加载失败
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        imageView.setImageResource(R.drawable.a01);
                    }
                });
        //将图片加载放入请求队列中去
        mQueue.add(imgRequest);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}




