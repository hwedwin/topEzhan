/**
 * 
 */
package com.topaiebiz.system.district.moble;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.system.district.exception.DistrictExceptionEnum;
import com.topaiebiz.system.district.service.DistrictService;

/**  
 * Description： app端 区域数据 控制层
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月19日 下午2:28:02 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Controller
@RequestMapping(path = "/app/system/district")
public class MobleDistrictController {

	@Autowired
	private DistrictService districtService;
	
	/**
	 * 
	 * Description： 获取所有一级区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有一级区域数据  
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getOneLevelDistrictData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOneLevelDistrictData() throws GlobalException {
		return new ResponseInfo(districtService.selectOneLevelDistrict());
	}
	
	/**
	 * 
	 * Description： 获取区域下的所有子区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有子区域数据
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getChildDistrictData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getChildDistrictData(Long parentDistrictId) throws GlobalException {
		if (StringUtils.isEmpty(parentDistrictId)) {
			throw new GlobalException(DistrictExceptionEnum.DISTRICT_ID_NOT_NULL);
		}
		return new ResponseInfo(districtService.selectChildDistrictData(parentDistrictId));
	}
	
}
