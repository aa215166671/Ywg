package com.kyun.android.baisibudejie.pro.mine.view.mine_all.GRYZ_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class XX extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_xuexiao );

        tiaozhuan();
        kongjiantoumingdu();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_xuexiao ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }

    public void kongjiantoumingdu(){
        View btn1 = findViewById(R.id.sousuo_xuexiao);
        btn1.getBackground().setAlpha(50);
    }
}
