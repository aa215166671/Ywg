package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.MyViewPagerAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentBangBang;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentJianZhi;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentKuaiDi;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentTiaoZao;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentXuanShang;
import com.kyun.android.baisibudejie.pro.essense.view.views.WebBannerAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.views.banner.BannerLayout;

import java.util.ArrayList;
import java.util.List;


public class BBActivity extends AppCompatActivity {
    LinearLayout llIndexContainer;
    private List <String> mADParseArray;
    private final int HOME_AD_RESULT = 1;
    private ViewPager vp_shuffling;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    vp_shuffling.setCurrentItem(vp_shuffling.getCurrentItem() + 1,
                            true);
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_essence_bangbang );
        lunbotu();
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment( FragmentXuanShang.newInstance(), "悬赏");//添加Fragment
        viewPagerAdapter.addFragment( FragmentKuaiDi.newInstance(), "快递");
        viewPagerAdapter.addFragment( FragmentBangBang.newInstance(), "帮帮");
        viewPagerAdapter.addFragment( FragmentTiaoZao.newInstance(), "跳蚤");
        viewPagerAdapter.addFragment( FragmentJianZhi.newInstance(), "兼职");
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("悬赏"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("快递"));
        mTabLayout.addTab(mTabLayout.newTab().setText("帮帮"));
        mTabLayout.addTab(mTabLayout.newTab().setText("跳蚤"));
        mTabLayout.addTab(mTabLayout.newTab().setText("兼职"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }

    public void lunbotu() {
        BannerLayout recyclerBanner = findViewById(R.id.recycler);
        List <String> list = new ArrayList <>();
        list.add("http://pic33.nipic.com/20131007/13639685_123501617185_2.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2293177440,3125900197&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3967183915,4078698000&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3184221534,2238244948&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1794621527,1964098559&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1243617734,335916716&fm=27&gp=0.jpg");
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(this, list);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BBActivity.this, "点击了第  " + position + "  项", Toast.LENGTH_SHORT).show();
            }
        });
        WebBannerAdapter WebBannerAdapter2 = new WebBannerAdapter(this, list);
        WebBannerAdapter2.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BBActivity.this, "点击了第  " + position + "  项", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
    }
}