package com.topaiebiz.transaction.cart.dto;

import java.util.Date;

/**
 * 
 * Description TODO 店铺运费模板dto 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月18日 下午7:34:39 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class StoreFreightInfoDto {
	
	private Long id;
	
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
	
	/** 备注。用于备注其他信息。*/
	private String memo;
	
	/** 创建人编号。取值为创建人的全局唯一主键标识符。*/
	private Long creatorId;
	
	/** 创建时间。取值为系统的当前时间。*/
	private Date createdTime;
	
	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。*/
	private Long lastModifierId;
	
	/** 最后修改时间。取值为系统的当前时间。*/
	private Date lastModifiedTime;
	
	/** 逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。*/
	private Integer deletedFlag;
	
	/** 信息版本号。乐观锁机制的辅助字段，用于控制信息的一致性。*/
	private Long version;
	
	
	
	
	private Long infoId;
	
	/** 店铺id*/
	private Long storeId;
	
	/** 运费模板名称*/
	private String freightName;
	
	/** 运费模板名称*/
	private Boolean infoIsDefault;
	
	/** 计价方式。1 件数 2体积 3重量*/
	private Short pricing;
	
	/** 是否仅配送特定地区。（1 为是 ，0为否）*/
	private Boolean onlyThis;
	
	/** 备注。用于备注其他信息。*/
	private String infoMemo;
	
	/** 创建人编号。取值为创建人的全局唯一主键标识符。*/
	private Long infoCreatorId;
	
	/** 创建时间。取值为系统的当前时间。*/
	private Date infoCreatedTime;
	
	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。*/
	private Long infoLastModifierId;
	
	/** 最后修改时间。取值为系统的当前时间。*/
	private Date infoLastModifiedTime;
	
	/** 逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。*/
	private Integer infoDeletedFlag;
	
	/** 信息版本号。乐观锁机制的辅助字段，用于控制信息的一致性。*/
	private Long infoVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Short getPricing() {
		return pricing;
	}

	public void setPricing(Short pricing) {
		this.pricing = pricing;
	}

	public String getInfoMemo() {
		return infoMemo;
	}

	public void setInfoMemo(String infoMemo) {
		this.infoMemo = infoMemo;
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

	public Boolean getInfoIsDefault() {
		return infoIsDefault;
	}

	public void setInfoIsDefault(Boolean infoIsDefault) {
		this.infoIsDefault = infoIsDefault;
	}

	public Boolean getOnlyThis() {
		return onlyThis;
	}

	public void setOnlyThis(Boolean onlyThis) {
		this.onlyThis = onlyThis;
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
