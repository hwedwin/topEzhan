package com.topaiebiz.member.address.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.member.address.entity.MemberAddressEntity;

/**
 * 
 * Description： 描述会员收货地址的接口，并向会员收货地址控制层提供相关的方法。 
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
public interface MemberAddressDao extends BaseDao<MemberAddressEntity>{
	/**
	 * 
	 * Description：删除会员收货地址    
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 */
	void deleteMemberAddress(Long id);
	/**
	 * 
	 * Description： 将默认地址清空 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 */
	void updateMemberAddressByMemberId(Long memberId);
	/**
	 * 
	 * Description： 会员收货地址列表  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	List<MemberAddressDto> selectMemberAddress(Long memberId);

	/**
	 * 
	 * Description： 查询出会员的默认地址
	 * 
	 * Author hxpeng   
	 * 
	 * @param memberId
	 * @return
	 */
	MemberAddressEntity findDefaultAddressByMemberId(Long memberId);
}
