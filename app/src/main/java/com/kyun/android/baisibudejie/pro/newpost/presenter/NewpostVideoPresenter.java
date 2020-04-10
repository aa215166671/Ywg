package com.kyun.android.baisibudejie.pro.newpost.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.kyun.android.baisibudejie.bean.PostsListBean;
import com.kyun.android.baisibudejie.http.utils.HttpUtils;
import com.kyun.android.baisibudejie.pro.base.presenter.BasePresenter;
import com.kyun.android.baisibudejie.pro.newpost.model.NewpostVideoModel;

import java.util.List;

//MVP中的P层：处理数据返回之后的逻辑
//例如：数据解析等等业务逻辑
public class NewpostVideoPresenter extends BasePresenter<NewpostVideoModel> {


    private int page=0;
    private String maxtime=null;

    public NewpostVideoPresenter(Context context) {
        super( context );
    }

    @Override
    public NewpostVideoModel bindModel() {
        return new NewpostVideoModel(getContext());
    }

    //定义解析方法
    public void getNewpostList(int type, final boolean isDownRefresh,final OnUIThreadListener<List<PostsListBean.PostList>> onUIThreadListener){

        if(isDownRefresh){
            maxtime=null;
        }
        //执行网络请求
        getModel().getNewpostList( type, page, maxtime, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if(TextUtils.isEmpty(result)){
                    //等于null--通知UI线程--刷新UI界面
                    onUIThreadListener.OnResult(null);
                }else{
                    //不等于null--解析数据
                    PostsListBean postsListBean=getGson().fromJson(result,PostsListBean.class);
                    //处理分页逻辑--UI层只负责显示数据
                    if(postsListBean!=null && postsListBean.getInfo()!=null){
                        maxtime=postsListBean.getInfo().getMaxtime();
                    }
                    if(isDownRefresh){
                        page=0;
                    }else{
                        page++;
                    }
                    onUIThreadListener.OnResult(postsListBean.getList());
                }
            }
        } );
    }
}
