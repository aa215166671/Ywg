package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SZ_file;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kyun.android.baisibudejie.MainActivity;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.utils.SaveUtils;

public class SZ extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_mine_shezhi );

        tiaozhuan();
    }

    public void tiaozhuan()
    {
        findViewById( R.id.fanhui_shezhi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        findViewById( R.id.zhanghaoanquan_shezhi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SZ.this, ZHAQ.class ) );
            }
        } );

        findViewById( R.id.xiaoxitishi_shezhi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SZ.this, XXTS.class ) );
            }
        } );

        findViewById( R.id.tuichuzhanghao_shezhi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveUtils.deletefile("data.txt");
                        Toast.makeText(SZ.this, "已退出当前账号！！", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };
                //创建对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(SZ.this);
                builder.setTitle("确定要退出吗？");
                builder.setPositiveButton("确定",listener);
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        } );
    }
}
