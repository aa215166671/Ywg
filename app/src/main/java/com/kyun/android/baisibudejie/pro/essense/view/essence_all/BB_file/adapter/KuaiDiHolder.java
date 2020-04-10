package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

class KuaiDiHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView mNeirong;
    ImageView mImg;
    TextView mjiage;
    TextView mqujiandizhi;
    TextView mshoujiandizhi;
    //寄
    TextView jNeirong;
    ImageView jImg;
    TextView jjiage;
    TextView jqujiandizhi;
    TextView jjijiandizhi;
    public KuaiDiHolder(View itemView) {
        super(itemView);
        mImg = (ImageView)itemView.findViewById( R.id.img);
        mNeirong = (TextView) itemView.findViewById(R.id.neirong);
        mjiage = (TextView) itemView.findViewById(R.id.na_jiage);
       mqujiandizhi = (TextView) itemView.findViewById(R.id.na_shoujiandizhi);
        mshoujiandizhi = (TextView) itemView.findViewById(R.id.na_shoujiandizhi);
        //寄
        jImg = (ImageView)itemView.findViewById(R.id.ji_img);
        jNeirong = (TextView) itemView.findViewById(R.id.ji_neirong);
        jjiage = (TextView) itemView.findViewById(R.id.ji_jiage);
        jqujiandizhi = (TextView) itemView.findViewById(R.id.ji_qujiandizhi);
        jjijiandizhi = (TextView) itemView.findViewById(R.id.ji_jijiandizhi);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}


