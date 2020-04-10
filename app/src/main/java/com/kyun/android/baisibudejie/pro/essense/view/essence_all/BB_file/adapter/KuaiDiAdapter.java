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
public class KuaiDiAdapter extends RecyclerView.Adapter<KuaiDiHolder> implements View.OnClickListener{
    private int [] Na_ImgList;
    private String[]Na_Neirong;
    private String[]Na_jiage;
    private String[]Na_qujiandizhi;
    private String[]Na_shoujiandizhi;
    //寄
    private int [] Ji_ImgList;
    private String[]Ji_Neirong;
    private String[]Ji_jiage;
    private String[]Ji_qujiandizhi;
    private String[]Ji_jijiandizhi;
    private Context context;
    private List <Integer> lists;
    private MyItemClickListener mOnItemClickListener = null;
    public KuaiDiAdapter(Context context, int [] Na_ImgList, String[]Na_Neirong,String[]Na_jiage,String[]Na_qujiandizhi,String[]Na_shoujiandizhi,int [] Ji_ImgList, String[]Ji_Neirong,String[]Ji_jiage,String[]Ji_qujiandizhi,String[]Ji_jijiandizhi) {
        this.Na_ImgList = Na_ImgList;
        this.Na_Neirong = Na_Neirong;
        this.Na_jiage = Na_jiage;
        this.Na_qujiandizhi = Na_qujiandizhi;
        this.Na_shoujiandizhi = Na_shoujiandizhi;
        //寄
        this.Ji_ImgList = Ji_ImgList;
        this.Ji_Neirong = Ji_Neirong;
        this.Ji_jiage = Ji_jiage;
        this.Ji_qujiandizhi = Ji_qujiandizhi;
        this.Ji_jijiandizhi = Ji_jijiandizhi;
        this.context = context;
    }


    @Override
    public KuaiDiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_kuaidi, parent, false);
        KuaiDiHolder viewHolder = new KuaiDiHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KuaiDiHolder holder, int position) {
        holder.mImg.setImageResource(Na_ImgList[position]);
        holder.mNeirong.setText(Na_Neirong[position]);
        holder.mjiage.setText(Na_jiage[position]);
        holder.mqujiandizhi.setText(Na_qujiandizhi[position]);
        holder.mshoujiandizhi.setText(Na_shoujiandizhi[position]);
        //寄
        holder.jImg.setImageResource(Ji_ImgList[position]);
        holder.jNeirong.setText(Ji_Neirong[position]);
        holder.jjiage.setText(Ji_jiage[position]);
        holder.jqujiandizhi.setText(Ji_qujiandizhi[position]);
        holder.jjijiandizhi.setText(Ji_jijiandizhi[position]);



        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return Na_ImgList.length;
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
