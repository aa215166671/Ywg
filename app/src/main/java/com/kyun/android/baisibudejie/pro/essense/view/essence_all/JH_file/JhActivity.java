package com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyun.android.baisibudejie.R;

public class JhActivity extends AppCompatActivity {

    //tab栏
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_essence_jiehu );

        //初始化视图
        initViews();

        //得到toolbar对象
        Toolbar toolbar = (Toolbar) findViewById(R.id.tools_bar);
//将toolbar原生title设为空
        toolbar.setTitle("");
//将toolbar对象加入到actionbar中
        setSupportActionBar(toolbar);
//得到toolbar中自定义控件，并进行处理
        TextView title = (TextView) findViewById(R.id.text);
        title.setText("解乎");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这句代码使启用Activity回退功能，并显示Toolbar上的左侧回退图标
    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);


        //设置Tab的图标


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载menu布局
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        //得到SearchView对象，SearchView一些属性可以直接使用，比如：setSubmitButtonEnabled，setQueryHint等
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //定义返回键
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("搜索");
        //如果想单独对SearchView定制，比如需要更换搜索图标等，可以通过一下代码实现。
        if(searchView != null) {
            //"android:id/search_plate"这个值得到方式：通过跟踪SearchView源码，找到其布局对应的id
            //这边修改的是点击搜索图标后展开界面的背景色，也可以根据不同的id修改对应的控件
            int id = searchView.getContext().getResources().getIdentifier(
                    "android:id/search_plate", null, null);
            LinearLayout layout = (LinearLayout) searchView.findViewById(id);
            layout.setBackgroundColor(Color.WHITE);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(JhActivity.this,query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){

            finish();
        }
        return true;
    }

}