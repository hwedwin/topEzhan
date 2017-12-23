package com.topaiebiz.transaction.order.merchant.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 退货订单详情  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月25日 上午11:24:31 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_return_order_detail")
public class ReturnOrderDetail extends BaseBizEntity<Long> {

	/*** 版本化序列号*/
	private static final long serialVersionUID = -1219746052569128841L;
	
	/*** 订单编号*/
	private Long orderId;
	
	/*** 商品编码*/
	private Long goodsId;
	
	/*** 商品名称*/
	private String name;
	
	/*** 商品属性集*/
	private String fieldValue;
	
	/*** 商品图片*/
	private String goodsImage;
	
	/*** 商品数量*/
	private Long goodsNum;
	
	/*** 商品原总价*/
	private Double toalPrice;
	
	/*** 状态*/
	private Integer state;
	
	/*** 物流类型*/
	private String logisticsType;
	
	/*** 单种商品的物流编号，如果分批次发可记录多个*/
	private String logisticsNo;

	public ReturnOrderDetail() {
		super();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getToalPrice() {
		return toalPrice;
	}

	public void setToalPrice(Double toalPrice) {
		this.toalPrice = toalPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

}
