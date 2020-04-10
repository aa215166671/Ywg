package com.kyun.android.baisibudejie.pro.mine.view.mine_all.XHW_file;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class XHW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_xiaoheiwu );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.tandianxiaoyuanguanli_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, TDXYGLGF.class ) );
            }
        } );

        findViewById( R.id.tandianxiaoyuangongyue_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, TDXYGY.class ) );
            }
        } );

        findViewById( R.id.weiguishenhe_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, WGSHZQ.class ) );
            }
        } );

        findViewById( R.id.shequgonggao_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, SQGG.class ) );
            }
        } );

        findViewById( R.id.xiaoheiwuchulimingdan_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, XHWCLMD.class ) );
            }
        } );

        findViewById( R.id.wodechufa_xiaoheiwu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( XHW.this, WDCF.class ) );
            }
        } );
    }
}
