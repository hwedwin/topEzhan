package com.topaiebiz.system.dict.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.dict.dto.DataDictDto;
import com.topaiebiz.system.dict.entity.DataDictEntity;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;

/**
 * Description: 数据持久层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午8:43:08
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface DataDictDao extends BaseDao<DataDictEntity> {

	/**
	 * Description：数据字典类型下拉框展示供添加数据字典信息时选择。
	 * 
	 * Author: Anthony
	 * 
	 * return 数据字典类型id和typeName数据
	 */
	List<DataDictTypeEntity> selectDataDictTypeById();

	/**
	 * Description：数据字典的编码进行重复验证 。
	 * 
	 * Author: Anthony
	 * 
	 * param dictCode 数据字典的编码
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictEntity selectDataDictByDictCode(DataDictDto dto);

	/**
	 * Description： 对数据字典的值重复验证。
	 * 
	 * Author: Anthony
	 * 
	 * param dictValue 数据字典的值
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictEntity selectDataDictByDictValue(DataDictDto dto);

	/**
	 * Description：查看数据字典信息(根据Id查看数据字典信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典信息id
	 * 
	 * return 数据字典信息表实体类对象
	 */
	DataDictEntity selectDataDictById(Long id);

	/**
	 * Description： 数据字典信息列表分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param page
	 *            分页参数
	 * param dictionaryDto
	 *            数据字典信息dto对象
	 */
	List<DataDictDto> selectDataDictList(Page<DataDictDto> page, DataDictDto dictionaryDto);

	/**
	 * Description： 批量删除（修改状态）数据字典信息
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典信息id
	 * 
	 * return 执行成功与否参数信息
	 */
	Integer deleteDataDictByIds(Long[] id);
    
	/**
	 * Description： 删除数据字典信息(修改状态)。  
	 * 
	 * Author: Anthony   
	 * 
	 * @param id 数据字典信息id
	 * @return 执行成功与否信息参数
	 */
	Integer deleteDataDictById(Long id);
    
	/**
	 * Description：数据字典的编码进行重复验证 。
	 * 
	 * Author: Anthony
	 * 
	 * param dictCode 数据字典的编码
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictEntity selectDataDictByDictCodes(DataDictEntity entity);
    
	/**
	 * Description： 对数据字典的值重复验证。
	 * 
	 * Author: Anthony
	 * 
	 * param dictValue 数据字典的值
	 * 
	 * return 数据字典类型实体类对象
	 */
	DataDictEntity selectDataDictByDictValues(DataDictEntity entity);

}
