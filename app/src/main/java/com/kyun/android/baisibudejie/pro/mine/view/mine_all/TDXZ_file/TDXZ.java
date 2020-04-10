package com.kyun.android.baisibudejie.pro.mine.view.mine_all.TDXZ_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class TDXZ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_tandianxunzhang );

        tiaozhuan();
        kongjiantoumingdu();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_tandianxunzhang ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }

    public void kongjiantoumingdu(){
        View btn1 = findViewById(R.id.beijingse1);
        btn1.getBackground().setAlpha(30);

        View btn2 = findViewById(R.id.beijingse2);
        btn2.getBackground().setAlpha(10);
    }
}
