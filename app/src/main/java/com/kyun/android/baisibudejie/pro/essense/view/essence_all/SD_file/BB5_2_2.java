package com.kyun.android.baisibudejie.pro.essense.view.essence_all.SD_file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kyun.android.baisibudejie.R;

public class BB5_2_2 extends Activity {
    TextView textView;
    ImageButton imageButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_essence_shudong_biaobai );

        imageButton=findViewById(R.id.fanhui);
        textView = findViewById(R.id.Tv_biaobai);
        Bundle bundle = this.getIntent().getExtras();
        String str = bundle.getString("text");
        textView.setText(str);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BB5_2_2.this, SdActivity.class);
                startActivity(intent);
            }
        });
    }


}
