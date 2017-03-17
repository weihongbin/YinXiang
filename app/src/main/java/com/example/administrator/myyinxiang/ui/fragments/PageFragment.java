package com.example.administrator.myyinxiang.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myyinxiang.HomeActivity;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.utils.L;
import com.example.administrator.myyinxiang.utils.T;

import butterknife.Bind;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.demo.PageFragment.java
 * @author: 魏红彬
 * @date: 2017-03-06 16:23
 */
public class PageFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String TAG = "PageFragment";

    private String pager;

    @Bind(R.id.tv)
    TextView mTextView;
    public static PageFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, text);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pager = getArguments().getString(TAG);
    }

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
        mTextView.setText("欢迎使用"+pager);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_item;
    }

    @Override
    public void onClick(View view) {
        T.show(getContext(),Integer.parseInt(pager));
    }
}