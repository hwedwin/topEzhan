/**
 * 
 */
package com.topaiebiz.system.district.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.district.dto.DistrictTypeDto;
import com.topaiebiz.system.district.entity.DistrictTypeEntity;

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

public interface DistrictTypeService {

	
	/**
	 * 
	 * Description： 获取所有区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域数据
	 */
	List<DistrictTypeEntity> getAllDistrictTypeData();
	
	
	/**
	 * 
	 * Description： 删除指定一条区域类型的数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return 执行成功与否信息参数
	 */
	Integer deleteDistrictTypeDataById(Long id);
	
	/**
	 * 
	 * Description： 修改区域类型的信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @return 返回操作结果标识
	 */
	Integer modifyDistrictType(DistrictTypeDto districtTypeDto) throws GlobalException;
	
	/**
	 * 
	 * Description： 新增区域类型的信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @return 返回操作结果标识
	 */
	Integer createDistrictType(DistrictTypeDto districtTypeDto)  throws GlobalException;
}
