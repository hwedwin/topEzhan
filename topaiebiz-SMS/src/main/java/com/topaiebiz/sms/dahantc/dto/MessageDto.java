package com.topaiebiz.sms.dahantc.dto;

/**
 * Description 信息DTO
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月19日 下午4:33:43
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MessageDto {

	// {"msgid":"","result":"2","desc":"密码错误","failPhones":""}

	private String msgid;

	private String result;

	private String desc;

	private String failPhones;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFailPhones() {
		return failPhones;
	}

	public void setFailPhones(String failPhones) {
		this.failPhones = failPhones;
	}

}
