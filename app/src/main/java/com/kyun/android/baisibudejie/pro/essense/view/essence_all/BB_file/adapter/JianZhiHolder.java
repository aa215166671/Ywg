package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;

class JianZhiHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView mTextView;
    ImageView mImg;
    public JianZhiHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById( R.id.jianzhi_neirong);
        mImg = (ImageView)itemView.findViewById(R.id.jianzhi_tupian);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}

