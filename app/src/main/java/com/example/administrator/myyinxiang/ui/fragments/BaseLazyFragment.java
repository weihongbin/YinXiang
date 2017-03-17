package com.example.administrator.myyinxiang.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myyinxiang.utils.L;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/31.
 */
public abstract class BaseLazyFragment extends Fragment {



    private boolean isVisible;
    private boolean isViewCreated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        isViewCreated = true;
        ButterKnife.bind(this, view);
        loadForUI();
        innitListner();
        return view;
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        L.showLog("TAGSSSSSSSSSSSSSSSSSSSSSSSS","1111111111111111111111111111111111111111");
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()) {
//            isVisible = true;
//            loadForData();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//        if (isViewCreated && isVisible)
//            loadForUI();
//    }

    protected abstract void onInvisible();

    protected abstract void loadForData();

    protected abstract void loadForUI();

    // provide layout id
    public abstract int getLayoutID();
    public  void innitListner(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
