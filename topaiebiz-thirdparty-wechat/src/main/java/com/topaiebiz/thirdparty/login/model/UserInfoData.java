package com.topaiebiz.thirdparty.login.model;

/**
 * Created by Cai on 2017-10-31.
 */
public class UserInfoData {
    
    
    private String openId;//授权用户唯一标识
    private String authToken;//接口调用凭证
    private String authRefreshToken;//用户刷新access_token
    private String scope;//授权作用域
    private Integer expiresIn;//access_token接口调用凭证超时时间，单位（秒）
    private String name;//微信用户昵称
    private String icon;//用户头像
    private String gender;//性别
    private String loginId;//当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthRefreshToken() {
        return authRefreshToken;
    }

    public void setAuthRefreshToken(String authRefreshToken) {
        this.authRefreshToken = authRefreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
