package com.kyun.android.baisibudejie.pro.essense.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.mvp.presenter.impl.MvpBasePresenter;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.BBActivity;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file.JhActivity;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.SD_file.SdActivity;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.SSZC;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.TD_file.TDmap;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.XYHD_file.XYHD;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.XYXB_file.XYXB;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.YB_file.YBActivity;
import com.kyun.android.baisibudejie.pro.essense.view.navigation.EssenceNavigationBuilder;
import com.kyun.android.baisibudejie.pro.essense.view.views.MyPagerAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.views.banner.BannerLayout;
import com.kyun.android.baisibudejie.pro.essense.view.views.WebBannerAdapter;
import com.kyun.android.baisibudejie.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EssenceFragment extends BaseFragment implements View.OnClickListener {
    private String s;
    private Banner banner;
    private ArrayList<String> list_path;
    private BannerLayout recyclerBanner;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    //这里的图片从百度图片中下载，图片规格是960*640
    private static final int[] drawableIds = new int[]{R.drawable.lunbotupian1,R.drawable.lunbotupian2,R.drawable.lunbotupian3,
            R.drawable.lunbotupian4,R.drawable.lunbotupian5};
    private Banner banner1;

    @Override
    public int getContentView() {
        return R.layout.fragment_essence;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initContentView(View viewContent) {
        initToolBar(viewContent);

        //3D画廊
        recyclerBanner=viewContent.findViewById(R.id.recycler);
        //轮播图
        lunbotu();
        list_path = new ArrayList <>();
        banner =(Banner)viewContent.findViewById(R.id.banner);
        initView();
        //卡片滑动
        mViewPager = viewContent.findViewById(R.id.viewpager);
        mPagerAdapter = new MyPagerAdapter(drawableIds,getActivity());
        mViewPager.setAdapter(mPagerAdapter);

        //初始化
        TextView tandian = viewContent.findViewById( R.id.tandian);
        TextView shudong= viewContent.findViewById( R.id.shudong);
        TextView yueba = viewContent.findViewById( R.id.yueba);
        TextView jiehu = viewContent.findViewById( R.id.jiehu);
        TextView bangbang = viewContent.findViewById( R.id.bangbang);
        TextView xiaoyuanhuodong = viewContent.findViewById( R.id.xiaoyuanhuodong);
        TextView xiaoyuanxiaobao = viewContent.findViewById( R.id.xiaoyuanxiaobao);
        TextView shishizhengce = viewContent.findViewById( R.id.shishizhengce);
        tandian.setOnClickListener(this);
        shudong.setOnClickListener(this);
        yueba.setOnClickListener(this);
        jiehu.setOnClickListener(this);
        bangbang.setOnClickListener(this);
        xiaoyuanhuodong.setOnClickListener(this);
        xiaoyuanxiaobao.setOnClickListener(this);
        shishizhengce.setOnClickListener(this);
    }

    private void initToolBar(View viewContent) {
        EssenceNavigationBuilder builder = new EssenceNavigationBuilder(getContext());
        builder.setTitleIcon(R.drawable.main_essence_title)
                .setLeftIcon(R.drawable.main_essence_btn_selector)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(),"！！！");
                    }
                })
                .setRightIcon(R.drawable.main_essence_btn_more_selector)
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(),"@ @ @");
                    }
                });
        builder.createAndBind((ViewGroup) viewContent);
    }
//Banner轮播图
    private void initView() {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置轮播间隔时间
        banner.setDelayTime(2000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT)
                .start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showToast(getActivity(),"点击了第" + position + "项");
            }
        });
    }

    //轮播图自定义的图片加载器
    private  class MyLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
//获取轮播图数据
    public void lunbotu() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://loco.xinbinlong.com/login/Shouye.php")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str=response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray json = new JSONArray(str);
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject jb = json.getJSONObject(i);
                                s = (String) jb.get("url");
                                list_path.add(s);
                                //设置集合
                                banner.setImages(list_path);
                                //banner执行完方法之后调用
                                banner.start();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tandian:
                startActivity(new Intent(getActivity(),TDmap.class));
                break;
            case R.id.shudong:
                startActivity(new Intent(getActivity(),SdActivity.class));
                break;
            case R.id.yueba:
                startActivity(new Intent(getActivity(),YBActivity.class));
                break;
            case R.id.jiehu:
                startActivity(new Intent(getActivity(), JhActivity.class));
                break;
            case R.id.bangbang:
                startActivity(new Intent(getActivity(),BBActivity.class));
                break;
            case R.id.xiaoyuanhuodong:
                startActivity(new Intent(getActivity(),XYHD.class));
                break;
            case R.id.xiaoyuanxiaobao:
                startActivity(new Intent(getActivity(),XYXB.class));
                break;
            case R.id.shishizhengce:
                startActivity(new Intent(getActivity(),SSZC.class));
                break;
            default: break;
        }
    }
    //校园小报01

}
