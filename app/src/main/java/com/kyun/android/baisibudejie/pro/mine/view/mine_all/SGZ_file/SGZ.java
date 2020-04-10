package com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.adapter.StatusExpandAdapter;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.entity.ChildStatusEntity;
import com.kyun.android.baisibudejie.pro.mine.view.mine_all.SGZ_file.entity.GroupStatusEntity;

import java.util.ArrayList;
import java.util.List;

public class SGZ extends Activity {

    private ExpandableListView expandlistView;
    private StatusExpandAdapter statusAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_mine_shiguangzhou );

        tiaozhuan();
        initView();
        initExpandListView();
        load();
    }

    private void initView() {
        expandlistView = (ExpandableListView) findViewById(R.id.expandlist);
    }

    public void tiaozhuan() {
        findViewById( R.id.fanhui_shiguangzhou ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );


        findViewById( R.id.fabu_shiguangzhou ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SGZ.this, Publish_send.class ) );
            }
        } );
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }){

        }.start();
    }

    /**
     * 初始化可拓展列表
     */
    private void initExpandListView() {


        statusAdapter = new StatusExpandAdapter(this, getListData());
        expandlistView.setAdapter(statusAdapter);
        expandlistView.setGroupIndicator(null); // 去掉默认带的箭头
        expandlistView.setSelection(0);// 设置默认选中项

        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandlistView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandlistView.expandGroup(i);
        }

        expandlistView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    private List<GroupStatusEntity> getListData() {
        List<GroupStatusEntity> groupList;
        String[] strArray = new String[] { "10月22日", "10月23日", "10月25日" };
        String[][] childTimeArray = new String[][] {
                { "俯卧撑十次", "仰卧起坐二十次", "大喊我爱紫豪二十次", "每日赞紫豪一次" },
                { "亲，快快滴点赞哦~" }, { "没有赞的，赶紧去赞哦~" } };
        groupList = new ArrayList<GroupStatusEntity>();
        for (int i = 0; i < strArray.length; i++) {
            GroupStatusEntity groupStatusEntity = new GroupStatusEntity();
            groupStatusEntity.setGroupName(strArray[i]);

            List<ChildStatusEntity> childList = new ArrayList<ChildStatusEntity>();

            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildStatusEntity childStatusEntity = new ChildStatusEntity();
                childStatusEntity.setCompleteTime(childTimeArray[i][j]);
                childStatusEntity.setIsfinished(true);
                childList.add(childStatusEntity);
            }

            groupStatusEntity.setChildList(childList);
            groupList.add(groupStatusEntity);
        }
        return groupList;
    }
}
