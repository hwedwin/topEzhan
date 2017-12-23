package com.topaiebiz.merchant.enter.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;


/**
 * Description: 运费模板实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午10:35:30
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_freight_templete")
public class FreightTempleteEntity extends BaseBizEntity<Long>{

	/** 版本序列化 */
	private static final long serialVersionUID = 8729370106228313972L;

	/** 店铺ID */
	private Long storeId;

	/** 运费名称 */
	private String freightName;

	/** 是否默认模板 */
	private Integer isDefault;

	/** 计价方式。1 件数 2体积 3重量 */
	private Integer pricing;

	/** 是否仅配送特定地区。（1 为是 ，0为否） */
	private Integer onlyThis;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getFreightName() {
		return freightName;
	}

	public void setFreightName(String freightName) {
		this.freightName = freightName;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getPricing() {
		return pricing;
	}

	public void setPricing(Integer pricing) {
		this.pricing = pricing;
	}

	public Integer getOnlyThis() {
		return onlyThis;
	}

	public void setOnlyThis(Integer onlyThis) {
		this.onlyThis = onlyThis;
	}

}
