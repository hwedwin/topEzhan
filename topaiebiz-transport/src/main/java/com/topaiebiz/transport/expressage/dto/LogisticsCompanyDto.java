package com.topaiebiz.transport.expressage.dto;

/**
 * Description TODO 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月24日 下午5:11:57 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class LogisticsCompanyDto {

	/**快递公司编码。*/
	private String comCode; 

	/**快递公司名称。*/
	private String comName;

	/**说明。*/
	private String description;
	
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
