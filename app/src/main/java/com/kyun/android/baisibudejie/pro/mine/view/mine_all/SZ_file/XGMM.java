package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SZ_file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class XGMM extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_mine_xiugaimima );

        tiaozhuan();
    }

    public void tiaozhuan()
    {
        findViewById( R.id.fanhui_xiugaimima ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.queding_xiugaimima ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
