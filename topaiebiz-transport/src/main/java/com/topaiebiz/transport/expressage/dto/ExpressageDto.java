package com.topaiebiz.transport.expressage.dto;

import javax.validation.constraints.NotNull;

/**
 * Description 快递信息DTO
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月18日 上午9:27:56
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ExpressageDto {

	/** 快递公司。 */
	@NotNull(message = "{validation.expressage.company}")
	private String company;

	/** 发出地。 */
	private String from;

	/** 寄往地。 */
	private String to;

	/** 快递编号。 */
	@NotNull(message = "{validation.expressage.number}")
	private String number;

	/** 是否签收。 */
	private String isCheck;

	/** 快递信息数组。 */
	private String[] dataArray;

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String[] getDataArray() {
		return dataArray;
	}

	public void setDataArray(String[] dataArray) {
		this.dataArray = dataArray;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
