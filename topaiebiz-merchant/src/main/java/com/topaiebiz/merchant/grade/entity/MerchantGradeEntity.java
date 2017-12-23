package com.topaiebiz.merchant.grade.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家等级管理实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月28日 下午7:40:39
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_grade")
public class MerchantGradeEntity extends BaseBizEntity<Long> {

	/** 序列化版本号 */
	private static final long serialVersionUID = -7539959099085812605L;

	/** 商家等级名称 */
	private String name;

	/** 商家等级的小图标 */
	private String smallIcon;

	/** 商家等级的大图标 */
	private String bigIcon;

	/** 所需积分下限，达到该值就是该等级 */
	private Long integralValue;

	/** 会员等级说明 */
	private String description;

	/** 备注 */
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
