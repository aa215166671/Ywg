package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

class XuanShangHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView mXs_userName;
    NetworkImageView mXs_usertouxiang;
    TextView tv_XUanshang_neirong;
    ImageView mNeirong_tupian;
    TextView Xs_xinyuzhi;
    TextView Xs_pinglun;
    TextView Xs_send_time;
    public XuanShangHolder(View itemView) {
        super(itemView);
        mXs_userName = (TextView) itemView.findViewById( R.id.name);
        mXs_usertouxiang = (NetworkImageView)itemView.findViewById(R.id.touxiang_xuanshang);
        tv_XUanshang_neirong = (TextView) itemView.findViewById(R.id.neirong_xuanshang);
        mNeirong_tupian = (ImageView)itemView.findViewById(R.id.neirongtupian);
        Xs_xinyuzhi = (TextView) itemView.findViewById(R.id.xinyuzhi_sum);
        Xs_pinglun = (TextView) itemView.findViewById(R.id.pinglun_sum);
        Xs_send_time = (TextView) itemView.findViewById(R.id.send_time);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
