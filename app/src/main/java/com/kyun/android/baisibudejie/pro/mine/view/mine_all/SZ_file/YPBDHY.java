package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SZ_file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class YPBDHY extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_yipingbidehaoyou );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_yipingbidehaoyou ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
