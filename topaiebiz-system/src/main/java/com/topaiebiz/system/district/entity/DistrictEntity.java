/**
 * 
 */
package com.topaiebiz.system.district.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： TODO (描述Java类的用途或使用说明。)
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月19日 上午11:57:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_sys_district")
public class DistrictEntity extends BaseBizEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 2114049483694256180L;

	private Long id;

	/**
	 * 区域信息的唯一编码。
	 */
	private String code;

	/**
	 * 区域的全名。
	 */
	private String fullName;

	/**
	 * 区域的简称。
	 */
	private String shortName;

	/**
	 * 区域的类型。
	 */
	private Long districtTypeId;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
