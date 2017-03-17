package com.example.administrator.myyinxiang.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.baseapp.AppConfig;
import com.example.administrator.myyinxiang.model.LastComversationModel;
import com.example.administrator.myyinxiang.ui.adapters.FirstAdapter;
import com.example.administrator.myyinxiang.ui.listener.OnItemClickListener;
import com.example.administrator.myyinxiang.ui.listener.OnItemLongClickListener;
import com.example.administrator.myyinxiang.ui.listener.OnLoadMoreListener;
import com.example.administrator.myyinxiang.utils.L;
import com.example.administrator.myyinxiang.utils.T;
import com.example.administrator.myyinxiang.utils.TimeUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/1/31.
 */
public class FirstFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener, OnItemLongClickListener,OnLoadMoreListener {

    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycleView_first)
    RecyclerView mRecyclerView;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    FirstAdapter mAdapter;


    List<LastComversationModel> data;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void loadForData() {

    }

    @Override
    protected void loadForUI() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light,
                R.color.holo_orange_light, R.color.holo_red_light);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        data = new ArrayList<>();
        for (int i = 0; i <12; i++) {
            LastComversationModel model = new LastComversationModel();
            model.head = ((char) i % 26) + "";
            model.name = "魏红彬";
            model.lastMeassage = ((char) i % 26) + "";
            model.time = TimeUtils.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
            data.add(model);
        }
        L.showLog(AppConfig.DEBUG_TAG, "ASSSSSSSSSSSSSSSSSSSSSSSS");
        mAdapter = new FirstAdapter(getActivity(), data, R.layout.fragment_first_item, mRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new com.example.administrator.myyinxiang.ui.adapters.DividerItemDecoration(getActivity(), com.example.administrator.myyinxiang.ui.adapters.DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);

    }



    @Override
    public int getLayoutID() {
        return R.layout.first_fragment;
    }

    @Override
    public void innitListner() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLongItemClickListener(this);
        mAdapter.setmOnLoadMoreListener(this);
    }


    @Override
    public void onRefresh() {
        //加载数据
        Log.e("ttttttttt", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Log.e("TTTTTTTT", "onLoadMore");
        for (int i=0;i<6;i++){
            LastComversationModel model = new LastComversationModel();
            model.name = "more"+i;
            mAdapter.addData(0,model);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onItemClick(View view, int position) {
        T.showMeassage(getContext(), data.get(position).toString());
    }

    @Override
    public void onLongItemClick(View view, int postion) {
        T.showMeassage(getContext(), "您长按了。。。");
    }

    @Override
    public void onLoadMore() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("TTTTTTTT","onLoadMore");
        for (int i=0;i<6;i++){
            LastComversationModel model = new LastComversationModel();
            model.name = "more"+i;
            mAdapter.addData(data.size(),model);
        }

        mAdapter.setLoading(false);

    }
}
