package com.example.administrator.myyinxiang.model;

import java.io.Serializable;

/**
 * Created by Xl on 2016/11/4.
 */
public class VersionModel implements Serializable {
    private static final long serialVersionUID = 7334407891678095225L;

    /**
     * 设备类型 IOS--1 android--2 Windows phone--3
     */
    private String type;

    /**
     * 应用类型  极致汇-1
     */
    private String typ1;

    /**
     * 版本状态
     */
    private String sts;

    /**
     * 版本名称
     */
    private String name;

    /**
     * 版本号
     */
    private String code;

    /**
     * 版本地址
     */
    private String adr;

    /**
     * 发布时间
     */
    private String enbTim;

    /**
     * 描述信息
     */
    private String info;

    /**
     * 上一版本号
     */
    private String code1;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTyp1() {
        return typ1;
    }

    public void setTyp1(String typ1) {
        this.typ1 = typ1;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getEnbTim() {
        return enbTim;
    }

    public void setEnbTim(String enbTim) {
        this.enbTim = enbTim;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }
}
