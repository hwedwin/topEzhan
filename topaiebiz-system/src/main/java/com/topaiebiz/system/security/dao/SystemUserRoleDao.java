package com.topaiebiz.system.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.topaiebiz.system.security.entity.SystemUserRoleEntity;

/**
 * Description：系统用户角色映射持久层接口
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年11月27日 下午3:07:33 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface SystemUserRoleDao extends BaseMapper<SystemUserRoleEntity> {

	/**
	 * Description： 根据用户ID查询角色ID  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param userId
	 * @return
	 */
	Long selectByMerchantId(Long userId);

}
