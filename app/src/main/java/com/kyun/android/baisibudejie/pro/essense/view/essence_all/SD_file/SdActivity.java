package com.kyun.android.baisibudejie.pro.essense.view.essence_all.SD_file;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kyun.android.baisibudejie.R;


public class SdActivity extends Activity {
    private Log log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_essence_shudong );

        init();
        ImageButton button = (ImageButton)findViewById(R.id.showMenuBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });

    }


    private void popWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);//获取一个填充器
        View view = inflater.inflate(R.layout.pl5_2_1, null);//填充我们自定义的布局
        Display display = getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
        Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
        display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
        int width = size.x;//从Point点对象中获取屏幕的宽度(单位像素)
        int height = size.y;//从Point点对象中获取屏幕的高度(单位像素)

        log.v("zxy", "width=" + width + ",height=" + height);//width=480,height=854可知手机的像素是480x854的
        //创建一个PopupWindow对象，第二个参数是设置宽度的，用刚刚获取到的屏幕宽度乘以2/3，取该屏幕的2/3宽度，从而在任何设备中都可以适配，高度则包裹内容即可，最后一个参数是设置得到焦点
        PopupWindow popWindow = new PopupWindow(view, 9 * width / 10, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());//设置PopupWindow的背景为一个空的Drawable对象，如果不设置这个，那么PopupWindow弹出后就无法退出了
        popWindow.setOutsideTouchable(true);//设置是否点击PopupWindow外退出PopupWindow
        WindowManager.LayoutParams params = getWindow().getAttributes();//创建当前界面的一个参数对象
        params.alpha = 0.6f;//设置参数的透明度为0.8，透明度取值为0~1，1为完全不透明，0为完全透明，因为android中默认的屏幕颜色都是纯黑色的，所以如果设置为1，那么背景将都是黑色，设置为0，背景显示我们的当前界面
        getWindow().setAttributes(params);//把该参数对象设置进当前界面中
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置PopupWindow退出监听器
            @Override
            public void onDismiss() {//如果PopupWindow消失了，即退出了，那么触发该事件，然后把当前界面的透明度设置为不透明
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1.0f;//设置为不透明，即恢复原来的界面
                getWindow().setAttributes(params);
            }
        });
        //第一个参数为父View对象，即PopupWindow所在的父控件对象，第二个参数为它的重心，后面两个分别为x轴和y轴的偏移量
        popWindow.showAtLocation(inflater.inflate(R.layout.fragment_essence_shudong, null), Gravity.CENTER, 0, 0);

    }

    //打开弹窗
    public void init() {
        ImageButton anniu1 = (ImageButton) findViewById(R.id.anniu1);
        anniu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popWindow();
            }
        });
    }

    //打开菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1,1,1,"发表白");
        menu.add(1,2,2,"发诉说");
        menu.add(2,3,3,"发寻人");
        menu.add(2,4,4,"发寻物");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                jump1(item.getTitle().toString());
                break;
            case 2:
                jump1(item.getTitle().toString());
                break;
            case 3:
                jump1(item.getTitle().toString());
                break;
            case 4:
                jump1(item.getTitle().toString());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void jump1(String value){
        Intent intent = new Intent();
        intent.setClass(SdActivity.this,BB5_2_2.class);
        Bundle bundle = new Bundle();
        bundle.putString("text",value);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
