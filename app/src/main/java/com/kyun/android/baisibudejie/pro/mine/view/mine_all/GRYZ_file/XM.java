package com.kyun.android.baisibudejie.pro.mine.view.mine_all.GRYZ_file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class XM extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_xingming );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_xingming ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.baocun_xingming ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
