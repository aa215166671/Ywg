package com.kyun.android.baisibudejie.pro.mine.view.mine_all;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class LT extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_liaotianjiemian );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_liaotian ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
