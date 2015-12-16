package com.lashou.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apiweibo {

    @Expose
    private Long id;
    @Expose
    private String idstr;
    @SerializedName("class")
    @Expose
    private Integer _class;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @Expose
    private String name;
    @Expose
    private String province;
    @Expose
    private String city;
    @Expose
    private String location;
    @Expose
    private String description;
    @Expose
    private String url;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @Expose
    private String domain;
    @Expose
    private String weihao;
    @Expose
    private String gender;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("friends_count")
    @Expose
    private Integer friendsCount;
    @SerializedName("pagefriends_count")
    @Expose
    private Integer pagefriendsCount;
    @SerializedName("statuses_count")
    @Expose
    private Integer statusesCount;
    @SerializedName("favourites_count")
    @Expose
    private Integer favouritesCount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Expose
    private Boolean following;
    @SerializedName("allow_all_act_msg")
    @Expose
    private Boolean allowAllActMsg;
    @SerializedName("geo_enabled")
    @Expose
    private Boolean geoEnabled;
    @Expose
    private Boolean verified;
    @SerializedName("verified_type")
    @Expose
    private Integer verifiedType;
    @Expose
    private String remark;
    @Expose
    private Integer ptype;
    @SerializedName("allow_all_comment")
    @Expose
    private Boolean allowAllComment;
    @SerializedName("avatar_large")
    @Expose
    private String avatarLarge;
    @SerializedName("avatar_hd")
    @Expose
    private String avatarHd;
    @SerializedName("verified_reason")
    @Expose
    private String verifiedReason;
    @SerializedName("verified_trade")
    @Expose
    private String verifiedTrade;
    @SerializedName("verified_reason_url")
    @Expose
    private String verifiedReasonUrl;
    @SerializedName("verified_source")
    @Expose
    private String verifiedSource;
    @SerializedName("verified_source_url")
    @Expose
    private String verifiedSourceUrl;
    @SerializedName("follow_me")
    @Expose
    private Boolean followMe;
    @SerializedName("online_status")
    @Expose
    private Integer onlineStatus;
    @SerializedName("bi_followers_count")
    @Expose
    private Integer biFollowersCount;
    @Expose
    private String lang;
    @Expose
    private Integer star;
    @Expose
    private Integer mbtype;
    @Expose
    private Integer mbrank;
    @SerializedName("block_word")
    @Expose
    private Integer blockWord;
    @SerializedName("block_app")
    @Expose
    private Integer blockApp;
    @SerializedName("credit_score")
    @Expose
    private Integer creditScore;
    @SerializedName("user_ability")
    @Expose
    private Integer userAbility;
    @Expose
    private Integer urank;

    /**
     * 
     * @return
     *     The id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The idstr
     */
    public String getIdstr() {
        return idstr;
    }

    /**
     * 
     * @param idstr
     *     The idstr
     */
    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    /**
     * 
     * @return
     *     The _class
     */
    public Integer getClass_() {
        return _class;
    }

    /**
     * 
     * @param _class
     *     The class
     */
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    /**
     * 
     * @return
     *     The screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * 
     * @param screenName
     *     The screen_name
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 
     * @param province
     *     The province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The profileImageUrl
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    /**
     * 
     * @param profileImageUrl
     *     The profile_image_url
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * 
     * @return
     *     The profileUrl
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * 
     * @param profileUrl
     *     The profile_url
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * 
     * @return
     *     The domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * @param domain
     *     The domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 
     * @return
     *     The weihao
     */
    public String getWeihao() {
        return weihao;
    }

    /**
     * 
     * @param weihao
     *     The weihao
     */
    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return
     *     The followersCount
     */
    public Integer getFollowersCount() {
        return followersCount;
    }

    /**
     * 
     * @param followersCount
     *     The followers_count
     */
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    /**
     * 
     * @return
     *     The friendsCount
     */
    public Integer getFriendsCount() {
        return friendsCount;
    }

    /**
     * 
     * @param friendsCount
     *     The friends_count
     */
    public void setFriendsCount(Integer friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * 
     * @return
     *     The pagefriendsCount
     */
    public Integer getPagefriendsCount() {
        return pagefriendsCount;
    }

    /**
     * 
     * @param pagefriendsCount
     *     The pagefriends_count
     */
    public void setPagefriendsCount(Integer pagefriendsCount) {
        this.pagefriendsCount = pagefriendsCount;
    }

    /**
     * 
     * @return
     *     The statusesCount
     */
    public Integer getStatusesCount() {
        return statusesCount;
    }

    /**
     * 
     * @param statusesCount
     *     The statuses_count
     */
    public void setStatusesCount(Integer statusesCount) {
        this.statusesCount = statusesCount;
    }

    /**
     * 
     * @return
     *     The favouritesCount
     */
    public Integer getFavouritesCount() {
        return favouritesCount;
    }

    /**
     * 
     * @param favouritesCount
     *     The favourites_count
     */
    public void setFavouritesCount(Integer favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The following
     */
    public Boolean getFollowing() {
        return following;
    }

    /**
     * 
     * @param following
     *     The following
     */
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    /**
     * 
     * @return
     *     The allowAllActMsg
     */
    public Boolean getAllowAllActMsg() {
        return allowAllActMsg;
    }

    /**
     * 
     * @param allowAllActMsg
     *     The allow_all_act_msg
     */
    public void setAllowAllActMsg(Boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    /**
     * 
     * @return
     *     The geoEnabled
     */
    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    /**
     * 
     * @param geoEnabled
     *     The geo_enabled
     */
    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    /**
     * 
     * @return
     *     The verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * 
     * @param verified
     *     The verified
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * 
     * @return
     *     The verifiedType
     */
    public Integer getVerifiedType() {
        return verifiedType;
    }

    /**
     * 
     * @param verifiedType
     *     The verified_type
     */
    public void setVerifiedType(Integer verifiedType) {
        this.verifiedType = verifiedType;
    }

    /**
     * 
     * @return
     *     The remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark
     *     The remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * @return
     *     The ptype
     */
    public Integer getPtype() {
        return ptype;
    }

    /**
     * 
     * @param ptype
     *     The ptype
     */
    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    /**
     * 
     * @return
     *     The allowAllComment
     */
    public Boolean getAllowAllComment() {
        return allowAllComment;
    }

    /**
     * 
     * @param allowAllComment
     *     The allow_all_comment
     */
    public void setAllowAllComment(Boolean allowAllComment) {
        this.allowAllComment = allowAllComment;
    }

    /**
     * 
     * @return
     *     The avatarLarge
     */
    public String getAvatarLarge() {
        return avatarLarge;
    }

    /**
     * 
     * @param avatarLarge
     *     The avatar_large
     */
    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    /**
     * 
     * @return
     *     The avatarHd
     */
    public String getAvatarHd() {
        return avatarHd;
    }

    /**
     * 
     * @param avatarHd
     *     The avatar_hd
     */
    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    /**
     * 
     * @return
     *     The verifiedReason
     */
    public String getVerifiedReason() {
        return verifiedReason;
    }

    /**
     * 
     * @param verifiedReason
     *     The verified_reason
     */
    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    /**
     * 
     * @return
     *     The verifiedTrade
     */
    public String getVerifiedTrade() {
        return verifiedTrade;
    }

    /**
     * 
     * @param verifiedTrade
     *     The verified_trade
     */
    public void setVerifiedTrade(String verifiedTrade) {
        this.verifiedTrade = verifiedTrade;
    }

    /**
     * 
     * @return
     *     The verifiedReasonUrl
     */
    public String getVerifiedReasonUrl() {
        return verifiedReasonUrl;
    }

    /**
     * 
     * @param verifiedReasonUrl
     *     The verified_reason_url
     */
    public void setVerifiedReasonUrl(String verifiedReasonUrl) {
        this.verifiedReasonUrl = verifiedReasonUrl;
    }

    /**
     * 
     * @return
     *     The verifiedSource
     */
    public String getVerifiedSource() {
        return verifiedSource;
    }

    /**
     * 
     * @param verifiedSource
     *     The verified_source
     */
    public void setVerifiedSource(String verifiedSource) {
        this.verifiedSource = verifiedSource;
    }

    /**
     * 
     * @return
     *     The verifiedSourceUrl
     */
    public String getVerifiedSourceUrl() {
        return verifiedSourceUrl;
    }

    /**
     * 
     * @param verifiedSourceUrl
     *     The verified_source_url
     */
    public void setVerifiedSourceUrl(String verifiedSourceUrl) {
        this.verifiedSourceUrl = verifiedSourceUrl;
    }

    /**
     * 
     * @return
     *     The followMe
     */
    public Boolean getFollowMe() {
        return followMe;
    }

    /**
     * 
     * @param followMe
     *     The follow_me
     */
    public void setFollowMe(Boolean followMe) {
        this.followMe = followMe;
    }

    /**
     * 
     * @return
     *     The onlineStatus
     */
    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * 
     * @param onlineStatus
     *     The online_status
     */
    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    /**
     * 
     * @return
     *     The biFollowersCount
     */
    public Integer getBiFollowersCount() {
        return biFollowersCount;
    }

    /**
     * 
     * @param biFollowersCount
     *     The bi_followers_count
     */
    public void setBiFollowersCount(Integer biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    /**
     * 
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The star
     */
    public Integer getStar() {
        return star;
    }

    /**
     * 
     * @param star
     *     The star
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * 
     * @return
     *     The mbtype
     */
    public Integer getMbtype() {
        return mbtype;
    }

    /**
     * 
     * @param mbtype
     *     The mbtype
     */
    public void setMbtype(Integer mbtype) {
        this.mbtype = mbtype;
    }

    /**
     * 
     * @return
     *     The mbrank
     */
    public Integer getMbrank() {
        return mbrank;
    }

    /**
     * 
     * @param mbrank
     *     The mbrank
     */
    public void setMbrank(Integer mbrank) {
        this.mbrank = mbrank;
    }

    /**
     * 
     * @return
     *     The blockWord
     */
    public Integer getBlockWord() {
        return blockWord;
    }

    /**
     * 
     * @param blockWord
     *     The block_word
     */
    public void setBlockWord(Integer blockWord) {
        this.blockWord = blockWord;
    }

    /**
     * 
     * @return
     *     The blockApp
     */
    public Integer getBlockApp() {
        return blockApp;
    }

    /**
     * 
     * @param blockApp
     *     The block_app
     */
    public void setBlockApp(Integer blockApp) {
        this.blockApp = blockApp;
    }

    /**
     * 
     * @return
     *     The creditScore
     */
    public Integer getCreditScore() {
        return creditScore;
    }

    /**
     * 
     * @param creditScore
     *     The credit_score
     */
    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * 
     * @return
     *     The userAbility
     */
    public Integer getUserAbility() {
        return userAbility;
    }

    /**
     * 
     * @param userAbility
     *     The user_ability
     */
    public void setUserAbility(Integer userAbility) {
        this.userAbility = userAbility;
    }

    /**
     * 
     * @return
     *     The urank
     */
    public Integer getUrank() {
        return urank;
    }

    /**
     * 
     * @param urank
     *     The urank
     */
    public void setUrank(Integer urank) {
        this.urank = urank;
    }

}
