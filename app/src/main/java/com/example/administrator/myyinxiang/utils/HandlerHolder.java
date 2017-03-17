package com.example.administrator.myyinxiang.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


public class HandlerHolder extends Handler {
    private Context context;
    WeakReference<Context> mListenerWeakReference;


    public HandlerHolder(Context context) {
        mListenerWeakReference = new WeakReference<>(context);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mListenerWeakReference != null && mListenerWeakReference.get() != null) {
        }else{
            return;
        }
    }
}

