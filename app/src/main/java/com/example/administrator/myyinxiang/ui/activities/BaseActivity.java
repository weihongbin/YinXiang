package com.example.administrator.myyinxiang.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrator.myyinxiang.BaseApplication;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.baseapp.AppManager;
import com.example.administrator.myyinxiang.utils.SystemBarTintManager;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    public Context nowContext;
    public SystemBarTintManager tintManager;
    protected BaseApplication baseApplication;
    /**
     * 加载条目
     */
    private ProgressDialog progressDialog;
    private boolean destroyed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        // 把actvity放到application栈中管理
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        this.nowContext=this;
        baseApplication = (BaseApplication) getApplication();
        innitView();
        innitListner();

    }
    protected void initWindows() {
        try {
            tintManager = new SystemBarTintManager(this);
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(getResources().getColor(R.color.colorPrimaryDark));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // ***************************************
    // 加载进度条
    // ***************************************
    public void showLoadingProgressDialog() {
        this.showProgressDialog("请稍后，加载中...");
    }

    public void showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        nowContext=null;
        baseApplication=null;
        ButterKnife.unbind(this);
    }

    //    /*********************子类实现*****************************/
//    //获取布局文件
    public abstract int getLayoutId();
//    //协调者
    public abstract void innitView();
//    //初始化view
    public abstract void innitListner();


    protected  void show(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }
}
