package com.example.administrator.myyinxiang.utils;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;


import com.example.administrator.myyinxiang.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

/**
 * compile 'com.squareup.okhttp3:logging-interceptor:3.1.2'
 * compile 'com.squareup.okhttp3:okhttp:3.2.0'
 * compile 'com.squareup.okio:okio:1.7.0'
 * Created by CXY on 2016/10/19.
 */
public class OK3Click {
    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private static final String OKHTTP_CACHE = "OkHttpCache";
    public static int connectTimeOut = 40;        //连接超时时间，单位秒
    private static Cache cache = null;

    private static Handler handler = null;


    public static void init(Context context) {
        handler = new Handler(context.getMainLooper());
        File baseDir = context.getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, OKHTTP_CACHE);
            cache = new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
        }
    }

    private static OK3Click instant = null;
    private static OkHttpClient client = null;

    public static OK3Click getInstant() {
        if (instant == null) {
            instant = new OK3Click();
        }
        return instant;
    }

    private OK3Click() {
        if (client == null) {
            if (cache == null) {
                throw new RuntimeException("请初始化缓存路径");
            }

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                    new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.e("OkHttp", "url: " + message);
                        }
                    }
            );
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            client = new OkHttpClient
                    .Builder()
                    .connectTimeout(connectTimeOut, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(interceptor)
                    .cookieJar(new CookieJar() {
                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url, cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url);
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    })
                    .build();
        }
    }

    private static abstract class BaseRequestParam {
        abstract RequestBody getParam();

        abstract String getUrl();
    }

    public static class RequestParam_xml extends BaseRequestParam {
        private String url;
        private RequestBody requestBody;

        public RequestParam_xml(String url) {
            this.url = url;
        }

        public void addParam(String xml) {
            requestBody = RequestBody.create(MediaType.parse("application/xml;charset=UTF-8"), xml);
        }

        @Override
        RequestBody getParam() {
            return requestBody;
        }

        @Override
        String getUrl() {
            return url;
        }
    }

    public static class RequestParam<T> extends BaseRequestParam {
        private String url;
        MultipartBody.Builder builder = null;

        public RequestParam(String url) {
            this.url = url;
        }

        public void addParam(String key, T value) {
            if (builder == null) {
                builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
            }
            if (value instanceof File) {
                File file = (File) value;
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
            } else {
                builder.addFormDataPart(key, String.valueOf(value));
            }
        }

        @Override
        RequestBody getParam() {
            return builder.build();
        }

        @Override
        String getUrl() {
            return url;
        }
    }


    public void post(BaseRequestParam requestParam, final Call call) {
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    call.onFailure("请检查网络连接");

                }
            });
            return;

        }
        final Request request = new Request.Builder()
                .url(requestParam.getUrl())
                .post(requestParam.getParam())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call c, IOException e) {
                if (e != null) {
                    final String s = e.toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            call.onFailure(s);
                        }
                    });
                }
            }

            @Override
            public void onResponse(okhttp3.Call c, final Response response) throws IOException {

                final String body = response.body().string();
                if (!TextUtils.isEmpty(body))
                    Log.e("OkHttp", body);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.code() == 200) {
                            call.onSuccess(body);
                        } else {
                            call.onFailure(response.code() + "\n" + body);
                        }
                    }
                });
            }
        });

    }

    public abstract static class Call {
        /**
         * 获取响应成功回调
         *
         * @param body 返回响应体
         */
        public abstract void onSuccess(String body);

        /**
         * 获取响应失败
         */
        public abstract void onFailure(String msg);
    }

    /**
     * 文件
     *
     * @param requestParam
     * @param map
     * @param call
     * @param file
     */
    public void post(BaseRequestParam requestParam, final Map<String, Object> map, final Call call, File file) {
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    call.onFailure("请检查网络连接");

                }
            });
            return;
        }
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        final Request request = new Request.Builder()
                .url(requestParam.getUrl())
                .post(requestBody.build())
                .tag("whb")
                .build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call c, IOException e) {
                if (e != null) {
                    final String s = e.toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            call.onFailure(s);
                        }
                    });
                }
            }

            @Override
            public void onResponse(final okhttp3.Call c, final Response response) throws IOException {
                final String body = response.body().string();
                if (!TextUtils.isEmpty(body))
                    Log.e("OkHttp", body);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.code() == 200) {
                            call.onSuccess(body);
                        } else {
                            call.onFailure(response.code() + "\n" + body);
                        }
                    }
                });
            }
        });


    }

    //    LoggingInterceptor logInterceptor
