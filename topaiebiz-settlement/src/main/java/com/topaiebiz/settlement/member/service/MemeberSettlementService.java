package com.topaiebiz.settlement.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.settlement.member.dto.MemberSettlementDto;
import com.topaiebiz.settlement.member.entity.MemberWithdrawalLogEntity;

/**
 * Description： 会员结算接口。
 * 
 * Author Harry
 * 
 * Date 2017年10月31日 下午5:34:42
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MemeberSettlementService {

	/**
	 * Description： 平台端会员分销结算分页列表搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	Page<MemberSettlementDto> getPlatMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 商家端会员分销结算分页列表加分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	Page<MemberSettlementDto> getMerchantMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 平台端会员分销结算分页列表搜索根据会员id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	Page<MemberSettlementDto> getPlatMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 商家端会员分销结算分页列表加分页加搜索根据会员id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	Page<MemberSettlementDto> getMerchantMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 定时计算会员结算。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	Integer saveMemeberSettlement();

	/**
	 * Description 添加会员结算记录
	 * 
	 * Author Hedda
	 * 
	 * @param memberWithdrawalLogEntity
	 *            会员记录
	 * @return
	 */
	Integer saveMemberWithdrawalLogDto(MemberWithdrawalLogEntity memberWithdrawalLogEntity)throws GlobalException;

}
