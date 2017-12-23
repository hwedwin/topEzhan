package com.topaiebiz.transaction.refundOrder.dto;

/**
 * 
 * Description 退货订单详情DTO  
 * 
 * 
 * Author Joe 
 *    
 * Date 2017年10月24日 下午7:35:19 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class RefundOrderDetailDto {

	/**
	 * 支付订单详情ID
	 */
	private Long orderDetailId;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 订单编号
	 */
	private Long orderId;

	/**
	 * 商品编码
	 */
	private Long goodsId;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品属性集
	 */
	private String fieldValue;

	/**
	 * 商品图片
	 */
	private String goodsImage;

	/**
	 * 商品数量
	 */
	private Long goodsNum;

	/**
	 * 退款金额
	 */
	private Double totalPrice;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 商品订单详情的状态
	 */
	private Integer storeOrderDetailState;

	/**
	 * 物流类型
	 */
	private String logisticsType;

	/**
	 * 物流编号
	 */
	private String logisticsNo;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 商品金额
	 */
	private Double goodsPrice;
	
	/**
	 * 商品原价
	 */
	private Double originalPrice;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStoreOrderDetailState() {
		return storeOrderDetailState;
	}

	public void setStoreOrderDetailState(Integer storeOrderDetailState) {
		this.storeOrderDetailState = storeOrderDetailState;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	

}
