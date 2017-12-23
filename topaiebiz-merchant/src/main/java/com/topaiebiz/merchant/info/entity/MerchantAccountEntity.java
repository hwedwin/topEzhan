package com.topaiebiz.merchant.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家账户信息实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月2日 下午7:50:56
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_account")
public class MerchantAccountEntity extends BaseBizEntity<Long> {

	/** 版本序列化 */
	private static final long serialVersionUID = 4595016165430217561L;

	/** 所属商家 */
	private Long merchantId;

	/** 银行开户名 */
	private String accountName;

	/** 公司银行帐号 */
	private String account;

	/** 开户银行支行名称 */
	private String bankName;

	/** 支行银联号 */
	private String bankNum;

	/** 开户行所在区域 */
	private Long districtId;

	/** 是否为结算账号 */
	private Integer isSettle;

	/** 开户银行许可证电子版 */
	private String electronicImage;

	/** 预留电话 */
	private String telephone;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Integer getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}

	public String getElectronicImage() {
		return electronicImage;
	}

	public void setElectronicImage(String electronicImage) {
		this.electronicImage = electronicImage;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
