package com.topaiebiz.settlement.store.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： 商家结算周期Entity。 
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午4:50:39 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_set_store_settlement_cycle")
public class StoreSettlementCycleEntity extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 2597487733472125946L;
	
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
