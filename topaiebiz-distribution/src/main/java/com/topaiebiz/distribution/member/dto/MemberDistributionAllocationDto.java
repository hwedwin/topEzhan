package com.topaiebiz.distribution.member.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Description： 会员分销Dto。 
 * 
 * Author Harry
 *    
 * Date 2017年10月5日 下午3:57:28 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberDistributionAllocationDto {
	
	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;

	/** 所属店铺*/
	private Long storeId;
	
	/** 分销级别 */
	@NotNull(message = "{validation.memberDistributionAllocation.distriLevel}")
	private String distriLevel;
	
	/** 商品skuId */
	private Long skuId;
	
	/** 分销比例 */
	@NotNull(message = "{validation.memberDistributionAllocation.ratio}")
	private Double ratio;
	
	/** 开始时间 */
	private Date startTime;
	
	/** 结束时间 */
	private Date endTime;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getDistriLevel() {
		return distriLevel;
	}

	public void setDistriLevel(String distriLevel) {
		this.distriLevel = distriLevel;
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
