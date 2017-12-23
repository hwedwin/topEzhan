package com.topaiebiz.transaction.cart.dto;

import java.util.Date;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * Description (描述Java类的用途或使用说明。)  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年10月14日 上午10:21:23 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberCardDto {
	
	/**全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/**所属礼卡*/
	@NotBlank
	private Long cardId;
	
	/**礼卡编号*/
	@NotBlank
	private Long cardNo;
	
	/**订单编号*/
	@NotBlank
	private Long orderId;
	
	/**激活密码*/
	private String password;
	
	/**可激活时间,虚拟卡一般为出售完后自动生成，实体卡一般为货到付款后自动升成*/
	private Date expirationTime;
	
	/**手机号，顾客输入，找回密码用的*/
	private String telephone;
	
	/**下载密码,顾客买卡时输入的，用于下载卡号和密码*/
	private String downloadPassword;
	
	/**使用订单号。购买商品后回显记录的字段。*/
	private String usedOrder;
	
	/**是否为本人使用*/
	private Integer selfState;
	
	/**激活会员*/
	private Long memeberId;
	
	/**激活时间*/
	private Date activeTime;
	
	/**关联主卡ID*/
	private Long mainCardId;
	
	/**礼卡余额*/
	private Double balance;
	
	/**卡面值*/
	@NotBlank
	private Double value;
	
	/**卡售价*/
	@NotBlank
	private Double price;
	
	/**美礼卡状态。1  新建   2已销售  3已激活。*/
	private Integer cardState;
	
	/**备注。用于备注其他信息。*/
	private String memo;
	
	/**创建人编号。取值为创建人的全局唯一主键标识符。*/
	private Long creatorId;
	
	/**创建时间。取值为系统的当前时间。*/
	private Date createdTime;
	
	/**最后修改人编号。取值为最后修改人的全局唯一主键标识符。*/
	private Integer lastModifierId;
	
	/**最后修改时间。取值为系统的当前时间。*/
	private Date lastModifiedTime;
	
	/**逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。*/
	private Integer deletedFlag;
	
	/**信息版本号。乐观锁机制的辅助字段，用于控制信息的一致性。*/
	private Long version;
	
	
	
	
	/** 礼卡信息的唯一主键ID，非自增 */
	private Long infoId;

	/** 礼卡介质的id */
	@NotBlank
	private Long infoCardMedium;

	/** 礼卡主题的ID */
	@NotBlank
	private Long infoCardLabel;

	/** 礼卡类型的ID */
	@NotBlank
	private Long infoCardType;

	/** 是否为副卡（1是，0 否） */
	@NotBlank
	private Integer infoIsVice;

	/** 绑定主卡id.只有副卡的时候填该此字段。 */
	private Long infoMainCardId;

	/** 是否为自定义卡，1 是 0 否 */
	private Integer infoIsCustom;

	/** 礼卡名称（标题）。 */
	@NotBlank
	private String infoName;

	/** 卡号前缀 */
	private String infoPrefix;

	/** 礼卡面值 */
	@NotBlank
	@Digits(integer=2,fraction=20) 
	private Double infoValue;

	/** 礼卡售价 */
	@NotBlank
	@Digits(integer=2,fraction=20) 
	private Double infoPrice;

	/** 平台补差 */
	private Double infoPlatformPrice;

	/** 店铺补差 */
	private Double infoStorePrice;

	/** 品牌补差 */
	private Double infoBrandPrice;

	/** 适用范围：1 平台 2店铺 3品牌 */
	private Integer infoRangeType;

	/** 经营店铺 */
	private Long infoStoreId;

	/** 经营品牌 */
	private Long infoBrandId;

	/** 发行批准人 */
	@NotBlank
	private String infoApprover;

	/** 自定义卡最小面值。 */
	private Double infoMinPrice;

	/** 自定义卡最大面值。 */
	private Double infoMaxPrice;

	/** 卡总数量 */
	@NotBlank
	private Long infoTotalNum;

	/** 卡剩余数量 */
	@NotBlank
	private Long infoSurplusNum;

	/** 到期时间，某一时间点 */
	private Date infoExpirationTime;

	/** ID限购数量 */
	private Long infoBanMemberNo;

	/** 礼卡销售时间。(系统时间) */
	private Date infoSaleTime;

	/** 卡号状态。（1 发布 2 上架 3 下架） */
	private Integer infoState;

	/** 卡图片。 */
	@NotBlank
	private String infoImage;

	/** 备注。用于备注其他信息。 */
	private String infoMemo;

	/** 创建人编号。取值为创建人的全局唯一主键标识符。 */
	private Long infoCreatorId;

	/** 创建时间。取值为系统的当前时间。 */
	private Date infoCreatedTime;

	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。 */
	private Long infoLastModifierId;

	/** 最后修改时间。取值为系统的当前时间。 */
	private Date infoLastModifiedTime;

	/** 逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。 */
	private Integer infoDeletedFlag;

	/** 信息版本号。乐观锁机制的辅助字段，用于控制信息的一致性。 */
	private Long infoVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
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

	public String getUsedOrder() {
		return usedOrder;
	}

	public void setUsedOrder(String usedOrder) {
		this.usedOrder = usedOrder;
	}

	public Integer getSelfState() {
		return selfState;
	}

	public void setSelfState(Integer selfState) {
		this.selfState = selfState;
	}

	public Long getMemeberId() {
		return memeberId;
	}

	public void setMemeberId(Long memeberId) {
		this.memeberId = memeberId;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	public Integer getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Integer lastModifierId) {
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public Long getInfoCardMedium() {
		return infoCardMedium;
	}

	public void setInfoCardMedium(Long infoCardMedium) {
		this.infoCardMedium = infoCardMedium;
	}

	public Long getInfoCardLabel() {
		return infoCardLabel;
	}

	public void setInfoCardLabel(Long infoCardLabel) {
		this.infoCardLabel = infoCardLabel;
	}

	public Long getInfoCardType() {
		return infoCardType;
	}

	public void setInfoCardType(Long infoCardType) {
		this.infoCardType = infoCardType;
	}

	public Integer getInfoIsVice() {
		return infoIsVice;
	}

	public void setInfoIsVice(Integer infoIsVice) {
		this.infoIsVice = infoIsVice;
	}

	public Long getInfoMainCardId() {
		return infoMainCardId;
	}

	public void setInfoMainCardId(Long infoMainCardId) {
		this.infoMainCardId = infoMainCardId;
	}

	public Integer getInfoIsCustom() {
		return infoIsCustom;
	}

	public void setInfoIsCustom(Integer infoIsCustom) {
		this.infoIsCustom = infoIsCustom;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getInfoPrefix() {
		return infoPrefix;
	}

	public void setInfoPrefix(String infoPrefix) {
		this.infoPrefix = infoPrefix;
	}

	public Double getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(Double infoValue) {
		this.infoValue = infoValue;
	}

	public Double getInfoPrice() {
		return infoPrice;
	}

	public void setInfoPrice(Double infoPrice) {
		this.infoPrice = infoPrice;
	}

	public Double getInfoPlatformPrice() {
		return infoPlatformPrice;
	}

	public void setInfoPlatformPrice(Double infoPlatformPrice) {
		this.infoPlatformPrice = infoPlatformPrice;
	}

	public Double getInfoStorePrice() {
		return infoStorePrice;
	}

	public void setInfoStorePrice(Double infoStorePrice) {
		this.infoStorePrice = infoStorePrice;
	}

	public Double getInfoBrandPrice() {
		return infoBrandPrice;
	}

	public void setInfoBrandPrice(Double infoBrandPrice) {
		this.infoBrandPrice = infoBrandPrice;
	}

	public Integer getInfoRangeType() {
		return infoRangeType;
	}

	public void setInfoRangeType(Integer infoRangeType) {
		this.infoRangeType = infoRangeType;
	}

	public Long getInfoStoreId() {
		return infoStoreId;
	}

	public void setInfoStoreId(Long infoStoreId) {
		this.infoStoreId = infoStoreId;
	}

	public Long getInfoBrandId() {
		return infoBrandId;
	}

	public void setInfoBrandId(Long infoBrandId) {
		this.infoBrandId = infoBrandId;
	}

	public String getInfoApprover() {
		return infoApprover;
	}

	public void setInfoApprover(String infoApprover) {
		this.infoApprover = infoApprover;
	}

	public Double getInfoMinPrice() {
		return infoMinPrice;
	}

	public void setInfoMinPrice(Double infoMinPrice) {
		this.infoMinPrice = infoMinPrice;
	}

	public Double getInfoMaxPrice() {
		return infoMaxPrice;
	}

	public void setInfoMaxPrice(Double infoMaxPrice) {
		this.infoMaxPrice = infoMaxPrice;
	}

	public Long getInfoTotalNum() {
		return infoTotalNum;
	}

	public void setInfoTotalNum(Long infoTotalNum) {
		this.infoTotalNum = infoTotalNum;
	}

	public Long getInfoSurplusNum() {
		return infoSurplusNum;
	}

	public void setInfoSurplusNum(Long infoSurplusNum) {
		this.infoSurplusNum = infoSurplusNum;
	}

	public Date getInfoExpirationTime() {
		return infoExpirationTime;
	}

	public void setInfoExpirationTime(Date infoExpirationTime) {
		this.infoExpirationTime = infoExpirationTime;
	}

	public Long getInfoBanMemberNo() {
		return infoBanMemberNo;
	}

	public void setInfoBanMemberNo(Long infoBanMemberNo) {
		this.infoBanMemberNo = infoBanMemberNo;
	}

	public Date getInfoSaleTime() {
		return infoSaleTime;
	}

	public void setInfoSaleTime(Date infoSaleTime) {
		this.infoSaleTime = infoSaleTime;
	}

	public Integer getInfoState() {
		return infoState;
	}

	public void setInfoState(Integer infoState) {
		this.infoState = infoState;
	}

	public String getInfoImage() {
		return infoImage;
	}

	public void setInfoImage(String infoImage) {
		this.infoImage = infoImage;
	}

	public String getInfoMemo() {
		return infoMemo;
	}

	public void setInfoMemo(String infoMemo) {
		this.infoMemo = infoMemo;
	}

	public Long getInfoCreatorId() {
		return infoCreatorId;
	}

	public void setInfoCreatorId(Long infoCreatorId) {
		this.infoCreatorId = infoCreatorId;
	}

	public Date getInfoCreatedTime() {
		return infoCreatedTime;
	}

	public void setInfoCreatedTime(Date infoCreatedTime) {
		this.infoCreatedTime = infoCreatedTime;
	}

	public Long getInfoLastModifierId() {
		return infoLastModifierId;
	}

	public void setInfoLastModifierId(Long infoLastModifierId) {
		this.infoLastModifierId = infoLastModifierId;
	}

	public Date getInfoLastModifiedTime() {
		return infoLastModifiedTime;
	}

	public void setInfoLastModifiedTime(Date infoLastModifiedTime) {
		this.infoLastModifiedTime = infoLastModifiedTime;
	}

	public Integer getInfoDeletedFlag() {
		return infoDeletedFlag;
	}

	public void setInfoDeletedFlag(Integer infoDeletedFlag) {
		this.infoDeletedFlag = infoDeletedFlag;
	}

	public Long getInfoVersion() {
		return infoVersion;
	}

	public void setInfoVersion(Long infoVersion) {
		this.infoVersion = infoVersion;
	}

}
