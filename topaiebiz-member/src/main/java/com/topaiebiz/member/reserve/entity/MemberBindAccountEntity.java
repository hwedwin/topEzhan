package com.topaiebiz.member.reserve.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 会员绑定信息表，第三方登录信息。
 * 
 * 
 * Author Scott
 * 
 * Date 2017年8月23日 下午7:50:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_mem_member_bind_account")
public class MemberBindAccountEntity extends BaseBizEntity<Long> {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -768318710074028575L;

	/*** 会员编号。 */
	private Long memberId;

	/*** 登录类型（1 微信，2 QQ)。 */
	private Integer accountType;

	/*** 登录账户。 */
	private String platformAccount;

	/*** 登录名。 */
	private String platformName;

	/*** 授权登录密码。*/
	private String password;

	/*** 登录头像。*/
	private String platformIcon;

	/*** 登录验证手机号。*/
	private String telephone;

	/*** 绑定时间。*/
	private Date bindingTime;
	
	/*** 绑定IP */
	private String bindingIp;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getPlatformAccount() {
		return platformAccount;
	}

	public void setPlatformAccount(String platformAccount) {
		this.platformAccount = platformAccount;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlatformIcon() {
		return platformIcon;
	}

	public void setPlatformIcon(String platformIcon) {
		this.platformIcon = platformIcon;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}

	public String getBindingIp() {
		return bindingIp;
	}

	public void setBindingIp(String bindingIp) {
		this.bindingIp = bindingIp;
	}
	

}
