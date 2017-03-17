package com.example.administrator.myyinxiang.dataServer.manager;

import android.content.Context;

import com.alibaba.fastjson.TypeReference;
import com.example.administrator.myyinxiang.dataServer.ActivityTaskCallback;
import com.example.administrator.myyinxiang.dataServer.ServerListener;
import com.example.administrator.myyinxiang.dataServer.ServerVariable;
import com.example.administrator.myyinxiang.model.VersionModel;


import java.util.List;

/**
 * Created by Czj_xl on 2016/8/22 10:51
 * E-mal ：xulei@t-tron.com
 */
public class SettingManager extends UserManager {
    /**
     * 构造函数
     *
     * @param context
     * @param transService
     */
    public SettingManager(Context context, String transService) {
        super(context, transService);
    }
    /**
     * 获取版本信息
     *
     * @param callback
     */
    public void getAppNewVersion(VersionModel versionModel, final ActivityTaskCallback<VersionModel> callback) {
        this.send2server(ServerVariable.SettingMethod.getAppNewVersion, versionModel, new ServerListener<VersionModel>(VersionModel.class, callback) {
        });
    }
}
