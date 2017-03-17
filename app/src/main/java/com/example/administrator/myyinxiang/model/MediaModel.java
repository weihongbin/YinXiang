package com.example.administrator.myyinxiang.model;

import java.io.Serializable;

/**
 * Created by admin on 2015/12/23.
 */
public class MediaModel implements Serializable {
    /**
     * 媒体类型(images-图片 voice-语音 video-视频)
     */
    private String type;
    /**
     * 原路径
     */
    private String mediaPath;
    /**
     * 视频时长
     */
    private String mediaTimeLength;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 创建地点
     */
    private String createPlace;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaTimeLength() {
        return mediaTimeLength;
    }

    public void setMediaTimeLength(String mediaTimeLength) {
        this.mediaTimeLength = mediaTimeLength;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatePlace() {
        return createPlace;
    }

    public void setCreatePlace(String createPlace) {
        this.createPlace = createPlace;
    }
}
