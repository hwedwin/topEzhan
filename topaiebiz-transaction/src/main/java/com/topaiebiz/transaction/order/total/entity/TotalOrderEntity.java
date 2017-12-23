package com.topaiebiz.transaction.order.total.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

import java.util.Date;

/**
 * 
 * Description 平台支付订单
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年8月31日 上午11:31:46
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_total_order")
public class TotalOrderEntity extends BaseBizEntity<Long> {

	/** 序列化版本号 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7553882818229374595L;

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

	/**
	 * 订单备注
	 */
	private String memo;

	public TotalOrderEntity() {
		super();
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Long getPlatformPromotion() {
		return platformPromotion;
	}

	public void setPlatformPromotion(Long platformPromotion) {
		this.platformPromotion = platformPromotion;
	}

	public Long getUsageScore() {
		return usageScore;
	}

	public void setUsageScore(Long usageScore) {
		this.usageScore = usageScore;
	}

	public Double getScorePrice() {
		return scorePrice;
	}

	public void setScorePrice(Double scorePrice) {
		this.scorePrice = scorePrice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
