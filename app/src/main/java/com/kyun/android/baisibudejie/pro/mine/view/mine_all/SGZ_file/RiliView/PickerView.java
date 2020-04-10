package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.RiliView;

import android.content.Context;

import android.text.TextUtils;

import android.util.AttributeSet;

import android.view.LayoutInflater;

import android.widget.LinearLayout;

import android.widget.RelativeLayout;

import android.widget.TextView;

import com.kyun.android.baisibudejie.R;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.List;


/**
 * 日期、时间选择控件
 * Created by 庞光渝 on 2017/2/18.
 */



public class PickerView extends RelativeLayout {

    /**
     * UI
     */
    private PickerItemView pivYear;

    private PickerItemView pivMonth;

    private PickerItemView pivDay;

    private PickerItemView pivHour;

    private PickerItemView pivMinute;

    private PickerItemView pivSecond;

    private PickerItemView pivMillisecond;

    private TextView tvYear;

    private TextView tvMonth;

    private TextView tvDay;

    private TextView tvHour;

    private TextView tvMinute;

    private TextView tvSecond;

    private TextView tvMillisecond;



    /**

     * 变量

     */

    private List<String> years;

    private List<String> months;

    private List<String> days;

    private List<String> hours;

    private List<String> minutes;

    private List<String> seconds;

    private List<String> milliseconds;



    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    private int second;

    private int millisecond;

    private OnPickerViewListener onPickerViewChangeListener;



    public PickerView(Context context) {

        super(context);

        initView(context);

    }



    public PickerView(Context context, AttributeSet attrs) {

        super(context, attrs);

        initView(context);

    }



