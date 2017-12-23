package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class OrderAddressDto {

	private Long id;

	/** 订单编号 */
	private Long orderId;

	/** 收货人姓名 */
	@NotNull(message = "{validation.order.name}")
	private String name;

	/** 地址区域。 */
	private Long districtId;

	/** 收货人地址 */
	@NotNull(message = "{validation.order.address}")
	private String address;

	/** 收货人邮编 */
	private String zipCode;

	/** 收货人手机号 */
	@NotNull(message = "{validation.address.telephone}")
	@Length(min = 11, max = 11)
	private String telephone;

	/** 收货人座机号 */
	private String landline;

	/** 紧急联系电话，备用电话。 */
	private String otherTelephone;

	/** 创建人编号。取值为创建人的全局唯一主键标识符。 */
	private Long creatorId;
	
	private Date createdTime;
	
	/** 礼卡信息最后一次修改的时间 */
	private Date lastModifiedTime;

	/** 礼卡最后一次修改的人的编号 */
	private Long lastModifierId;
	
	/**物流编号*/
	private String logisticsNo;
	
	/**物流类型*/
	private String logisticsType;
	
	

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}
	
	

}
