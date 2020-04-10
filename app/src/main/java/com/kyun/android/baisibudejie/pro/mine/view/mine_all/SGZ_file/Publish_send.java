package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kyun.android.baisibudejie.R;

public class Publish_send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sy_wd_sgz_fb );

        findViewById(R.id.fanhui_shiguangzhou).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById(R.id.fabu_shiguangzhou).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"发布成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        } );
    }
}
