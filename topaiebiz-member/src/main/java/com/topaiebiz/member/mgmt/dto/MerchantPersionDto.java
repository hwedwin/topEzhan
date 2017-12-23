package com.topaiebiz.member.mgmt.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Description 商家人员DTO
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月30日 下午2:47:07
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantPersionDto {

	/** 手机号 */
	@NotNull(message = "{validation.merchantStorePersion.telephone}")
	@Length(min = 11, max = 11)
	private String telephone;

	/** 验证码 */
	@NotNull(message = "{validation.merchantStorePersion.verificationCode}")
	private String captcha;

	/** 密码 */
	@NotNull(message = "{validation.merchantStorePersion.password}")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
