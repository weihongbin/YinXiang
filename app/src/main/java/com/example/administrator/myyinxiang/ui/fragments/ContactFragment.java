package com.example.administrator.myyinxiang.ui.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.utils.ImageUtils;
import com.example.administrator.myyinxiang.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/1/31.
 */
public class ContactFragment extends BaseLazyFragment {
    @Bind(R.id.edt_content)
    EditText mEditText;
    @Bind(R.id.iv_icon)
    ImageView mImageView;
    @Bind(R.id.btn)
    Button mButton;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

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
//        mTextView.setText("通讯录");

    }

    @OnClick(R.id.btn)
    public void onClick(View view) {
        String content = mEditText.getText().toString().trim();
        try {
             //判空，空指针
            if (TextUtils.isEmpty(content)) {
                return;
            }
            Bitmap bitmap = EncodingHandler.createQRCode(content, 350);
            bitmap = EncodingHandler.addLogo(bitmap, ImageUtils.drawable2Bitmap(getResources().getDrawable(R.drawable.ic_launcher), null));
            mImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_contact;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.first_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
//                mAdapter.addData(1);
                break;
            case R.id.id_action_delete:
//                mAdapter.removeData(1);
                break;
        }
        return true;
    }

}
