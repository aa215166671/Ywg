package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SZ_file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class ZHAQ extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_mine_zhanghaoanquan );

        tiaozhuan();
    }

    public void tiaozhuan()
    {
        findViewById( R.id.fanhui_zhanghaoanquan ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.xiugaimima_zhanghaoanquan ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( ZHAQ.this, XGMM.class ) );
            }
        } );

        findViewById( R.id.yipingbidehaoyou_zhanghaoanquan ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( ZHAQ.this, YPBDHY.class ) );
            }
        } );
    }
}
