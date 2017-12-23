/**
 * 
 */
package com.topaiebiz.system.district.dto;

import javax.validation.constraints.NotNull;


/**  
 * Description： 区域 数据Dto
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月19日 下午2:50:40 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class DistrictDto {

	private Long id;
	
	/**
	 * 区域信息的唯一编码。
	 */
	@NotNull(message = "{validation.district.code}")
	private String code;

	/**
	 * 区域的全名。
	 */
	@NotNull(message = "{validation.district.fullName}")
	private String fullName;

	/**
	 * 区域的简称。
	 */
	@NotNull(message = "{validation.district.shortName}")
	private String shortName;

	/**
	 * 区域的类型。
	 */
	@NotNull(message = "{validation.district.districtTypeId}")
	private Long districtTypeId;
	
	/**
	 * 区域的类型名称
	 */
	private String districtTypeName;

	/**
	 * 区域的父区域的编号
	 */
	private Long parentDistrictId;

	/**
	 * 区域的父区域的名称。注意：取值自父区域的区域名称。冗余字段，方便显示。
	 */
	private String parentDistrictName;

	/**
	 * 区域的ISO两字母代码
	 */
	private String isoTwoAlphabetCode;

	/**
	 * 区域的ISO三字母代码
	 */
	private String isoThreeAlphabetCode;

	/**
	 * 区域的ISO三数字代码
	 */
	private Long isoThreeNumericCode;

	/**
	 * 区域的序列号。由父区域序列号+“.”+当前区域的全局唯一主键构成。冗余字段，方便查询。
	 */
	private String serialNo;

	/**
	 * 区域的序列名。由父区域序列名+“ -> ”+当前区域的简称构成。冗余字段，方便显示
	 */
	private String serialName;

	/**
	 * 区域的ISO语言代码
	 */
	private String isoLanguageCode;

	/**
	 * 区域的ISO语言名称
	 */
	private String isoLanguageName;

	/**
	 * 区域的电话区号
	 */
	private String phoneAreaCode;

	/**
	 * 区域的描述信息
	 */
	private String description;

	/**
	 * 备注。用于备注其他信息。
	 */
	private String memo;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Long getDistrictTypeId() {
		return districtTypeId;
	}

	public void setDistrictTypeId(Long districtTypeId) {
		this.districtTypeId = districtTypeId;
	}

	public Long getParentDistrictId() {
		return parentDistrictId;
	}

	public void setParentDistrictId(Long parentDistrictId) {
		this.parentDistrictId = parentDistrictId;
	}

	public String getParentDistrictName() {
		return parentDistrictName;
	}

	public void setParentDistrictName(String parentDistrictName) {
		this.parentDistrictName = parentDistrictName;
	}

	public String getIsoTwoAlphabetCode() {
		return isoTwoAlphabetCode;
	}

	public void setIsoTwoAlphabetCode(String isoTwoAlphabetCode) {
		this.isoTwoAlphabetCode = isoTwoAlphabetCode;
	}

	public String getIsoThreeAlphabetCode() {
		return isoThreeAlphabetCode;
	}

	public void setIsoThreeAlphabetCode(String isoThreeAlphabetCode) {
		this.isoThreeAlphabetCode = isoThreeAlphabetCode;
	}

	public Long getIsoThreeNumericCode() {
		return isoThreeNumericCode;
	}

	public void setIsoThreeNumericCode(Long isoThreeNumericCode) {
		this.isoThreeNumericCode = isoThreeNumericCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getIsoLanguageCode() {
		return isoLanguageCode;
	}

	public void setIsoLanguageCode(String isoLanguageCode) {
		this.isoLanguageCode = isoLanguageCode;
	}

	public String getIsoLanguageName() {
		return isoLanguageName;
	}

	public void setIsoLanguageName(String isoLanguageName) {
		this.isoLanguageName = isoLanguageName;
	}

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDistrictTypeName() {
		return districtTypeName;
	}

	public void setDistrictTypeName(String districtTypeName) {
		this.districtTypeName = districtTypeName;
	}
	
	
	
	
}