    public PickerView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        initView(context);

    }



    private void initView(Context context) {

        // 加载布局

        LayoutInflater.from(context).inflate(R.layout.layout_picker, this);

        pivYear = (PickerItemView) findViewById(R.id.piv_year);

        pivMonth = (PickerItemView) findViewById(R.id.piv_month);

        pivDay = (PickerItemView) findViewById(R.id.piv_day);

//        pivHour = (PickerItemView) findViewById(R.id.piv_hour);
//
//        pivMinute = (PickerItemView) findViewById(R.id.piv_minute);
//
//        pivSecond = (PickerItemView) findViewById(R.id.piv_second);
//
//        pivMillisecond = (PickerItemView) findViewById(R.id.piv_millisecond);

        tvYear = (TextView) findViewById(R.id.tv_year);

        tvMonth = (TextView) findViewById(R.id.tv_month);

        tvDay = (TextView) findViewById(R.id.tv_day);

//        tvHour = (TextView) findViewById(R.id.tv_hour);
//
//        tvMinute = (TextView) findViewById(R.id.tv_minute);
//
//        tvSecond = (TextView) findViewById(R.id.tv_second);
//
//        tvMillisecond = (TextView) findViewById(R.id.tv_millisecond);

        initView();

    }



    /**

     * 部分机型在popupWindow中，如果没有show popupWindow的话高度是一直为0的

     * 所以需要重写一下popupWindow的show方法中重新调用一下initView进行初始化，否则会显示错误

     */

    public void initView() {

        initData();

        initPIV();

        initWidth();

    }



    /**

     * 动态的写一下各个item的宽度

     */

    private void initWidth() {

        post(new Runnable() {

            @Override

            public void run() {

                float size = (float) ((getHeight() / 5) * 0.65);

                LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) pivYear.getLayoutParams();

                params1.width = (int) (size * 3);

                pivYear.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) pivMonth.getLayoutParams();

                params2.width = (int) (size * 1.7);

                pivMonth.setLayoutParams(params2);

                pivDay.setLayoutParams(params2);

//                pivHour.setLayoutParams(params2);
//
//                pivMinute.setLayoutParams(params2);
//
//                pivSecond.setLayoutParams(params2);

//                LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) pivMonth.getLayoutParams();
//
//                params2.width = (int) (size * 2.4);

 //              pivMillisecond.setLayoutParams(params3);



                /**

                 * 设置中间分隔字体的大小

                 */

                tvYear.setTextSize(24);

                tvMonth.setTextSize(24);

                tvDay.setTextSize(24);

//                tvHour.setTextSize(size);

//                tvMinute.setTextSize(size);

//                tvSecond.setTextSize(size);

//                tvMillisecond.setTextSize(size);

            }

        });

    }



    /**

     * 响应监听

     */

    private void onChange() {

        if (onPickerViewChangeListener != null) {

            onPickerViewChangeListener.onChange(PickerView.this);

        }

    }



    /**

     * 初始化修改监听

     */

    private void initOnChange() {

        pivYear.setOnPitchOnChangeListener(new OnStringListener() {

            @Override

            public void onClick(String str) {

                try {

                    year = Integer.parseInt(pivYear.getPitchOn());

                    updateDay(str, pivMonth.getPitchOn());

                    onChange();

                } catch (Exception e) {



                }

            }

        });

        pivMonth.setOnPitchOnChangeListener(new OnStringListener() {

            @Override

            public void onClick(String str) {

                try {

                    month = Integer.parseInt(pivMonth.getPitchOn());

                    updateDay(pivYear.getPitchOn(), str);

                    onChange();

                } catch (Exception e) {



                }

            }

        });

        pivDay.setOnPitchOnChangeListener(new OnStringListener() {

            @Override

            public void onClick(String str) {

                try {

                    day = Integer.parseInt(pivDay.getPitchOn());

                    onChange();

                } catch (Exception e) {



                }

            }

        });

//        pivHour.setOnPitchOnChangeListener(new OnStringListener() {
//
//            @Override
//
//            public void onClick(String str) {
//
//                try {
//
//                    hour = Integer.parseInt(pivHour.getPitchOn());
//
//                    onChange();
//
//                } catch (Exception e) {
//
//
//
//                }
//
//            }
//
//        });
//
//        pivMinute.setOnPitchOnChangeListener(new OnStringListener() {
//
//            @Override
//
//            public void onClick(String str) {
//
//                try {
//
//                    minute = Integer.parseInt(pivMinute.getPitchOn());
//
//                    onChange();
//
//                } catch (Exception e) {
//
//
//
//                }
//
//            }
//
//        });
//
//        pivSecond.setOnPitchOnChangeListener(new OnStringListener() {
//
//            @Override
//
//            public void onClick(String str) {
//
//                try {
//
//                    second = Integer.parseInt(pivSecond.getPitchOn());
//
//                    onChange();
//
//                } catch (Exception e) {
//
//
//
//                }
//
//            }
//
//        });
//
//        pivMillisecond.setOnPitchOnChangeListener(new OnStringListener() {
//
//            @Override
//
//            public void onClick(String str) {
//
//                try {
//
//                    millisecond = Integer.parseInt(pivMillisecond.getPitchOn());
//
//                    onChange();
//
//                } catch (Exception e) {
//
//
//
//                }
//
//            }
//
//        });

    }



    /**

     * 初始化每一个Item的初始值

     */

    private void initPIV() {

        //年

        years = new ArrayList<>();

        for (int i = 1970; i < 2101; i++) {

            years.add(i + "");

        }

        pivYear.setList(years);



        //月

        months = new ArrayList<>();

        for (int i = 1; i < 13; i++) {

            months.add(int2Str2(i));

        }

        pivMonth.setList(months);



        //日

        days = getDayNumber(years.get(0), months.get(0));

        pivDay.setList(days);

//
//
//        //时
//
//        hours = new ArrayList<>();
//
//        for (int i = 0; i < 24; i++) {
//
//            hours.add(int2Str2(i));
//
//        }
//
//        pivHour.setList(hours);
//
//
//
//        //分
//
//        minutes = new ArrayList<>();
//
//        for (int i = 0; i < 60; i++) {
//
//            minutes.add(int2Str2(i));
//
//        }
//
//        pivMinute.setList(minutes);
//
//
//
//        //秒
//
//        seconds = new ArrayList<>();
//
//        for (int i = 0; i < 60; i++) {
//
//            seconds.add(int2Str2(i));
//
//        }
//
//        pivSecond.setList(seconds);
//
//
//
//        //毫秒
//
//        milliseconds = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i += 50) {
//
//            milliseconds.add(int2Str2(i));
//
//        }
//
//        pivMillisecond.setList(milliseconds);



        //设置监听

        initOnChange();

    }



    /**

     * 动态更新日期天数

     *

     * @param year

     * @param month

     */

    public void updateDay(String year, String month) {

        try {

            List<String> strings = getDayNumber(year, month);

            if (strings.size() != pivDay.getList().size()) {

                pivDay.setList(strings);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    /**

     * 获取一个月的天数

     */

    private List<String> getDayNumber(String year, String monthStr) {

        int num = 30;

        try {

            int month = Integer.parseInt(monthStr);

            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {

                num = 31;

            }

            if (month == 2) {

                if (getLeapYear(year)) {

                    num = 29;

                } else {

                    num = 28;

                }

            }

        } catch (Exception e) {

            num = 31;

        }



        List<String> strs = new ArrayList<>();

        for (int i = 1; i <= num; i++) {

            strs.add(int2Str2(i));

        }

        return strs;

    }



    /**

     * 设置初始值

     */

    private void initData() {

        Calendar now = Calendar.getInstance();

        year = now.get(Calendar.YEAR);

        month = now.get(Calendar.MONTH) + 1;

        day = now.get(Calendar.DAY_OF_MONTH);

        hour = now.get(Calendar.HOUR);

        minute = now.get(Calendar.MINUTE);

        second = now.get(Calendar.SECOND);

        millisecond = (int) (now.get(Calendar.MILLISECOND) % 1000);

        //毫秒的最小单位是50，所以转换一下

        millisecond = (millisecond / 50) * 50;

        update();

    }



    /**

     * 更新

     */

    private void update() {

        //设置初始值

        pivYear.setPitchOn(year + "");

        pivMonth.setPitchOn(int2Str2(month));

        pivDay.setPitchOn(int2Str2(day));

//        pivHour.setPitchOn(int2Str2(hour));
//
//        pivMinute.setPitchOn(int2Str2(minute));
//
//        pivSecond.setPitchOn(int2Str2(second));
//
//        pivMillisecond.setPitchOn(int2Str3(millisecond));

    }



    /**

     * 设置修改监听

     *

     * @param onPickerViewChangeListener

     */

    public void setOnPickerViewChangeListener(OnPickerViewListener onPickerViewChangeListener) {

        this.onPickerViewChangeListener = onPickerViewChangeListener;

    }



    /**

     * 根据需要，传入单位文本，如果为null或者空字符则表示不需要该项，

     * 如：只传时、分，则整个控件只有选择小时和分钟

     *

     * @param year

     * @param month

     * @param day

     * @param hour

     * @param minute

     * @param second

     * @param millisecond

     */

    public void setNameFormat(String year, String month, String day, String hour, String minute, String second, String millisecond) {

        //年

        if (TextUtils.isEmpty(year)) {

            tvYear.setVisibility(GONE);

            pivYear.setVisibility(GONE);

        } else {

            tvYear.setText(year);

            tvYear.setVisibility(VISIBLE);

            pivYear.setVisibility(VISIBLE);

        }



        //月

        if (TextUtils.isEmpty(month)) {

            tvMonth.setVisibility(GONE);

            pivMonth.setVisibility(GONE);

        } else {

            tvMonth.setText(month);

            tvMonth.setVisibility(VISIBLE);

            pivMonth.setVisibility(VISIBLE);

        }



        //日

        if (TextUtils.isEmpty(day)) {

            tvDay.setVisibility(GONE);

            pivDay.setVisibility(GONE);

        } else {

            tvDay.setText(day);

            tvDay.setVisibility(VISIBLE);

            pivDay.setVisibility(VISIBLE);

        }



        //时

        if (TextUtils.isEmpty(hour)) {

            tvHour.setVisibility(GONE);

            pivHour.setVisibility(GONE);

        } else {

            tvHour.setText(hour);

            tvHour.setVisibility(VISIBLE);

            pivHour.setVisibility(VISIBLE);

        }



        //分

        if (TextUtils.isEmpty(minute)) {

            tvMinute.setVisibility(GONE);

            pivMinute.setVisibility(GONE);

        } else {

            tvMinute.setText(minute);

            tvMinute.setVisibility(VISIBLE);

            pivMinute.setVisibility(VISIBLE);

        }



        //秒

        if (TextUtils.isEmpty(second)) {

            tvSecond.setVisibility(GONE);

            pivSecond.setVisibility(GONE);

        } else {

            tvSecond.setText(second);

            tvSecond.setVisibility(VISIBLE);

            pivSecond.setVisibility(VISIBLE);

        }



        //毫秒

        if (TextUtils.isEmpty(millisecond)) {

            tvMillisecond.setVisibility(GONE);

            pivMillisecond.setVisibility(GONE);

        } else {

            tvMillisecond.setText(millisecond);

            tvMillisecond.setVisibility(VISIBLE);

            pivMillisecond.setVisibility(VISIBLE);

        }

    }



    /**

     * 根据高度设置最大最小字体的大小，范围是20-80

     *

     * @param minPercent

     * @param maxPercent

     */

    public void setFontSize(int minPercent, int maxPercent) {

        minPercent=25;maxPercent=36;
        pivYear.setFontSize(minPercent, maxPercent);

        pivMonth.setFontSize(minPercent, maxPercent);

        pivDay.setFontSize(minPercent, maxPercent);

        pivHour.setFontSize(minPercent, maxPercent);

        pivMinute.setFontSize(minPercent, maxPercent);

        pivSecond.setFontSize(minPercent, maxPercent);

        pivMillisecond.setFontSize(minPercent, maxPercent);

    }



    /**

     * 设置文字颜色

     *

     * @param normal 未选中颜色

     * @param press  选中颜色

     */

    public void setFontColor(int normal, int press) {

        pivYear.setFontColor(normal, press);

        pivMonth.setFontColor(normal, press);

        pivDay.setFontColor(normal, press);

        pivHour.setFontColor(normal, press);

        pivMinute.setFontColor(normal, press);

        pivSecond.setFontColor(normal, press);

        pivMillisecond.setFontColor(normal, press);

    }



    /**

     * 设置分隔字体的样式

     *

     * @param fontSize  字体大小，空为默认

     * @param fontColor 颜色大小，空位默认

     */

    public void setSeparateTvStyle(Integer fontSize, Integer fontColor) {

        fontSize=25;
        fontColor=0xffDD8E0F;
        if (fontSize != null) {

            tvYear.setTextSize(fontSize);

            tvMonth.setTextSize(fontSize);

            tvDay.setTextSize(fontSize);

            tvHour.setTextSize(fontSize);

            tvMinute.setTextSize(fontSize);

            tvSecond.setTextSize(fontSize);

            tvMillisecond.setTextSize(fontSize);

        }

        if (fontColor != null) {

            tvYear.setTextColor(fontColor);

            tvMonth.setTextColor(fontColor);

            tvDay.setTextColor(fontColor);

            tvHour.setTextColor(fontColor);

            tvMinute.setTextColor(fontColor);

            tvSecond.setTextColor(fontColor);

            tvMillisecond.setTextColor(fontColor);

        }

    }



    /**

     * 计算是否是闰年

     * （能被4整除且不是整百年份的是闰年，能被4整除且是整百的只有能被400整除的才是闰年，即2000是闰年，但1900不是

     *

     * @param year

     * @return

     */

    private boolean getLeapYear(String year) {

        int num = Integer.parseInt(year);

        if (num % 4 == 0) {

            if (num % 100 == 0 && num % 400 != 0) {

                return false;

            }

            return true;

        }

        return false;

    }



    /**

     * 1-->01

     */

    public static String int2Str2(int num) {

        String str = "";

        if (num < 10) {

            str = "0" + num;

        } else {

            str = "" + num;

        }

        return str;

    }



    /**

     * 1-->001

     */

    public static String int2Str3(int num) {

        String str = "";

        if (num < 10) {

            str = "00" + num;

        } else {

            if (num < 100) {

                str = "0" + num;

            } else {

                str = "" + num;

            }

        }

        return str;

    }



    public static interface OnPickerViewListener {

        public void onChange(PickerView pickerView);

    }



    public int getYear() {

        return year;

    }



    public void setYear(int year) {

        this.year = year;

        update();

    }



    public int getMonth() {

        return month;

    }



    public void setMonth(int month) {

        this.month = month;

        update();

    }



    public int getDay() {

        return day;

    }



    public void setDay(int day) {

        this.day = day;

        update();

    }



    public int getHour() {

        return hour;

    }



    public void setHour(int hour) {

        this.hour = hour;

        update();

    }



    public int getMinute() {

        return minute;

    }



    public void setMinute(int minute) {

        this.minute = minute;

        update();

    }



    public int getSecond() {

        return second;

    }



    public void setSecond(int second) {

        this.second = second;

        update();

    }



    public int getMillisecond() {

        return millisecond;

    }



    public void setMillisecond(int millisecond) {

        this.millisecond = millisecond;

        update();

    }

}



