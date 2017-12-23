package com.topaiebiz.system.dict.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.dict.dto.DataDictTypeDto;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;

/**
 * Description: 数据字典业务逻辑层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月24日 下午4:07:35
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface DataDictTypeService {

	/**
	 * Description：添加数据字典类型
	 * 
	 * Author: Anthony
	 * 
	 * param entity 数据字典类型实体类对象
	 * 
	 * return 返回执行成功与否结果参数
	 */
	Integer saveDataDictType(DataDictTypeEntity entity) throws GlobalException;

	/**
	 * Description：删除数据字典类型(修改状态)。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	boolean removeDataDictTypeById(Long id) throws GlobalException;

	/**
	 * Description： 查看数据字典类型(根据Id查看数据字典类型数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 数据字典类型信息
	 */
	DataDictTypeEntity getDataDictTypeById(Long id) throws GlobalException;

	/**
	 * Description：编辑(修改)数据字典类型。
	 * 
	 * Author: Anthony
	 * 
	 * param entity 数据字典实体类对象
	 * 
	 * return 返回结果信息提示
	 * 
	 * throws GlobalException 全局异常类
	 */
	Integer modifyDataDictType(DataDictTypeDto dto) throws GlobalException;

	/**
	 * Description：数据字典类型分页检索
	 * 
	 * Author: Anthony
	 * 
	 * @param page
	 * @param dictionaryTypeDto
	 * @return
	 */
	Page<DataDictTypeDto> getDataDictTypeList(Page<DataDictTypeDto> page, DataDictTypeDto dataDictTypeDto);

	/**
	 * Description：批量删除数据字典类型（修改状态）
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 执行成功与否参数信息
	 * 
	 * throws GlobalException 全局异常类
	 */
	Integer removeDataDictTypeByIds(Long[] id) throws GlobalException;

}
