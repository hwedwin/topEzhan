/**
 * 
 */
package com.topaiebiz.system.district.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.district.entity.DistrictTypeEntity;

/**
 * Description： 区域类型 数据持久性接口
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月19日 下午4:01:57
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface DistrictTypeDao extends BaseDao<DistrictTypeEntity> {

	/**
	 * 
	 * Description： 根据code查询一条区域类型
	 * 
	 * Author hxpeng   
	 * 
	 * @param code
	 * @return 一个区域类型实体
	 */
	DistrictTypeEntity selectOneByCode(String code);
	
	/**
	 * 
	 * Description： 查询所有区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域数据
	 */
	List<DistrictTypeEntity> selectDistrictType();
	
	/**
	 * 
	 * Description： 删除单条区域数据 
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteDistrictTypeById(Long id);
	
}
