package com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kyun.android.baisibudejie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment1 extends Fragment{
    ListView list;
    private int[] Jh_touxiang = { R.drawable.jiehu_touxiang1,R.drawable.jiehu_touxiang2,R.drawable.jiehu_touxiang3,R.drawable.jiehu_touxiang4};
    private String[] Jh_Name = { "洛凡@", "白首不分离", "盆鱼宴", "皇冠囍" };
    private String[] Jh_neirong = { "啊，求同学们帮我呀，我快崩溃了", "快来围观，这道题怎么做呀？", "设计的同学在那里，求这个特效", "我软件的，太难了，太难了！" };
    private int[] Jh_neirong_tupian = { R.drawable.jiehu1,R.drawable.jiehu2,R.drawable.jiehu3,R.drawable.jiehu4};
    private String[] Jh_dianzan= { "157", "523", "365", "256" };
    private String[] Jh_budianzan= { "-23", "-10", "0", "-8" };
    private String[] Jh_pinglun= { "52", "53", "35", "20" };
    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment1, null);
        list = (ListView)view.findViewById(R.id.lv1);
        for(int i = 0; i < Jh_touxiang.length; i ++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("jh_touxiang", Jh_touxiang[i]);
            item.put("jh_name", Jh_Name[i]);
            item.put("jh_neirong", Jh_neirong[i]);
            item.put("jh_tupian_neirong", Jh_neirong_tupian[i]);
            item.put("jh_tdianzan", Jh_dianzan[i]);
            item.put("jh_budianzan", Jh_budianzan[i]);
            item.put("jh_pinglun", Jh_pinglun[i]);
            mData.add(item);
        }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,R.layout.dayi,new String[]{"jh_touxiang","jh_name","jh_neirong","jh_tupian_neirong","jh_tdianzan","jh_budianzan","jh_pinglun"},new int[]{R.id.jh_touxiang,R.id.jh_name,R.id.jh_neirong,R.id.jh_neirongtupian,R.id.jh_dianzan,R.id.jh_budianzan,R.id.jh_pinglun});
        list.setAdapter(adapter);
        return view;
    }

    }


