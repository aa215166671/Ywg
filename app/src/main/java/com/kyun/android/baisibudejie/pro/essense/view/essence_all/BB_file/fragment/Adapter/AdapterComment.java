package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.pro.mine.view.Utils.BitmapCache;
import com.kyun.android.baisibudejie.pro.newpost.view.views.CircleNetworkImageImage;

import java.util.List;

/**
 * Created by yyp on 2016/8/10.
 */
public class AdapterComment extends BaseAdapter {

    public  Context context;
    public  static List<Bb_post> mList;
    private RequestQueue mQueue;
    public AdapterComment(Context c, List<Bb_post> data){
        this.context = c;
        this.mList = data;
    }

    @Override
    public  int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.xuangshuang_item_comment, null);
            mQueue = Volley.newRequestQueue(context);
            holder.Comment_userURL =(CircleNetworkImageImage) convertView.findViewById(R.id.comment_item_touxiang);
            holder.Comment_username=(TextView)convertView.findViewById(R.id.comment_item_fromname);
            holder.Comment_time=(TextView)convertView.findViewById(R.id.comment_item_time);
            holder.Comment_zan=(TextView)convertView.findViewById(R.id.comment_item_zan);
            holder.Comment_text=(TextView)convertView.findViewById(R.id.comment_item_comment);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据

        holder.Comment_username.setText(mList.get(i).getUser_name());
        loadImage(holder.Comment_userURL,mList.get(i).getUser_url());
        holder.Comment_time.setText(mList.get(i).getTime());
        holder.Comment_zan.setText(mList.get(i).getComment_zan());
        holder.Comment_text.setText(mList.get(i).getComment_text());
        return convertView;
    }
    //加载头像
    protected void loadImage(NetworkImageView imageView, String url){
        RequestQueue queue= Volley.newRequestQueue(context);
        com.android.volley.toolbox.ImageLoader imageLoader=new com.android.volley.toolbox.ImageLoader( queue, new BitmapCache());
        imageView.setImageUrl(url,imageLoader);
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        private CircleNetworkImageImage Comment_userURL;
        private TextView Comment_username;
        private TextView Comment_time;
        private TextView Comment_zan;
        private TextView Comment_text;
    }

}
