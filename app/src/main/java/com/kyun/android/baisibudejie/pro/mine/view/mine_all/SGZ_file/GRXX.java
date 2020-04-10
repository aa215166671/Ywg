package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.FragmentXuanShang;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.RiliView.PickerView;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GRXX extends Activity implements View.OnClickListener{
    private Log log;
    private static final String TAG = "GRXX";

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView touxiang_gerenxinxi;

    protected static final int NAN = 0;
    protected static final int NV = 1;

    private String imageName;
    private String imagePath;
    //头像
    private final String user_url = "http://loco.xinbinlong.com/login/User_url_uploadimg.php";
    Map<String, String> map = new HashMap<String, String>();

    private Bb_post my_user_url;
    private Handler handler;
    private static String url_my_user_url = "http://loco.xinbinlong.com/login/my_user_url.php";

    Map<String, String> map_text = new HashMap<String, String>();
    Bb_post my_info;
    private static String url_my_info = "http://loco.xinbinlong.com/login/Info/my_info.php";

//性别
    private static String url_my_info_sex_man = "http://loco.xinbinlong.com/login/Info/my_info_sex.php";
    Bb_post my_info_sex_man;
//生日
    Bb_post shengri_result;
    private ImageView cir_user;
    private RequestQueue mQueue;
    private TextView shuju_nicheng;
    private TextView xingbie;
    private TextView shuju_shengri;
    private String sex="";


    @BindView(R.id.shuju_xingzuo)
    TextView mSshuju_xingzuo;
    private String shengri;
    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mine_gerenxinxi );
        load();
        mQueue = Volley.newRequestQueue(this);
        tiaozhuan();
        initview();

        ButterKnife.bind(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:

                        break;
                    case 2:

                        loadImgByVolley(touxiang_gerenxinxi,my_info.getUser_url());
                        shuju_nicheng.setText(my_info.getUser_name());
                        if (my_info.getSex()==1){
                            xingbie.setText("女");
                        }else{
                            xingbie.setText("男");
                        }
                        Log.i(TAG, "handleMessage: "+my_info.getBirthday());
                        if (my_info.getBirthday()==null){
                            shuju_shengri.setHint("设置自己的生日吧、");
                        }else{
                            shuju_shengri.setText(my_info.getBirthday());
                        }
                        if (my_info.getXingzuo()==null){
                            mSshuju_xingzuo.setHint("还有星座哦！");
                        }else{
                            mSshuju_xingzuo.setText(my_info.getXingzuo());
                        }


                        break;
                    case 3:
                        //性别
                            onCreate(null);
                            Toast.makeText(GRXX.this,"设置成功！",Toast.LENGTH_SHORT).show();
                        break;
                            //生日
                    case 4:
                            onCreate(null);
                            Toast.makeText(GRXX.this,"设置成功！",Toast.LENGTH_SHORT).show();
                            mBottomSheetDialog.dismiss();

                        break;
                }
            }
        };
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map_text.put("user_id", DL_3.user_id);
                    HttpUtil.post(url_my_info, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            my_info = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 2;
                            msg.obj = my_info; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map_text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initview() {
        touxiang_gerenxinxi = (ImageView) findViewById( R.id.touxiang_gerenxinxi);
        cir_user = (ImageView) findViewById(R.id.cir_user);
        shuju_nicheng =(TextView)findViewById(R.id.shuju_nicheng);
        xingbie =(TextView)findViewById(R.id.shuju_xingbie);
        shuju_shengri =(TextView)findViewById(R.id.shuju_shengri);
    }
//设置生日
    public void shengri_gerenxinxi(View v){
        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_mine_shengri, null);
        PickerView pvTime=(PickerView)view.findViewById(R.id.pv_time);
        final TextView Xingzuo=(TextView)view.findViewById(R.id.xingzuo);
        Button mSet_shengri=(Button)view.findViewById(R.id.set_shengri) ;
        pvTime.setOnPickerViewChangeListener(new PickerView.OnPickerViewListener() {
            String xingzuo;

            @Override

            public void onChange(PickerView pickerView) {
                int month = pickerView.getMonth();
                int day = (pickerView.getDay() % 100);

                switch (month) {
                    case 1:
                        xingzuo = day < 21 ? "摩羯座" : "水瓶座";
                        break;
                     case 2:
                        xingzuo = day < 20 ? "水瓶座": "双鱼座";
                        break;
                    case 3:
                        xingzuo = day < 21 ?"双鱼座":"白羊座";
                        break;
                    case 4:
                        xingzuo = day < 21 ?"白羊座":"金牛座";
                        break;
                    case 5:
                        xingzuo = day < 22 ?"金牛座":"双子座";
                        break;
                    case 6:
                        xingzuo = day < 22 ?"双子座":"巨蟹座";
                        break;
                    case 7:
                        xingzuo = day < 23 ?"巨蟹座":"狮子座";
                        break;
                    case 8:
                        xingzuo = day < 24 ?"狮子座":"处女座";
                        break;
                    case 9:
                        xingzuo = day < 24 ?"处女座":"天秤座";
                        break;
                    case 10:
                        xingzuo = day < 24 ?"天秤座":"天蝎座";
                        break;
                    case 11:
                        xingzuo = day < 23 ?"天蝎座":"射手座";
                        break;
                    case 12:
                        xingzuo = day < 22 ?"射手座":"摩羯座";
                        break;
                }
               Xingzuo.setText(xingzuo);
                shengri = pickerView.getYear()+"-"+pickerView.getMonth()+"-"+pickerView.getDay();
            }
        });

        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        mBottomSheetDialog.show();



        //把生日数据传上
        mSet_shengri.setOnClickListener(new View.OnClickListener() {

            Map<String,String> mSet_shengri=new HashMap<>();
            private  String url_my_info_set_shengri= "http://loco.xinbinlong.com/login/Info/url_my_info_set_shengri.php";
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            mSet_shengri.put("user_id",DL_3.user_id);
                            mSet_shengri.put("shengri",shengri);
                            mSet_shengri.put("xingzuo",Xingzuo.getText().toString());
                            HttpUtil.post(url_my_info_set_shengri, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    onCreate(null);
                                    Toast.makeText(GRXX.this,"更改失败！",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBody=response.body().string();
                                    Gson gson=new Gson();
                                    shengri_result=gson.fromJson(responseBody,Bb_post.class);
                                    Message msg=handler.obtainMessage();
                                    msg.what=4;
                                    msg.obj=shengri_result;
                                    handler.sendMessage(msg);
                                }
                            },mSet_shengri);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_gerenxinxi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
//设置名称
        findViewById( R.id.nicheng_gerenxinxi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( GRXX.this, Set_name.class ) );
                finish();
            }
        } );
