package com.example.administrator.myyinxiang;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myyinxiang.ui.activities.AboutActivity;
import com.example.administrator.myyinxiang.ui.activities.BaseActivity;
import com.example.administrator.myyinxiang.ui.activities.StyleActivity;
import com.example.administrator.myyinxiang.ui.fragments.ContactFragment;
import com.example.administrator.myyinxiang.ui.fragments.FirstFragment;
import com.example.administrator.myyinxiang.ui.fragments.FoundFragment;
import com.example.administrator.myyinxiang.ui.fragments.MeFragment;
import com.example.administrator.myyinxiang.utils.T;
import com.example.administrator.myyinxiang.zxing.ui.CaptureActivity;

import butterknife.Bind;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    @Bind(R.id.main_first)
    LinearLayout first;
    @Bind(R.id.main_second)
    LinearLayout second;
    @Bind(R.id.main_third)
    LinearLayout third;
    @Bind(R.id.main_four)
    LinearLayout four;
    @Bind(R.id.main_qrcode)
    LinearLayout qrcode;
    @Bind(R.id.iv_main_first)
    ImageView iv_first;
    @Bind(R.id.iv_main_second)
    ImageView iv_second;
    @Bind(R.id.iv_main_third)
    ImageView iv_third;
    @Bind(R.id.iv_main_four)
    ImageView iv_four;
    @Bind(R.id.tv_main_first)
    TextView tv_first;
    @Bind(R.id.tv_main_second)
    TextView tv_second;
    @Bind(R.id.tv_main_third)
    TextView tv_third;
    @Bind(R.id.tv_main_four)
    TextView tv_four;
    @Bind(R.id.fragment_container)
    FrameLayout fragment_container;
    @Bind(R.id.toolbar)
    Toolbar toolBar;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigationView)
    NavigationView navigation;
    private ActionBarDrawerToggle mDrawerToggle;
    int selected, nomarl;
    Fragment[] fragment;
    private int currentIndex;
    private int index;
    View  headerView;
    private long    oldOutTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void innitView() {
        headerView=navigation.getHeaderView(0);
        toolBar.setTitle("Toolbar");
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                tintManager.setStatusBarAlpha(0);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolBar.setTitle("印象");
                tintManager.setStatusBarAlpha(1);
            }
        };
        mDrawerToggle.syncState();
        selected = getResources().getColor(R.color.text_FFFFFF);
        nomarl = getResources().getColor(R.color.text_black);
        fragment = new Fragment[4];
        fragment[0] = new FirstFragment();
        fragment[1] = new ContactFragment();
        fragment[2] = new FoundFragment();
        fragment[3] = new MeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment[0]).commitAllowingStateLoss();


}

    @Override
    public void innitListner() {
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigation.setNavigationItemSelectedListener(this);
        headerView.setOnClickListener(this);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        four.setOnClickListener(this);
        qrcode.setOnClickListener(this);
        //特殊的点击事件
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showMeassage(BaseApplication.getAppContext(), "ll_head");
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Snackbar.make(drawerLayout, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();

        switch (item.getItemId()){
            case R.id.nav_attention:
                startActivity(new Intent(this, StyleActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left  ,android.R.anim.slide_out_right  );
                break;
            case R.id.nav_photo:
                break;
            case R.id.nav_video:
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left  ,android.R.anim.slide_out_right  );
                break;
            case R.id.nav_share:
                share();
                break;
            case R.id.nav_night_mode:
                break;
        }
        T.showMeassage(BaseApplication.getAppContext(), item.getTitle() + " pressed");
        item.setChecked(true);
        drawerLayout.closeDrawers();


        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_first:
                index = 0;
                setTabs(index);
                break;
            case R.id.main_second:
                index = 1;
                setTabs(index);
                break;
            case R.id.main_third:
                index = 2;
                setTabs(index);
                break;
            case R.id.main_four:
                index = 3;
                setTabs(index);
                break;
            case R.id.main_qrcode:
                T.showMeassage(BaseApplication.getAppContext(), "扫码");
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                break;

        }
        if (currentIndex != index) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(fragment[currentIndex]);
            if (!fragment[index].isAdded()) {
                ft.add(R.id.fragment_container, fragment[index]);
            }
            ft.show(fragment[index]).commit();
        }
        currentIndex = index;
    }




    private void setTabs(int index) {
        resetColor();
        switch (index) {
            case 0:
                iv_first.setImageResource(R.mipmap.ic_home_selected);
                tv_first.setTextColor(selected);
                break;
            case 1:
                iv_second.setImageResource(R.mipmap.ic_girl_selected);
                tv_second.setTextColor(selected);
                break;
            case 2:
                iv_third.setImageResource(R.mipmap.ic_video_selected);
                tv_third.setTextColor(selected);
                break;
            case 3:
                iv_four.setImageResource(R.mipmap.ic_care_selected);
                tv_four.setTextColor(selected);
                break;
        }
    }

    private void resetColor() {
        iv_first.setImageResource(R.mipmap.ic_home_normal);
        iv_second.setImageResource(R.mipmap.ic_girl_normal);
        iv_third.setImageResource(R.mipmap.ic_video_normal);
        iv_four.setImageResource(R.mipmap.ic_care_normal);
        tv_first.setTextColor(nomarl);
        tv_second.setTextColor(nomarl);
        tv_third.setTextColor(nomarl);
        tv_four.setTextColor(nomarl);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 分享文字
     */
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shareSub));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
        startActivity(Intent.createChooser(intent, getTitle()));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
                if( System.currentTimeMillis() - oldOutTime > 2000){
                    oldOutTime = System.currentTimeMillis();
                    Toast.makeText(HomeActivity.this,"再次点击，退出应用",Toast.LENGTH_SHORT).show();
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
