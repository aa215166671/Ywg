package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class BangBangAdapter extends RecyclerView.Adapter<BangBangHolder> implements View.OnClickListener{
    private Context context;
    private List<User> lists;
    private RequestQueue mQueue;

    private MyItemClickListener mOnItemClickListener = null;
    public BangBangAdapter(Context context, List <User> data) {
        this.lists=data;
        this.context = context;
    }


    @Override
    public BangBangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_bangbang, parent, false);
        BangBangHolder viewHolder = new BangBangHolder(view);
        view.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(context);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(BangBangHolder holder, int position) {
        //用原始图片占用位置
        //内容图片
        holder.bneirong_tupian.setImageResource(R.drawable.a00);
        //头像
        holder.buser_url.setImageResource(R.drawable.a00);

        //头像
        String url1=lists.get(position).getUser_url();
        holder.buser_url.setTag(url1);
        loadImage(holder.buser_url,url1);
//用户名
        holder.buser_name.setText(lists.get(position).getUser_name());
//信誉值
        holder.bxinyuzhi.setText(lists.get(position).getReputation());
//发布内容
        holder.bneirong.setText(lists.get(position).getText());
//内容图片
        String url2=lists.get(position).getText_url();
        holder.bneirong_tupian.setTag(url2);
        loadImgByVolley(holder.bneirong_tupian,url2);

//赞数
        holder.bzan.setText(lists.get(position).getZan());
//评论数
        holder.bpinglun.setText(lists.get(position).getComment());
//发布时间
        holder.bshijian.setText(lists.get(position).getTime());
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
    private void loadImage(NetworkImageView imageView,String url){
        RequestQueue queue= Volley.newRequestQueue(context);
        com.android.volley.toolbox.ImageLoader imageLoader=new com.android.volley.toolbox.ImageLoader( queue, new BitmapCache());
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
