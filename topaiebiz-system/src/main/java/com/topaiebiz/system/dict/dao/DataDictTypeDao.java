package com.topaiebiz.system.dict.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.dict.dto.DataDictTypeDto;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;

/**
 * Description: 数据字典数据持久层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月24日 下午4:07:00
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface DataDictTypeDao extends BaseDao<DataDictTypeEntity> {

	/**
	 * Description：删除数据字典类型（修改状态）
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 执行成功与否参数信息
	 */
	boolean deleteDataDictTypeById(Long id);

	/**
	 * Description： 查看数据字典类型(根据Id查看数据字典类型数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictTypeEntity selectDataDictTypeById(Long id);

	/**
	 * Description： 数据字典类型分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param page 分页参数
	 * 
	 * param dictionaryTypeDto 数据字典类型Dto对象
	 * 
	 * return 数据字典类型数据信息
	 */
	List<DataDictTypeDto> selectDataDictTypeList(Page<DataDictTypeDto> page, DataDictTypeDto dictionaryTypeDto);

	/**
	 * Description： 批量输出数据字典类型（修改状态）
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 执行成功与否信息参数
	 */
	Integer deleteDataDictTypeByIds(Long[] id);

	/**
	 * Description：根据数据字典类型名称查出数据字典类型的数据
	 * 
	 * Author: Anthony
	 * 
	 * param typeName 数据字典类型名称
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictTypeEntity selectDataDictTypeByTypeName(DataDictTypeDto dto);

	/**
	 * Description： 根据数据字典唯一的编码查出数据字典类型的数据
	 * 
	 * Author: Anthony
	 * 
	 * param typeCode 数据字典类型编码
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictTypeEntity selectDataDictTypeByTypeCode(DataDictTypeDto dto);
    
	/**
	 * Description：根据数据字典类型名称查出数据字典类型的数据
	 * 
	 * Author: Anthony
	 * 
	 * param typeName 数据字典类型名称
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictTypeEntity selectDataDictTypeByTypeNames(DataDictTypeEntity entity);
    
	/**
	 * Description： 根据数据字典唯一的编码查出数据字典类型的数据
	 * 
	 * Author: Anthony
	 * 
	 * param typeCode 数据字典类型编码
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictTypeEntity selectDataDictTypeByTypeCodes(DataDictTypeEntity entity);

	

}
