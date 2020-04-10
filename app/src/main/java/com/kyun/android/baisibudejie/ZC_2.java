package com.kyun.android.baisibudejie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.kyun.android.baisibudejie.pro.publish.view.Utilss.parseJSONWithGsonUtils.parseJSONWithGson;

public class ZC_2 extends Activity {

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private EditText edit5;
    private Handler handler;
    private String s;
    private CheckBox gou;
    private JSONObject json = new JSONObject();
    private String url = "http://loco.xinbinlong.com/login/Login/Register.php";
    User m_result;
    Map <String, String> map = new java.util.HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_2);
        kongjiantoumingdu();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                   String code=m_result.getStatus();

                        switch (code) {
                            case "200":
                                Toast.makeText(ZC_2.this, "注册成功", Toast.LENGTH_SHORT).show();
                                //跳转到登录界面
                                Intent intent = new Intent(ZC_2.this, DL_3.class);
                                startActivity(intent);
                                break;
                            case "407":
                            case "500":
                                Toast.makeText(ZC_2.this, "注册失败，请重新输入！", Toast.LENGTH_SHORT).show();
                                break;
                            case "400":
                                Toast.makeText(ZC_2.this, "该账号已有人注册！", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                }
            }
        };
    }

    public void kongjiantoumingdu() {
        edit1 = findViewById(R.id.edit_shoujihaoma);
        edit1.getBackground().setAlpha(100);
        edit2 = findViewById(R.id.edit_huoquyanzhengma);
        edit2.getBackground().setAlpha(100);
        edit3 = findViewById(R.id.edit_mima);
        edit3.getBackground().setAlpha(100);
        edit4 = findViewById(R.id.edit_zaishurumima);
        edit4.getBackground().setAlpha(100);
        edit5 = findViewById(R.id.edit_youxiang);
        edit5.getBackground().setAlpha(100);
        gou = findViewById(R.id.zc_gou);
    }

    public void fanhui_click(View v) {
        finish();
    }

    public void tijiao_click(View v) {
          new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            map.put("username", edit1.getText().toString());
                            map.put("password", edit3.getText().toString());
                            map.put("email", edit5.getText().toString());
                            HttpUtil.post(url, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("DaiDai", "OnFaile:", e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBody = response.body().string();
                                    Gson gson = new Gson();
                                    m_result = gson.fromJson(responseBody, User.class);
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
    public boolean yanzheng(String user,String password,String password2,String email){
        user =edit1.getText().toString().trim();
        password=edit3.getText().toString().trim();
        password2=edit4.getText().toString().trim();
        email=edit5.getText().toString().trim();
        if (TextUtils.isEmpty(user)) { edit1.setError("账号不能为空！");return false; }
        if (TextUtils.isEmpty(password)) { edit3.setError("密码不能为空！");return false; }
        if (!password.equals(password2)) { edit4.setError("两次密码输入不一致！");return false; }
        if (isEmail(email) && email.length() <= 31) { }else {return false; }
        if (!gou.isChecked()) {Toast.makeText(ZC_2.this, "请选择同意协议!", Toast.LENGTH_SHORT).show();return false; }
        return true;
    }
    //email 格式
    public static boolean isEmail(String email){
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
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

