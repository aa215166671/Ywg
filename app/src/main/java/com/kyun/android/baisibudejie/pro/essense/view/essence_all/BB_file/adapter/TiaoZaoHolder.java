package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

class TiaoZaoHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView tneirong;
    ImageView tImg;
    TextView tchakan_sum;
    TextView tleixing;
    TextView tmaimai;
    TextView tjiage;
    public TiaoZaoHolder(View itemView) {
        super(itemView);
        tImg = (ImageView)itemView.findViewById(R.id.tz_img);
        tneirong = (TextView) itemView.findViewById(R.id.tz_neirong);
        tchakan_sum = (TextView) itemView.findViewById(R.id.tz_chakan_sum);
        tleixing = (TextView) itemView.findViewById(R.id.tz_leixing);
        tmaimai = (TextView) itemView.findViewById(R.id.tz_maimai);
        tjiage = (TextView) itemView.findViewById(R.id.tz_jiage);

    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}

