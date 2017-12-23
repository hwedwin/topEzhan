package com.topaiebiz.system.moble.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.entity.TokenEntity;

/**
 * Description app令牌dao 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午2:33:28 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {

	/**
	 * Description 根据用户名查询token信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param telephone
	 * @return
	 */
	TokenEntity selectByTelephone(String telephone);
	
	/**
	 * Description 根据token查询token信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @return
	 */
	TokenEntity selectByToken(String token);
	
	/**
	 * Description 根据token查询 会员信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @return
	 */
	TokenDto selectDetailByToken(String token);

}
