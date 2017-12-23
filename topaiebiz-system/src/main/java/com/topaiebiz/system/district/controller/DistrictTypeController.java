/**
 * 
 */
package com.topaiebiz.system.district.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.dict.exception.DataDictTypeExceptionEnum;
import com.topaiebiz.system.district.dto.DistrictTypeDto;
import com.topaiebiz.system.district.service.DistrictTypeService;

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
@RequestMapping(path = "/system/districtType")
public class DistrictTypeController {

	@Autowired
	private DistrictTypeService districtTypeService;
	
	
	/**
	 * 
	 * Description： 获取所有区域类型的数据  
	 * 
	 * Author hxpeng   
	 * 
	 * @return 返回所有区域类型的数据
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getAllDistrictTypeData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAllDistrictTypeData() throws GlobalException {
		return new ResponseInfo(districtTypeService.getAllDistrictTypeData());
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
	@RequestMapping(path = "/deleteDistrictType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo deleteDistrictType(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_ID_NOT_NULL);
		}
		return new ResponseInfo(districtTypeService.deleteDistrictTypeDataById(id));
	}
	
	/**
	 * 
	 * Description： 新增区域类型数据信息  
	 * 
	 * Author hxpeng   
	 * 
	 * @param districtDto
	 * @param result
	 * @return 返回操作结果标识
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/createDistrictType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo createDistrictType(@Valid DistrictTypeDto districtTypeDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(districtTypeService.createDistrictType(districtTypeDto));
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
	@RequestMapping(path = "/modifyDistrictType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo modifyDistrictType(@Valid DistrictTypeDto districtTypeDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(districtTypeService.modifyDistrictType(districtTypeDto));
	}
	
	
	
	
	
	
}
