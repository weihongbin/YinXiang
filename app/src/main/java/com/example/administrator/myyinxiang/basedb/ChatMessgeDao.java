package com.example.administrator.myyinxiang.basedb;

import android.content.Context;

import com.example.administrator.myyinxiang.model.MeassageModel;

import java.util.List;



/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.chatwei.baseDb.ChatMessgeDao.java
 * @author: 魏红彬
 * @date: 2017-02-08 16:18
 */
public class ChatMessgeDao {
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USER_ID = "userId";
    public static final String COLUMN_NAME_TO_USER_ID = "toUserId";
    public static final String COLUMN_NAME_TO_IP = "toIp";
    public static final String COLUMN_NAME_TO_PORT = "toPort";
    public static final String COLUMN_NAME_IS_SELF = "isSelf";
    public static final String COLUMN_NAME_CURRENTTIME = "currentTime";
    public static final String COLUMN_NAME_HEAD_URL = "headUrl";
    public static final String COLUMN_NAME_MSG_TYPE = "msgType";
    public static final String COLUMN_NAME_MSG_CONTENT = "msgContent";
    public static final String COLUMN_NAME_FILE_SECRET= "fileSecret";
    public static final String COLUMN_NAME_LOCAL_URL= "localUrl";
    public static final String COLUMN_NAME_TIME_LONG = "timeLong";
    public static final String COLUMN_NAME_IS_READ = "isRead";
    public static final String COLUMN_NAME_IS_REACH = "isReach";
    public static final String COLUMN_NAME_TYPE= "type";

    public ChatMessgeDao(Context context) {
    }

    /**
     * save MeassageModel
     *
     * @param meassageModelList
     */
    public void saveChatMsgList(List<MeassageModel> meassageModelList) {
        DBManager.getInstance().saveChatMsgList(meassageModelList);
    }

    /**
     * 自己from.to目标对话
     * @param from
     * @param to
     * @return
     */
    public List<MeassageModel> getMessagesList(String from, String to) {

        return DBManager.getInstance().getMessagesList(from,to);
    }

    /**
     * delete navigation_selector.xml contact
     * @param from
     */
    public void deleteChatMsg(String from){
        DBManager.getInstance().deleteChatMsg(from);
    }

    /**
     * save navigation_selector.xml contact
     * @param msg
     */
    public void saveChatMsg(MeassageModel msg){
        DBManager.getInstance().saveChatMsg(msg);
    }

    public void updateChatMsg(MeassageModel msg){
        DBManager.getInstance().updateChatMsg(msg);
    }
}