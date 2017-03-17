package com.example.administrator.myyinxiang;

import android.content.Context;

import com.example.administrator.myyinxiang.basedb.ChatMessgeDao;
import com.example.administrator.myyinxiang.model.MeassageModel;

import java.util.List;



public class DemoModel {
    protected Context context = null;

    public DemoModel(Context ctx) {
        context = ctx;
    }

    /**
     * save MeassageModel
     *
     * @param meassageModelList
     */
    public void addChatMsgList(List<MeassageModel> meassageModelList) {
        ChatMessgeDao chatMessgeDao = new ChatMessgeDao(context);
        chatMessgeDao.saveChatMsgList(meassageModelList);
    }

    /**
     * 自己from.to目标对话
     *
     * @param from
     * @param to
     * @return
     */
    public List<MeassageModel> getMessagesList(String from, String to) {
        ChatMessgeDao chatMessgeDao = new ChatMessgeDao(context);
        return chatMessgeDao.getMessagesList(from, to);
    }

    /**
     * remove navigation_selector.xml ChatMsg
     *
     * @param from
     */
    public void removeChatMsg(String from) {
        ChatMessgeDao chatMessgeDao = new ChatMessgeDao(context);
        chatMessgeDao.deleteChatMsg(from);
    }

    /**
     * save ChatMsg
     *
     * @param msg
     */
    public void addChatMsg(MeassageModel msg) {
        ChatMessgeDao chatMessgeDao = new ChatMessgeDao(context);
        chatMessgeDao.saveChatMsg(msg);
    }
    /**
     * save ChatMsg
     *
     * @param msg
     */
    public void updateChatMsg(MeassageModel msg) {
        ChatMessgeDao chatMessgeDao = new ChatMessgeDao(context);
        chatMessgeDao.updateChatMsg(msg);
    }
}
