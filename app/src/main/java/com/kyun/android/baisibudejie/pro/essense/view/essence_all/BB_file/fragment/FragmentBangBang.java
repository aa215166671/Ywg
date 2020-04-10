package com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.Bb_post;
import com.kyun.android.baisibudejie.bean.User;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.adapter.BangBangAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.essence_all.BB_file.view.LoadMoreRecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FragmentBangBang extends Fragment {
    public ArrayList <User> userBeanList;
    private static String URL = "http://loco.xinbinlong.com/login/Bb_post_read.php";
    private static FragmentBangBang instance = null;
    private RecyclerView mRecyclerView;

    public static FragmentBangBang newInstance() {
        if (instance == null) {
            instance = new FragmentBangBang();
        }
        return instance;
    }

    public FragmentBangBang() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //获取fragment布局
        View view = inflater.inflate(R.layout.fragment_bangbang, container, false);
        //改frament的布局
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.BangBang_Rview);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //改Adapter类
       new MyAsycTask().execute(URL);
        return view;
    }

    class MyAsycTask extends AsyncTask<String,Void,List<User>>{

        @Override
        protected List <User> doInBackground(String... strings) {
            return getJsonData(URL);
        }

        @Override
        protected void onPostExecute(List <User> users) {
            BangBangAdapter bangBangAdapter=new BangBangAdapter(getActivity(),users);
            mRecyclerView.setAdapter(bangBangAdapter);
            super.onPostExecute(users);
        }
    }

    private List<User> getJsonData(String url) {
        userBeanList=new ArrayList <>();
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



