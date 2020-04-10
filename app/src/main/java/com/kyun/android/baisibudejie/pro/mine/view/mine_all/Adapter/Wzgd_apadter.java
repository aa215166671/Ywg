package com.kyun.android.baisibudejie.pro.mine.view.mine_all.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.pro.newpost.view.views.CircleNetworkImageImage;

import java.util.List;

public class Wzgd_apadter extends BaseAdapter {
    public Context context;
    public  static List<Bb_post> mList;
    private RequestQueue mQueue;

    public Wzgd_apadter(Context c, List<Bb_post> data){
        this.context = c;
        this.mList = data;

    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Wzgd_apadter.ViewHolder holder;
        // 重用convertView
        holder = new Wzgd_apadter.ViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.fragment_mine_wozanguode_item, null);
        mQueue = Volley.newRequestQueue(context);
        holder.My_zan_userURL =(CircleNetworkImageImage) convertView.findViewById(R.id.my_zan_urseurl);
        holder.My_zan_username=(TextView)convertView.findViewById(R.id.my_zan_username);
        holder.My_zan_xinyuzhi=(TextView)convertView.findViewById(R.id.my_zan_xinyuzhi_sum);
        holder.My_zan_text=(TextView)convertView.findViewById(R.id.my_zan_text);
        holder.My_zan_texturl=(ImageView)convertView.findViewById(R.id.my_zan_texturl);
        holder.My_zan_pinglun=(TextView)convertView.findViewById(R.id.my_zan_pinglun_sum);
        holder.My_zan_zan=(TextView)convertView.findViewById(R.id.my_zan_zan_sum);
        holder.My_zan_time=(TextView)convertView.findViewById(R.id.my_zan_time);
        // 适配数据

//先设置图片占位置
        holder.My_zan_userURL.setImageResource(R.drawable.a00);
        //先设置图片占位置
        holder.My_zan_texturl.setImageResource(R.drawable.a00);
        //评论者的头像
        String url1=mList.get(position).getUser_url();
        holder.My_zan_userURL.setTag(url1);
        loadImage(holder.My_zan_userURL,url1);
        //评论的名字
        holder.My_zan_username.setText(mList.get(position).getUser_name());
        //帖子的信誉值
        holder.My_zan_xinyuzhi.setText(mList.get(position).getReputation());
        //评论的内容
        holder.My_zan_text.setText(mList.get(position).getText());

        //贴子图片
        String url2=mList.get(position).getText_url();
        holder.My_zan_texturl.setTag(url2);
        loadImgByVolley(holder.My_zan_texturl,url2);
        //评论数
        holder.My_zan_pinglun.setText(mList.get(position).getComment());
        //赞数
        holder.My_zan_zan.setText(mList.get(position).getZan());

        //帖子时间
        holder.My_zan_time.setText(mList.get(position).getTime());
        return convertView;
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        private CircleNetworkImageImage My_zan_userURL;
        private TextView My_zan_username;
        private TextView My_zan_xinyuzhi;
        private TextView My_zan_text;
        private ImageView My_zan_texturl;
        private TextView My_zan_pinglun;
        private TextView My_zan_zan;
        private TextView My_zan_time;


    }
    //加载头像
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        com.android.volley.toolbox.ImageLoader imageLoader = new com.android.volley.toolbox.ImageLoader(queue, new com.android.volley.toolbox.ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        imageView.setImageUrl(url, imageLoader);
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
}
