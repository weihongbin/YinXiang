package com.example.administrator.myyinxiang.ui.handler;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * MyHandler的基类
 * 
 * @author Administrator
 *
 */
public abstract class MyHandler extends Handler {
	public Context context;
	public WeakReference<Context> weakReference;

	public MyHandler(Context context) {
		this.context = context;
		weakReference = new WeakReference<Context>(context);
	}

	@Override
	public abstract void handleMessage(Message msg);

}
