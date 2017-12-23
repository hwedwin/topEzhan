package com.topaiebiz.system.dict.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.dict.dto.DataDictDto;
import com.topaiebiz.system.dict.entity.DataDictEntity;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;

/**
 * Description: 数据字典信息业务层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午8:38:03
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface DataDictService {

	/**
	 * Description：数据字典类型下拉框展示供添加数据字典信息时选择
	 * 
	 * Author: Anthony
	 * 
	 */
	List<DataDictTypeEntity> getDataDictTypeById();

	/**
	 * Description： 添加数据字典信息
	 * 
	 * Author: Anthony
	 * 
	 * @param entity
	 *            数据字典信息实体类对象
	 * @return saveInteger 添加成功与否的提示信息
	 * @throws GlobalException
	 *             全局统一异常类
	 */
	Integer saveDataDict(DataDictEntity entity) throws GlobalException;

	/**
	 * Description： 查看数据字典信息(根据Id查看数据字典信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 *            数据字典信息id
	 * @return 指定的数据字典信息表数据
	 */
	DataDictEntity getDataDictById(Long id);

	/**
	 * Description：编辑（修改）数据字典信息。
	 * 
	 * Author: Anthony
	 * 
	 * @param entity
	 *            数据字典信息实体类对象
	 * @return 返回结果提示信息
	 */
	Integer modifyDataDictById(DataDictDto dto) throws GlobalException;

	/**
	 * Description： 数据字典信息列表分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * @param page
	 *            分页参数
	 * @param dictionaryDto
	 *            数据字典信息Dto对象
	 * @return list 数据字典信息列表数据
	 */
	Page<DataDictDto> getDataDictList(Page<DataDictDto> page, DataDictDto dataDictDto) throws GlobalException;

	/**
	 * Description：批量删除数据字典信息（修改状态）。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 *            数据字典信息id
	 * @return 执行结果信息参数
	 */
	Integer removeDataDictByIds(Long[] id) throws GlobalException;
    
	/**
	 * Description： 删除数据字典信息(修改状态)。  
	 * 
	 * Author: Anthony   
	 * 
	 * @param id 数据字典信息id
	 * @return 执行成功与否信息参数
	 */
	Integer removeDataDictById(Long id);

}
