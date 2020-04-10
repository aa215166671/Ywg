package com.kyun.android.baisibudejie;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.attention.view.AttentionFragment;
import com.kyun.android.baisibudejie.pro.essense.view.EssenceFragment;
import com.kyun.android.baisibudejie.pro.mine.view.MineFragment;
import com.kyun.android.baisibudejie.pro.newpost.view.NewpostFragment;
import com.kyun.android.baisibudejie.pro.publish.view.PublishFragment;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.DateUtils;
import com.kyun.android.baisibudejie.utils.HttpUtil;
import com.kyun.android.baisibudejie.utils.SaveUtils;
import com.kyun.android.baisibudejie.utils.ToastUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.kyun.android.baisibudejie.DL_3.user_id;
import static com.kyun.android.baisibudejie.pro.publish.view.Utilss.parseJSONWithGsonUtils.parseJSONWithGson;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    private List<TabItem> tabItemList;
    private String url_load = "http://loco.xinbinlong.com/login/Login/Login_token.php";
    Map<String, String> map_load = new HashMap<String, String>();
    User u_result;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        initTabData();
        initTabHost();
        load();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 01:
                        String code=u_result.getResult();
                        switch (code) {
                            case "-001":
                                //没有登录过的
                                Toast.makeText(MainActivity.this, "您还没有登录，登录后才可开锁等多功能哦！！", Toast.LENGTH_SHORT).show();
                                break;
                            case "003":
                                Toast.makeText(MainActivity.this, "太长时间没有登录，需要重新登录！！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, DL_3.class);
                                startActivity(intent);
                                finish();
                                break;
                            case "001":
                                DL_3.user_id=u_result.getUser_id();
                                break;
                        }
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
                    map_load.put("token", SaveUtils.getFile("data.txt"));
                    HttpUtil.post(url_load, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            u_result = gson.fromJson(responseBody, User.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 01;
                            msg.obj = u_result; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map_load);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //初始化Tab数据
    private void initTabData() {
        tabItemList = new ArrayList<>();
        //添加精华Tab
        tabItemList.add(new TabItem(R.drawable.main_bottom_essence_normal
                ,R.drawable.main_bottom_essence_press,R.string.main_essence_text, EssenceFragment.class));
        //添加新帖Tab
        tabItemList.add(new TabItem(R.drawable.main_bottom_newpost_normal
                ,R.drawable.main_bottom_newpost_press,R.string.main_newpost_text, NewpostFragment.class));
        //添加发布Tab
        tabItemList.add(new TabItem(R.drawable.main_bottom_public_normal
                ,R.drawable.main_bottom_public_press,0, PublishFragment.class));
        //添加关注Tab
        tabItemList.add(new TabItem(R.drawable.main_bottom_attention_normal
                ,R.drawable.main_bottom_attention_press,R.string.main_attention_text, AttentionFragment.class));
        //添加我的Tab
        tabItemList.add(new TabItem(R.drawable.main_bottom_mine_normal
                ,R.drawable.main_bottom_mine_press,R.string.main_mine_text, MineFragment.class));
    }

    //初始化初页选项卡视图
    private void initTabHost() {
        //获取FragmentTabHost
        FragmentTabHost fragmentTabHost =(FragmentTabHost) findViewById(android.R.id.tabhost);
        //绑定TabHost(绑定我们的body)
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < tabItemList.size(); i++) {
            TabItem tabItem = tabItemList.get(i);
            //绑定Fragment(将Fragment添加到FragmentTabHost组件上面)
            //newTabSpec:代表Tab名字
            //setIndicator:图片(采用布局文件--Tab样式自己做）
            TabHost.TabSpec tabSpec = fragmentTabHost
                    .newTabSpec(tabItem.getTitleString())
                    .setIndicator(tabItem.getView());
            //添加Fragment
            //tabSpec:选项卡
            //getFragmentClass ：具体的Fragment
            //getBundle：给具体的Fragment传参数
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClass(),tabItem.getBundle());
            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.main_bottom_bg));
            //监听点击Tab
            fragmentTabHost.setOnTabChangedListener(this);
            //默认选择第一个Tab
            if ( i== 0) {
                tabItem.setChecked(true);
            }
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        ToastUtil.showToast(this,tabId);
        //重置Tab样式
        for (int i = 0; i < tabItemList.size(); i++) {
            TabItem tabItem = tabItemList.get(i);
            if (tabId.equals(tabItem.getTitleString())) {
                //选中则设置为选中状态
                tabItem.setChecked(true);
            } else {
                //没有选择的tab样式为正常
                tabItem.setChecked(false);
            }
        }
    }

    //代表每一个Tab
    class TabItem {
        //正常情况下显示的图片
        private int imageNormal;

        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;
        private Class<? extends Fragment> fragmentClass;
        private View view;
        private ImageView imageView;
        private TextView textView;
        private Bundle bundle;

        public TabItem(int imageNormal,int imagePress, int title, Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass =fragmentClass;
        }

        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return title;
        }

        public String getTitleString() {
            if(title == 0) {
                return "";
            }
            if (TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public Bundle getBundle() {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putString("title", getTitleString());
            }
            return bundle;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }

        //切换Tab方法--改变Tab样式
        public void setChecked(boolean isChecked) {
            if ( imageView != null) {
                if(isChecked) {
                    imageView.setImageResource(imagePress);
                }else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if (textView != null && title != 0) {
                if (isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_select));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.main_bottom_text_normal));
                }
            }

        }

        public View getView() {
            if(this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator,null);
                this.imageView =(ImageView)this.view.findViewById(R.id.iv_tab);
                this.textView = (TextView)this.view.findViewById(R.id.tv_tab);
                //判断资源是否存在，不在就隐藏
                if (this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                }else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }
    }

// 当用户在首Activity点击返回键时，提示用户是否退出

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0)
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("退出")
                    .setMessage("您确认要退出吗？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            }).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
