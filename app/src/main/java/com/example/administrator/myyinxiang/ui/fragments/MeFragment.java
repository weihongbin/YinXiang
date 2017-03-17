package com.example.administrator.myyinxiang.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.ui.customeView.ConvenientBanner.CBViewHolderCreator;
import com.example.administrator.myyinxiang.ui.customeView.ConvenientBanner.ConvenientBanner;
import com.example.administrator.myyinxiang.ui.customeView.ConvenientBanner.NetworkImageHolderView;
import com.example.administrator.myyinxiang.ui.customeView.ConvenientBanner.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/1/31.
 */
public class MeFragment extends BaseLazyFragment implements OnItemClickListener {
    private List<String> networkImages;
    @Bind(R.id.banner)
    ConvenientBanner mConvenientBanner;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private String[] images = {"http://navigation_selector.xml.hiphotos.baidu.com/image/h%3D200/sign=62c595edf0d3572c79e29bdcba136352/e850352ac65c10383b570cc6b0119313b07e898d.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=af15bbb1ccea15ce5eeee60f86003a25/9c16fdfaaf51f3de18a16c5091eef01f3a2979f7.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=a5cbb285cbea15ce5eeee60f86013a25/9c16fdfaaf51f3de127f656496eef01f3b2979e5.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=e7f96606adc379316268802fdbc4b784/a1ec08fa513d2697c6e7ac5d57fbb2fb4316d8fe.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=9506a72ad01b0ef473e89e58edc551a1/b151f8198618367aeb7090d92c738bd4b31ce53d.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=1c9a50843ec79f3d90e1e2368aa0cdbc/f636afc379310a5566becb8fb24543a982261036.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=1e2baf30233fb80e13d167d106d02ffb/4034970a304e251f8a0847f9a586c9177f3e5337.jpg",
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        setBanner();
    }

    private void setBanner() {
        //网络加载例子
        networkImages = Arrays.asList(images);
        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.me_fragment;
    }


    @Override
    public void onItemClick(int position) {
        Snackbar.make(getView(), "onItemClick(" + position + ")", Snackbar.LENGTH_SHORT).show();
    }
    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        mConvenientBanner.startTurning(2000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        mConvenientBanner.stopTurning();
    }
}
