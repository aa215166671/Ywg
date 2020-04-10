package com.kyun.android.baisibudejie.pro.mine.view.mine_all;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import com.kyun.android.baisibudejie.R;

public class WDHY extends TabActivity {

    private TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_wodehaoyou );

        kongjiantoumingdu();
        TabLan();
        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_wodehaoyou ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.fasongxiaoxi_wodehaoyou ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( WDHY.this, LT.class ) );
            }
        } );
    }

    public void kongjiantoumingdu(){
        View btn1 = findViewById(R.id.sousuo_wodehaoyou);
        btn1.getBackground().setAlpha(50);

        View btn2 = findViewById(R.id.sousuo_tianjiahaoyou);
        btn2.getBackground().setAlpha(50);
    }

    public void TabLan(){
        //Tab栏切换
        tabhost = getTabHost();
        tabhost.addTab(tabhost.newTabSpec("one").setIndicator("我的好友").setContent(R.id.wodehaoyou_wodehaoyou));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("我的学院").setContent(R.id.wodexueyuan_wodehaoyou));
        tabhost.addTab(tabhost.newTabSpec("two").setIndicator("添加好友").setContent(R.id.tianjiahaoyou_wodehaoyou));
    }
}
