package com.topaiebiz.system.moble.security.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description 令牌token实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月17日 下午2:31:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName(value = "t_app_token")
public class TokenEntity extends BaseEntity<Long> {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2508741975264418861L;

	/** 手机号。 */
	private String telephone;

	/** appToken令牌。 */
	private String appToken;

	/** 登录手机类型，备用。 */
	private String type;

	/** 是否允许多用户同时登录，0为不允许。 */
	private Integer moreLogin;

	/** 最后修改时间。 */
	private Date lastModifiedTime;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMoreLogin() {
		return moreLogin;
	}

	public void setMoreLogin(Integer moreLogin) {
		this.moreLogin = moreLogin;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}
