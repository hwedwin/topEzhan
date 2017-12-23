package com.topaiebiz.member.upgrade.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.upgrade.entity.MemberUpgradeLogEntity;
/**
 * 
 * Description： 描述会员成长值记录的接口，并向会员成长值记录控制层提供相关的方法。 
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
public interface MemberUpgradeLogDao extends BaseDao<MemberUpgradeLogEntity>{
	
}
