package com.example.administrator.myyinxiang.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.administrator.myyinxiang.R;

import butterknife.Bind;

public class AboutActivity extends BaseActivity {


    @Bind(R.id.app_about_text)
    TextView textView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        innitTitle();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void innitView() {
    }

    @Override
    public void innitListner() {
    }
    private void initData() {
        textView.setAutoLinkMask(Linkify.ALL);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void innitTitle() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
