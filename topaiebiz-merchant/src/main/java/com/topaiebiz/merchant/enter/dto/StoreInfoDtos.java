package com.topaiebiz.merchant.enter.dto;

import javax.validation.constraints.NotNull;

public class StoreInfoDtos {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 所属商家ID */
	private Long merchantId;

	/** 店铺名称 */
	private String name;

	/** 选择店铺类型 */
	@NotNull(message = "{validation.storeInfo.merchantType}")
	private Integer merchantType;

	/** 选择商品的第三极类目 */
	private Long[] ids;

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
