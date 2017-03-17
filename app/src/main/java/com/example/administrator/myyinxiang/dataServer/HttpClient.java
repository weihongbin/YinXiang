package com.example.administrator.myyinxiang.dataServer;

import android.content.Context;
import android.os.Build;
import android.os.Handler;


import com.alibaba.fastjson.JSON;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.dataServer.manager.BaseManager;
import com.example.administrator.myyinxiang.model.MessageModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/3
 * 网络请求.
 */
public class HttpClient extends BaseManager {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    //mdiatype 这个需要和服务端保持一致
    private static final String TAG = HttpClient.class.getSimpleName();
    private static final String BASE_URL = "http://xxx.com/openapi";//请求接口根地址
    private static volatile HttpClient mInstance;
    public static final int TYPE_GET = 0;//get请求
    public static final int TYPE_POST_JSON = 1;// post请求参数为json
    public static final int TYPE_POST_FORM = 2;//post请求参数为表单
    private OkHttpClient mOkHttpClient;//okHttpClient 实例
    private Handler okHttpHandler;//全局处理子线程和M主线程通信
    private  String requestUrl;
    public HttpClient(Context context) {
        requestUrl = context.getString(R.string.requestUrl);
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        okHttpHandler = new Handler(context.getMainLooper());
    }

    public void execPost(Map<String, String> params, List<File> files, final ServerListener<?> listener) {

        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for(File file:files){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", file.getName(), body);
        }
        if (params != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : params.entrySet()) {
                requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url("请求地址").post(requestBody.build()).tag(context).build();
        // readTimeout("请求超时时间" , 时间单位);
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MessageModel view = new MessageModel();
                view.setResultCode("09999");
                view.setResultName(e.getMessage());
                listener.setMessage(view);
                listener.dowork();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        JSONObject j = new JSONObject(response.body().string());
                        MessageModel view = JSON.parseObject(j.getString("message"), MessageModel.class);
                        listener.setMessage(view);
                        listener.dowork();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    MessageModel view = new MessageModel();
                    view.setResultCode("09999");
                    view.setResultName(response.message());
                    listener.setMessage(view);
                    listener.dowork();
                }
            }
        });



    }

    public void execPost(Map<String, String> params, final ServerListener<?> listener) {
        try {
            StringBuilder tempParams = new StringBuilder();
            int pot = 0;
            for (String key : params.keySet()) {
                if (pot > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(params.get(key), "utf-8")));
                pot++;
            }
            String param=tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,param);
            final Request request = addHeaders().url(requestUrl).post(body).build();
//            request.body();
            final Call call = mOkHttpClient.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    MessageModel view = new MessageModel();
                    view.setResultCode("09999");
                    view.setResultName(e.getMessage());
                    listener.setMessage(view);
                    listener.dowork();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                       if(response.isSuccessful()){
                           try {
                               JSONObject j = new JSONObject(response.body().string());
                               MessageModel view = JSON.parseObject(j.getString("message"), MessageModel.class);
                               listener.setMessage(view);
                               listener.dowork();
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }else{
                           MessageModel view = new MessageModel();
                           view.setResultCode("09999");
                           view.setResultName(response.message());
                           listener.setMessage(view);
                           listener.dowork();
                       }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** * 统一为请求添加头信息 * @return */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "1.0.0");
        return builder;
    }
    protected void post_file(final String url, final Map<String, Object> map, File file) {


    }
}
