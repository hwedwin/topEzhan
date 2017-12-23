package com.topaiebiz.transaction.order.total.dto;

import java.util.Date;

/**
 * Description 平台订单（支付订单） DTO
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月11日 下午2:42:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class TotalOrderDto {

	/** 订单号。 */
	private Long id;

	/** 单据类型.(1 购买商品 2 退货 3 美礼卡购买) */
	private Short orderType;

	/** 会员id */
	private Long memberId;

	/** 支付状态(1失败 2成功) */
	private Integer payState;

	/** 1微信 2支付宝 3等 可以写到数据字典里 */
	private String payType;

	/** 支付时间 */
	private Date payTime;

	/** 支付交易号 */
	private String payCallbackNo;

	/** 商品的标题/交易标题/订单标题/订单关键字等 */
	private String subject;

	/** 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 */
	private String body;

	/** 总金额 */
	private Double totalPrice;

	/** 所用支付级营销活动 */
	private Long platformPromotion;

	/** 平台优惠活动金额 */
	private Double platformDeduction;

	/** 美礼卡支付金额 */
	private Double cardPrice;

	/** 使用积分 */
	private Long usageScore;

	/** 积分抵扣金额 */
	private Double scorePrice;

	/** 实际支付金额 */
	private Double payPrice;

	/** 退货订单ID */
	private Long refundOrderId;

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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayCallbackNo() {
		return payCallbackNo;
	}

	public void setPayCallbackNo(String payCallbackNo) {
		this.payCallbackNo = payCallbackNo;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getPlatformPromotion() {
		return platformPromotion;
	}

	public void setPlatformPromotion(Long platformPromotion) {
		this.platformPromotion = platformPromotion;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}

	public Double getCardPrice() {
		return cardPrice;
	}

	public void setCardPrice(Double cardPrice) {
		this.cardPrice = cardPrice;
	}

	public Long getUsageScore() {
		return usageScore;
	}

	public void setUsageScore(Long usageScore) {
		this.usageScore = usageScore;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Double getScorePrice() {
		return scorePrice;
	}

	public void setScorePrice(Double scorePrice) {
		this.scorePrice = scorePrice;
	}

	public Long getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(Long refundOrderId) {
		this.refundOrderId = refundOrderId;
	}

}
