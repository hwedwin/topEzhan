package com.topaiebiz.transaction.order.total.dto;

import java.util.Date;

/**
 * 
 * Description 平台订单&订单三方支付记录dto  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月5日 上午11:15:56 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PlatformPayOrderLogDto{
	
	/** 平台支付订单id*/
	private Long id;
	
	/** 单据类型.(1 购买商品 2 退货 3 美礼卡购买)*/
	private Short orderType;
	
	/** 支付状态*/
	private Integer payState;
	
	/** 1微信 2支付宝 3等 可以写到数据字典里*/
	private String platformPayType;
	
	/** 支付时间*/
	private Date payTime;
	
	/** 商品的标题/交易标题/订单标题/订单关键字等*/
	private String subject;
	
	/** 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。*/
	private String body;
	
	/** 总金额*/
	private Double totalPrice;
	
	/** 会员id*/
	private Long memberId;
	
	/** 支付交易号*/
	private String payCallbackNo;
	
	/** 美礼卡支付金额*/
	private Double cardPrice;
	
	/** 平台优惠活动金额*/
	private Double platformDeduction;
	
	/** 积分抵扣金额*/
	private Double integralPrice;
	
	/** 支付金额*/
	private Double payPrice;
	
	/** 平台订单备注*/
	private String platformMemo;
	
	
	
	
	/** 订单三方支付记录id*/
	private Long payOrderLogId;

	/** 1微信 2支付宝 3等 可以写到数据字典里*/
	private String payType;
	
	/** 是否成功*/
	private Integer successState;
	
	/** 金额*/
	private Double money;
	
	/** 对方账号*/
	private String otherAccount;
	
	/** 三方支付记录备注*/
	private String payOrderMemo;
	
	
	
	/** 会员编号*/
	private String memberCode;
	
	/** 会员名称*/
	private String userName;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}

	public String getPlatformPayType() {
		return platformPayType;
	}

	public void setPlatformPayType(String platformPayType) {
		this.platformPayType = platformPayType;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPayCallbackNo() {
		return payCallbackNo;
	}

	public void setPayCallbackNo(String payCallbackNo) {
		this.payCallbackNo = payCallbackNo;
	}

	public Double getCardPrice() {
		return cardPrice;
	}

	public void setCardPrice(Double cardPrice) {
		this.cardPrice = cardPrice;
	}

	public Double getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(Double integralPrice) {
		this.integralPrice = integralPrice;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public String getPlatformMemo() {
		return platformMemo;
	}

	public void setPlatformMemo(String platformMemo) {
		this.platformMemo = platformMemo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getSuccessState() {
		return successState;
	}

	public void setSuccessState(Integer successState) {
		this.successState = successState;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public String getPayOrderMemo() {
		return payOrderMemo;
	}

	public void setPayOrderMemo(String payOrderMemo) {
		this.payOrderMemo = payOrderMemo;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Long getPayOrderLogId() {
		return payOrderLogId;
	}

	public void setPayOrderLogId(Long payOrderLogId) {
		this.payOrderLogId = payOrderLogId;
	}

}
