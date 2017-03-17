package com.example.administrator.myyinxiang.model;

/**
 * 交换对象
 */
public class MessageModel {
    private String sessionId;// 会话标识
    private String userId;// 用户id
    private String channel;// 访问渠道
    private String versionName;// 应用版本名称
    private String versionCode;// 应用版本号
    private String phoneModel; // 手机型号
    private String imei; // 移动设备码
    private String macAddress;// mac地址
    private String transService;// 交易服务
    private String transMethod;// 服务方法
    private String size;// 每页条数
    private String resultCode;// 返回码
    private String resultName;// 返回信息
    private String jsonData; // 数据集合


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getTransService() {
        return transService;
    }

    public void setTransService(String transService) {
        this.transService = transService;
    }

    public String getTransMethod() {
        return transMethod;
    }

    public void setTransMethod(String transMethod) {
        this.transMethod = transMethod;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "sessionId='" + sessionId + '\'' +
                ", userId='" + userId + '\'' +
                ", channel='" + channel + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", phoneModel='" + phoneModel + '\'' +
                ", imei='" + imei + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", transService='" + transService + '\'' +
                ", transMethod='" + transMethod + '\'' +
                ", size='" + size + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultName='" + resultName + '\'' +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }
}
