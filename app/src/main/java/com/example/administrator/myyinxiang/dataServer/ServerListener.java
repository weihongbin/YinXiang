package com.example.administrator.myyinxiang.dataServer;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.administrator.myyinxiang.model.MessageModel;
import com.example.administrator.myyinxiang.ui.activities.BaseActivity;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/2/3.
 * <p>
 * 处理回调监听
 */
public abstract class ServerListener<T> {


    //数据类型
    private Type cla;
    //回调
    private ActivityTaskCallback<T> callback;
    //网络状态
    protected boolean isNetwork;
    //网络数据交换
    private MessageModel message;

    public ServerListener() {
    }

    //构造方法传值
    public ServerListener(Type cla, ActivityTaskCallback<T> callback) {
        this.cla = cla;
        this.callback = callback;
    }

    //设置网络是否可用
    public void setNetwork(boolean network) {
        isNetwork = network;
    }

    public void dowork(){
        if (isNetwork) {
            callback.setNetWork(true);
            if ("00000000".equals(message.getResultCode())) {
                if (message.getJsonData()==null) {
                    success(null);
                    return;
                }
                T obj = JSON.parseObject(message.getJsonData(), cla);
                success(obj);
                handleDB(obj);
            } else if ("90000007".equals(message.getResultCode()) // 支付时余额不足
                    ) {
                T obj = JSON.parseObject(message.getJsonData(), cla);
                success(obj);
                handleDB(obj);
            } else if ("00009999".equals(message.getResultCode())) {
                doServerErr();
            } else {

                fail(message.getResultCode(), message.getJsonData());
            }
        } else {
            callback.setNetWork(false);
            T obj = queryCacheData();
            if (obj == null) {
                fail("9998", "网络连接失败，请检查网络设置");
            } else {
                success(obj);
            }
        }
    };
    public void success(T t) {
        callback.dismissProgress();
        callback.success(t);
    }

    public void fail(String code, String description) {
        callback.dismissProgress();
        callback.fail(code, description);
    }
    public void doServerErr() {
        T obj = queryCacheData();
        if (obj == null) {
            fail(message.getResultCode(), message.getResultName());
        } else {
            isNetwork = false;
            callback.setNetWork(false);
            success(obj);
        }
    }
    protected void handleDB(T t) {
    }

    protected T queryCacheData() {
        return null;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
