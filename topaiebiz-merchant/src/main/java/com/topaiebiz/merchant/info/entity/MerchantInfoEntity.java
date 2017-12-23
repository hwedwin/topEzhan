package com.topaiebiz.merchant.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家信息表实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月27日 下午1:36:24
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_info")
public class MerchantInfoEntity extends BaseBizEntity<Long> {

	/** 版本序列号 */
	private static final long serialVersionUID = 6531030767549130962L;

	/** 公司名称 */
	private String name;

	/** 连锁店、直营店等暂定 */
	private Integer merchantType;

	/** 上级商户 */
	private Long parentMerchant;

	/** 入驻状态。0填写资料 1申请中，2审核通过 3 审核不通过 4类目不通过  5类目通过 6待付款 7付款已提交 8已完成*/
	private Integer state;

	/** 商家联系人姓名 */
	private String contactName;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/** 联系人手机号 */
	private String contactTele;

	/** 店铺的积分。和后期奖惩有关系 */
	private Long integral;

	/** 商家等级积分 */
	private Long gradeIntegral;

	/** 商家等级 */
	private Long merchantGradeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Long getParentMerchant() {
		return parentMerchant;
	}

	public void setParentMerchant(Long parentMerchant) {
		this.parentMerchant = parentMerchant;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTele() {
		return contactTele;
	}

	public void setContactTele(String contactTele) {
		this.contactTele = contactTele;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Long getGradeIntegral() {
		return gradeIntegral;
	}

	public void setGradeIntegral(Long gradeIntegral) {
		this.gradeIntegral = gradeIntegral;
	}

	public Long getMerchantGradeId() {
		return merchantGradeId;
	}

	public void setMerchantGradeId(Long merchantGradeId) {
		this.merchantGradeId = merchantGradeId;
	}

}
