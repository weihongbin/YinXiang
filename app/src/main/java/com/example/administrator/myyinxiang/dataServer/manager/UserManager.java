package com.example.administrator.myyinxiang.dataServer.manager;

import android.content.Context;

import com.example.administrator.myyinxiang.dataServer.ActivityTaskCallback;
import com.example.administrator.myyinxiang.dataServer.ServerListener;
import com.example.administrator.myyinxiang.dataServer.ServerVariable;
import com.example.administrator.myyinxiang.model.UserModel;

/**
 * Created by Administrator on 2017/2/3.
 */
public class UserManager  extends BaseManager{
    /**
     * 构造函数
     *
     * @param context
     * @param transService
     */
    public UserManager(Context context, String transService) {
        super(context, transService);
    }
    /**
     * 登录
     *
     * @param userModel
     * @param callback
     */
    public void userLogin(UserModel userModel, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.userLogin, userModel, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }
    /**
     * 登录（更换手机）
     *
     * @param userModel
     * @param callback
     */
    public void replaceDeciceLogin(UserModel userModel, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.replaceDeciceLogin, userModel, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }

    /**
     * 获取手机验证码
     *
     * @param callback
     */
    public void getIdentifyCode(UserModel userModel, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.getIdentifyCode, userModel, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }


    /**
     * 注册
     *
     * @param userModel
     * @param callback
     */
    public void registerUser(UserModel userModel, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.registerUser, userModel, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @param callback
     */
    public void updateUserInfo(UserModel user, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.updateUserInfo, user, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }
    /**
     * 查询用户信息
     *
     * @param user
     * @param callback
     */
    public void getuserinfo(UserModel user, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.getuserinfo, user,  new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @param callback
     */
    public void reviseUserPassword(UserModel user, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.reviseUserPassword, user, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }
    /**
     * 找回用户密码方法
     *
     * @param user
     * @param callback
     */
    public void resetUserPassword(UserModel user, final ActivityTaskCallback<UserModel> callback) {
        this.send2server(ServerVariable.UserMethod.resetUserPassword, user, new ServerListener<UserModel>(UserModel.class, callback) {
        });
    }

}
