package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;

/**
 * 
 * Description： 礼卡消费记录的DTO.
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年10月11日 上午9:34:19
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ExpenseCalendarDto {

	/** 礼卡主键 */
	private Long id;

	/** 礼卡编号 */
	private String cardNo;

	/** 商品所属店铺名称 */
	private String storeName;

	/** 所购买商品名称 */
	private String goodsName;

	/** 礼卡余额 */
	private Double balance;

	/** 扣款 */
	private Double deduction;

	/** 退款金额 */
	private Double refundPrice;
	
	/**商品ID*/
	private Long goodsId;

	/** 消费时间 */
	private Date consumptionTime;

	/**购买的商品数量*/
	private Long goodsNum;
	
	/**单个商品优惠后的价格*/
	private Double salePrice;
	
	
	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Double getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(Double refundPrice) {
		this.refundPrice = refundPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getDeduction() {
		return deduction;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	public Date getConsumptionTime() {
		return consumptionTime;
	}

	public void setConsumptionTime(Date consumptionTime) {
		this.consumptionTime = consumptionTime;
	}

}
