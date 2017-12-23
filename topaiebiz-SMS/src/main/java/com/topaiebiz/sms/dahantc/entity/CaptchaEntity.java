package com.topaiebiz.sms.dahantc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description： 验证码实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月6日 下午3:11:08
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_captcha")
public class CaptchaEntity extends BaseEntity<Long> {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -3345105167993565920L;

	/** '手机号。' */
	private String telephone;

	/** 验证码。 */
	private String captcha;

	/** 失效时间。 */
	private Date lapseTime;

	/** 最后修改时间。取值为系统的当前时间。 */
	private Date lastModifiedTime;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Date getLapseTime() {
		return lapseTime;
	}

	public void setLapseTime(Date lapseTime) {
		this.lapseTime = lapseTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}
