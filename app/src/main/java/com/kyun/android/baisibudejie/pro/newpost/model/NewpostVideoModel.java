package com.kyun.android.baisibudejie.pro.newpost.model;

import android.content.Context;
import android.text.TextUtils;

import com.kyun.android.baisibudejie.http.impl.RequestParam;
import com.kyun.android.baisibudejie.http.impl.SystemHttpCommand;
import com.kyun.android.baisibudejie.http.utils.HttpTask;
import com.kyun.android.baisibudejie.http.utils.HttpUtils;
import com.kyun.android.baisibudejie.pro.base.model.BaseModel;

//M层：数据层
//M层访问数据用的
//请求网络数据
//加载本地数据库缓存数据
//加载SD卡数据等等
public class NewpostVideoModel extends BaseModel {

    public NewpostVideoModel(Context context) {
        super( context );
    }

    private String getUrl(){
        return getServerUrl().concat("/api/api_open.php");
    }
    //定义访问精华接口
    //第一步：定义URL
    //第二部：定义接口

    //type--数据类型（例如：图片，视频等）
    //page--页码
    //maxtime--用户加载更多
    //onHttpResultListener--数据回调监听
    public void getNewpostList(int type, int page, String maxtime, HttpUtils.OnHttpResultListener onHttpResultListener){
        RequestParam requestParam=new RequestParam();
        requestParam.put("a","list");
        requestParam.put("c","data");
        requestParam.put("type",type);
        requestParam.put("page",page);
        if(!TextUtils.isEmpty(maxtime)){
            requestParam.put("maxtime",maxtime);
        }
        //发送请求
        HttpTask httpTask=new HttpTask(
                getUrl(),
                requestParam,
                onHttpResultListener,
                new SystemHttpCommand() );
        httpTask.execute();
    }
}