//    class LogginInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            HttpLoggingInterceptor.Level level = this.level;
//
//            Request request = chain.request();
//            chain.
//
//
//            boolean logBody = level == HttpLoggingInterceptor.Level.BODY;
//            boolean logHeaders = logBody || level == HttpLoggingInterceptor.Level.HEADERS;
//
//            RequestBody requestBody = request.body();
//            boolean hasRequestBody = requestBody != null;
//
//            Connection connection = chain.connection();
//            Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
//            String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
//            if (!logHeaders && hasRequestBody) {
//                requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
//            }
//            logger.log(requestStartMessage);
//
//            if (logHeaders) {
//                if (hasRequestBody) {
//                    // Request body headers are only present when installed as navigation_selector.xml network interceptor. Force
//                    // them to be included (when available) so there values are known.
//                    if (requestBody.contentType() != null) {
//                        logger.log("Content-Type: " + requestBody.contentType());
//                    }
//                    if (requestBody.contentLength() != -1) {
//                        logger.log("Content-Length: " + requestBody.contentLength());
//                    }
//                }
//
//                Headers headers = request.headers();
//                for (int i = 0, count = headers.size(); i < count; i++) {
//                    String name = headers.name(i);
//                    // Skip headers from the request body as they are explicitly logged above.
//                    if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
//                        logger.log(name + ": " + headers.value(i));
//                    }
//                }
//
//                if (!logBody || !hasRequestBody) {
//                    logger.log("--> END " + request.method());
//                } else if (bodyEncoded(request.headers())) {
//                    logger.log("--> END " + request.method() + " (encoded body omitted)");
//                } else {
//                    Buffer buffer = new Buffer();
//                    requestBody.writeTo(buffer);
//
//                    Charset charset = UTF8;
//                    MediaType contentType = requestBody.contentType();
//                    if (contentType != null) {
//                        charset = contentType.charset(UTF8);
//                    }
//
//                    logger.log("");
//                    logger.log(buffer.readString(charset));
//
//                    logger.log("--> END " + request.method()
//                            + " (" + requestBody.contentLength() + "-byte body)");
//                }
//            }
//
//            long startNs = System.nanoTime();
//            Response response = chain.proceed(request);
//            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
//
//            ResponseBody responseBody = response.body();
//            long contentLength = responseBody.contentLength();
//            String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
//            logger.log("<-- " + response.code() + ' ' + response.message() + ' '
//                    + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
//                    + bodySize + " body" : "") + ')');
//
//            if (logHeaders) {
//                Headers headers = response.headers();
//                for (int i = 0, count = headers.size(); i < count; i++) {
//                    logger.log(headers.name(i) + ": " + headers.value(i));
//                }
//
//                if (!logBody || !HttpEngine.hasBody(response)) {
//                    logger.log("<-- END HTTP");
//                } else if (bodyEncoded(response.headers())) {
//                    logger.log("<-- END HTTP (encoded body omitted)");
//                } else {
//                    BufferedSource source = responseBody.source();
//                    source.request(Long.MAX_VALUE); // Buffer the entire body.
//                    Buffer buffer = source.buffer();
//
//                    Charset charset = UTF8;
//                    MediaType contentType = responseBody.contentType();
//                    if (contentType != null) {
//                        try {
//                            charset = contentType.charset(UTF8);
//                        } catch (UnsupportedCharsetException e) {
//                            logger.log("");
//                            logger.log("Couldn't decode the response body; charset is likely malformed.");
//                            logger.log("<-- END HTTP");
//
//                            return response;
//                        }
//                    }
//
//                    if (contentLength != 0) {
//                        logger.log("");
//                        logger.log(buffer.clone().readString(charset));
//                    }
//
//                    logger.log("<-- END HTTP (" + buffer.size() + "-byte body)");
//                }
//            }
//
//            return response;
//        }
//    }
}
