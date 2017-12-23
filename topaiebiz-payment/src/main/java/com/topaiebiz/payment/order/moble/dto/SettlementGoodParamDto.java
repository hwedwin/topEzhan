/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

/**
 * Description： 结算商品 参数容器
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月23日 上午10:59:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class SettlementGoodParamDto {

	// 商品ID
	private Long goodId;
		
	// 商品具体SKU ID
	private Long goodSkuId;
		
	// 商品数量
	private Integer goodNum;
	
	// 商品已选择的营销活动ID(单品)
	private Long promotionId;
	
	//物流类型
	private String logisticsType;
	
	//物流编号
	private String logisticsNo;
	
	
	public Long getGoodId() {
		return goodId;
	}
		
	public void setGoodId(Long goodId) {
		this.goodId = goodId;
	}
		
	public Long getGoodSkuId() {
		return goodSkuId;
	}
		
	public void setGoodSkuId(Long goodSkuId) {
		this.goodSkuId = goodSkuId;
	}
		
	public Integer getGoodNum() {
		return goodNum;
	}
		
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
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
