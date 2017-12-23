/**
 * 
 */
package com.topaiebiz.system.district.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.district.dto.DistrictDto;
import com.topaiebiz.system.district.entity.DistrictEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Description： 区域 数据持久性接口
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月19日 下午1:37:56
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface DistrictDao extends BaseDao<DistrictEntity> {

	/**
	 * 
	 * Description：查询一级区域
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回一个集合区域
	 */
	List<DistrictEntity> selectOneLevelDistrict();
	
	/**
	 * Description： 根据id查询一级区域
	 *
	 * Author Aaron.Xue
	 *
	 * @param id
	 * @return
	 */
	DistrictEntity selectOneLevelById(Long id);

	/**
	 * Description： 根据id和父区域id查询区域
	 *
	 * Author Aaron.Xue
	 *
	 * @param id
	 * @param parentId
	 * @return
	 */
	DistrictEntity selectByIdAndParentId(@Param("parentId") Long parentId, @Param("id") Long id);


	/**
	 * 
	 * Description： 根据code 查询区域数据
	 * 
	 * Author hxpeng   
	 * 
	 * @param code
	 * @return 返回一个区域数据
	 */
	DistrictEntity selectOneByCode(String code);
	
	/**
	 * 
	 * Description： 查询所有区域的数据
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有的区域数据
	 */
	List<DistrictEntity> selectAllDistrict();
	
	/**
	 * 
	 * Description： 根据父区域ID,查询此区域下的子数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域数据
	 */
	List<DistrictDto> selectChildDistrict(Long parentDistrictId);
	
	/**
	 * 
	 * Description： 删除单条区域数据 
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteDistrictById(Long id);
	
	
	
	
	
}