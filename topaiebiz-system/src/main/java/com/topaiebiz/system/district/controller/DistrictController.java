/**
 * 
 */
package com.topaiebiz.system.district.controller;


import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.district.dto.DistrictDto;
import com.topaiebiz.system.district.exception.DistrictExceptionEnum;
import com.topaiebiz.system.district.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**  
 * Description： 区域数据 控制层
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
@RequestMapping(path = "/system/district")
public class DistrictController {

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

	@RequestMapping(path = "/getOneDistrictById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOneDistrictById(Long districtId) throws GlobalException {
		return new ResponseInfo(districtService.selectOneById(districtId));
	}

	/**
	 * 
	 * Description： 获取所有区域数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域数据  
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getAllDistrictData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAllDistrictData() throws GlobalException {
		return new ResponseInfo(districtService.selectAllDistrict());
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
	
	
	/**
	 * 
	 * Description：删除指定一条区域数据
	 * 
	 * Author hxpeng   
	 * 
	 * @param id
	 * @return 返回操作结果标识
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/deleteDistrictById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo deleteDistrictById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DistrictExceptionEnum.DISTRICT_ID_NOT_NULL);
		}
		return new ResponseInfo(districtService.deleteDistrictDataById(id));
	}
	
	
	/**
	 * 
	 * Description： 新增区域数据信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @param result
	 * @return 返回操作结果标识
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/createDistrict", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo createDistrict(@Valid DistrictDto districtDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(districtService.createDistrict(districtDto));
	}
	
	
	/**
	 * 
	 * Description： 修改区域数据信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @param result
	 * @return 返回操作结果标识
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/modifyDistrict", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo modifyDistrict(@Valid DistrictDto districtDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		if(districtDto.getId() == null){
			throw new GlobalException(DistrictExceptionEnum.DISTRICT_ID_NOT_NULL);
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(districtService.modifyDistrict(districtDto));
	}
	
	
	
	
	
	
}
