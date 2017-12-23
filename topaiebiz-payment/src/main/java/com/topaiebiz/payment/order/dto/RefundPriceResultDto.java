package com.topaiebiz.payment.order.dto;

import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;

import java.util.List;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/23 19:04
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class RefundPriceResultDto {

	// 订单详情集合
	private List<OrderDetailsDto> orderDetailsDtoList;

	// 总订单ID
	private long totalOrderId;

	// 店铺订单ID
	private long storeOrderId;

	// 总退款金额
	private double refundPrice;

	// 总退款美礼卡额度
	private double refundCardPrice;

	// 总退款积分
	private int refundIntegral;

	// 总退款积分抵扣金额
	private double refundIntegralPrice;

	private List<RefundGoodsInfo> refundGoodsInfoList;

	//退款详情 内部类
	public static class RefundGoodsInfo {

		/**
		 * 支付店铺订单详情ID
		 */
		private Long orderDetailId;

		/** 商品id*/
		private Long goodsId;

		/** 商品名称*/
		private String name;

		/** 商品属性集*/
		private String fieldValue;

		/** 商品图片*/
		private String goodsImage;

		/** 商品数量*/
		private Long goodsNum;

		private double refundPrice;

		public Long getOrderDetailId() {
			return orderDetailId;
		}

		public void setOrderDetailId(Long orderDetailId) {
			this.orderDetailId = orderDetailId;
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

		public double getRefundPrice() {
			return refundPrice;
		}

		public void setRefundPrice(double refundPrice) {
			this.refundPrice = refundPrice;
		}
	}

	public List<OrderDetailsDto> getOrderDetailsDtoList() {
		return orderDetailsDtoList;
	}

	public void setOrderDetailsDtoList(List<OrderDetailsDto> orderDetailsDtoList) {
		this.orderDetailsDtoList = orderDetailsDtoList;
	}

	public long getTotalOrderId() {
		return totalOrderId;
	}

	public void setTotalOrderId(long totalOrderId) {
		this.totalOrderId = totalOrderId;
	}

	public long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public double getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(double refundPrice) {
		this.refundPrice = refundPrice;
	}

	public double getRefundCardPrice() {
		return refundCardPrice;
	}

	public void setRefundCardPrice(double refundCardPrice) {
		this.refundCardPrice = refundCardPrice;
	}

	public int getRefundIntegral() {
		return refundIntegral;
	}

	public void setRefundIntegral(int refundIntegral) {
		this.refundIntegral = refundIntegral;
	}

	public double getRefundIntegralPrice() {
		return refundIntegralPrice;
	}

	public void setRefundIntegralPrice(double refundIntegralPrice) {
		this.refundIntegralPrice = refundIntegralPrice;
	}

	public List<RefundGoodsInfo> getRefundGoodsInfoList() {
		return refundGoodsInfoList;
	}

	public void setRefundGoodsInfoList(List<RefundGoodsInfo> refundGoodsInfoList) {
		this.refundGoodsInfoList = refundGoodsInfoList;
	}
}
