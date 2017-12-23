package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;

/**
 * @Description 礼卡信息及订单的数据传输类（包括：info，Binding,Detail）
 * @Author Murray.Li
 * @Date 2017年8月28日 下午2:44:30
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GiftCardInfoDto {

	/** 礼卡详细信息主键detail */
	private Long id;

	/** 礼卡信息主键info */
	private Long cardId;

	/** 礼卡编号 */
	private String cardNo;

	/** 订单编号 */
	private Long orderId;
	
	/** 密码 */
	private String password;

	/** 用户手机号 */
	private String telephone;

	/** 下载密码 */
	private String downloadPassword;

	/** 礼卡状态 */
	private Integer saleState;

	/** 是否为本人使用 */
	private Integer selfState;

	/** 绑定的会员ID */
	private Long memberId;

	/** 激活时间 */
	private Date activeTime;

	private String memo;

	private Long creatorId;

	private Date createdTime;

	private Long lastModifierId;

	private Date lastModifiedTime;

	private Integer deletedFlag;

	/** 礼卡介质ID */
	private Long cardMedium;

	/** 礼卡标签ID */
	private Long cardLabel;

	/** 礼卡经营类型ID */
	private Long cardType;

	/** 是否为副卡 */
	private Integer isVice;

	/** 主卡ID */
	private Long mainCardId;

	/** 是否为自定义卡 */
	private Integer isCustom;

	/** 礼卡名称 */
	private String name;

	/** 标签名称 */
	private String labelName;

	/** 介质名称 */
	private String mediumName;

	/** 店铺名称 */
	private String storeName;

	/** 品牌名称 */
	private String brandName;

	/** 经营类型名称 */
	private String typeName;

	/** 礼卡前缀 */
	private String prefix;

	/** 礼卡面值 */
	private Double value;

	/** 礼卡售价 */
	private Double price;

	/** 平台补差 */
	private Double platformPrice;

	/** 店铺补差 */
	private Double storePrice;

	/** 品牌补差 */
	private Double brandPrice;

	/** 经营范围 */
	private Integer rangeType;

	/** 店铺ID */
	private Long storeId;

	/** 品牌ID */
	private Long brandId;

	/** 批准人 */
	private String approver;

	/** 最小面值 */
	private Double minPrice;

	/** 最大面值 */
	private Double maxPrice;

	/** 礼卡总数量 */
	private Long totalNum;

	/** 剩余数量 */
	private Long surplusNum;

	/** 到期时间 */
	private Date expirationTime;

	/***/
	private Long banMemberNo;

	/** 出售时间 */
	private Date saleTime;

	/** info礼卡状态 */
	private Integer state;

	private String image;

	/** 礼卡状态detail */
	private Integer cardState;

	/** 使用订单号。购买商品后回显记录的字段。 */
	private String usedOrder;
	
	/** 订单编号 */
	private Long orderNo;

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getUsedOrder() {
		return usedOrder;
	}

	public void setUsedOrder(String usedOrder) {
		this.usedOrder = usedOrder;
	}

	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDownloadPassword() {
		return downloadPassword;
	}

	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
	}

	public Integer getSelfState() {
		return selfState;
	}

	public void setSelfState(Integer selfState) {
		this.selfState = selfState;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
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

	public Long getCardMedium() {
		return cardMedium;
	}

	public void setCardMedium(Long cardMedium) {
		this.cardMedium = cardMedium;
	}

	public Long getCardLabel() {
		return cardLabel;
	}

	public void setCardLabel(Long cardLabel) {
		this.cardLabel = cardLabel;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Integer getIsVice() {
		return isVice;
	}

	public void setIsVice(Integer isVice) {
		this.isVice = isVice;
	}

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
	}

	public Integer getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Integer isCustom) {
		this.isCustom = isCustom;
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

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public Integer getRangeType() {
		return rangeType;
	}

	public void setRangeType(Integer rangeType) {
		this.rangeType = rangeType;
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

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Long getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Long surplusNum) {
		this.surplusNum = surplusNum;
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

	@Override
	public String toString() {
		return "GiftCardInfoDto [ cardId=" + cardId + ", cardNo=" + cardNo + ", orderId=" + orderId + ", password="
				+ password + ", telephone=" + telephone + ", downloadPassword=" + downloadPassword + ", saleState="
				+ saleState + ", selfState=" + selfState + ", memeberId=" + memberId + ", activeTime=" + activeTime
				+ ", memo=" + memo + ", creatorId=" + creatorId + ", createdTime=" + createdTime + ", lastModifierId="
				+ lastModifierId + ", lastModifiedTime=" + lastModifiedTime + ", deletedFlag=" + deletedFlag
				+ ", cardMedium=" + cardMedium + ", cardLabel=" + cardLabel + ", cardType=" + cardType + ", isVice="
				+ isVice + ", mainCardId=" + mainCardId + ", isCustom=" + isCustom + ", name=" + name + ", labelName="
				+ labelName + ", mediumName=" + mediumName + ", storeName=" + storeName + ", brandName=" + brandName
				+ ", typeName=" + typeName + ", prefix=" + prefix + ", value=" + value + ", price=" + price
				+ ", platformPrice=" + platformPrice + ", storePrice=" + storePrice + ", brandPrice=" + brandPrice
				+ ", rangeType=" + rangeType + ", storeId=" + storeId + ", brandId=" + brandId + ", approver="
				+ approver + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", totalNum=" + totalNum
				+ ", surplusNum=" + surplusNum + ", expirationTime=" + expirationTime + ", banMemberNo=" + banMemberNo
				+ ", saleTime=" + saleTime + ", state=" + state + ", image=" + image + ", cardState=" + cardState + "]";
	}

}
