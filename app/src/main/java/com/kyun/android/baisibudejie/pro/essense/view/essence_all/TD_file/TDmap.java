package com.kyun.android.baisibudejie.pro.essense.view.essence_all.TD_file;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kyun.android.baisibudejie.R;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptor;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

public class TDmap extends AppCompatActivity implements View.OnClickListener, TencentLocationListener {
    private ImageView ivLocation;
    private TencentLocationManager locationManager;
    private TencentLocationRequest locationRequest;
    private TencentMap tencentMap;
    private MapView mapView;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_essence_tandian );
        initView();
        initListener();
    }

    private void initView() {
        locationManager = TencentLocationManager.getInstance(this);
        locationRequest = TencentLocationRequest.create();
        mapView = findViewById(R.id.map);
        tencentMap = mapView.getMap();

        ivLocation = findViewById(R.id.iv_my_location);
        ivLocation.setOnClickListener(this);

    }

    private void initListener() {
        tencentMap.setOnMapCameraChangeListener(new TencentMap.OnMapCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                Log.i("mwb", "当前地图的中心点 经度:" + tencentMap.getMapCenter().getLongitude());
                Log.i("mwb", "当前地图的中心点 维度:" + tencentMap.getMapCenter().getLatitude());

                if (NetWorkUtils.isNetworkAvailable(TDmap.this)) {
                    setMarkerPostion(tencentMap.getMapCenter().getLongitude(), tencentMap.getMapCenter().getLatitude());
                } else {
                    showToast(TXMapConstant.NETWORK_UNAVAILABLE);
                }
            }
        });
    }

    /**
     * Toast
     *
     * @param msg
     */
    private void showToast(CharSequence msg) {
        ToastUtil.showShort(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_my_location) {
            checkNetWork();
        }
    }

    /**
     * 检查网络状态
     */
    private void checkNetWork() {
        if (NetWorkUtils.isNetworkAvailable(this)) { // 当前网络可用
            checkMyLocation();
        } else { // 当前网络不可用
            showToast(TXMapConstant.NETWORK_UNAVAILABLE);
        }
    }

    /**
     * 检查所需权限
     */
    private void checkMyLocation() {
        // 1.判断是否拥有定位的权限
        // 1.1 拥有权限进行相应操作
        // 1.2 没有权限申请权限
        // 1.2.1 Android 6.0 动态申请权限
        // 1.2.1.1 用户给予权限进行相应操作
        // 1.2.1.2 用户没有给予权限 作出相应提示
        // 1.2.2 某些5.0权限的手机执行相应操作

        String[] permission = {
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (ContextCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_GRANTED) { // 拥有权限
            getMyLocation();
        } else { // 没有权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //6.0
                requestPermissions(permission, 0);
            } else {
                // 此处为某些5.0动态权限的手机
                goSystemLocationActivity(); // TODO =======待删除======
                tipPermissionAlertDialog();
            }
        }
    }

    private void tipPermissionAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(TXMapConstant.SET_LOCATION_PERMISSION);
        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog alertDialog = builder.show();
                alertDialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 跳转系统定位页面
     */
    private void goSystemLocationActivity() {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    /**
     * 获取当前定位
     */
    private void getMyLocation() {
        int error = locationManager.requestLocationUpdates(
                locationRequest, this);

        switch (error) {
            case 0:
                Log.i("mwb", "成功注册监听器");
                break;
            case 1:
                Log.i("mwb", "设备缺少使用腾讯定位服务需要的基本条件");
                break;
            case 2:
                Log.i("mwb", "manifest 中配置的 key 不正确");
                break;
            case 3:
                Log.i("mwb", "自动加载libtencentloc.so失败");
                break;
            default:
                break;
        }
    }

    /**
     * 用户授权回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UserAuthorization(requestCode, grantResults);
    }

    /**
     * 用户授权情况
     *
     * @param requestCode
     * @param grantResults
     */
    private void UserAuthorization(int requestCode, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 拥有了授权
            checkMyLocation();
        } else {
            tipPermissionAlertDialog();
        }
    }

    private void AlertDialogShow() { // TODO 此处为打开定位设置 非授权，授权需另加弹窗让用户自己设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("重要提示");
        builder.setMessage("没有授权将无法使用该功能，是否授权？");
        builder.setPositiveButton("去授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goSystemLocationActivity();
            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.create().show();
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        Log.i("mwb", "tencentLocation（新的位置）：" + tencentLocation);
        Log.i("mwb", "错误码：" + i);
        Log.i("mwb", "错误信息：" + s);
        Log.i("mwb", "精度：" + tencentLocation.getLatitude());
        Log.i("mwb", "维度：" + tencentLocation.getLongitude());

        locationManager.removeUpdates(this);

        LatLng currentPostion;
        currentPostion = new LatLng(tencentLocation.getLatitude(),
                tencentLocation.getLongitude());
        tencentMap.animateTo(currentPostion); // 动画移动至经纬度
        tencentMap.setZoom(16); // 设置地图缩放比例

//        tencentMap.setCenter(new LatLng(tencentLocation.getLatitude(),
//                tencentLocation.getLongitude())); // 设置地图中心点

    }

    /**
     * 设置marker位置
     *
     * @param longitude 经度
     * @param latitude  维度
     */
    private void setMarkerPostion(double longitude, double latitude) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_address_pink);
        Bitmap bitmap1 = zoomImg(bitmap, 90, 90);
        if (mMarker != null) {
            mMarker.remove();
        }

        mMarker = tencentMap.addMarker(new MarkerOptions()
//                .setHidingAnination(translateAnimation)
//                .setShowingAnination(translateAnimation)
                .icon(new BitmapDescriptor(bitmap1)) // 设置图标
                .position(new LatLng(latitude, longitude))); // 设置marker位置经纬度
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        Log.i("mwb", "GPSorWifi：" + s);
        Log.i("mwb", "新的状态：" + i);
        Log.i("mwb", "状态描述：" + s1);
    }

    /**
     * 处理图片
     *
     * @param bm        所要转换的bitmap
     * @param newWidth  新的宽
     * @param newHeight 新的高
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


}
