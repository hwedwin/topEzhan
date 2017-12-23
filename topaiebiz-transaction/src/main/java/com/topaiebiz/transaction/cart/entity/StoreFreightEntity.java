package com.topaiebiz.transaction.cart.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 店铺运费模板  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月18日 下午3:44:20 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_store_freight")
public class StoreFreightEntity extends BaseBizEntity<Long> {

	/** 序列化版本号*/
	private static final long serialVersionUID = 5470945497704528938L;
	
	/** 店铺id*/
	private Long storeId;
	
	/** 运费模板名称*/
	private String freightName;
	
	/** 是否为默认运费*/
	private Boolean isDefault;
	
	/** 计价方式。1 件数 2体积 3重量*/
	private Short pricing;
	
	/** 是否仅配送特定地区。（1 为是 ，0为否）*/
	private Boolean onlyThis;

	public StoreFreightEntity() {
		super();
	}

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

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Short getPricing() {
		return pricing;
	}

	public void setPricing(Short pricing) {
		this.pricing = pricing;
	}

	public Boolean getOnlyThis() {
		return onlyThis;
	}

	public void setOnlyThis(Boolean onlyThis) {
		this.onlyThis = onlyThis;
	}

}
