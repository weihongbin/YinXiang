package com.example.administrator.myyinxiang.model;

import java.io.Serializable;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.chatwei.bean.MeassageModel.java
 * @author: 魏红彬
 * @date: 2017-02-08 10:20
 */
public class MeassageModel implements Serializable {
    private static final String TAG = "MeassageModel";

    private String id;//消息id

    private String userId;//自己

    private String toUserId;//ip &port 对方

    private String toIp;

    private int toPort;

    private boolean isSelf;//是否是自己0,1（0为假，非零为真）

    private String currentTime;//消息的当前时间

    private String headUrl;//头像地址

    private int msgType;//文字0，表情1，图片2，动图3，位置4，语音5，视频6等文件形式

    private String msgContent;//消息的内容，语音/视频则为文件的名称

    private String fileSecret;//文件签名

    private String localUrl;//用来保存图片，语音，视频等静态的本地地址

    private int timeLong;//语音或是视频时间长度

    private boolean isRead;//已读未读0,1

    private boolean isReach;//是否到达0,1

    private int type;//消息类型，心跳0，单聊1，群聊2等

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getFileSecret() {
        return fileSecret;
    }

    public void setFileSecret(String fileSecret) {
        this.fileSecret = fileSecret;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public int getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(int timeLong) {
        this.timeLong = timeLong;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MeassageModel{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", toIp='" + toIp + '\'' +
                ", toPort=" + toPort +
                ", isSelf=" + isSelf +
                ", currentTime='" + currentTime + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", msgType=" + msgType +
                ", msgContent='" + msgContent + '\'' +
                ", fileSecret='" + fileSecret + '\'' +
                ", localUrl='" + localUrl + '\'' +
                ", timeLong=" + timeLong +
                ", isRead=" + isRead +
                ", isReach=" + isReach +
                ", type=" + type +
                '}';
    }
}