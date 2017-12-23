package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class GiftCardOrderDto {

	private String orderNo;

	/** 会员编号，主键ID */
	private Long memberId;

	/** 定单时间 */
	private Date orderTime;

	/** 订单总金额 */
	private Double totalPrice;

	/** 数量 */
	private Long number;

	/** 当前ID购买此礼卡的总数量 */
	private Long sum;

	/** 是否需要发货 */
	private Integer needSend;
	
	/** 订单状态。1、代付款，2、代发货，3、待收货，4、待评价，5、已完成 */
	private Integer orderState;

	/** 支付单据号 */
	private Long payOrderId;

	private Long districtId;

	/** 收货人地址 */
	@NotBlank
	private String address;

	/** 收货人邮编 */
	private String zipCode;

	/** 收货人座机号 */
	private String landline;

	/** 紧急联系电话，备用电话。 */
	@NotBlank
	private String otherTelephone;

	private Date lastModifedTime;

	private Long mainCardId;

	private Long viceCardId;

	/** 礼卡编号 */
	private Long cardNo;

	/** 订单编号 */
	private Long orderId;

	/** 礼卡的密码 */
	private String password;

	/** 找回密码的手机号 */
	private String telephone;

	/** 下载密码 */
	private String downloadPassword;

	private Integer saleState;

	private Integer selfState;

	/** 会员编号 */
	private String memberCode;

	private String memo;

	private Long creatorId;

	private Date createdTime;

	private Long lastModifierId;

	private Date lastModifiedTime;

	private Integer deletedFlag;

	private Long version;

	/** 礼卡编号,主键ID */
	@NotBlank
	private Long cardId;

	/** 礼卡介质 */
	private Long cardMedia;

	/** 礼卡标签 */
	private Long cardLabel;

	/** 礼卡类型 */
	private Long cardType;

	/** 是否为副卡（1是，0 否） */
	private Integer isVice;

	/** 是否为自定义卡，1 是 0 否 */
	private Integer isCustom;

	/** 标签名称 */
	private String labelName;

	/** 介质名称 */
	private String mediaName;

	/** 店铺名称 */
	private String storeName;

	/** 品牌名称 */
	private String brandName;

	/** 经营类型名称 */
	private String typeName;

	/** 收货人姓名 */
	@NotBlank
	private String name;

	/** 商品名称 */
	private String goodsName;

	/** 卡号的前缀 */
	private String prefix;

	/** 礼卡的面值 */
	@NotBlank
	private Double value;

	/** 礼卡的售价 */
	@NotBlank
	private Double price;

	/** 一个订单中礼卡总售价 */
	private Double prices;

	/** 一个订单中礼卡的总面值 */
	private Double values;

	/** 平台补差 */
	private Double platformPrice;

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

	/** 批准人 */
	private String approver;

	/** 自定义最小面值 */
	private Double minPrice;

	/** 自定义最大面值 */
	private Double maxPrice;

	/** 礼卡总数量 */
	private Long totalNum;

	/** 礼卡剩余数量 */
	private Long surplusNum;

	/** 到期时间 */
	private Date expirationTime;

	/** ID限购数量 */
	private Long banMemberNo;

	/** 礼卡的出售时间 */
	private Date saleTime;

	/** 礼卡的状态（1 发布 2 上架 3 下架） */
	private Integer state;

	/** 礼卡的图片 */
	private String image;

	/** 美礼卡状态。1 新建 2已销售 3已激活。 */
	private Integer cardState;

	/** 礼卡名称 */
	private String infoName;

	/** 开始时间 */
	private String beganTime;

	/** 结束时间 */
	private String endTime;

	/** 用户名 */
	private String userName;

	private String description;

	/** 使用订单号。购买商品后回显记录的字段。 */
	private String usedOrder;
	
	/**物流类型*/
	private String logisticsType;
	
	/**物流编号*/
	private String logisticsNo;

	/** 盛放礼卡信息的集合 */
	private List<GiftCardDto> list;

	
	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public Double getValues() {
		return values;
	}

	public void setValues(Double values) {
		this.values = values;
	}

	public List<GiftCardDto> getList() {
		return list;
	}

	public void setList(List<GiftCardDto> list) {
		this.list = list;
	}

	public String getUsedOrder() {
		return usedOrder;
	}

	public void setUsedOrder(String usedOrder) {
		this.usedOrder = usedOrder;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBeganTime() {
		return beganTime;
	}

	public void setBeganTime(String beganTime) {
		this.beganTime = beganTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSaleState() {
		return saleState;
	}

	public void setSaleState(Integer saleState) {
		this.saleState = saleState;
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

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public Long getCardMedia() {
		return cardMedia;
	}

	public void setCardMedia(Long cardMedia) {
		this.cardMedia = cardMedia;
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

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
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

	public Long getBanMemberNo() {
		return banMemberNo;
	}

	public void setBanMemberNo(Long banMemberNo) {
		this.banMemberNo = banMemberNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getViceCardId() {
		return viceCardId;
	}

	public void setViceCardId(Long viceCardId) {
		this.viceCardId = viceCardId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getNeedSend() {
		return needSend;
	}

	public void setNeedSend(Integer needSend) {
		this.needSend = needSend;
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
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

	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getOtherTelephone() {
		return otherTelephone;
	}

	public void setOtherTelephone(String otherTelephone) {
		this.otherTelephone = otherTelephone;
	}

	public Date getLastModifedTime() {
		return lastModifedTime;
	}

	public void setLastModifedTime(Date lastModifedTime) {
		this.lastModifedTime = lastModifedTime;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDownloadPassword() {
		return downloadPassword;
	}

	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
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

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
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

	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

}
