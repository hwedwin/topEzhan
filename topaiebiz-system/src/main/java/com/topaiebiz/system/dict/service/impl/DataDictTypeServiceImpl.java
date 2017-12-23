package com.topaiebiz.system.dict.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.dict.dao.DataDictTypeDao;
import com.topaiebiz.system.dict.dto.DataDictTypeDto;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;
import com.topaiebiz.system.dict.exception.DataDictTypeExceptionEnum;
import com.topaiebiz.system.dict.service.DataDictTypeService;

/**
 * Description: 业务逻辑层实现类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月24日 下午4:10:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class DataDictTypeServiceImpl implements DataDictTypeService {

	@Autowired
	private DataDictTypeDao dataDictTypeDao;

	public Integer saveDataDictType(DataDictTypeEntity entity) throws GlobalException {
		/** 数据字典类型的名称进行重复验证 */
		DataDictTypeEntity findDataDictTypeByTypeName = dataDictTypeDao
				.selectDataDictTypeByTypeNames(entity);
		if (findDataDictTypeByTypeName != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_TYPENAME_NOT_REPETITION);
		}
		/** 对数据字典唯一编码重复验证 */
		DataDictTypeEntity findDataDictTypeByTypeCode = dataDictTypeDao
				.selectDataDictTypeByTypeCodes(entity);
		if (findDataDictTypeByTypeCode != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_TYPECODE_NOT_REPETITION);
		}
		entity.setCreatedTime(new Date());
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		return dataDictTypeDao.insert(entity);
	}

	public boolean removeDataDictTypeById(Long id) {
		return dataDictTypeDao.deleteDataDictTypeById(id);
	}

	public DataDictTypeEntity getDataDictTypeById(Long id) {
		return dataDictTypeDao.selectDataDictTypeById(id);
	}

	public Integer modifyDataDictType(DataDictTypeDto dto) throws GlobalException {
		/** 数据字典类型的名称进行重复验证 */
		DataDictTypeEntity findDataDictTypeByTypeName = dataDictTypeDao
				.selectDataDictTypeByTypeName(dto);
		if (findDataDictTypeByTypeName != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_TYPENAME_NOT_REPETITION);
		}
		/** 对数据字典唯一编码重复验证 */
		DataDictTypeEntity findDataDictTypeByTypeCode = dataDictTypeDao
				.selectDataDictTypeByTypeCode(dto);
		if (findDataDictTypeByTypeCode != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_TYPECODE_NOT_REPETITION);
		}
		DataDictTypeEntity dataDictType = dataDictTypeDao.selectById(dto.getId());
		BeanUtils.copyProperties(dto, dataDictType);
		dataDictType.setLastModifiedTime(new Date());
		dataDictType.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return dataDictTypeDao.updateById(dataDictType);
	
	}

	public Page<DataDictTypeDto>getDataDictTypeList(Page<DataDictTypeDto> page,
			DataDictTypeDto dataDictTypeDto) {
		page.setRecords(dataDictTypeDao.selectDataDictTypeList(page, dataDictTypeDto));
		return page;
	}

	public Integer removeDataDictTypeByIds(Long[] id) throws GlobalException {
		for (Long long1 : id) {
			/** 对id进行查询 */
			DataDictTypeEntity selectDataDictTypeById = dataDictTypeDao.selectDataDictTypeById(long1);
			if (selectDataDictTypeById == null) {
				throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_ID_NOT_EXIST);
			}
		}
		return dataDictTypeDao.deleteDataDictTypeByIds(id);
	}

}
