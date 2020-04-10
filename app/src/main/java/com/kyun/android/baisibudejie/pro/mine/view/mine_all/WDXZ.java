package com.kyun.android.baisibudejie.pro.mine.view.mine_all;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.AZB_file.AZB;

public class WDXZ extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_wodexiazai );

        tiaozhuan();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_wodexiazai ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.anzhuangbao_wodexiazai ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( WDXZ.this, AZB.class ) );
            }
        } );
    }
}
