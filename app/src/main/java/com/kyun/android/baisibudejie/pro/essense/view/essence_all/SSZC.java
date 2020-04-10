package com.kyun.android.baisibudejie.pro.essense.view.essence_all;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class SSZC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_essence_shishizhengce );

        tiaozhuan();
    }

    public void tiaozhuan(){
        findViewById( R.id.fanhui_shishizhengce ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
