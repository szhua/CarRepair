package com.lei.repair.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.lei.repair.R;
import com.lei.repair.widget.LoamoreView;
import com.runer.liabary.recyclerviewUtil.ItemDecorations;
import com.runer.liabary.util.RunerLinearManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by szhua on 2017/7/18/018.
 * github:https://github.com/szhua
 * TouMaiNetApp
 * BaseLoadMoreFragment
 *
 * 下拉刷新加载更多基类，可自定义实现效果，注意生命周期的运用；
 */
public abstract class BaseLoadMoreFragment extends BaseFragment {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    private BaseQuickAdapter baseQuickAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment_list_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(baseQuickAdapter==null){
            baseQuickAdapter =getAdater() ;
        }

        if(baseQuickAdapter!=null){
            //设置上拉加载更多；
            baseQuickAdapter.setLoadMoreView(getLoadMoreView());
            baseQuickAdapter.setOnLoadMoreListener(this,recyclerView);
        }

        recyclerView.setLayoutManager(getLayoutManager(getContext()));
        recyclerView.addItemDecoration(getDecoration(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(baseQuickAdapter);

        //设置刷新控件
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeColors(getRefreshColor(getContext()));
        super.onViewCreated(view, savedInstanceState);
    }


    //设置刷新和加载更多的完成；
    @Override
    public void onCompeleteRefresh() {
        super.onCompeleteRefresh();
        if(swipeRefresh!=null){
            swipeRefresh.setRefreshing(false);
        }
        if(recyclerView!=null&&baseQuickAdapter!=null){
            baseQuickAdapter.loadMoreComplete();
        }
    }


    public RecyclerView.ItemDecoration  getDecoration(Context context){
        return  ItemDecorations.vertical(context)
                .type(0, R.drawable.item_decoration_shape).create();
    }

    public  LinearLayoutManager getLayoutManager(Context context){
      return    new RunerLinearManager(context, LinearLayoutManager.VERTICAL,false);
    }

    public LoadMoreView getLoadMoreView(){
        return  new LoamoreView();
    }
    public abstract BaseQuickAdapter getAdater();
    @Override
    public void onRefresh(){
        super.onRefresh();
        refresh();
    }
    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
        loadMore();
    }

    public abstract void loadMore();

    public abstract  void refresh();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
