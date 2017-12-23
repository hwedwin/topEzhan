package com.topaiebiz.transaction.cart.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 运费模板详情 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月18日 下午4:08:18 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_store_freight_detail")
public class StoreFreightDetailEntity extends BaseBizEntity<Long> {

	/** 序列化版本号*/
	private static final long serialVersionUID = 7096182873835270346L;
	
	/** 关联的运费模板ID*/
	private Long freightId;
	
	/** 配送方式*/
	private Short type;
	
	/** 配送区域集合*/
	private String districtIdList;
	
	/** 首次价格*/
	private Double firstPrice;
	
	/** 首次件数*/
	private Double firstNum;
	
	/** 续件价格*/
	private Double addPrice;
	
	/** 续件件数*/
	private Double addNum;
	
	/** 是否为默认运费*/
	private Boolean isDefault;

	public StoreFreightDetailEntity() {
		super();
	}

	public Long getFreightId() {
		return freightId;
	}

	public void setFreightId(Long freightId) {
		this.freightId = freightId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getDistrictIdList() {
		return districtIdList;
	}

	public void setDistrictIdList(String districtIdList) {
		this.districtIdList = districtIdList;
	}

	public Double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(Double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public Double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(Double addPrice) {
		this.addPrice = addPrice;
	}

	public Double getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Double firstNum) {
		this.firstNum = firstNum;
	}

	public Double getAddNum() {
		return addNum;
	}

	public void setAddNum(Double addNum) {
		this.addNum = addNum;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
