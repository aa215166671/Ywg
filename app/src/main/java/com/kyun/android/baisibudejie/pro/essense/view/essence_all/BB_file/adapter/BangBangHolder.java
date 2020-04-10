package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

class BangBangHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView buser_name;
    NetworkImageView buser_url;
    TextView bneirong;
    ImageView bneirong_tupian;
    TextView bxinyuzhi;
    TextView bzan;
    TextView bpinglun;
    TextView bshijian;
    public BangBangHolder(View itemView) {
        super(itemView);
        buser_url = (NetworkImageView) itemView.findViewById( R.id.bb_touxiang);
        buser_name = (TextView) itemView.findViewById(R.id.bb_name);
        bxinyuzhi = (TextView) itemView.findViewById(R.id.bb_xinyuzhi);
        bneirong = (TextView) itemView.findViewById(R.id.bb_neirong);
        bneirong_tupian = (ImageView)itemView.findViewById(R.id.bb_tupian);
        bzan = (TextView) itemView.findViewById(R.id.bb_zan);
        bpinglun = (TextView) itemView.findViewById(R.id.bb_pinglun);
        bshijian = (TextView) itemView.findViewById(R.id.bb_shijian);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}

