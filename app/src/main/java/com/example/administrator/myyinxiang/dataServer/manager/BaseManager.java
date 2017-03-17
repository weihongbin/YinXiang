package com.example.administrator.myyinxiang.dataServer.manager;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.dataServer.ManagerEngine;
import com.example.administrator.myyinxiang.dataServer.HttpClient;
import com.example.administrator.myyinxiang.dataServer.ServerListener;
import com.example.administrator.myyinxiang.model.MediaModel;
import com.example.administrator.myyinxiang.model.MessageModel;
import com.example.administrator.myyinxiang.utils.NetWorkUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/3.
 */
public class BaseManager {
    //交易服务
    protected String transService = "";
    protected Context context;
    //获取网络客户端
    protected HttpClient okHttpClient;

    public BaseManager() {
    }

    public BaseManager(Context context) {
        this.context = context;
        okHttpClient= ManagerEngine.getInstance().getOkHttpClient();
    }

    public BaseManager( Context context,String transService) {
        this(context);//注意
        this.transService = transService;
    }

    /**
     * 请求数据
     *
     * @param transMethod
     * @param request
     * @param listener
     */
    protected void send2server(String transMethod, Object request, ServerListener<?> listener) {
        send2server(transMethod, request, null, listener);
    }

    private void send2server(String transMethod, Object request, List<String> files, ServerListener<?> listener) {
            if(NetWorkUtils.isConnected(context)){
                 listener.setNetwork(true);
                MessageModel message = ManagerEngine.getInstance().getMessage();
                message.setTransService(transService);
                message.setTransMethod(transMethod);
                if (request != null && !TextUtils.isEmpty(request.toString())) {
                    message.setJsonData(JSON.toJSONString(request));
                }
                // 判断是否带文件
                if (files != null) {
                    Map<String, String> params = getHeadValue(message);
                    List<File> listFile = new ArrayList<File>();
                    File file = null;
                    for (String path : files) {
                        file = new File(path);
                        listFile.add(file);
                    }

                    //TODO 文件上传
                    okHttpClient.execPost(params, listFile, listener);
                } else {
                    Map<String, String> params = getHeadValue(message);
                    okHttpClient.execPost(params, listener);
                }

            }else{
                //无网络
                listener.setNetwork(false);
                listener.dowork();
            }
    }
    /**
     * 获取媒体路径
     *
     * @param mediaList
     * @return
     */
    protected List<String> getFiles(List<MediaModel> mediaList) {
        List<String> files = new ArrayList<String>();
        if (mediaList != null) {
            for (MediaModel media : mediaList) {
                files.add(media.getMediaPath());
            }
        }
        return files;
    }

    /**
     * 设置 头
     *
     * @param message
     */
    private Map<String, String> getHeadValue(MessageModel message) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("serviceId", message.getTransService());
        params.put("token", context.getSharedPreferences(context.getString(R.string.user_token),
                Context.MODE_PRIVATE).getString("token", ""));
        params.put("body", JSON.toJSONString(message.getJsonData()));
        return params;
    }

}
