package com.example.administrator.myyinxiang.dataServer.manager;

import android.content.Context;

import com.alibaba.fastjson.TypeReference;
import com.example.administrator.myyinxiang.dataServer.ActivityTaskCallback;
import com.example.administrator.myyinxiang.dataServer.ServerListener;
import com.example.administrator.myyinxiang.dataServer.ServerVariable;
import com.example.administrator.myyinxiang.model.GroupModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/3.
 */
public class GroupManager extends BaseManager {
    public GroupManager(Context context, String transService) {
        super(context, transService);
    }
    /**
     * 通过用户的id找组
     *
     * @param groupModel
     * @param callback
     */
    public void getGroupByUserId(GroupModel groupModel, final ActivityTaskCallback<List<GroupModel>> callback) {
        this.send2server(ServerVariable.GroupMethod.getGroupByUserId, groupModel, new ServerListener<List<GroupModel>>(new TypeReference<List<GroupModel>>(){}.getType(), callback) {
        });
    }

    /**
     * 通过组名和userid添加组
     *
     * @param groupModel
     * @param callback
     */
    public void addGroupByGroupNameAndUserId(GroupModel groupModel, final ActivityTaskCallback<GroupModel> callback) {
        this.send2server(ServerVariable.GroupMethod.addGroupByGroupNameAndUserId, groupModel, new ServerListener<GroupModel>(GroupModel.class, callback) {
        });
    }

    /**
     * 通过组号(id)和组名更新组
     *
     * @param groupModel
     * @param callback
     */
    public void updateGroupByGroupIdAndGroupName(GroupModel groupModel, final ActivityTaskCallback<GroupModel> callback) {
        this.send2server(ServerVariable.GroupMethod.updateGroupByGroupIdAndGroupName, groupModel, new ServerListener<GroupModel>(GroupModel.class, callback) {
        });
    }


    /**
     * 通过组号(id)删除组
     *
     * @param groupModel
     * @param callback
     */
    public void deleteGroupByGroupId(GroupModel groupModel, final ActivityTaskCallback<GroupModel> callback) {
        this.send2server(ServerVariable.GroupMethod.deleteGroupByGroupId, groupModel, new ServerListener<GroupModel>(GroupModel.class, callback) {
        });
    }
}
