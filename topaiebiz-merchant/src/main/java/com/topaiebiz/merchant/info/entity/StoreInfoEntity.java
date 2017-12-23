package com.topaiebiz.merchant.info.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 店铺信息实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月2日 下午7:30:25
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_store_info")
public class StoreInfoEntity extends BaseBizEntity<Long> {

	/** 版本序列化 */
	private static final long serialVersionUID = -9016636675413808094L;

	/** 所属商家ID */
	private Long merchantId;

	/** 店铺名称 */
	private String name;

	/** 店铺模板 */
	private Long templateId;

	/** 实体店所在区域 */
	private Long districtId;

	/** 实体店位置 */
	private String storeAddress;

	/** 店铺的积分。和后期奖惩有关系 */
	private Long integral;

	/** 店铺等级积分 */
	private Long gradeIntegral;

	/** 店铺等级。和商家保持一致 */
	private Long merchantGradeId;

	/** 商家联系人姓名 */
	private String contactName;

	/** 联系人手机号 */
	private String contactTele;

	/** 门店电话 */
	private String storeTele;

	/** 商家介绍 */
	private String description;

	/** 门店照片多张 */
	private String images;
	
	/**地理位置。*/
	private String position;
	
	@TableField(exist = false)
	private boolean flag;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Long getGradeIntegral() {
		return gradeIntegral;
	}

	public void setGradeIntegral(Long gradeIntegral) {
		this.gradeIntegral = gradeIntegral;
	}

	public Long getMerchantGradeId() {
		return merchantGradeId;
	}

	public void setMerchantGradeId(Long merchantGradeId) {
		this.merchantGradeId = merchantGradeId;
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

	public String getStoreTele() {
		return storeTele;
	}

	public void setStoreTele(String storeTele) {
		this.storeTele = storeTele;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	

}
