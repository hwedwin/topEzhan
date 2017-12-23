package com.topaiebiz.distribution.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description：商品分销记录实体类
 * 
 * Author Harry
 * 
 * Date 2017年10月6日 下午8:05:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dis_goods_distribution_log")
public class GoodsDistributionLogEntity extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3119175325700932914L;

	/** 所属商家 */
	private Long merchantId;

	/** 所属店铺 */
	private Long storeId;

	/** 店铺等级 */
	private Long storeGrade;

	/** 商品SKU */
	private Long skuId;

	/** 分润比例 */
	private Double ratio;

	/** 分润日期 */
	private Date distriDate;

	/** 销售数量 */
	private Long saleNum;

	/** 单价 */
	private Double price;

	/** 总金额 */
	private Double totalPrice;

	/** 分润金额 */
	private Double distriPrice;

	/** 结算状态 (1为已结算 0为未结算) */
	private Integer settleState;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(Long storeGrade) {
		this.storeGrade = storeGrade;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Date getDistriDate() {
		return distriDate;
	}

	public void setDistriDate(Date distriDate) {
		this.distriDate = distriDate;
	}

	public Long getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDistriPrice() {
		return distriPrice;
	}

	public void setDistriPrice(Double distriPrice) {
		this.distriPrice = distriPrice;
	}

	public Integer getSettleState() {
		return settleState;
	}

	public void setSettleState(Integer settleState) {
		this.settleState = settleState;
	}

}
