package com.example.administrator.myyinxiang.dataServer.manager;

import android.content.Context;

import com.alibaba.fastjson.TypeReference;
import com.example.administrator.myyinxiang.dataServer.ActivityTaskCallback;
import com.example.administrator.myyinxiang.dataServer.ServerListener;
import com.example.administrator.myyinxiang.dataServer.ServerVariable;
import com.example.administrator.myyinxiang.model.FriendsModel;
import com.example.administrator.myyinxiang.model.PersonalInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/3.
 */
public class FriendsManager  extends BaseManager{
    public FriendsManager(Context context, String transService) {
        super(context, transService);
    }
    /**
     * 通过输入的信息找用户
     *
     * @param friendsModel
     * @param callback
     */
    public void getUserByInfo(FriendsModel friendsModel, final ActivityTaskCallback<List<PersonalInfo>> callback) {
        this.send2server(ServerVariable.FriendsMethod.getUserByInfo, friendsModel, new ServerListener<List<PersonalInfo>>(new TypeReference<List<PersonalInfo>>(){}.getType(), callback) {
        });
    }

    /**
     * 通过id删除用户
     *
     * @param friendsModel
     * @param callback
     */
    public void deleteFriendByUserId(FriendsModel friendsModel, final ActivityTaskCallback<PersonalInfo> callback) {
        this.send2server(ServerVariable.FriendsMethod.deleteFriendByUserId, friendsModel, new ServerListener<PersonalInfo>(PersonalInfo.class, callback) {
        });
    }
    /**
     * 通过用户id和组的id添加好友
     *
     * @param friendsModel
     * @param callback
     */
    public void addFriendByUserIdAndGroupId(FriendsModel friendsModel, final ActivityTaskCallback<PersonalInfo> callback) {
        this.send2server(ServerVariable.FriendsMethod.addFriendByUserIdAndGroupId, friendsModel, new ServerListener<PersonalInfo>(PersonalInfo.class, callback) {
        });
    }

    /**
     * 通过组的id更改好友的信息
     * @param friendsModel
     * @param callback
     */
    public void updateFriendByGroupIdAndFriendName(FriendsModel friendsModel, final ActivityTaskCallback<PersonalInfo> callback) {
        this.send2server(ServerVariable.FriendsMethod.updateFriendByGroupIdAndFriendName, friendsModel, new ServerListener<PersonalInfo>(PersonalInfo.class, callback) {
        });
    }

    /**
     * 通过输入的信息查好友
     * @param friendsModel
     * @param callback
     */
    public void getFriendByInfo(FriendsModel friendsModel, final ActivityTaskCallback<PersonalInfo> callback) {
        this.send2server(ServerVariable.FriendsMethod.getFriendByInfo, friendsModel, new ServerListener<PersonalInfo>(PersonalInfo.class, callback) {
        });
    }

    /**
     * 通过组的id查好友
     * @param friendsModel
     * @param callback
     */
    public void getFriendByGroupId(FriendsModel friendsModel, final ActivityTaskCallback<PersonalInfo> callback) {
        this.send2server(ServerVariable.FriendsMethod.getFriendByGroupId, friendsModel, new ServerListener<PersonalInfo>(PersonalInfo.class, callback) {
        });
    }
}
