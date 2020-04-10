package com.kyun.android.baisibudejie.pro.publish.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.MainActivity;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.BitmapUtil;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.DateUtils;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.parseJSONWithGsonUtils;
import com.kyun.android.baisibudejie.utils.HttpUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Publish extends Activity  implements View.OnClickListener {
    private ImageView mPost_return;
    private EditText mPost_text;
    private Button mPost_send;
    private Handler handler;
    User m_result;
    Map <String, String> map = new HashMap <String, String>();
    private String url = "http://loco.xinbinlong.com/login/Bb_post_send.php";
    private final String text_url = "http://loco.xinbinlong.com/login/uploadimg.php";
    private Button btnCamera;
    private Button btnPhoto;
    private TextView mTextPhoto;//相册
    private TextView mTextCamera;//相机
    private ImageView img; //显示压缩处理过后的图片
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private Uri imageUri;

    private String imagePath;
    private String imageName="take_photo"+System.currentTimeMillis()+".jpg";
    //处理登录成功消息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_publish);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        Toast.makeText(Publish.this, "发布成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Publish.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        };
        initView();
    }
    private void initView() {
        mPost_text = findViewById(R.id.post_text);
        mPost_send = findViewById(R.id.post_send);
        mPost_return = findViewById(R.id.post_return);
        mTextPhoto = findViewById(R.id.TextPhoto);
        mTextCamera = findViewById(R.id.TextCamera);

        //相册
        btnCamera = (Button) findViewById(R.id.Post_Camera);
        btnPhoto = (Button) findViewById(R.id.Post_Photo);
        img = (ImageView) findViewById(R.id.post_img);
        mPost_send.setOnClickListener(this);
        mTextPhoto.setOnClickListener(this);
        mTextCamera.setOnClickListener(this);
        mPost_return.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TextPhoto:
                //从相册中选取图片
                select_photo();
                break;
            case R.id.TextCamera:
                //照片的路径
                take_photo();
                break;
            case R.id.post_return:
                finish();
                break;
            case R.id.post_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            map.put("user_id", DL_3.user_id);
                            map.put("text", mPost_text.getText().toString().trim());
                            map.put("time", DateUtils.getNowDateTime());
                            map.put("text_url",imageName);

                            HttpUtil.post(url, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("DaiDai", "OnFaile:", e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String responseBody = response.body().string();
                                    m_result = parseJSONWithGsonUtils.parseJSONWithGson(responseBody);
                                    //发送登录成功的消息
                                    Message msg = handler.obtainMessage();
                                    msg.what = 1;
                                    msg.obj = m_result; //把登录结果也发送过去
                                    handler.sendMessage(msg);
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadMultiFile();
                    }
                }).start();
                break;
        }
    }
    //把图片发送到服务器
    private void uploadMultiFile() {

        File file=new File(BitmapUtil.compressImage(imagePath));
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageName, fileBody)
                .build();
        Request request = new Request.Builder()
                .url(text_url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                finish();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.i("bb", "uploadMultiFile() response=" + response.body().string());
            }
        });
    }


    /**
     *拍照获取图片
     **/
    public void take_photo() {
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            //创建File对象，用于存储拍照后的图片
            File outputImage = new File(Environment.getExternalStorageDirectory()+"/Pictures/"+System.currentTimeMillis()+".jpg");

            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(this, "com.llk.study.activity.PhotoActivity", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            //启动相机程序
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        }else
        {

            Toast.makeText(Publish.this, "没有储存卡",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 从相册中获取图片
     * */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            openAlbum();
        }
    }
    /**
     * 打开相册的方法
     * */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                       Toast.makeText(this,"URi1"+imageUri,Toast.LENGTH_SHORT).show();
                       imagePath=imageUri.getPath();
                        displayImage(imagePath);
                        imageName="take_photo"+System.currentTimeMillis()+".jpg";
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO :
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImgeOnKitKat(data);
                    }else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     *4.4以下系统处理图片的方法
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);

        Toast.makeText(Publish.this,"failed to get image"+imagePath,Toast.LENGTH_SHORT).show();
        displayImage(imagePath);
    }
    /**
     * 4.4及以上系统处理图片的方法
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {

        imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri,null);
            }else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }
    /**
     * 根据图片路径显示图片的方法
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            img.setImageBitmap(bitmap);
        }else {
            Toast.makeText(Publish.this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 通过uri和selection来获取真实的图片路径
     * */
    private String getImagePath(Uri uri,String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(Publish.this,"failed to get image",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

