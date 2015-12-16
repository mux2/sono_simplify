package com.lashou.www.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goodlist {

    @SerializedName("l_content")
    @Expose
    private String lContent;
    @SerializedName("time_refund")
    @Expose
    private Integer timeRefund;
    @SerializedName("new_cat")
    @Expose
    private String newCat;
    @SerializedName("left_time")
    @Expose
    private Integer leftTime;
    @Expose
    private String lng;
    @SerializedName("l_price")
    @Expose
    private String lPrice;
    @SerializedName("l_text")
    @Expose
    private String lText;
    @Expose
    private String product;
    @SerializedName("short_title")
    @Expose
    private String shortTitle;
    @Expose
    private String distance;
    @SerializedName("is_new")
    @Expose
    private String isNew;
    @Expose
    private String title;
    @Expose
    private String price;
    @SerializedName("goods_id")
    @Expose
    private String goodsId;
    @SerializedName("goods_type")
    @Expose
    private String goodsType;
    @Expose
    private String value;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("is_sell_up")
    @Expose
    private String isSellUp;
    @SerializedName("seven_refund")
    @Expose
    private String sevenRefund;
    @SerializedName("l_display")
    @Expose
    private Integer lDisplay;
    @Expose
    private Integer bought;
    @Expose
    private String lat;
    @SerializedName("is_appointment")
    @Expose
    private Integer isAppointment;

    /**
     * 
     * @return
     *     The lContent
     */
    public String getLContent() {
        return lContent;
    }

    /**
     * 
     * @param lContent
     *     The l_content
     */
    public void setLContent(String lContent) {
        this.lContent = lContent;
    }

    /**
     * 
     * @return
     *     The timeRefund
     */
    public Integer getTimeRefund() {
        return timeRefund;
    }

    /**
     * 
     * @param timeRefund
     *     The time_refund
     */
    public void setTimeRefund(Integer timeRefund) {
        this.timeRefund = timeRefund;
    }

    /**
     * 
     * @return
     *     The newCat
     */
    public String getNewCat() {
        return newCat;
    }

    /**
     * 
     * @param newCat
     *     The new_cat
     */
    public void setNewCat(String newCat) {
        this.newCat = newCat;
    }

    /**
     * 
     * @return
     *     The leftTime
     */
    public Integer getLeftTime() {
        return leftTime;
    }

    /**
     * 
     * @param leftTime
     *     The left_time
     */
    public void setLeftTime(Integer leftTime) {
        this.leftTime = leftTime;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * 
     * @return
     *     The lPrice
     */
    public String getLPrice() {
        return lPrice;
    }

    /**
     * 
     * @param lPrice
     *     The l_price
     */
    public void setLPrice(String lPrice) {
        this.lPrice = lPrice;
    }

    /**
     * 
     * @return
     *     The lText
     */
    public String getLText() {
        return lText;
    }

    /**
     * 
     * @param lText
     *     The l_text
     */
    public void setLText(String lText) {
        this.lText = lText;
    }

    /**
     * 
     * @return
     *     The product
     */
    public String getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *     The product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 
     * @return
     *     The shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * 
     * @param shortTitle
     *     The short_title
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The isNew
     */
    public String getIsNew() {
        return isNew;
    }

    /**
     * 
     * @param isNew
     *     The is_new
     */
    public void setIsNew(String isNew) {
        this.isNew = isNew;
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
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 
     * @param goodsId
     *     The goods_id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 
     * @return
     *     The goodsType
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 
     * @param goodsType
     *     The goods_type
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The isSellUp
     */
    public String getIsSellUp() {
        return isSellUp;
    }

    /**
     * 
     * @param isSellUp
     *     The is_sell_up
     */
    public void setIsSellUp(String isSellUp) {
        this.isSellUp = isSellUp;
    }

    /**
     * 
     * @return
     *     The sevenRefund
     */
    public String getSevenRefund() {
        return sevenRefund;
    }

    /**
     * 
     * @param sevenRefund
     *     The seven_refund
     */
    public void setSevenRefund(String sevenRefund) {
        this.sevenRefund = sevenRefund;
    }

    /**
     * 
     * @return
     *     The lDisplay
     */
    public Integer getLDisplay() {
        return lDisplay;
    }

    /**
     * 
     * @param lDisplay
     *     The l_display
     */
    public void setLDisplay(Integer lDisplay) {
        this.lDisplay = lDisplay;
    }

    /**
     * 
     * @return
     *     The bought
     */
    public Integer getBought() {
        return bought;
    }

    /**
     * 
     * @param bought
     *     The bought
     */
    public void setBought(Integer bought) {
        this.bought = bought;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The isAppointment
     */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /**
     * 
     * @param isAppointment
     *     The is_appointment
     */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

}
