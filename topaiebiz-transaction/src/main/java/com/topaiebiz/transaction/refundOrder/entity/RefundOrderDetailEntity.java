package com.topaiebiz.transaction.refundOrder.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

import java.util.Date;

/**
 * 
 * Description 退货订单详情表
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月24日 下午7:26:57
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_refund_order_detail")
public class RefundOrderDetailEntity extends BaseEntity<Long> {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 4414856189682657512L;

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
	 * 店铺订单详情ID
	 */
	private Long storeOrderDetailId;

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
	 * 创建人编号
	 */
	private Long creatorId;
	
	/**
	 * 创建时间
	 */
	private Date createdTime;

	/**
	 * 寄回退换货的时间
	 */
	private Date shipmentsTime;

	/**
	 * 签收退换货的时间
	 */
	private Date takeTime;

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

	public Long getStoreOrderDetailId() {
		return storeOrderDetailId;
	}

	public void setStoreOrderDetailId(Long storeOrderDetailId) {
		this.storeOrderDetailId = storeOrderDetailId;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public RefundOrderDetailEntity() {
		// TODO Auto-generated constructor stub
	}

	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
}
