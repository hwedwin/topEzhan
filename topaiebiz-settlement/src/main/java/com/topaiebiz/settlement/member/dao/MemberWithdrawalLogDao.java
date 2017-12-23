package com.topaiebiz.settlement.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.settlement.member.entity.MemberWithdrawalLogEntity;

/**
 * Description 会员提现记录dao
 * 
 * Author Hedda
 * 
 * Date 2017年11月13日 下午7:54:47
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface MemberWithdrawalLogDao extends BaseDao<MemberWithdrawalLogEntity> {

	/**
	 * Description 计算每个会员已提现的钱
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	Double selectWithdrawalSum(Long memberId);
	
	/**
	 * Description 根据会员id查询会员信息
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	MemberEntity selectMemberByMemberId(Long memberId);

}