//设置性别
        findViewById( R.id.xingbie_gerenxinxi ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                };

                final TextView xingbie=(TextView)findViewById(R.id.shuju_xingbie);
                AlertDialog.Builder builder=new AlertDialog.Builder(GRXX.this);
                builder.setTitle("选择性别");
                String[] items = { "男", "女" };
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case NAN: // 男
                                xingbie.setText("男");
                                sex ="0";
                                Set_man();

                                break;
                            case NV: // 女
                                xingbie.setText("女");
                                sex ="1";
                                Set_girl();
                                break;
                        }
                    }
                });
                builder.show();
            }
        } );
    }

    private void Set_girl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("user_id", DL_3.user_id);
                    map.put("sex",sex);
                    HttpUtil.post(url_my_info_sex_man, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            onCreate(null);
                            Toast.makeText(GRXX.this,"更改失败！",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            my_info_sex_man = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.obj = my_info_sex_man; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Set_man() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("user_id", DL_3.user_id);
                    map.put("sex",sex);
                    HttpUtil.post(url_my_info_sex_man, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            my_info_sex_man = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 3;
                            msg.obj = my_info_sex_man; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //换头像
    public void touxiang_gerenxinxi(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(GRXX.this, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d(TAG,"setImageToView:"+photo);
            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            touxiang_gerenxinxi.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        imageName = String.valueOf(System.currentTimeMillis());
        imagePath = ImageUtils.savePhoto(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp_Touxiang", imageName);
        Log.e("imagePath", imagePath +"");
        if(imagePath != null){
            // 拿着imagePath上传了
            uploadMultiFile();
            //设置头像
            Userurl();
            Log.d(TAG,"imagePath:"+ imagePath);
        }
    }
    //设置头像
    private void Userurl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    map.put("user_id", DL_3.user_id);
                    map.put("img_name", imageName+".png");
                    HttpUtil.post(url_my_user_url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("DaiDai", "OnFaile:", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseBody = response.body().string();
                            Gson gson = new Gson();
                            my_user_url = gson.fromJson(responseBody, Bb_post.class);
                            //发送登录成功的消息
                            Message msg = handler.obtainMessage();
                            msg.what = 1;
                            msg.obj = my_user_url; //把登录结果也发送过去
                            handler.sendMessage(msg);
                        }
                    }, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //把图片发送到服务器
    private void uploadMultiFile() {
        File file=new File(imagePath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageName+".png", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(user_url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
            map.put("user_id", DL_3.user_id);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                finish();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.i("bb", "uploadMultiFile() response=" + response.body().string());
            }
        }
        );
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void popWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);//获取一个填充器
        View view = inflater.inflate(R.layout.tab_wd_xb, null);//填充我们自定义的布局
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
        popWindow.showAtLocation(inflater.inflate(R.layout.fragment_mine_gerenxinxi, null), Gravity.CENTER, 0, 0);

    }
    //加载图片
    public void loadImgByVolley(final ImageView imageView, final String imgUrl) {
        ImageRequest imgRequest = new ImageRequest(imgUrl,
                new com.android.volley.Response.Listener<Bitmap>() {
                    /**
                     * 加载成功
                     * @param arg0
                     */
                    @Override
                    public void onResponse(Bitmap arg0) {

                        imageView.setImageBitmap(arg0);
                    }
                }, 300, 200, Bitmap.Config.ARGB_8888,
                new com.android.volley.Response.ErrorListener() {
                    //加载失败
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        imageView.setImageResource(R.drawable.a01);
                    }
                });
        //将图片加载放入请求队列中去
        mQueue.add(imgRequest);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }
}
