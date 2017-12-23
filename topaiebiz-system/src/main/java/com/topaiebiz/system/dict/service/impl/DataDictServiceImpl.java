package com.topaiebiz.system.dict.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.dict.dao.DataDictDao;
import com.topaiebiz.system.dict.dto.DataDictDto;
import com.topaiebiz.system.dict.entity.DataDictEntity;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;
import com.topaiebiz.system.dict.exception.DataDictTypeExceptionEnum;
import com.topaiebiz.system.dict.service.DataDictService;

/**
 * Description: 业务逻辑层实现类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午8:41:04
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class DataDictServiceImpl implements DataDictService {

	@Autowired
	private DataDictDao dataDictDao;

	public List<DataDictTypeEntity> getDataDictTypeById() {
		return dataDictDao.selectDataDictTypeById();
	}

	public Integer saveDataDict(DataDictEntity entity) throws GlobalException {
		/** 数据字典的编码进行重复验证 */
		DataDictEntity findDataDictByDictCode = dataDictDao.selectDataDictByDictCodes(entity);
		if (findDataDictByDictCode != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_DICTCODE_NOT_REPETITION);
		}
		/** 对数据字典的值重复验证 */
		DataDictEntity findDataDictByDictValue = dataDictDao.selectDataDictByDictValues(entity);
		if (findDataDictByDictValue != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_DICTVALUE_NOT_REPETITION);
		}
		entity.setCreatedTime(new Date());
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		return dataDictDao.insert(entity);
	}

	@Override
	public DataDictEntity getDataDictById(Long id) {
		return dataDictDao.selectDataDictById(id);
	}

	@Override
	public Integer modifyDataDictById(DataDictDto dto) throws GlobalException {
		/** 数据字典的编码进行重复验证 */
		DataDictEntity findDataDictByDictCode = dataDictDao.selectDataDictByDictCode(dto);
		if (findDataDictByDictCode != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_DICTCODE_NOT_REPETITION);
		}
		/** 对数据字典的值重复验证 */
		DataDictEntity findDataDictByDictValue = dataDictDao.selectDataDictByDictValue(dto);
		if (findDataDictByDictValue != null) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_DICTVALUE_NOT_REPETITION);
		}
		DataDictEntity dataDict = dataDictDao.selectById(dto.getId());
		BeanUtils.copyProperties(dto, dataDict);
		dataDict.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		dataDict.setLastModifiedTime(new Date());
		return dataDictDao.updateById(dataDict);
	}

	@Override
	public Page<DataDictDto> getDataDictList(Page<DataDictDto> page, DataDictDto dataDictDto) throws GlobalException {
		page.setRecords(dataDictDao.selectDataDictList(page, dataDictDto));
		return page;
	}

	@Override
	public Integer removeDataDictByIds(Long[] id) throws GlobalException {
		for (Long long1 : id) {
			/** 对id进行查询 */
			DataDictEntity selectDataDictById = dataDictDao.selectDataDictById(long1);
			if (selectDataDictById == null) {
				throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_ID_NOT_NULL);
			}
		}
		return dataDictDao.deleteDataDictByIds(id);
	}

	@Override
	public Integer removeDataDictById(Long id) {
		return dataDictDao.deleteDataDictById(id);
	}

}
