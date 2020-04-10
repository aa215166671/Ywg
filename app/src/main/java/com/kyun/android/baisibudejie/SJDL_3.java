package com.kyun.android.baisibudejie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kyun.android.baisibudejie.pro.essense.view.EssenceFragment;


public class SJDL_3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sjdl_3);
        kongjiantoumingdu();
        tiaozhuan();
    }

    public void kongjiantoumingdu()
    {
        View edit1 = findViewById(R.id.edit_shoujihaoma);
        edit1.getBackground().setAlpha(100);
        View edit2 = findViewById(R.id.edit_duanxin);
        edit2.getBackground().setAlpha(100);
    }

    public void tiaozhuan()
    {
        findViewById( R.id.fanhui_sjdl ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.btn_shoujijiemiandenglu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SJDL_3.this, MainActivity.class ) );
            }
        } );
    }

}

