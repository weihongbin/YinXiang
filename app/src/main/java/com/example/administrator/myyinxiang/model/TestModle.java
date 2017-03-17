package com.example.administrator.myyinxiang.model;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.example.administrator.myyinxiang.model.TestModle.java
 * @author: 魏红彬
 * @date: 2017-03-08 11:26
 */
public class TestModle {
    private static final String TAG = "TestModle";

    private String content;

    private int ids;



    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "TestModle{" +
                "content='" + content + '\'' +
                ", ids=" + ids +
                '}';
    }
}