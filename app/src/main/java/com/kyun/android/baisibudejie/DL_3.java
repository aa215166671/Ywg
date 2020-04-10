package com.kyun.android.baisibudejie;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.utils.HttpUtil;
import com.kyun.android.baisibudejie.utils.SaveUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.kyun.android.baisibudejie.pro.publish.view.Utilss.parseJSONWithGsonUtils.parseJSONWithGson;

public class DL_3 extends Activity {
    private Handler handler;
    private ArrayAdapter<String> adapter;
    private EditText edit1;
    private EditText edit2;
    private Spinner spinner;
    private String school;
    private String[] xuexiao;
    private CheckBox gou;
    private ProgressDialog progressDialog;
    private Handler mHandler = new Handler();
    public static String user_id="";
    public String user_name;
    public String user_url;
    private String url = "http://loco.xinbinlong.com/login/Login/Login.php";
    User m_result;
    Map<String, String> map = new HashMap<String, String>();


    private String imei2;
    private String imei1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_3);
        kongjiantoumingdu();
        tiaozhuan();
        //处理登录成功消息
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        try {
                            //获取用户登录的结果
                            User result = (User) msg.obj;
                            user_id = result.getUser_id();
                            user_name = result.getInfo();
                            user_url = result.getUser_url();
                            String status = result.getStatus();
                            String token=result.getToken();
                            if (status.equals("1")) {
                                Toast.makeText(DL_3.this, "成功登录", Toast.LENGTH_SHORT).show();
                                //跳转到登录成功的界面
                                Intent intent = new Intent(DL_3.this, MainActivity.class);
                                startActivity(intent);
                                //保存token
                                SaveUtils.saveFile(token,"data.txt");
                            } else if (status.equals("-2")) {
                                Toast.makeText(DL_3.this, "密码错误！", Toast.LENGTH_SHORT).show();
                            } else if (status.equals("-1")) {
                                Toast.makeText(DL_3.this, "不存在该用户！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
    }


    public void kongjiantoumingdu() {
        edit1 = findViewById(R.id.edit_shoujihaoma);
        edit1.getBackground().setAlpha(100);
        edit2 = findViewById(R.id.edit_mima);
        edit2.getBackground().setAlpha(100);
        gou = findViewById(R.id.dl_gou);
    }

    public void tiaozhuan() {
        findViewById(R.id.fanhui_qs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btn_shoujidenglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            map.put("user", edit1.getText().toString().trim());
                            map.put("pswd", edit2.getText().toString().trim());
                            map.put("imei", getIMEI());
                            HttpUtil.post(url, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("DaiDai", "OnFaile:", e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBody = response.body().string();
                                    m_result = parseJSONWithGson(responseBody);
                                    //发送登录成功的消息
                                    Message msg = handler.obtainMessage();
                                    msg.what = 123;
                                    msg.obj = m_result; //把登录结果也发送过去
                                    handler.sendMessage(msg);
                                }
                            }, map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }

    //获取设备MEID
    @SuppressLint("MissingPermission")
    protected String getIMEI() {
        @SuppressLint("MissingPermission")

        //实例化TelephonyManager对象
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Method method = null;
        try {
            method = telephonyManager.getClass().getMethod("getDeviceId", int.class);
            //获取IMEI号
            imei1 = telephonyManager.getDeviceId();
            imei2 = (String) method.invoke(telephonyManager, 1);
            //获取MEID号
            String meid = (String) method.invoke(telephonyManager, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei1;
    }
    //判断用户输入条件
    public boolean yanzheng(String user, String password){
        user =edit1.getText().toString().trim();
        password=edit2.getText().toString().trim();
        if (TextUtils.isEmpty(user)) { edit1.setError("账号不能为空！");return false; }
        if (TextUtils.isEmpty(password)) { edit2.setError("密码不能为空！");return false; }
        if (school.equals("选择学校")) { Toast.makeText(DL_3.this, "请选择所在的学校!", Toast.LENGTH_SHORT).show();return false; }
        if (!gou.isChecked()) {Toast.makeText(DL_3.this, "请选择所在的学校!", Toast.LENGTH_SHORT).show();return false; }
        return true;
    }

    //获取下拉选项的值
    class  SpinnerSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
            school=(String)xuexiao[position];

        }

        @Override
        public void onNothingSelected(AdapterView <?> parent) {
        }
    }
    //加载提示框
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(DL_3.this, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }
    /*
     * 隐藏提示加载
     */
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /** 判断网络是否连接 */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }
}



