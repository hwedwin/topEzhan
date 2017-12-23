package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;

/**
 * 
 * @Description 礼卡信息的数据传输类（包含binding，info，type，label，media的实体类）
 * @Author Murray
 * @Date 2017年8月28日 上午11:19:47
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GiftCardDto {

	/** 礼卡详细信息表主键detail */
	private Long id;

	/** 礼卡详细信息的编号 */
	private String cardNo;

	/** 礼卡信息主键 */
	private Long cardId;

	private Integer isVice;

	private Integer isCustom;

	/** 标签名称 */
	private String labelName;

	/** 介质名称 */
	private String mediumName;

	/** 经营类型名称 */
	private String typeName;

	/** 礼卡介质的主键 */
	private Long cardMedium;

	/** 礼卡经营类型主键 */
	private Long cardType;

	/** 礼卡标签主键 */
	private Long cardLabel;

	/** 礼卡名称(不可为空) */
	private String infoName;

	/** 品牌名称 */
	private String brandName;
	
	/**副卡面值*/
	private Double viceValue;

	/** 店铺名称 */
	private String storeName;

	/** 礼卡面值(不可为空) */
	private Double value;

	/** 礼卡售价 */
	private Double price;

	/** 礼卡的抵扣金额 */
	private Double deductionPrice;

	/** 礼卡的余额 */
	private Double balance;

	/** 平台补差 */
	private Double platformPrice;
	
	/**平台总优惠*/
	private Double sumPlatformPrice;

	/** 店铺补差 */
	private Double storePrice;

	/** 品牌补差 */
	private Double brandPrice;

	/** 经营范围 */
	private Integer rangeType;

	/** 店铺编号，主键 */
	private Long storeId;

	/** 品牌编号，主键 */
	private Long brandId;

	/** 订单主键 */
	private Long orderId;

	/** 批准人姓名 */
	private String approver;

	/** 最小面额 */
	private Double minPrice;

	/** 最大面额 */
	private Double maxPrice;

	/** 礼卡剩余数量 */
	private Long surplusNum;

	/** 礼卡总数 */
	private Long totalNum;

	/** 礼卡到期时间 */
	private Date expirationTime;

	/** id限购数量 */
	private Long banMemberNo;

	/** 出售时间 */
	private Date saleTime;

	/** 礼卡状态，1为发布，2为上架，3为下架 */
	private Integer state;

	/** 礼卡的图片 */
	private String image;

	/** 礼卡备注 */
	private String memo;

	/** 盛放ID的数组 */
	private Long[] list;

	/** 礼卡信息创建人编号 */
	private Long creatorId;

	/** 礼卡信息的创建时间 */
	private Date createdTime;

	/** 礼卡最后修改人的编号 */
	private Long lastModifierId;

	/** 礼卡最后一次修改的时间 */
	private Date lastModifiedTime;

	/** 礼卡的删除状态 */
	private Integer deletedFlag;

	/** 礼卡的描述 */
	private String description;

	/** 绑定的主卡的ID */
	private Long mainCardId;

	/***/
	private Long viceCardId;

	/** 用户选择购买的每种礼卡的数量 */
	private Long number;

	/** 用户选择的礼卡的总价格 */
	private Double sumPrice;

	/** 用户选择购买的礼卡的总数量 */
	private Long numbers;

	/** 礼卡的绑定状态 */
	private Integer cardState;
	
	/**实际支付*/
	private Double payMentPrice;
	
	
	public Integer getRangeType() {
		return rangeType;
	}

	public void setRangeType(Integer rangeType) {
		this.rangeType = rangeType;
	}

	public Double getPayMentPrice() {
		return payMentPrice;
	}

	public void setPayMentPrice(Double payMentPrice) {
		this.payMentPrice = payMentPrice;
	}

	/** 激活的会员编号 */
	private Long memberId;

	public Long[] getList() {
		return list;
	}

	public void setList(Long[] list) {
		this.list = list;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getDeductionPrice() {
		return deductionPrice;
	}

	public void setDeductionPrice(Double deductionPrice) {
		this.deductionPrice = deductionPrice;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getCardMedium() {
		return cardMedium;
	}

	public void setCardMedium(Long cardMedium) {
		this.cardMedium = cardMedium;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Long getCardLabel() {
		return cardLabel;
	}

	public void setCardLabel(Long cardLabel) {
		this.cardLabel = cardLabel;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	
	public Double getViceValue() {
		return viceValue;
	}

	public void setViceValue(Double viceValue) {
		this.viceValue = viceValue;
	}

	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

	public Long getNumbers() {
		return numbers;
	}

	public void setNumbers(Long numbers) {
		this.numbers = numbers;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Long surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getTypeName() {
		return typeName;
	}
	
	public Double getSumPlatformPrice() {
		return sumPlatformPrice;
	}

	public void setSumPlatformPrice(Double sumPlatformPrice) {
		this.sumPlatformPrice = sumPlatformPrice;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
	}

	public Long getViceCardId() {
		return viceCardId;
	}

	public void setViceCardId(Long viceCardId) {
		this.viceCardId = viceCardId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsVice() {
		return isVice;
	}

	public void setIsVice(Integer isVice) {
		this.isVice = isVice;
	}

	public Integer getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Integer isCustom) {
		this.isCustom = isCustom;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Double getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Double storePrice) {
		this.storePrice = storePrice;
	}

	public Double getBrandPrice() {
		return brandPrice;
	}

	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}


	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getBanMemberNo() {
		return banMemberNo;
	}

	public void setBanMemberNo(Long banMemberNo) {
		this.banMemberNo = banMemberNo;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "GiftCardDto [id=" + id + ", isVice=" + isVice + ", isCustom=" + isCustom + ", value=" + value
				+ ", price=" + price + ", platformPrice=" + platformPrice + ", storePrice=" + storePrice
				+ ", brandPrice=" + brandPrice + ", rangeType=" + rangeType + ", storeId=" + storeId + ", brandId="
				+ brandId + ", approver=" + approver + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", expirationTime=" + expirationTime + ", banMemberNo=" + banMemberNo + ", saleTime=" + saleTime
				+ ", state=" + state + ", image=" + image + ", memo=" + memo + ", creatorId=" + creatorId
				+ ", createdTime=" + createdTime + ", lastModifierId=" + lastModifierId + ", lastModifiedTime="
				+ lastModifiedTime + ", deletedFlag=" + deletedFlag + ", description=" + description + ", mainCardId="
				+ mainCardId + ", viceCardId=" + viceCardId + "]";
	}

}
