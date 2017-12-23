/**
 * 
 */
package com.topaiebiz.system.district.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.district.dao.DistrictTypeDao;
import com.topaiebiz.system.district.dto.DistrictTypeDto;
import com.topaiebiz.system.district.entity.DistrictTypeEntity;
import com.topaiebiz.system.district.exception.DistrictExceptionEnum;
import com.topaiebiz.system.district.service.DistrictTypeService;
import com.topaiebiz.system.security.util.SecurityContextUtils;

/**
 * Description： 区域类型 业务逻辑实现类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月19日 下午2:23:22
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Service
public class DistrictTypeServiceImpl implements DistrictTypeService {

	@Autowired
	private DistrictTypeDao districtTypeDao;

	@Override
	public List<DistrictTypeEntity> getAllDistrictTypeData() {
		return districtTypeDao.selectDistrictType();
	}

	@Override
	public Integer deleteDistrictTypeDataById(Long id) {
		return districtTypeDao.deleteDistrictTypeById(id);
	}

	@Override
	public Integer createDistrictType(DistrictTypeDto districtTypeDto) throws GlobalException {
		String code = districtTypeDto.getCode();
		DistrictTypeEntity districtTypeEntity = districtTypeDao.selectOneByCode(code);
		if (null != districtTypeEntity) {
			throw new GlobalException(DistrictExceptionEnum.DISTRICT_TYPE_CODE_CANNOT_BE_REPEATED);
		}
		districtTypeEntity = new DistrictTypeEntity();
		BeanUtils.copyProperties(districtTypeDto, districtTypeEntity);
		districtTypeEntity.setCreatedTime(new Date());
		districtTypeEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		districtTypeDao.insert(districtTypeEntity);

		// 如果存父区域字段存在值，更新该区域的序列号和名称
		Long parentTypeId = districtTypeEntity.getParentTypeId();
		if (null != parentTypeId) {
			DistrictTypeEntity parentDistrictTypeEntity = districtTypeDao.selectById(parentTypeId);
			if (null != parentDistrictTypeEntity) {
				districtTypeEntity.setParentTypeName(parentDistrictTypeEntity.getName());
				String parentDistrictTypeSerialNo = parentDistrictTypeEntity.getSerialNo();
				String parentDistrictTypeSerialName = parentDistrictTypeEntity.getSerialName();
				if (!StringUtils.isEmpty(parentDistrictTypeSerialNo)) {
					districtTypeEntity.setSerialNo(parentDistrictTypeSerialNo + "." + districtTypeEntity.getId());
				}
				if (!StringUtils.isEmpty(parentDistrictTypeSerialName)) {
					districtTypeEntity.setSerialName(parentDistrictTypeSerialName + "->" + districtTypeEntity.getName());
				}
			}
		}else{
			districtTypeEntity.setParentTypeId(0l);
			districtTypeEntity.setParentTypeName(" ");
			districtTypeEntity.setSerialNo(districtTypeEntity.getId().toString());
			districtTypeEntity.setSerialName(districtTypeEntity.getName());
		}
		return districtTypeDao.updateById(districtTypeEntity);
	}

	@Override
	public Integer modifyDistrictType(DistrictTypeDto districtTypeDto) throws GlobalException {
		DistrictTypeEntity districtTypeEntity = districtTypeDao.selectById(districtTypeDto.getId());
		if (null == districtTypeEntity) {
			throw new GlobalException(DistrictExceptionEnum.DISTRICT_TYPE_ID_NOT_EXIST);
		}
		//检查code唯一性
		String code = districtTypeDto.getCode();
		if(!code.equals(districtTypeEntity.getCode())){
			if (null != districtTypeDao.selectOneByCode(code)) {
				throw new GlobalException(DistrictExceptionEnum.DISTRICT_TYPE_CODE_CANNOT_BE_REPEATED);
			}
		}
		// 父区域类型被改，更新序列号和名称
		Boolean seriaChangeFalg = false;
		Long parentDistrictTypeId = districtTypeDto.getParentTypeId();
		if(parentDistrictTypeId == null){
			districtTypeDto.setParentTypeId(0l);
			districtTypeDto.setParentTypeName(" ");
			districtTypeDto.setSerialNo(districtTypeEntity.getId().toString());
			districtTypeDto.setSerialName(districtTypeEntity.getName());
		}else{
			if (!parentDistrictTypeId.equals(districtTypeEntity.getParentTypeId())) {
				seriaChangeFalg = true;
			}
		}
		if(seriaChangeFalg){
			DistrictTypeEntity parentDistrictTypeEntity = districtTypeDao.selectById(parentDistrictTypeId);
			if (null != parentDistrictTypeEntity) {
				districtTypeDto.setParentTypeName(parentDistrictTypeEntity.getName());
				String parentDistrictTypeSerialNo = parentDistrictTypeEntity.getSerialNo();
				String parentDistrictTypeSerialName = parentDistrictTypeEntity.getSerialName();
				if (!StringUtils.isEmpty(parentDistrictTypeSerialNo)) {
					districtTypeDto.setSerialNo(parentDistrictTypeSerialNo + "." + districtTypeEntity.getId());
				}
				if (!StringUtils.isEmpty(parentDistrictTypeSerialName)) {
					districtTypeDto.setSerialName(parentDistrictTypeSerialName + "->" + districtTypeEntity.getName());
				}
			}
		}
		
		BeanUtils.copyProperties(districtTypeDto, districtTypeEntity);
		districtTypeEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		districtTypeEntity.setLastModifiedTime(new Date());
		return districtTypeDao.updateById(districtTypeEntity);
	}

}
