package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.kyun.android.baisibudejie.R;

public class OtherActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注意这里不使用finish
                ActivityCompat.finishAfterTransition(OtherActivity.this);
            }
        });
    }
}
