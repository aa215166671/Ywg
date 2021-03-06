package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

import java.util.List;

/**
 * Created by ytx on 2016/10/8.
 */
public class JianZhiAdapter extends RecyclerView.Adapter<JianZhiHolder> implements View.OnClickListener{
    private int [] mImgList;
    private String[]mTag;
    private Context context;
    private List<Integer> lists;
    private MyItemClickListener mOnItemClickListener = null;
    public JianZhiAdapter(Context context,int [] mImgList, String[]mTag) {
        this.mImgList = mImgList;
        this.mTag = mTag;
        this.context = context;
    }


    @Override
    public JianZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //改item的布局
        View view = LayoutInflater.from(context).inflate( R.layout.item_jianzhi, parent, false);
        JianZhiHolder viewHolder = new JianZhiHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JianZhiHolder holder, int position) {
        holder.mImg.setBackgroundResource(mImgList[position]);
        holder.mTextView.setText(mTag[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mImgList.length;
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
}
