package com.topaiebiz.distribution.member.dto;

import java.util.List;

/**
 * Description：会员分销(配置分销比例)添加时Dto。 
 * 
 * Author Harry
 *    
 * Date 2017年10月14日 下午7:58:13 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberDisributionAndItemDto {
	
	/**商品itemId*/
	private Long itemId;
	
	/**会员分销集合*/
	private List<MemberDistributionAllocationDto> memberDistributionAllocationDtos;

	public Long getItemId() { 
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public List<MemberDistributionAllocationDto> getMemberDistributionAllocationDtos() {
		return memberDistributionAllocationDtos;
	}

	public void setMemberDistributionAllocationDtos(
			List<MemberDistributionAllocationDto> memberDistributionAllocationDtos) {
		this.memberDistributionAllocationDtos = memberDistributionAllocationDtos;
	}
	

}
