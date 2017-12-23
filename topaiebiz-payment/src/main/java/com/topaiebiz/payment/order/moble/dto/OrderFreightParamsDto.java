package com.topaiebiz.payment.order.moble.dto;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/27 12:58
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderFreightParamsDto {

	// 商品所属物流模板
	private long logisticsId;

	// 商品数量
	private int goodsNum;

	// 单个商品重量/体积
	private double weightOrBulk;

	public long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(long logisticsId) {
		this.logisticsId = logisticsId;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public double getWeightOrBulk() {
		return weightOrBulk;
	}

	public void setWeightOrBulk(double weightOrBulk) {
		this.weightOrBulk = weightOrBulk;
	}
}
