package com.kyun.android.baisibudejie.pro.mine.view.mine_all.XHW_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class XHWCLMD extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_xiaoheiwuchulimingdan );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_xiaoheiwuchulimingdan ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}
