package com.topaiebiz.member.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.point.dto.MemberPointLogDto;
import com.topaiebiz.member.point.entity.MemberPointLogEntity;
/**
 * 
 * Description： 描述会员积分的接口，并向会员积分控制层提供相关的方法。 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年11月28日 下午1:37:49 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemberPointDao extends BaseDao<MemberPointLogEntity> {
	
	/**
	 * 
	 * Description：查询会员总积分 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	MemberPointLogDto selectMemberPointSum(Long memberId);
	/**
	 * 
	 * Description：查询会员积分获取记录    
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPointLogDto> selectMemberPointLog(Long memberId);
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
	MemberPointLogEntity selectByGainType1(MemberPointLogEntity memberPointLogEntity);
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
	List<MemberPointLogEntity> selectByGainType2(MemberPointLogEntity memberPointLogEntity);
	/**
	 * 
	 * Description： 退还会员积分
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberPointLogDto
	 * @return
	 */
	MemberPointLogDto returnMemberPointLog(MemberPointLogDto memberPointLogDto);
	
}
