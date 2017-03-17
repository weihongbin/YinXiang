package com.example.administrator.myyinxiang.model;

/**
 * Created by Administrator on 2017/2/3.
 */
public class UserModel {
    /**
     * 用户id
     */
    private  String userId;
    /**
     * 用户性别
     */
    private  int sex;//0.1
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像路径
     */
    private String facepath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacepath() {
        return facepath;
    }

    public void setFacepath(String facepath) {
        this.facepath = facepath;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", facepath='" + facepath + '\'' +
                '}';
    }
}
