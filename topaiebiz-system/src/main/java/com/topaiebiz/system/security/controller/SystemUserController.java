package com.topaiebiz.system.security.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.security.dto.SystemUserDto;
import com.topaiebiz.system.security.entity.SystemUserEntity;
import com.topaiebiz.system.security.exception.SystemExceptionEnum;
import com.topaiebiz.system.security.service.SystemUserService;

@RestController
@RequestMapping(path = "/sysmgt/api/user")
public class SystemUserController {

	@Autowired
	private SystemUserService systemUserService;

	@ResponseBody
	@PostMapping(value = "/login")
	public ResponseInfo login(HttpSession session, @Valid SystemUserDto systemUserDto, BindingResult result) throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		//调用service查询
		SystemUserEntity entity = systemUserService.login(systemUserDto);
		if(null == entity) {
			throw new GlobalException(SystemExceptionEnum.USER_DONT_EXIST);
		}
		//模拟将当前用户放到工具类里
		session.removeAttribute("user");
		session.setAttribute("user", entity);
		session.removeAttribute("urlList");
		session.setAttribute("urlList", entity.getUrlList());
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(entity);
	}
	
//	@ResponseBody
//	@PostMapping(value = "/userTest")
//	public ResponseInfo userTest(Long userId) throws GlobalException {
//		systemUserService.userTest(userId);
//		return new ResponseInfo();
//	}
	
}
