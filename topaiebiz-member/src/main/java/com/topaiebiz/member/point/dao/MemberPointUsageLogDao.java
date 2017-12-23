package com.topaiebiz.member.point.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.point.dto.MemberPointLogDto;
import com.topaiebiz.member.point.entity.MemberPointUsageLogEntity;
/**
 * 
 * Description： 描述会员积分使用记录的接口，并向会员积分使用记录控制层提供相关的方法。 
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
public interface MemberPointUsageLogDao extends BaseDao<MemberPointUsageLogEntity> {

	/**
	 * 
	 * Description： 根据订单号查询订单记录 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param orderId
	 * @return
	 */
	MemberPointUsageLogEntity selectByOrderId(Long orderId);
	/**
	 * 
	 * Description： 删除会员积分使用记录(逻辑删除)
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 */
	void deletememberPointUsageLog(Long id);
	/**
	 * 
	 * Description：  查询会员积分支出记录  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPointLogDto> getMemberPointSpendingLog(Long memberId);
	

}
