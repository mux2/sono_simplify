package com.lashou.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpTwoBanner {

    @Expose
    private String content;
    @SerializedName("advert_type")
    @Expose
    private Integer advertType;
    @SerializedName("advert_id")
    @Expose
    private String advertId;
    @Expose
    private String title;
    @SerializedName("update_time")
    @Expose
    private String updateTime;
    @SerializedName("img_big")
    @Expose
    private String imgBig;
    @SerializedName("advert_name")
    @Expose
    private String advertName;
    @SerializedName("img_small")
    @Expose
    private String imgSmall;
    @SerializedName("img_mid")
    @Expose
    private String imgMid;
    @SerializedName("advert_position")
    @Expose
    private String advertPosition;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The advertType
     */
    public Integer getAdvertType() {
        return advertType;
    }

    /**
     * 
     * @param advertType
     *     The advert_type
     */
    public void setAdvertType(Integer advertType) {
        this.advertType = advertType;
    }

    /**
     * 
     * @return
     *     The advertId
     */
    public String getAdvertId() {
        return advertId;
    }

    /**
     * 
     * @param advertId
     *     The advert_id
     */
    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime
     *     The update_time
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return
     *     The imgBig
     */
    public String getImgBig() {
        return imgBig;
    }

    /**
     * 
     * @param imgBig
     *     The img_big
     */
    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    /**
     * 
     * @return
     *     The advertName
     */
    public String getAdvertName() {
        return advertName;
    }

    /**
     * 
     * @param advertName
     *     The advert_name
     */
    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    /**
     * 
     * @return
     *     The imgSmall
     */
    public String getImgSmall() {
        return imgSmall;
    }

    /**
     * 
     * @param imgSmall
     *     The img_small
     */
    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    /**
     * 
     * @return
     *     The imgMid
     */
    public String getImgMid() {
        return imgMid;
    }

    /**
     * 
     * @param imgMid
     *     The img_mid
     */
    public void setImgMid(String imgMid) {
        this.imgMid = imgMid;
    }

    /**
     * 
     * @return
     *     The advertPosition
     */
    public String getAdvertPosition() {
        return advertPosition;
    }

    /**
     * 
     * @param advertPosition
     *     The advert_position
     */
    public void setAdvertPosition(String advertPosition) {
        this.advertPosition = advertPosition;
    }

}
