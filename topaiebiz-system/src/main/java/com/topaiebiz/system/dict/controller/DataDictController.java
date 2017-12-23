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
import com.topaiebiz.system.dict.dto.DataDictDto;
import com.topaiebiz.system.dict.entity.DataDictEntity;
import com.topaiebiz.system.dict.exception.DataDictTypeExceptionEnum;
import com.topaiebiz.system.dict.service.DataDictService;

/**
 * Description: 数据字典信息控制层
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午8:35:15
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/system/dataDict")
public class DataDictController {

	@Autowired
	private DataDictService dataDictService;

	/**
	 * Description： 数据字典类型下拉框展示供添加数据字典信息时选择。
	 * 
	 * Author: Anthony
	 * 
	 * return DataDictTypeId 数据字典类型的id和typeName数据信息对象
	 */
	@RequestMapping(path = "/selectDataDictByTypeId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDataDictTypeById() throws GlobalException {
		return new ResponseInfo(dataDictService.getDataDictTypeById());
	}

	/**
	 * Description： 添加数据字典信息。
	 * 
	 * Author: Anthony
	 * 
	 * param dto 数据字典信息dto
	 * 
	 * param result 绑定的结果异常
	 * 
	 * throws GlobalException 全局统一异常类
	 * 
	 * return saveInteger 执行成功或失败的提示信息
	 */
	@RequestMapping(path = "/saveDataDict", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addDataDictType(@Valid DataDictDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		DataDictEntity entity = new DataDictEntity();
		BeanUtils.copyProperties(dto, entity);
		return new ResponseInfo(dataDictService.saveDataDict(entity));
	}

	/**
	 * Description：查看数据字典信息(根据Id查看数据字典信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典信息主键id
	 * 
	 * return dataDict 数据字典信息数据
	 * 
	 * throws GlobalException 全局统一异常类
	 */
	@RequestMapping(path = "/getDataDictById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDataDictListById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictService.getDataDictById(id));
	}

	/**
	 * Description： 编辑（修改）数据字典信息。
	 * 
	 * Author: Anthony
	 * 
	 * param dto 数据字典信息Dto
	 * 
	 * param result 绑定的结果异常对象
	 * 
	 * return updateInteger 修改成功与否的提示信息
	 * 
	 * throws GlobalException 全局统一异常类
	 */
	@RequestMapping(path = "updateDataDictById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editDataDictById(@Valid DataDictDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(dataDictService.modifyDataDictById(dto));
	}

	/**
	 * Description： 数据字典信息列表分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param page 分页参数
	 * 
	 * param DataDictDto 数据字典信息Dto
	 * 
	 * return list 数据字典信息列表数据
	 * 
	 * throws GlobalException 全局统一异常类
	 */
	@RequestMapping(path = "/getDataDictList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDataDictList(Page<DataDictDto> page, DataDictDto dataDictDto) throws GlobalException {
		return new ResponseInfo(dataDictService.getDataDictList(page, dataDictDto));
	}

	/**
	 * Description：批量删除数据字典信息（修改状态）
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典信息id
	 * 
	 * return 执行成功与否参数
	 * 
	 * throws GlobalException 全局结果异常类
	 */
	@RequestMapping(path = "/deleteDataDictByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelDataDictByIds(Long[] id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARYTYPE_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictService.removeDataDictByIds(id));
	}

	/**
	 * Description：删除数据字典信息(修改状态)。
	 * 
	 * Author: Anthony
	 * 
	 * param id 数据字典信息id
	 * 
	 * return 执行成功与否信息参数
	 * 
	 * throws GlobalException 全局异常类
	 */
	@RequestMapping(path = "/deleteDataDictById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelDataDictById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(DataDictTypeExceptionEnum.DICTIONARY_ID_NOT_NULL);
		}
		return new ResponseInfo(dataDictService.removeDataDictById(id));
	}
}
