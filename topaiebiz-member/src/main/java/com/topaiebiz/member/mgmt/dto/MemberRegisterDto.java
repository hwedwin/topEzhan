package com.topaiebiz.member.mgmt.dto;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * Description：会员商家注册信息表  
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月13日 上午午10:11:24 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class MemberRegisterDto {
	
	
	private String token; 
	
	/*** 会员信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/*** 显示用户名。*/
	private String userName;
	
	/*** 密码。*/
	private String password;
	
	/*** 会员手机号。*/
	@Length(min = 11,max = 11)
	private String telephone;
	
	/*** 支付密码*/
	private String payPassword;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
