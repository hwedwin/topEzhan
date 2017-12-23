package com.topaiebiz.member.address.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.member.address.entity.MemberAddressEntity;

public interface MemberAddressService {
	/**
	 * 
	 * Description：添加会员收货地址  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param entity
	 * 	会员收货地址entity
	 * @return
	 */
	Integer saveMemberAddress(MemberAddressEntity entity);
	/**
	 * 
	 * Description：修改会员收货地址  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param entity
	 * 	会员收货地址entitDto
	 * @return
	 * @throws GlobalException 
	 */
	Integer modifyMemberAddress(MemberAddressDto memberAddressDto) throws GlobalException;
	/**
	 * 
	 * Description： 根据id查询收货地址信息
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException 
	 */
	MemberAddressEntity findMemberAddressById(Long id) throws GlobalException;
	/**
	 * 
	 * Description： 删除会员收货地址   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * @throws GlobalException
	 */
	void removeMemberAddress(Long id) throws GlobalException;
	/**
	 * 
	 * Description： 会员收货地址列表   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	List<MemberAddressDto> listMemberAddress(Long memberId);


}
