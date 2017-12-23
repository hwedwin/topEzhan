package com.topaiebiz.distribution.merchant.dto;

import java.util.List;

/**
 * Description： 店铺分销添加(配置分销比例)时传参Dto。
 * 
 * Author Harry
 * 
 * Date 2017年10月14日 下午4:25:08
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsDistributionAndItemDto {

	/** 商品itemId */
	private Long[] itemId;

	/** 店铺分销集合 */
	private List<GoodsDistributionDto> goodsDistributionAllocationDtos;

	public Long[] getItemId() {
		return itemId;
	}

	public void setItemId(Long[] itemId) {
		this.itemId = itemId;
	}

	public List<GoodsDistributionDto> getGoodsDistributionAllocationDtos() {
		return goodsDistributionAllocationDtos;
	}

	public void setGoodsDistributionAllocationDtos(List<GoodsDistributionDto> goodsDistributionAllocationDtos) {
		this.goodsDistributionAllocationDtos = goodsDistributionAllocationDtos;
	}

}
