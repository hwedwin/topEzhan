package com.topaiebiz.merchant.enter.dto;

/**
 * Description: 运费模板与运费模板详情dto类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午10:58:44
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class FreightTempleteDto {

	/** 全局主键id */
	private Long id;

	/** 店铺ID */
	private Long storeId;

	/** 运费名称 */
	private String freightName;

	/** 计价方式。1 件数 2体积 3重量 */
	private Integer pricing;

	/** 是否仅配送特定地区。（1 为是 ，0为否） */
	private Integer onlyThis;

	/** 关联的运费模板ID */
	private Long freightId;

	/** 配送方式 */
	private Integer type;

	/** 配送区域集合 */
	private String districtIdList;

	/** 首次价格 */
	private double firstPrice;

	/** 首次件数 */
	private double firstNum;

	/** 续件价格 */
	private double addPrice;

	/** 续件件数 */
	private double addNum;

	/** 是否为默认运费 */
	private Integer isDefault;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getFreightId() {
		return freightId;
	}

	public void setFreightId(Long freightId) {
		this.freightId = freightId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDistrictIdList() {
		return districtIdList;
	}

	public void setDistrictIdList(String districtIdList) {
		this.districtIdList = districtIdList;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(double firstNum) {
		this.firstNum = firstNum;
	}

	public double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(double addPrice) {
		this.addPrice = addPrice;
	}

	public double getAddNum() {
		return addNum;
	}

	public void setAddNum(double addNum) {
		this.addNum = addNum;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
