/**
 * 
 */
package com.topaiebiz.system.district.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.district.dto.DistrictDto;
import com.topaiebiz.system.district.entity.DistrictEntity;

/**  
 * Description： 区域业务层接口
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月19日 下午2:18:57 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface DistrictService {


	/**
	 * 
	 * Description： 根据ID返回一个地区实体
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return
	 */
	DistrictEntity selectOneById(Long id);
	
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
	 * Description： 获取子区域数据
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域数据
	 */
	List<DistrictDto> selectChildDistrictData(Long parentDistrictId);
	
	
	/**
	 * 
	 * Description： 删除指定一条区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return 执行成功与否信息参数
	 */
	Integer deleteDistrictDataById(Long id);
	
	/**
	 * 
	 * Description： 新增区域信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @return 返回操作结果标识
	 */
	Integer createDistrict(DistrictDto districtDto) throws GlobalException;
	
	
	/**
	 * 
	 * Description： 修改区域信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @return 返回操作结果标识
	 */
	Integer modifyDistrict(DistrictDto districtDto) throws GlobalException;
}
