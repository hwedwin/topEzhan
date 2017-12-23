package com.topaiebiz.goods.sku.dto;


/**
 * Description 商品销售情况  
 * 
 * Author Hedda 
 *    
 * Date 2017年11月1日 下午8:24:31 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class ItemSellDto {

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	/** 商品名称(标题显示的名称)。 */
	private String name;
	
	/** 付款人数。*/
	private Integer  paymentPeople;
	
	/** 销售数量。*/
	private Integer saleNumber;
	
	/** 销售金额。*/
	private Double salesAmount;
	
	/** 时间。*/
	private String createdTimes;
	
	/** 时间查询。*/
	private Integer days;
	
	/** 店铺id。*/
	private Long storeId;
	
	/** 数量。*/
	private Integer count;
	
	/** 第一级类目id。*/
	private Long categoryId;
	
	/** 第一级类目的名称。*/
	private String categoryName;
	
	/** 第三级类目id。*/
	private Long categoryIdt;

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

	public Integer getPaymentPeople() {
		return paymentPeople;
	}

	public void setPaymentPeople(Integer paymentPeople) {
		this.paymentPeople = paymentPeople;
	}

	public Integer getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getCreatedTimes() {
		return createdTimes;
	}

	public void setCreatedTimes(String createdTimes) {
		this.createdTimes = createdTimes;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryIdt() {
		return categoryIdt;
	}

	public void setCategoryIdt(Long categoryIdt) {
		this.categoryIdt = categoryIdt;
	}
	
}
