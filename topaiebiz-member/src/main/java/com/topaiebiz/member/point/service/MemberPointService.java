package com.topaiebiz.member.point.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.point.dto.MemberPointLogDto;
import com.topaiebiz.member.point.dto.MemberPointRuleDto;
import com.topaiebiz.member.point.entity.MemberPointLogEntity;

public interface MemberPointService {
	/**
	 * 
	 * Description： 会员积分规则列表 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	List<MemberPointRuleDto> listMemberPointRule();
	/**
	 * 
	 * Description：添加会员积分获取记录  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * @param MemberId
	 * @throws GlobalException
	 */
	void saveMemberPointLog(Long id,Long MemberId) throws GlobalException;
	/**
	 * 
	 * Description： 查询会员总积分 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	MemberPointLogDto getMemberPointSum(Long memberId);
	/**
	 * 
	 * Description：查询会员积分获取记录  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPointLogDto> getMemberPointLog(Long memberId);
	/**
	 * 
	 * Description：会员积分使用记录   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @param orderId
	 * @param usageScore
	 * @param deductibleAmount
	 * @throws GlobalException
	 */
	void subtractMemberPoint(Long memberId,Long orderId,Long usageScore,Double deductibleAmount) throws GlobalException;
	/**
	 *
	 * Description：  退还会员积分
	 *
	 * Author Scott.Yang
	 *
	 * @param orderId
	 * @throws GlobalException
	 */
	void returnMemberPoint(Long orderId) throws GlobalException;

	/**
	 *
	 * Description：  退还会员积分
	 *
	 * Author Scott.Yang
	 *
	 * @param orderId
	 * @throws GlobalException
	 */
	void returnMemberPoint(MemberPointLogDto memberPointLogDto) throws GlobalException;

	/**
	 * 
	 * Description： 添加会员积分获取记录  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * @param memberId
	 * @param orderId
	 * @throws GlobalException 
	 */
	void saveMemberPointLogForOrder(Long memberId, Long orderId,Double price) throws GlobalException;
	/**
	 * 
	 * Description： 查询会员积分支出记录
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPointLogDto> getMemberPointSpendingLog(Long memberId);
	/**
	 * 
	 * Description：判断新用户注册是否获取积分 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @param gainType1
	 * @return
	 */
	MemberPointLogEntity selectByGainType1(Long memberId, Long gainType1);
	/**
	 * 
	 * Description： 判断用户今天是否已经签到.
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @param gainType2
	 * @return
	 */
	List<MemberPointLogEntity> selectByGainType2(Long memberId,Long gainType2);
	
}
