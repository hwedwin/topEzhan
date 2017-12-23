package com.topaiebiz.member.reserve.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.reserve.entity.MemberBindAccountEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Description 第三方信息绑定接口基础Dao
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/18 16:13
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MemberBindAccountDao extends BaseDao<MemberBindAccountEntity> {

	/**
	*
	* Description: 根据用户ID查询微信资料
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	MemberBindAccountEntity findOneByMemberIdAndAccountType(@Param(value = "memberId")Long memberId, @Param(value = "accountType")Integer accountType);


	/**
	 *
	 * Description: 根据openId查询用户绑定信息
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/18
	 *
	 * @param:
	 **/
	MemberBindAccountEntity findOneByOpenIdInWechat(@Param(value = "openId") String openId);


	/**
	 *
	 * Description: 根据openId查询用户绑定信息
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/18
	 *
	 * @param:
	 **/
	MemberBindAccountEntity findOneByMemberInWechat(@Param(value = "memberId") Long memberId);
}
