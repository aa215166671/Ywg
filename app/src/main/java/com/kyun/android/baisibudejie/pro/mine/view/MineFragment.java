package com.kyun.android.baisibudejie.pro.mine.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.MainActivity;
import com.kyun.android.baisibudejie.QS_1;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;
import com.kyun.android.baisibudejie.pro.mine.view.Utils.BitmapCache;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.GRYZ_file.GRYZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.LLLS;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.GRXX;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.SGZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SZ_file.SZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.TDXZ_file.TDXZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WDHY;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WDPL;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WDSC;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WDTZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WDXZ;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.WZGD;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.XHW_file.XHW;
import com.kyun.android.baisibudejie.pro.mine.view.navigation.MineNavigationBuilder;
import com.kyun.android.baisibudejie.pro.newpost.view.views.CircleNetworkImageImage;
import com.kyun.android.baisibudejie.utils.HttpUtil;
import com.kyun.android.baisibudejie.utils.SaveUtils;
import com.kyun.android.baisibudejie.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MineFragment extends BaseFragment implements View.OnClickListener {


    private TextView tv_user;
    private CircleNetworkImageImage cir_user;
    private ImageView shezhi_wode;
    private TextView gerenyanzheng_wode;
    private TextView wodetiezi_wode;
    private TextView wodepinglun_wode;
    private TextView wozanguode_wode;
    private RelativeLayout wodehaoyou_wode;
    private RelativeLayout tandianxunzhang_wode;
    private RelativeLayout wodeshoucang_wode;
    private RelativeLayout wodexiazai_wode;
    private RelativeLayout liulanlishi_wode;
    private RelativeLayout xiaoheiwu_wode;
    private Button shiguangzhou_wode;
    private Button gerenxinxi_shiguangzhou;
    private RequestQueue mQueue;
    private String url_load = "http://loco.xinbinlong.com/login/Login/Login_load.php";
    Map<String, String> map_load = new HashMap<String, String>();
    User u_result;
    private Handler handler;
    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {

        initToolBar(viewContent);

        cir_user = viewContent.findViewById( R.id.cir_user);
        tv_user = viewContent.findViewById( R.id.tv_user);
//        shezhi_wode = viewContent.findViewById( R.id.shezhi_wode);
        shiguangzhou_wode = viewContent.findViewById( R.id.shiguangzhou_wode );
        gerenyanzheng_wode = viewContent.findViewById( R.id.gerenyanzheng_wode);
        wodetiezi_wode = viewContent.findViewById( R.id.wodetiezi_wode);
        wodepinglun_wode = viewContent.findViewById( R.id.wodepinglun_wode);
        wozanguode_wode = viewContent.findViewById( R.id.wozanguode_wode);
        wodehaoyou_wode = viewContent.findViewById( R.id.wodehaoyou_wode);
        tandianxunzhang_wode = viewContent.findViewById( R.id.tandianxunzhang_wode);
        wodeshoucang_wode = viewContent.findViewById( R.id.wodeshoucang_wode);
        wodexiazai_wode = viewContent.findViewById( R.id.wodexiazai_wode);
        liulanlishi_wode = viewContent.findViewById( R.id.liulanlishi_wode);
        xiaoheiwu_wode = viewContent.findViewById( R.id.xiaoheiwu_wode);

        gerenxinxi_shiguangzhou = viewContent.findViewById( R.id.gerenxinxi_shiguangzhou);
        cir_user.setOnClickListener(this);
        tv_user.setOnClickListener(this);
//        shezhi_wode.setOnClickListener(this);
        shiguangzhou_wode.setOnClickListener(this);
        gerenyanzheng_wode.setOnClickListener(this);
        wodetiezi_wode.setOnClickListener(this);
        wodepinglun_wode.setOnClickListener(this);
        wozanguode_wode.setOnClickListener(this);
        wodehaoyou_wode.setOnClickListener(this);
        tandianxunzhang_wode.setOnClickListener(this);
        wodeshoucang_wode.setOnClickListener(this);
        wodexiazai_wode.setOnClickListener(this);
        liulanlishi_wode.setOnClickListener(this);
        xiaoheiwu_wode.setOnClickListener(this);
        gerenxinxi_shiguangzhou.setOnClickListener(this);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        loadImage(cir_user,u_result.getUser_url());
                        tv_user.setText(u_result.getUser_name());
                }
            }
        };


        if(DL_3.user_id!=""){
                load();

        }
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map_load.put("user_id",DL_3.user_id );
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
                            msg.what = 123;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    private void initToolBar(View viewContent){
        MineNavigationBuilder builder = new MineNavigationBuilder(getContext());
        builder.setModelRes(R.drawable.main_mine_night_model_selector)
                .setModelOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(),"夜间模式");
                    }
                })
                .setTitle(R.string.main_mine_title_text)
                .setRightIcon(R.drawable.main_mine_setting_selector)
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(),SZ.class));
                    }
                });
        builder.createAndBind((ViewGroup) viewContent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cir_user:
            case R.id.tv_user:
                startActivity(new Intent(getActivity(), QS_1.class));
                break;
            case R.id.shiguangzhou_wode:
                startActivity(new Intent(getActivity(),SGZ.class));
                break;
            case R.id.gerenyanzheng_wode:
                startActivity(new Intent(getActivity(),GRYZ.class));
                break;
            case R.id.wodetiezi_wode:
                startActivity(new Intent(getActivity(),WDTZ.class));
                break;
            case R.id.wodepinglun_wode:
                startActivity(new Intent(getActivity(),WDPL.class));
                break;
            case R.id.wozanguode_wode:
                startActivity(new Intent(getActivity(),WZGD.class));
                break;
            case R.id.wodehaoyou_wode:
                startActivity(new Intent(getActivity(),WDHY.class));
                break;
            case R.id.tandianxunzhang_wode:
                startActivity(new Intent(getActivity(),TDXZ.class));
                break;
            case R.id.wodeshoucang_wode:
                startActivity(new Intent(getActivity(),WDSC.class));
                break;
            case R.id.wodexiazai_wode:
                startActivity(new Intent(getActivity(),WDXZ.class));
                break;
            case R.id.liulanlishi_wode:
                startActivity(new Intent(getActivity(),LLLS.class));
                break;
            case R.id.xiaoheiwu_wode:
                startActivity(new Intent(getActivity(),XHW.class));
                break;
            case R.id.gerenxinxi_shiguangzhou:
                startActivity( new Intent( getActivity(), GRXX.class ) );
                break;
                default: break;
        }
    }
    //加载头像
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        com.android.volley.toolbox.ImageLoader imageLoader = new com.android.volley.toolbox.ImageLoader(queue,new BitmapCache());
        imageView.setImageUrl(url, imageLoader);
    }
}
