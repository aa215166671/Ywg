package com.kyun.android.baisibudejie.pro.essense.view.essence_all.YB_file;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.pro.essense.view.views.barragephoto.adapter.AdapterListener;
import com.kyun.android.baisibudejie.pro.essense.view.views.barragephoto.adapter.BarrageAdapter;
import com.kyun.android.baisibudejie.pro.essense.view.views.barragephoto.ui.BarrageView;

import java.util.LinkedList;
import java.util.List;

/**
 * 作者：Loco on 2019/7/27.
 * 邮箱：8752439@qq.com
 * 版本：v1.0
 */
public class YBActivity extends AppCompatActivity {

    private String SEED[] = {"景色还不错啊", "下午5点新球场3v3缺1个～", "又去哪里玩了？我也要去！带我去～", "王者上分车队？", "一起吃鸡啊！"};
    private final int ICON_RESOURCES[] = {R.drawable.cat, R.drawable.corgi, R.drawable.lovelycat, R.drawable.boy, R.drawable.girl,R.drawable.samoyed};

    private BarrageView barrageView;
    private BarrageAdapter<BarrageDara> mAdapter;
    //轮盘
    private String[] mItemTexts = new String[]{"篮球 ", "王者", "吃鸡",
            "图书馆", "跑步", "旅游"};
    private int[] mItemImgs = new int[]{R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal};


    //private Button btnStop;

    public static void show(Context context){
        Intent intent = new Intent(context,YBActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_yueba );

        barrageView = findViewById(R.id.barrage);
        //btnStop = findViewById(R.id.btn_add);

        initBarrage();

        /*btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                barrageView.destroy();
            }
        });*/

        //轮盘
        CircleMenuLayout mCircleMenuLayout = findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {

            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(YBActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(YBActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initBarrage(){
        BarrageView.Options options = new BarrageView.Options()
                .setGravity(BarrageView.GRAVITY_TOP)                // 设置弹幕的位置
                .setInterval(50)                                     // 设置弹幕的发送间隔
                .setSpeed(100,15)                   // 设置速度和波动值
                .setModel(BarrageView.MODEL_COLLISION_DETECTION)     // 设置弹幕生成模式
                .setRepeat(-1)                                       // 循环播放 默认为1次 -1 为无限循环
                .setClick(true);                                    // 设置弹幕是否可以点击
        barrageView.setOptions(options);
        // 设置适配器 第一个参数是点击事件的监听器
        barrageView.setAdapter(mAdapter = new BarrageAdapter<BarrageDara>(null, this) {
            @Override
            public BarrageViewHolder<BarrageDara> onCreateViewHolder(View root, int type) {
                return new YBActivity.ViewHolder(root);
            }

            @Override
            public int getItemLayout(BarrageDara barrageData) {
                return R.layout.barrage_item_normal;
            }
        });

        // 设置监听器
        mAdapter.setAdapterListener(new AdapterListener<BarrageDara>() {
            @Override
            public void onItemClick(BarrageAdapter.BarrageViewHolder<BarrageDara> holder, BarrageDara item) {
                Toast.makeText(YBActivity.this, item.getContent() + "点击了一次", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        initData();
    }

    private void initData() {
        int strLength = SEED.length;
        List<BarrageDara> dataList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new BarrageDara(SEED[i%strLength], 0,i));
        }
        mAdapter.addList(dataList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        barrageView.destroy();
    }

    class ViewHolder extends BarrageAdapter.BarrageViewHolder<BarrageDara> {

        private ImageView mHeadView;
        private TextView mContent;

        ViewHolder(View itemView) {
            super(itemView);

            mHeadView = itemView.findViewById(R.id.image);
            mContent = itemView.findViewById(R.id.content);
        }

        @Override
        protected void onBind(BarrageDara data) {
            Glide.with(YBActivity.this).load(ICON_RESOURCES[data.getPos()%ICON_RESOURCES.length])
                    .apply(RequestOptions.circleCropTransform())
                    .into(mHeadView);
            mContent.setText(data.getContent());
        }
    }


}
