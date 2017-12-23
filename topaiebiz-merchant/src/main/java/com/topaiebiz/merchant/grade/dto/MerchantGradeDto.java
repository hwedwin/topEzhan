package com.topaiebiz.merchant.grade.dto;

import javax.validation.constraints.NotNull;



/**
 * Description: 商家等级管理dto
 * 
 * Author : Anthony
 * 
 * Date :2017年9月28日 下午7:42:58
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantGradeDto {

	/** 商家等级的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 商家等级名称 */
	@NotNull(message = "{validation.merchantGrade.name}")
	private String name;

	/** 商家等级的小图标 */
	@NotNull(message = "{validation.merchantGrade.smallIcon}")
	private String smallIcon;

	/** 商家等级的大图标 */
	@NotNull(message = "{validation.merchantGrade.bigIcon}")
	private String bigIcon;

	/** 所需积分下限，达到该值就是该等级 */
	@NotNull(message = "{validation.merchantGrade.integralValue}")
	private Long integralValue;

	/** 会员等级说明 */
	private String description;

	/** 创建人编号。取值为创建人的全局唯一主键标识符。 */
	private Long creatorId;

	/** 备注 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	public Long getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Long integralValue) {
		this.integralValue = integralValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
