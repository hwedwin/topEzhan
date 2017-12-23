package com.topaiebiz.member.mgmt.dto;


/**
 * 
 * Description 会员信息表
 * 
 * 
 * Author scott
 *    
 * Date 2017年8月23日 下午7:49:54 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class MemberMgmtDto {
	
	private String token; 
	
	/*** 会员信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/*** 昵称。*/
	private String nickName;
	
	
	/*** 性别（1 南  0 女)。*/
	private Integer gender;
	
	/*** 生日。*/
	private String birthday;
	
	/*** 小会员头像。*/
	private String smallIcon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
