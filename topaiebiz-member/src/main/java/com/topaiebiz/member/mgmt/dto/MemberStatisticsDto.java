package com.topaiebiz.member.mgmt.dto;

import java.util.Date;

/**
 * 
 * Description： 统计管理(会员分析)Dto  
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年11月2日 下午3:18:05 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberStatisticsDto {

	/*** 注册时间。*/
	private String registerTimeStr;
	
	/*** 注册时间。*/
	private Date registerTime;
	
	/*** 年。*/
	private String years;
	
	/*** 月。*/
	private String months;
	
	/*** 日。*/
	private String days;
	
	/*** 注册量。*/
	private Long record;
	
	/***所属店铺。*/
	private Long storeId;
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getRegisterTimeStr() {
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Long getRecord() {
		return record;
	}

	public void setRecord(Long record) {
		this.record = record;
	}

}
