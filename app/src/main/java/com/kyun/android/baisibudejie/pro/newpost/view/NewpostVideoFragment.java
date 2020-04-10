package com.kyun.android.baisibudejie.pro.newpost.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.kyun.android.baisibudejie.R;
import com.kyun.android.baisibudejie.bean.PostsListBean;
import com.kyun.android.baisibudejie.mvp.presenter.impl.MvpBasePresenter;
import com.kyun.android.baisibudejie.pro.base.presenter.BasePresenter;
import com.kyun.android.baisibudejie.pro.base.view.BaseFragment;
import com.kyun.android.baisibudejie.pro.newpost.presenter.NewpostVideoPresenter;
import com.kyun.android.baisibudejie.pro.newpost.view.adapter.NewpostVideoAdapter;
import com.kyun.android.baisibudejie.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class NewpostVideoFragment extends BaseFragment {

    private int mType = 0;
    private String mTitle;
    private NewpostVideoPresenter presenter;

    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private NewpostVideoAdapter videoAdapter;

    private List<PostsListBean.PostList> postList=new ArrayList <>();

    public void setType(int mType) {
        this.mType = mType;
    }
    public void setTitle(String title){
        this.mTitle = title;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        presenter = new NewpostVideoPresenter(getContext());
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_newpost_video;
    }

    @Override
    public void initContentView(View contentView) {
        xRefreshView=contentView.findViewById(R.id.xrefreshView);
        //设置是否下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        //设置是否上拉加载
        xRefreshView.setPullLoadEnable(true);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        //静默加载模式不能设置footerview
        //设置支持自动刷新
        xRefreshView.setAutoLoadMore(true);
        //设置静默加载是提前加载的item个数
        xRefreshView.setPreLoadCount(4);

        recyclerView=contentView.findViewById(R.id.recycler_View_test_rv);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        videoAdapter=new NewpostVideoAdapter(getContext(), postList);
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));
        xRefreshView.setXRefreshViewListener( new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                loadData(false);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double offset, int offsetY) {

            }
        } );
    }

    @Override
    public void initData() {
        if(postList.size()==0){
            loadData(true);
        }
    }

    private void loadData(final boolean isDownRefresh){
        presenter.getNewpostList( mType, isDownRefresh, new BasePresenter.OnUIThreadListener <List <PostsListBean.PostList>>() {
            @Override
            public void OnResult(List <PostsListBean.PostList> result) {
                if(isDownRefresh){
                    xRefreshView.stopRefresh();
                }else{
                    xRefreshView.stopLoadMore();
                }
                if(result==null){
                    ToastUtil.showToast(getContext(),"加载失败");
                }else{
                    ToastUtil.showToast(getContext(),"加载成功");
                    //刷新UI
                    if(isDownRefresh){
                        //如果是下拉刷新，就刷新列表数据
                        postList.clear();
                    }
                    postList.addAll(result);
                    videoAdapter.notifyDataSetChanged();
                }
            }
        } );
    }
}
