package com.example.administrator.myyinxiang.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.ui.adapters.ClassFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/5.
 *
 *
 * 　　　app:layout_anchor="@id/appbar"

 　　　　且设置当前锚点的位置

 　　　　app:layout_anchorGravity=”bottom|end|right”
 */
public class StyleActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        innitTitle();
        innitData();
    }

    private void innitTitle() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 加载数据
     */
    private void innitData() {
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add(String.format(Locale.CANADA, "第%02d页", i));
        }
        ClassFragmentPagerAdapter adapter = new ClassFragmentPagerAdapter(getSupportFragmentManager(), mData);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_style;
    }

    @Override
    public void innitView() {
    }

    @Override
    public void innitListner() {
    }
}
