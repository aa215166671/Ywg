package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kyun.android.baisibudejie.DL_3;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.BangBangAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.XuanShangAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.listener.MyItemClickListener;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.view.LoadMoreRecyclerView;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.DateUtils;
import com.kyun.android.baisibudejie.pro.publish.view.Utilss.parseJSONWithGsonUtils;
import com.kyun.android.baisibudejie.utils.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FragmentXuanShang extends Fragment {
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static FragmentXuanShang instance = null;
    private LoadMoreRecyclerView mRecyclerView;
    public ArrayList<User> userBeanList;

    private static String URL = "http://loco.xinbinlong.com/login/Bb_post_read.php";
    public static String post_id;
    public static XuanShangAdapter xuanShangAdapter;

    public static FragmentXuanShang newInstance() {
        if (instance == null) {
            instance = new FragmentXuanShang();
        }
        return instance;
    }

    public FragmentXuanShang() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xuanshang, container, false);
        mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.XuanShang_Rview);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        //添加动画库
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//下拉刷新
        mRecyclerView.setAutoLoadMoreEnable(true);

        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_SHORT).show();
                        //TODO 下面注释掉的代码中的false是当数据加载完成以后为false的，然后加载更多的条目就会隐藏。
//                        mRecyclerView.notifyMoreFinish(false);
                    }
                }, 1000);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
            }
        });
        new MyAsycTask().execute(URL);

        return view;
    }

    class MyAsycTask extends AsyncTask<String, Void, List<User>> {

        @Override
        protected List<User> doInBackground(String... strings) {
            return getJsonData(URL);

        }

        @Override
        protected void onPostExecute(final List<User> users) {

            xuanShangAdapter = new XuanShangAdapter(getActivity(), users);
            mRecyclerView.setAdapter(xuanShangAdapter);
            xuanShangAdapter.setOnItemClickListener(new MyItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    Toast.makeText(getActivity(), "test" + users.get(postion).getPost_id(), Toast.LENGTH_SHORT).show();
                    post_id = users.get(postion).getPost_id();
                    Intent intent = new Intent(getActivity(), XuanShang_comment.class);
                    startActivity(intent);
                }
            });
            super.onPostExecute(users);
        }
    }

    private List<User> getJsonData(String url) {
        userBeanList = new ArrayList<>();
        try {
            String jsonString = readSteam(new URL(url).openStream());
            //解析数据
            //Json的解析类对象
            JsonParser parser = new JsonParser();
            //将JSON的String 转成一个JsonArray对象
            JsonArray jsonArray = parser.parse(jsonString).getAsJsonArray();
            Gson gson = new Gson();

            //加强for循环遍历JsonArray
            for (JsonElement user1 : jsonArray) {
                //使用GSON，直接转成Bean对象
                User user = gson.fromJson(user1, User.class);
                userBeanList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBeanList;
    }

    private String readSteam(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}



