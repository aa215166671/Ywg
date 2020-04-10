package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;
import com.kyun.android.baisibudejie.pro.mine.view.Utils.BitmapCache;

import java.util.List;

/**
 * Created by ytx on 2016/10/8.
 */
public class XuanShangAdapter extends RecyclerView.Adapter<XuanShangHolder> implements View.OnClickListener{
    private Context context;
    private List<User> lists;
    private RequestQueue mQueue;
    private MyItemClickListener mOnItemClickListener = null;
    public XuanShangAdapter(Context context, List <User> data) {
        this.lists=data;
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public XuanShangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //改布局layout
        View view = LayoutInflater.from(context).inflate( R.layout.item_xuanshang, parent, false);
        XuanShangHolder viewHolder = new XuanShangHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(XuanShangHolder holder, int position) {
        //用原始图片占用位置
        //内容图片
        holder.mNeirong_tupian.setImageResource(R.drawable.a00);
        //头像
        holder.mXs_usertouxiang.setImageResource(R.drawable.a00);
        //用户头像
        String url1=lists.get(position).getUser_url();
        holder.mXs_usertouxiang.setTag(url1);
        loadImage(holder.mXs_usertouxiang,url1);
        //用户名字
        holder.mXs_userName.setText(lists.get(position).getUser_name());
        //发布内容
        holder.tv_XUanshang_neirong.setText(lists.get(position).getText());
        //图片内容
        String url2=lists.get(position).getText_url();
        holder.mNeirong_tupian.setTag(url2);
        loadImgByVolley(holder.mNeirong_tupian,url2);

        //信誉值
        holder.Xs_xinyuzhi.setText(lists.get(position).getReputation());
        //评论数
        holder.Xs_pinglun.setText(lists.get(position).getComment());
        //发布时间
        holder.Xs_send_time.setText(lists.get(position).getTime());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //加载头像
    private void loadImage(NetworkImageView imageView, String url){
        RequestQueue queue= Volley.newRequestQueue(context);
        com.android.volley.toolbox.ImageLoader imageLoader=new com.android.volley.toolbox.ImageLoader( queue,new BitmapCache());
        imageView.setImageUrl(url,imageLoader);
    }
    //加载图片
    public void loadImgByVolley(final ImageView imageView, final String imgUrl) {
        ImageRequest imgRequest = new ImageRequest(imgUrl,
                new Response.Listener<Bitmap>() {
                    /**
                     * 加载成功
                     * @param arg0
                     */
                    @Override
                    public void onResponse(Bitmap arg0) {

                        imageView.setImageBitmap(arg0);
                    }
                }, 300, 200, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    //加载失败
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        imageView.setImageResource(R.drawable.a01);
                    }
                });
        //将图片加载放入请求队列中去
        mQueue.add(imgRequest);
    }
}
