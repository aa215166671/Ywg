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
public class TiaoZaoAdapter extends RecyclerView.Adapter<TiaoZaoHolder> implements View.OnClickListener{
    private int [] Tz_ImgList;
    private String[]Tz_neirong;
    private String[]TZ_chakan_sum;
    private String[]Tz_leixing;
    private String[]Tz_maimai;
    private String[]Tz_jiage;
    private Context context;
    private List <Integer> lists;
    private MyItemClickListener mOnItemClickListener = null;
    public TiaoZaoAdapter(Context context, int [] Tz_ImgList, String[]Tz_neirong,String[] TZ_chakan_sum,String[] Tz_leixing,String[]Tz_maimai,String[]Tz_jiage) {
        this.Tz_ImgList = Tz_ImgList;
        this.Tz_neirong = Tz_neirong;
        this.TZ_chakan_sum = TZ_chakan_sum;
        this.Tz_leixing = Tz_leixing;
        this.Tz_maimai = Tz_maimai;
        this.Tz_jiage = Tz_jiage;
        this.context = context;
    }


    @Override
    public TiaoZaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_tiaozao, parent, false);
        TiaoZaoHolder viewHolder = new TiaoZaoHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TiaoZaoHolder holder, int position) {
        holder.tImg.setImageResource(Tz_ImgList[position]);
        holder.tneirong.setText(Tz_neirong[position]);
        holder.tchakan_sum.setText(TZ_chakan_sum[position]);
        holder.tleixing.setText(Tz_leixing[position]);
        holder.tmaimai.setText(Tz_maimai[position]);
        holder.tjiage.setText(Tz_jiage[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return Tz_ImgList.length;
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
