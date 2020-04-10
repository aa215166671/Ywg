package com.kyun.android.baisibudejie.pro.mine.view.mine_all;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class WDSC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_wodeshoucang );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_wodeshoucang ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
