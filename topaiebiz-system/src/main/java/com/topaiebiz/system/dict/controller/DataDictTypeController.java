package com.topaiebiz.system.dict.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.dict.dto.DataDictTypeDto;
import com.topaiebiz.system.dict.entity.DataDictTypeEntity;
import com.topaiebiz.system.dict.exception.DataDictTypeExceptionEnum;
import com.topaiebiz.system.dict.service.DataDictTypeService;

/**
 * Description: 数据字典控制层
 * 
 * Author : Anthony
 * 
 * Date :2017年9月23日 下午9:39:50
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/system/dataDictType")
public class DataDictTypeController {

	@Autowired
	private DataDictTypeService dataDictTypeService;

	/**
	 * Description： 添加数据字典类型。
	 * 
	 * Author: Anthony
	 * 
	 * param dto 数据字典类型dto对象
	 * 
	 * param result 绑定异常类结果返回对象
	 * 
	 * return 执行成功与否信心信息
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "saveDataDictType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addDataDictType(@Valid DataDictTypeDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		DataDictTypeEntity entity = new DataDictTypeEntity();
		BeanUtils.copyProperties(dto, entity);
		return new ResponseInfo(dataDictTypeService.saveDataDictType(entity));
	}

	/**
	 * Description：删除数据字典类型(修改状态)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 执行成功与否信息参数
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "/removeDataDictType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelDataDictTypeById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictTypeService.removeDataDictTypeById(id));
	}

	/**
	 * Description：查看数据字典类型(根据Id查看数据字典类型数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典类型id
	 * 
	 * return 数据字典类型数据
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getDataDictTypeById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDataDictTypeById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictTypeService.getDataDictTypeById(id));

	}

	/**
	 * Description：编辑(修改)数据字典类型。
	 * 
	 * Author: Anthony
	 * 
	 * param dto 数据字典类型dto对象
	 * 
	 * param result 绑定异常结果集
	 * 
	 * return 执行成功与否的信息
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "updateDataDictTypeById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editDataDictTypeById(@Valid DataDictTypeDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(dataDictTypeService.modifyDataDictType(dto));
	}

	/**
	 * Description： 数据字典类型列表分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param page 分页参数
	 * 
	 * param dictionaryTypeDto 数据字典类型dto对象
	 * 
	 * return 数据字典类型数据
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getDataDictTypeList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDataDictTypeList(Page<DataDictTypeDto> page, DataDictTypeDto dataDictTypeDto)
			throws GlobalException {
		return new ResponseInfo(dataDictTypeService.getDataDictTypeList(page, dataDictTypeDto));
	}

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
	@RequestMapping(path = "/deleteDataDictTypeByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelDataDictTypeByIds(Long[] id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictTypeService.removeDataDictTypeByIds(id));

	}

}
