package com.kyun.android.baisibudejie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class QS_1 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qs_1);
        kongjiantoumingdu();
    }

    public void kongjiantoumingdu()
    {
        View btn1 = findViewById(R.id.btn_shoujidenglu);
        btn1.getBackground().setAlpha(100);
        View btn2 = findViewById(R.id.btn_login);//找到你要设透明背景的layout 的id
        btn2.getBackground().setAlpha(100);//0~255透明度值 ，0为完全透明，255为不透明
        View btn3 = findViewById(R.id.btn_zhuce);
        btn3.getBackground().setAlpha(100);
    }



    public void shouji_click(View v)
    {
        Intent intent=new Intent(this,SJDL_3.class);
        startActivity(intent);
    }

    public void denglu_click(View v)
    {
        Intent intent=new Intent(this,DL_3.class);
        startActivity(intent);
    }

    public void zhuce_click(View v)
    {
        Intent intent=new Intent(this,ZC_2.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}

