package com.topaiebiz.settlement.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.settlement.member.dto.MemberSettlementDto;
import com.topaiebiz.settlement.member.entity.MemberSettlementEntity;

/**
 * Description： 会员结算Dao。
 * 
 * Author Harry
 * 
 * Date 2017年10月31日 下午5:34:42
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemeberSettlementDao extends BaseDao<MemberSettlementEntity> {

	/**
	 * Description： 平台端会员分销结算分页列表搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * 
	 * @param memberSettlementDto
	 * @return
	 */
	List<MemberSettlementDto> selectPlatMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 根据会员id查询上级会员的名称
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 * @return
	 */
	String selectParentNameByMemberId(Long memberId);

	/**
	 * Description： 商家端会员结算分页列表加搜索.
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * 
	 * @param memberSettlementDto
	 * @return
	 */
	List<MemberSettlementDto> selectMerchantMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 平台端会员分销结算分页列表搜索根据会员id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * 
	 * @param memberSettlementDto
	 * @return
	 */
	List<MemberSettlementDto> selectPlatMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);

	/**
	 * Description： 商家端会员分销结算分页列表加分页加搜索根据会员id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * 
	 * @param memberSettlementDto
	 * @return
	 */
	List<MemberSettlementDto> selectMerchantMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto);


}
