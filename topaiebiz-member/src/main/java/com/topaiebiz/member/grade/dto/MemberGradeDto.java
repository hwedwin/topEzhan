package com.topaiebiz.member.grade.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 
 * Description 会员等级表
 * 
 * 
 * Author Scott
 *    
 * Date 2017年8月28日 下午7:46:17 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberGradeDto {
	
	/** 会员等级的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/*** 会员等级的唯一编码。本字段是从业务角度考虑的，相当于全局的唯一业务主键。*/
	private String  gradeCode;
	
	/*** 会员等级名称。*/
	@NotNull(message = "{validation.membergrade.name}")
	private String  name;
	
	/** 所属店铺。*/
	private Long storeId;
	
	/*** 会员等级的小图标。*/
	private String  smallIcon;
	
	/*** 会员等级的大图标。*/
	private String  bigIcon;
	
	/*** 所需成长值下限，达到该值就是该等级。*/
	@NotNull(message = "{validation.membergrade.needScore}")
	private Long  needScore;
	
	/*** 会员等级说明。*/
	private String  description;
	
	/** 创建时间。取值为系统的当前时间。*/
	private Date createdTime;

	/**备注*/
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	public Long getNeedScore() {
		return needScore;
	}

	public void setNeedScore(Long needScore) {
		this.needScore = needScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
