package com.topaiebiz.distribution.merchant.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Description： 商品分销Dto。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:10:27
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsDistributionDto {

	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;

	/** 商品SKU主键 */
	private Long skuId;

	/** 店铺等级 */
	@NotNull(message = "{validation.goodsDistribution.storeGradeId}")
	private String storeGradeId;

	/** 分润比例 */
	@NotNull(message = "{validation.goodsDistribution.ratio}")
	private Double ratio;

	/** 开始时间 */
	private Date startTime;

	/** 截止时间 */
	private Date endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStoreGradeId() {
		return storeGradeId;
	}

	public void setStoreGradeId(String storeGradeId) {
		this.storeGradeId = storeGradeId;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
