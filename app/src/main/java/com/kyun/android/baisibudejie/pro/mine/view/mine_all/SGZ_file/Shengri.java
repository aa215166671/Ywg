package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.RiliView.PickerView;

public class Shengri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_shengri );

        Log.i("qwe", "onCreate: +qwqeqweqweqw");

        PickerView pvTime=(PickerView)findViewById(R.id.pv_time);

        pvTime.setOnPickerViewChangeListener(new PickerView.OnPickerViewListener() {

            @Override

            public void onChange(PickerView pickerView) {

                Log.i("时间", "onChange: "+pickerView.getYear()+"年:"+pickerView.getMonth()+"月:"+pickerView.getDay()+"日"+

                        pickerView.getHour()+"时"+pickerView.getMinute()+"分");

            }

        });

        pvTime.setNameFormat("年","月","日",null,null,null,null);//年月日时分秒毫秒，需要哪个填哪个，自由定制

        pvTime.setFontColor(0xff3EB9E6,0xffF90000);//设置滑动选中和非选中的字体颜色

        pvTime.setSeparateTvStyle(25,0xffDD8E0F);//设置下分隔字体的颜色大小，null为使用默认

        pvTime.setFontSize(25,36);//设置滚动的最大值和最小字体值
    }
}
