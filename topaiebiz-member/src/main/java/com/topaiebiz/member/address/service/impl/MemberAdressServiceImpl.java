package com.topaiebiz.member.address.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.address.dao.MemberAddressDao;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.member.address.entity.MemberAddressEntity;
import com.topaiebiz.member.address.exception.MemberAddressExceptionEnum;
import com.topaiebiz.member.address.service.MemberAddressService;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;

@Service
public class MemberAdressServiceImpl implements MemberAddressService {
	
	@Autowired
	private MemberAddressDao memberAddressDao;
	/**区域 数据持久性接口*/
	@Autowired
	private DistrictDao districtDao;

	@Override
	public Integer saveMemberAddress(MemberAddressEntity entity) {
		entity.setCreatorId(entity.getId());
		entity.setCreatedTime(new Date());
		return memberAddressDao.insert(entity);
	}

	@Override
	public Integer modifyMemberAddress(MemberAddressDto memberAddressDto) throws GlobalException {
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(memberAddressDto.getId());
		/** 判断收货地址是否存在*/
		if(null == memberAddressEntity) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_EXIST);
		}
		/** 判断收货地址是否不是默认收货地址*/
		if(null !=memberAddressDto.getIsDefault()) {
			memberAddressDao.updateMemberAddressByMemberId(memberAddressEntity.getMemberId());
			memberAddressDto.setIsDefault(1);
		}
		BeanUtils.copyProperties(memberAddressDto,memberAddressEntity);
		memberAddressEntity.setLastModifierId(memberAddressDto.getId());
		memberAddressEntity.setLastModifiedTime(new Date());
		return memberAddressDao.updateById(memberAddressEntity);
	}

	@Override
	public MemberAddressEntity findMemberAddressById(Long id) throws GlobalException {
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(id);
		/** 判断收货地址是否存在*/
		if(null == memberAddressEntity) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_EXIST);
		}
		return memberAddressEntity;
	}

	@Override
	public void removeMemberAddress(Long id) throws GlobalException {
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(id);
		/** 判断收货地址是否存在*/
		if(null == memberAddressEntity) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_EXIST);
		}
		memberAddressDao.deleteMemberAddress(id);
	}

	@Override
	public List<MemberAddressDto> listMemberAddress(Long memberId) {
		List<MemberAddressDto> selectMemberAddress = memberAddressDao.selectMemberAddress(memberId);
		for(MemberAddressDto memberAddressDto:selectMemberAddress) {
			Long districtId = memberAddressDto.getDistrictId();
			/**根据区id查询市名称*/
			DistrictEntity districtEntity = districtDao.selectById(districtId);
			DistrictEntity selectById = districtDao.selectById(districtEntity.getParentDistrictId());
			/**根据市id查询省对象*/
			memberAddressDto.setDistrictName( districtEntity.getFullName());
			memberAddressDto.setProvinceName(selectById.getParentDistrictName());
			memberAddressDto.setCityId(selectById.getId());
			memberAddressDto.setCityName(selectById.getFullName()); 
		}
		return selectMemberAddress;
	}

}
