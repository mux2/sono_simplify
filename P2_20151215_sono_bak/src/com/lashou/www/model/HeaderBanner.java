package com.lashou.www.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeaderBanner {

    @SerializedName("header_banner")
    @Expose
    private List<HeaderBanner_> headerBanner = new ArrayList<HeaderBanner_>();
    @SerializedName("center_banner")
    @Expose
    private List<CenterBanner> centerBanner = new ArrayList<CenterBanner>();
    @SerializedName("down_slip_banner")
    @Expose
    private List<Object> downSlipBanner = new ArrayList<Object>();
    @SerializedName("down_three_banner")
    @Expose
    private List<Object> downThreeBanner = new ArrayList<Object>();
    @SerializedName("up_three_banner")
    @Expose
    private List<UpThreeBanner> upThreeBanner = new ArrayList<UpThreeBanner>();
    @SerializedName("down_two_banner")
    @Expose
    private List<Object> downTwoBanner = new ArrayList<Object>();
    @SerializedName("up_slip_banner")
    @Expose
    private List<Object> upSlipBanner = new ArrayList<Object>();
    @SerializedName("quit_banner")
    @Expose
    private List<Object> quitBanner = new ArrayList<Object>();
    @SerializedName("up_two_banner")
    @Expose
    private List<UpTwoBanner> upTwoBanner = new ArrayList<UpTwoBanner>();

    /**
     * 
     * @return
     *     The headerBanner
     */
    public List<HeaderBanner_> getHeaderBanner() {
        return headerBanner;
    }

    /**
     * 
     * @param headerBanner
     *     The header_banner
     */
    public void setHeaderBanner(List<HeaderBanner_> headerBanner) {
        this.headerBanner = headerBanner;
    }

    /**
     * 
     * @return
     *     The centerBanner
     */
    public List<CenterBanner> getCenterBanner() {
        return centerBanner;
    }

    /**
     * 
     * @param centerBanner
     *     The center_banner
     */
    public void setCenterBanner(List<CenterBanner> centerBanner) {
        this.centerBanner = centerBanner;
    }

    /**
     * 
     * @return
     *     The downSlipBanner
     */
    public List<Object> getDownSlipBanner() {
        return downSlipBanner;
    }

    /**
     * 
     * @param downSlipBanner
     *     The down_slip_banner
     */
    public void setDownSlipBanner(List<Object> downSlipBanner) {
        this.downSlipBanner = downSlipBanner;
    }

    /**
     * 
     * @return
     *     The downThreeBanner
     */
    public List<Object> getDownThreeBanner() {
        return downThreeBanner;
    }

    /**
     * 
     * @param downThreeBanner
     *     The down_three_banner
     */
    public void setDownThreeBanner(List<Object> downThreeBanner) {
        this.downThreeBanner = downThreeBanner;
    }

    /**
     * 
     * @return
     *     The upThreeBanner
     */
    public List<UpThreeBanner> getUpThreeBanner() {
        return upThreeBanner;
    }

    /**
     * 
     * @param upThreeBanner
     *     The up_three_banner
     */
    public void setUpThreeBanner(List<UpThreeBanner> upThreeBanner) {
        this.upThreeBanner = upThreeBanner;
    }

    /**
     * 
     * @return
     *     The downTwoBanner
     */
    public List<Object> getDownTwoBanner() {
        return downTwoBanner;
    }

    /**
     * 
     * @param downTwoBanner
     *     The down_two_banner
     */
    public void setDownTwoBanner(List<Object> downTwoBanner) {
        this.downTwoBanner = downTwoBanner;
    }

    /**
     * 
     * @return
     *     The upSlipBanner
     */
    public List<Object> getUpSlipBanner() {
        return upSlipBanner;
    }

    /**
     * 
     * @param upSlipBanner
     *     The up_slip_banner
     */
    public void setUpSlipBanner(List<Object> upSlipBanner) {
        this.upSlipBanner = upSlipBanner;
    }

    /**
     * 
     * @return
     *     The quitBanner
     */
    public List<Object> getQuitBanner() {
        return quitBanner;
    }

    /**
     * 
     * @param quitBanner
     *     The quit_banner
     */
    public void setQuitBanner(List<Object> quitBanner) {
        this.quitBanner = quitBanner;
    }

    /**
     * 
     * @return
     *     The upTwoBanner
     */
    public List<UpTwoBanner> getUpTwoBanner() {
        return upTwoBanner;
    }

    /**
     * 
     * @param upTwoBanner
     *     The up_two_banner
     */
    public void setUpTwoBanner(List<UpTwoBanner> upTwoBanner) {
        this.upTwoBanner = upTwoBanner;
    }

}
