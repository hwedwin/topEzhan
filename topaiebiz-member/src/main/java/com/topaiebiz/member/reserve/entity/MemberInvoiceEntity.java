package com.topaiebiz.member.reserve.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description: 会员增值税发票 
 * 
 * 
 * Author Scott
 *    
 * Date 2017年9月20日 下午7:27:45 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_invoice")
public class MemberInvoiceEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7721460285400592860L;
	
	/** 会员编号。*/
	private Long memberId;
	
	/** 单位名称。*/
	private String organizationName;
	
	/** 纳税人识别号。*/
	private String taxpayerNo;
	
	/** 注册地址。*/
	private String address;
	
	/** 注册电话。*/
	private String telephone;
	
	/** 开户银行。*/
	private String openBank;
	
	/** 银行账户。*/
	private String account;
	
	/** 状态。1 可以使用 0 不可以使用  。由平台审核置入*/
	private Integer state;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTaxpayerNo() {
		return taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
