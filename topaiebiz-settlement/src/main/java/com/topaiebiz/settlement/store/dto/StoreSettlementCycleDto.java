package com.topaiebiz.settlement.store.dto;

import java.util.Date;

/**
 * Description：商家结算周期Dto。
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午5:06:47 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class StoreSettlementCycleDto {
	
	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;
	
	/**商家ID*/
	private Long merchantId;
	
	/**店铺ID*/
	private Long storeId;
	
	/**周期类型 1 销售结算  2 分润结算*/
	private Integer cycleType;
	
	/**结算周期*/
	private Long settleCycle;
	
	/**下个结算日。*/
	private Date nextSettleDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}

	public Long getSettleCycle() {
		return settleCycle;
	}

	public void setSettleCycle(Long settleCycle) {
		this.settleCycle = settleCycle;
	}

	public Date getNextSettleDate() {
		return nextSettleDate;
	}

	public void setNextSettleDate(Date nextSettleDate) {
		this.nextSettleDate = nextSettleDate;
	}
	

}
