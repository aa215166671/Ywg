package com.kyun.android.baisibudejie.pro.mine.view.mine_all.GRYZ_file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Toast;

import com.kyun.android.baisibudejie.R;

public class GRYZ extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_gerenyanzheng );

        tiaozhuan();
    }

    public void diqu_gerenyanzheng(View v){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_mine_diqu, null);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
    }

    public void xueyuan_gerenyanzheng(View v){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_mine_xueyuan, null);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.xingming_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( GRYZ.this, XM.class ) );
            }
        } );

        findViewById( R.id.xuehao_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( GRYZ.this, XH.class ) );
            }
        } );

        findViewById( R.id.queding_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        } );

        findViewById( R.id.xuexiao_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( GRYZ.this, XX.class ) );
            }
        } );

        findViewById( R.id.xueshengzheng_gerenyanzheng ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( GRYZ.this, XSZ.class ) );
            }
        } );
    }
}
