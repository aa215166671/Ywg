package com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.MainActivity;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.utils.ToastUtil;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import okhttp3.Callback;


/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment2 extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView recyclerWanggeView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment2, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerWanggeView = (RecyclerView) view.findViewById(R.id.recycler_wangge);
        inttwangge();
        initView();
        return view;
    }

    private void inttwangge() {
        //设置布局管理器
        //1、第一种LinearLayoutManager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //2、第二种 GridLayoutManager
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        //3、第三种
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
//        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerWanggeView.setLayoutManager(layoutManager);
        //绑定适配器
        RecyclerWanggeAdapter adapter = new RecyclerWanggeAdapter(getActivity(), getSousuoData());
        recyclerWanggeView.setAdapter(adapter);

    }

    /**
     * 获取数据源
     *
     * @return
     */
    public List<Bean.DataBean> getSousuoData() {
        List<Bean.DataBean> data = new ArrayList <>();
        for (int i = 0; i < 9; i++) {
            Bean.DataBean model = new Bean.DataBean();
            model.setText("按钮" + i);
            data.add(model);
        }
        return data;
    }



    private void initView() {
        //设置布局管理器
        //1、第一种LinearLayoutManager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //2、第二种 GridLayoutManager
       //GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
        //3、第三种
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        //设置布局的排版方向
//        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //绑定适配器
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Bean.DataBean model) {
                Toast.makeText(getActivity(), "你点击了第"+position, Toast.LENGTH_SHORT).show();
            }

        });//将接口传递到数据产生的地方
    }

    /**
    /**
     * 获取数据源
     *
     * @return
     */
    public List<Bean.DataBean> getData() {
        List<Bean.DataBean> data = new ArrayList <>();
        for (int i = 0; i < 30; i++) {
            Bean.DataBean model = new Bean.DataBean();
            model.setText("猴子请来的都比---" + i);
            data.add(model);
        }
        return data;
    }
}

