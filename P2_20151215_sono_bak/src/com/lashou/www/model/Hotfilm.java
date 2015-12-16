package com.lashou.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotfilm {

    @Expose
    private String filmName;
    @Expose
    private String imageUrl;
    @Expose
    private Integer showCinemasCount;
    @Expose
    private Integer status;
    @Expose
    private String dimensional;
    @Expose
    private String posterUrl;
    @SerializedName("short_brief")
    @Expose
    private String shortBrief;
    @Expose
    private Integer showSchedulesCount;
    @Expose
    private String duration;
    @Expose
    private String releaseDate;
    @Expose
    private String starCode;
    @SerializedName("have_schedule")
    @Expose
    private Integer haveSchedule;
    @Expose
    private String grade;
    @Expose
    private String imax;
    @Expose
    private String brief;
    @Expose
    private String filmId;

    /**
     * 
     * @return
     *     The filmName
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     * 
     * @param filmName
     *     The filmName
     */
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The showCinemasCount
     */
    public Integer getShowCinemasCount() {
        return showCinemasCount;
    }

    /**
     * 
     * @param showCinemasCount
     *     The showCinemasCount
     */
    public void setShowCinemasCount(Integer showCinemasCount) {
        this.showCinemasCount = showCinemasCount;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The dimensional
     */
    public String getDimensional() {
        return dimensional;
    }

    /**
     * 
     * @param dimensional
     *     The dimensional
     */
    public void setDimensional(String dimensional) {
        this.dimensional = dimensional;
    }

    /**
     * 
     * @return
     *     The posterUrl
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * 
     * @param posterUrl
     *     The posterUrl
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    /**
     * 
     * @return
     *     The shortBrief
     */
    public String getShortBrief() {
        return shortBrief;
    }

    /**
     * 
     * @param shortBrief
     *     The short_brief
     */
    public void setShortBrief(String shortBrief) {
        this.shortBrief = shortBrief;
    }

    /**
     * 
     * @return
     *     The showSchedulesCount
     */
    public Integer getShowSchedulesCount() {
        return showSchedulesCount;
    }

    /**
     * 
     * @param showSchedulesCount
     *     The showSchedulesCount
     */
    public void setShowSchedulesCount(Integer showSchedulesCount) {
        this.showSchedulesCount = showSchedulesCount;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The starCode
     */
    public String getStarCode() {
        return starCode;
    }

    /**
     * 
     * @param starCode
     *     The starCode
     */
    public void setStarCode(String starCode) {
        this.starCode = starCode;
    }

    /**
     * 
     * @return
     *     The haveSchedule
     */
    public Integer getHaveSchedule() {
        return haveSchedule;
    }

    /**
     * 
     * @param haveSchedule
     *     The have_schedule
     */
    public void setHaveSchedule(Integer haveSchedule) {
        this.haveSchedule = haveSchedule;
    }

    /**
     * 
     * @return
     *     The grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 
     * @param grade
     *     The grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 
     * @return
     *     The imax
     */
    public String getImax() {
        return imax;
    }

    /**
     * 
     * @param imax
     *     The imax
     */
    public void setImax(String imax) {
        this.imax = imax;
    }

    /**
     * 
     * @return
     *     The brief
     */
    public String getBrief() {
        return brief;
    }

    /**
     * 
     * @param brief
     *     The brief
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * 
     * @return
     *     The filmId
     */
    public String getFilmId() {
        return filmId;
    }

    /**
     * 
     * @param filmId
     *     The filmId
     */
    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

}
