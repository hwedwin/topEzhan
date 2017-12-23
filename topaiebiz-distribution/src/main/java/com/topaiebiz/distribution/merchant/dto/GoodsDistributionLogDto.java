package com.topaiebiz.distribution.merchant.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Description： 商品分销记录dto
 * 
 * Author Harry
 * 
 * Date 2017年10月6日 下午8:19:34
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsDistributionLogDto {

	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;

	/** 所属商家 */
	private Long merchantId;

	/** 所属店铺 */
	private Long storeId;

	/** 店铺等级 */
	private Long storeGrade;

	/** 店铺等级 */
	private String storeGradeId;

	/** 店铺等级名称 */
	private String storeGradeName;

	/** 商品SKU */
	private Long skuId;

	/** 分润比例 */
	private Double ratio;

	/** 分润日期 */
	@NotNull(message = "{validation.goodsDistributionLog.distriDate}")
	private Date distriDate;

	/** 分期日期 */
	private String distriDates;

	/** 销售数量 */
	@NotNull(message = "{validation.goodsDistributionLog.saleNum}")
	private Long saleNum;

	/** 单价 */
	@NotNull(message = "{validation.goodsDistributionLog.price}")
	private Double price;

	/** 总金额 */
	@NotNull(message = "{validation.goodsDistributionLog.totalPrice}")
	private Double totalPrice;

	/** 分润金额 */
	@NotNull(message = "{validation.goodsDistributionLog.distriPrice}")
	private Double distriPrice;

	/** 结算状态 (1为已结算 0为未结算) */
	private Integer settleState;

	/** 店铺名称 */
	private String storeName;

	/** 店铺联系人 */
	private String contactName;

	/** 店铺联系人联系电话 */
	private String contactTele;

	/** 商品名称 */
	private String itemName;

	/** 购买人数 */
	private Integer peopleBuyCount;

	/** 店铺总分润金额 */
	private Double distributionPriceAmount;

	/** 购买商品数 */
	private Integer goodsNum;

	/** 会员名称 */
	private String userName;

	/** 商家名称 */
	private String merchantName;

	/** 分销级别 */
	private String distriLevel;
	
	/**商品分销金额*/
	private Double storedistriPrice;
	

	public Double getStoredistriPrice() {
		return storedistriPrice;
	}

	public void setStoredistriPrice(Double storedistriPrice) {
		this.storedistriPrice = storedistriPrice;
	}

	public String getDistriLevel() {
		return distriLevel;
	}

	public void setDistriLevel(String distriLevel) {
		this.distriLevel = distriLevel;
	}

	public String getDistriDates() {
		return distriDates;
	}

	public void setDistriDates(String distriDates) {
		this.distriDates = distriDates;
	}

	public String getStoreGradeName() {
		return storeGradeName;
	}

	public void setStoreGradeName(String storeGradeName) {
		this.storeGradeName = storeGradeName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Integer getPeopleBuyCount() {
		return peopleBuyCount;
	}

	public void setPeopleBuyCount(Integer peopleBuyCount) {
		this.peopleBuyCount = peopleBuyCount;
	}

	public Double getDistributionPriceAmount() {
		return distributionPriceAmount;
	}

	public void setDistributionPriceAmount(Double distributionPriceAmount) {
		this.distributionPriceAmount = distributionPriceAmount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Date getDistriDate() {
		return distriDate;
	}

	public void setDistriDate(Date distriDate) {
		this.distriDate = distriDate;
	}

	public Long getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDistriPrice() {
		return distriPrice;
	}

	public void setDistriPrice(Double distriPrice) {
		this.distriPrice = distriPrice;
	}

	public Integer getSettleState() {
		return settleState;
	}

	public void setSettleState(Integer settleState) {
		this.settleState = settleState;
	}

	public Long getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(Long storeGrade) {
		this.storeGrade = storeGrade;
	}

	public String getStoreGradeId() {
		return storeGradeId;
	}

	public void setStoreGradeId(String storeGradeId) {
		this.storeGradeId = storeGradeId;
	}

}
