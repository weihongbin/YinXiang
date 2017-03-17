package com.example.administrator.myyinxiang;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.example.administrator.myyinxiang.model.UserModel;
import com.squareup.leakcanary.LeakCanary;


/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.base.BaseApplication.java
 * @author: 魏红彬
 * @date: 2017-01-04 11:24
 */
public class BaseApplication extends Application{
    private static final String TAG = "BaseApplication";
    private static BaseApplication baseApplication;
    UserModel baseUser;
    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
        baseApplication = this;
        DemoHelper.getInstance().init(baseApplication);
    }

    public static Context getAppContext() {
        return baseApplication;
    }
    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    /**
     * 从本地文件中获得用户对象
     */
    public UserModel getUser() {
        if (baseUser == null) {
            baseUser = new UserModel();
            SharedPreferences sharedPreferences = getSharedPreferences(
                    getString(R.string.user_token), Context.MODE_PRIVATE);
            baseUser.setUserId(sharedPreferences.getString("userId", ""));
            baseUser.setAccount(sharedPreferences.getString("account", ""));
            baseUser.setNickname(sharedPreferences.getString("nickname", ""));
            baseUser.setFacepath(sharedPreferences.getString("facePath", ""));
            baseUser.setSex(sharedPreferences.getInt("sex", 0));
        }
        return baseUser;
    }

    /**
     * 将用户信息存入本地文件
     */
    public void storeUser(UserModel user) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.user_token),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();// 获取编辑器
        baseUser = new UserModel();
        editor.putString("userId", user.getUserId());
        baseUser.setUserId(user.getUserId());
        editor.putString("account", user.getAccount());
        baseUser.setAccount(user.getAccount());
        editor.putString("nickname", user.getNickname());
        baseUser.setNickname(user.getNickname());
        editor.putString("facePath", user.getFacepath());
        baseUser.setFacepath(user.getFacepath());

        editor.commit();// 提交修改
    }

    /**
     * 不保存自动登录将用户保存在缓存中
     */
    public void setUser(UserModel user) {
        this.baseUser = user;
    }

    /**
     * 注销用户信息
     */
    public void cancelUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.user_token),
                Context.MODE_APPEND);// 追加覆盖
        SharedPreferences.Editor editor = sharedPreferences.edit();// 获取编辑器
        editor.putString("userId", "");//用户编码
        editor.putString("account", "");//用户帐户
        editor.putString("nickname", "");// 昵称
        editor.putString("facePath", "");// 图片路径
        editor.putString("myCode", "");// 邀请码
        baseUser = new UserModel();
        //网络请求 退出登录
//        ManagerEngine.getSingleton().logout();
        editor.commit();// 提交修改
    }


    /**
     * 判断用户是否已经登录
     *
     * @return
     */
    public boolean isHadLogin() {
        UserModel view = getUser();
        if (view.getUserId() != null && !"".equals(view.getUserId().trim())) {
            return true;
        } else {
            return false;
        }
    }
}