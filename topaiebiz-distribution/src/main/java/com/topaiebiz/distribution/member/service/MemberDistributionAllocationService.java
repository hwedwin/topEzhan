package com.topaiebiz.distribution.member.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.dto.MemberDistributionAllocationDto;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.member.entity.MemberDistributionAllocationEntity;

/**
 * Description：会员分销接口。
 * 
 * Author Harry
 * 
 * Date 2017年10月5日 下午3:33:35
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MemberDistributionAllocationService {

	/**
	 * Description：添加会员分销。
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * @param memberDistributionAllocationEntitis
	 * @return
	 * @throws GlobalException
	 */
	Integer saveMemberDistributionAllocation(Long itemId,
			List<MemberDistributionAllocationEntity> memberDistributionAllocationEntitis) throws GlobalException;

	/**
	 * Description： 定时任务计算会员的分销记录。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	Integer saveMemberDistributionLog();

	/**
	 * 
	 * Description：平台端会员分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销列表加分页
	 * @return
	 */

	Page<MemberDistributionLogDto> getMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description： 商家端会员分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	Page<MemberDistributionLogDto> getMerchantMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description： 商家端根据memberId查询会员分销商品的记录。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	Page<MemberDistributionLogDto> findMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description： 根据会员id查询会员子集与下级分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	Page<MemberDistributionLogDto> findMembersDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);
	/**
	 * Description： 根据itemId查询会员分销比例(回显数据)。
	 * 
	 * Author Harry 
	 * 
	 * @param itemId
	 * @return
	 * @throws GlobalException 
	 */
	List<MemberDistributionAllocationDto> findMemberDistributionAllocationByItemId(Long itemId) throws GlobalException;

}
