package com.topaiebiz.distribution.member.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Description：会员分销记录Dto。
 * 
 * Author Harry
 * 
 * Date 2017年10月7日 下午7:54:25
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberDistributionLogDto {

	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;

	/** 所属店铺ID */
	private Long storeId;

	/** 会员编号 */
	private Long memberId;

	/** 商品SKU */
	private Long skuId;

	/** 分期日期 */
	@NotNull(message = "{validation.MemberDistributionLog.distriDate}")
	private Date distriDate;
	
	/**分期日期*/
	private String distriDates;
	
	/** 分销级别 */
	@NotNull(message = "{validation.MemberDistributionLog.distriLevel}")
	private Integer distriLevel;

	/** 分销比例 */
	@NotNull(message = "{validation.MemberDistributionLog.ratio}")
	private Double ratio;

	/** 下级会员购买人数 */
	private Integer underlingMemberNum;

	/** 购买商品数 */
	private Long goodsNum;

	/** 总金额 */
	private Double totalPrice;

	/** 分销金额 */
	private Double distriPrice;
	
	/** 商品总金额 */
	private Double totalPriceAmount;
	
	/** 会员总分销金额 */
	private Double distriPriceAmount;

	/** 店铺名称 */
	private String storeName;

	/** 会员编号 */
	private String memberCode;

	/** 分销时间开始时间 */
	private Date createdTime;

	/** 商品名称 */
	private String itemName;

	/** 商家Id */
	private Long merchantId;
	
	/**一级分销金额*/
	private Double  primaryDistributionPrice;
	
	/**二级分销金额*/
	private Double levelDistributionPrice;
	
	/**会员姓名*/
	private String memberName;
	
	/**一级分销总数量*/
	private Long primarytotalDistributionNum;
	
	/**二级分销总数量*/
	private Long leveltotalDistributionNum;
	
	/**商品分销总数量*/
	private Long totalDistributionAmount;
	
	

	public Long getTotalDistributionAmount() {
		return totalDistributionAmount;
	}

	public void setTotalDistributionAmount(Long totalDistributionAmount) {
		this.totalDistributionAmount = totalDistributionAmount;
	}

	public Long getPrimarytotalDistributionNum() {
		return primarytotalDistributionNum;
	}

	public void setPrimarytotalDistributionNum(Long primarytotalDistributionNum) {
		this.primarytotalDistributionNum = primarytotalDistributionNum;
	}

	public Long getLeveltotalDistributionNum() {
		return leveltotalDistributionNum;
	}

	public void setLeveltotalDistributionNum(Long leveltotalDistributionNum) {
		this.leveltotalDistributionNum = leveltotalDistributionNum;
	}
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Double getPrimaryDistributionPrice() {
		return primaryDistributionPrice;
	}

	public void setPrimaryDistributionPrice(Double primaryDistributionPrice) {
		this.primaryDistributionPrice = primaryDistributionPrice;
	}

	public Double getLevelDistributionPrice() {
		return levelDistributionPrice;
	}

	public void setLevelDistributionPrice(Double levelDistributionPrice) {
		this.levelDistributionPrice = levelDistributionPrice;
	}

	public String getDistriDates() {
		return distriDates;
	}

	public void setDistriDates(String distriDates) {
		this.distriDates = distriDates;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Double getTotalPriceAmount() {
		return totalPriceAmount;
	}

	public void setTotalPriceAmount(Double totalPriceAmount) {
		this.totalPriceAmount = totalPriceAmount;
	}

	public Double getDistriPriceAmount() {
		return distriPriceAmount;
	}

	public void setDistriPriceAmount(Double distriPriceAmount) {
		this.distriPriceAmount = distriPriceAmount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Date getDistriDate() {
		return distriDate;
	}

	public void setDistriDate(Date distriDate) {
		this.distriDate = distriDate;
	}

	public Integer getDistriLevel() {
		return distriLevel;
	}

	public void setDistriLevel(Integer distriLevel) {
		this.distriLevel = distriLevel;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Integer getUnderlingMemberNum() {
		return underlingMemberNum;
	}

	public void setUnderlingMemberNum(Integer underlingMemberNum) {
		this.underlingMemberNum = underlingMemberNum;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
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

	public MemberDistributionLogDto() {
		super();
	}

}
