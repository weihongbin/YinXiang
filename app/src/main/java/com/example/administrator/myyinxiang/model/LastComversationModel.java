package com.example.administrator.myyinxiang.model;

/**
 * Created by Administrator on 2017/2/4.
 */
public class LastComversationModel {


    public String head;

    public String name;

    public String lastMeassage;

    public  String time;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMeassage() {
        return lastMeassage;
    }

    public void setLastMeassage(String lastMeassage) {
        this.lastMeassage = lastMeassage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LastComversationModel{" +
                "head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", lastMeassage='" + lastMeassage + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
