package com.example.administrator.myyinxiang.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.administrator.myyinxiang.HomeActivity;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.model.TestModle;
import com.example.administrator.myyinxiang.ui.adapters.IndicateAdapter;
import com.example.administrator.myyinxiang.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnItemClickListener {
    @Bind(R.id.pager_indicate)
    ViewPager mViewPager;
    List<TestModle> mdata;
    IndicateAdapter adapter;
    private int currentItem = 0; // 当前图片的索引号
    private int oldPosition = 0;
    // 定义的4个指示点
    @Bind(R.id.v_dot0)
    View dot0;
    @Bind(R.id.v_dot1)
    View dot1;
    @Bind(R.id.v_dot2)
    View dot2;
    @Bind(R.id.v_dot3)
    View dot3;

    private List<View> dotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();
        innitListner();
    }

    public void setFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initData() {
        dotList = new ArrayList<>();
        dotList.add(dot0);
        dotList.add(dot1);
        dotList.add(dot2);
        dotList.add(dot3);
        mdata = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TestModle testModle = new TestModle();
            testModle.setContent("" + i);
            testModle.setIds(R.mipmap.ic_launcher);
            mdata.add(testModle);
        }
        adapter = new IndicateAdapter(mdata, this);
        mViewPager.setAdapter(adapter);

    }

    public void   innitListner() {
        adapter.setOnItemClickListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        dotList.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
        dotList.get(position).setBackgroundResource(R.drawable.dot_focused);
        oldPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "iv" + position, Toast.LENGTH_SHORT).show();
        if(position==mdata.size()-1){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
