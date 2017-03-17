package com.example.administrator.myyinxiang.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.myyinxiang.ui.fragments.PageFragment;

import java.util.List;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.demo.MyFragmentPagerAdapter.java
 * @author: 魏红彬
 * @date: 2017-03-06 16:12
 */
public class ClassFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mData;
    public ClassFragmentPagerAdapter(FragmentManager fm, List<String> mData) {
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(mData.get(position));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }

}