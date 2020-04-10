package com.kyun.android.baisibudejie.pro.newpost.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.PostsListBean;
import com.kyun.android.baisibudejie.pro.newpost.view.views.CircleNetworkImageImage;
import com.kyun.android.baisibudejie.utils.DataUtils;

import java.util.List;

public class NewpostVideoAdapter extends BaseRecyclerAdapter<NewpostVideoAdapter.VideoAdapterViewHolder> {

    private Context context;
    private List<PostsListBean.PostList> list;

    public NewpostVideoAdapter(Context context, List <PostsListBean.PostList> list) {
        this.context = context;
        this.list = list;
    }
    //配置ViewHolder
    @Override
    public VideoAdapterViewHolder getViewHolder(View view) {
        return new VideoAdapterViewHolder(view,false);
    }
    //创建布局
    @Override
    public VideoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v= LayoutInflater.from(context).inflate( R.layout.item_newpost_video_layout,parent,false);
        VideoAdapterViewHolder holder=new VideoAdapterViewHolder(v,true);
        return holder;
    }
    //给视图绑定数据
    @Override
    public void onBindViewHolder(VideoAdapterViewHolder holder, int position, boolean isItem) {
        PostsListBean.PostList postList=this.list.get(position);
        loadImage(holder.iv_header,postList.getProfile_image());
        holder.tv_name.setText(postList.getName());
        holder.tv_time.setText( DataUtils.parseDate(postList.getCreate_time()));
        holder.tv_content.setText(postList.getText());
        holder.tv_like.setText(postList.getDing());
        holder.tv_dislike.setText(postList.getCai());
        holder.tv_forword.setText(postList.getRepost());
        holder.tv_comment.setText(postList.getComment());
    }

    @Override
    public int getAdapterItemCount() {
        return this.list.size();
    }

    private void loadImage(NetworkImageView imageView,String url){
        RequestQueue queue= Volley.newRequestQueue(context);
        ImageLoader imageLoader=new ImageLoader( queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        } );
        imageView.setImageUrl(url,imageLoader);
    }

    public class VideoAdapterViewHolder extends RecyclerView.ViewHolder{

        public CircleNetworkImageImage iv_header;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_content;
        public ImageView iv_video;

        public LinearLayout ll_like;
        public TextView tv_like;
        public LinearLayout ll_dislike;
        public TextView tv_dislike;
        public LinearLayout ll_forword;
        public TextView tv_forword;
        public LinearLayout ll_comment;
        public TextView tv_comment;

        public VideoAdapterViewHolder(View itemView, CircleNetworkImageImage iv_header, TextView tv_name, TextView tv_time, TextView tv_content, ImageView iv_video, LinearLayout ll_like, TextView tv_like, LinearLayout ll_dislike, TextView tv_dislike, LinearLayout ll_forword, TextView tv_forword, LinearLayout ll_comment, TextView tv_comment) {
            super( itemView );
            this.iv_header = iv_header;
            this.tv_name = tv_name;
            this.tv_time = tv_time;
            this.tv_content = tv_content;
            this.iv_video = iv_video;
            this.ll_like = ll_like;
            this.tv_like = tv_like;
            this.ll_dislike = ll_dislike;
            this.tv_dislike = tv_dislike;
            this.ll_forword = ll_forword;
            this.tv_forword = tv_forword;
            this.ll_comment = ll_comment;
            this.tv_comment = tv_comment;
        }

        public CircleNetworkImageImage getIv_header() {
            return iv_header;
        }

        public void setIv_header(CircleNetworkImageImage iv_header) {
            this.iv_header = iv_header;
        }

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public TextView getTv_time() {
            return tv_time;
        }

        public void setTv_time(TextView tv_time) {
            this.tv_time = tv_time;
        }

        public TextView getTv_content() {
            return tv_content;
        }

        public void setTv_content(TextView tv_content) {
            this.tv_content = tv_content;
        }

        public ImageView getIv_video() {
            return iv_video;
        }

        public void setIv_video(ImageView iv_video) {
            this.iv_video = iv_video;
        }

        public LinearLayout getLl_like() {
            return ll_like;
        }

        public void setLl_like(LinearLayout ll_like) {
            this.ll_like = ll_like;
        }

        public TextView getTv_like() {
            return tv_like;
        }

        public void setTv_like(TextView tv_like) {
            this.tv_like = tv_like;
        }

        public LinearLayout getLl_dislike() {
            return ll_dislike;
        }

        public void setLl_dislike(LinearLayout ll_dislike) {
            this.ll_dislike = ll_dislike;
        }

        public TextView getTv_dislike() {
            return tv_dislike;
        }

        public void setTv_dislike(TextView tv_dislike) {
            this.tv_dislike = tv_dislike;
        }

        public LinearLayout getLl_forword() {
            return ll_forword;
        }

        public void setLl_forword(LinearLayout ll_forword) {
            this.ll_forword = ll_forword;
        }

        public TextView getTv_forword() {
            return tv_forword;
        }

        public void setTv_forword(TextView tv_forword) {
            this.tv_forword = tv_forword;
        }

        public LinearLayout getLl_comment() {
            return ll_comment;
        }

        public void setLl_comment(LinearLayout ll_comment) {
            this.ll_comment = ll_comment;
        }

        public TextView getTv_comment() {
            return tv_comment;
        }

        public void setTv_comment(TextView tv_comment) {
            this.tv_comment = tv_comment;
        }

        public VideoAdapterViewHolder(View itemView, boolean isItem) {
            super( itemView );
            if(isItem){
                iv_header=itemView.findViewById(R.id.iv_header);
                tv_name=itemView.findViewById(R.id.tv_name);
                tv_time=itemView.findViewById(R.id.tv_time);
                tv_content=itemView.findViewById(R.id.tv_content);
                iv_video=itemView.findViewById(R.id.iv_video);
                ll_like=itemView.findViewById(R.id.ll_like);
                tv_like=itemView.findViewById(R.id.tv_like);
                ll_dislike=itemView.findViewById(R.id.ll_dislike);
                tv_dislike=itemView.findViewById(R.id.tv_dislike);
                ll_forword=itemView.findViewById(R.id.ll_forword);
                tv_forword=itemView.findViewById(R.id.tv_forword);
                ll_comment=itemView.findViewById(R.id.ll_comment);
                tv_comment=itemView.findViewById(R.id.tv_comment);
            }
        }
    }
}
