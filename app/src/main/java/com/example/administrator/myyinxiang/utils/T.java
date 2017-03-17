package com.example.administrator.myyinxiang.utils;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/1.
 */
public class T {
    //长时间与短时间
    public static void showMeassage(Context context,String meassage){
        Toast.makeText(context,meassage,Toast.LENGTH_SHORT).show();
    }
    public static void LongShowMeassage(Context context,String meassage){
        Toast.makeText(context,meassage,Toast.LENGTH_LONG).show();
    }
    public static <T> void show(Context context, T msg) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e("TAG", "当前线程不为主线程，[" + String.valueOf(msg) + " ]无法Toast");
            return;
        }
        Toast.makeText(context, String.valueOf(msg), Toast.LENGTH_SHORT).show();

    }
}
