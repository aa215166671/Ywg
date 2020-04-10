package com.kyun.android.baisibudejie.pro.publish.view.Utilss;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {

        @SuppressLint("SimpleDateFormat")
        public static String getNowDateTime() {
            SimpleDateFormat s_format = new SimpleDateFormat("yyyy/MM/dd");
            return s_format.format(new Date());
        }

    @SuppressLint("SimpleDateFormat")
    public static String getComment_NowDateTime() {
        SimpleDateFormat s_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return s_format.format(new Date());
    }
}
