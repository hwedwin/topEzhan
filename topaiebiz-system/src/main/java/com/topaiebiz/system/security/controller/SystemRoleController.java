package com.topaiebiz.system.security.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.exception.SystemExceptionEnum;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.security.dto.SystemRoleDto;
import com.topaiebiz.system.security.entity.SystemRoleEntity;
import com.topaiebiz.system.security.service.SystemRoleService;

@RestController
@RequestMapping(path = "/sysmgt/api")
public class SystemRoleController {

	@Autowired
	private SystemRoleService systemRoleService;

	@ResponseBody
	@PostMapping(value = "/add")
	public ResponseInfo add(@Valid SystemRoleDto systemRoleDto, BindingResult result) throws GlobalException {

		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		SystemRoleEntity entity = new SystemRoleEntity();
		BeanUtils.copyProperties(systemRoleDto, entity);
		systemRoleService.save(entity);
		return new ResponseInfo(systemRoleService.getList());

	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseInfo edit(@Valid SystemRoleDto systemRoleDto, BindingResult result) throws GlobalException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		SystemRoleEntity entity = new SystemRoleEntity();
		BeanUtils.copyProperties(systemRoleDto, entity);
		systemRoleService.modify(entity);
		return new ResponseInfo(systemRoleService.getList());

	}

	@ResponseBody
	@GetMapping(path = "/list")
	public ResponseInfo getList(String userName) throws GlobalException {

		// 如果参数为空，抛出“参数不完整”异常。
		if (StringUtils.isEmpty(userName)) {
			throw new GlobalException(SystemExceptionEnum.INCOMPLETE_PARAM);
		} else {
			return new ResponseInfo(systemRoleService.getList());
		}

	}

}
