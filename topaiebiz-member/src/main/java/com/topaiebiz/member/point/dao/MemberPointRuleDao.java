package com.topaiebiz.member.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.point.dto.MemberPointRuleDto;
import com.topaiebiz.member.point.entity.MemberPointRuleEntity;
/**
 * 
 * Description： 描述会员积分规则的接口，并向会员积分规则控制层提供相关的方法。 
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
public interface MemberPointRuleDao extends BaseDao<MemberPointRuleEntity> {
	
	/**
	 * 
	 * Description： TODO 会员积分规则列表 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	List<MemberPointRuleDto> selectMemberPointRule();
	


}
