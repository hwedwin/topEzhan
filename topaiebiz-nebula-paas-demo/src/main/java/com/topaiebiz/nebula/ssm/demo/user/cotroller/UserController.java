package com.topaiebiz.nebula.ssm.demo.user.cotroller;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.nebula.ssm.demo.user.dto.UserDto;
import com.topaiebiz.nebula.ssm.demo.user.entity.UserEntity;
import com.topaiebiz.nebula.ssm.demo.user.exception.UserExceptionEnum;
import com.topaiebiz.nebula.ssm.demo.user.service.UserService;



@RestController
@RequestMapping(path = "/demo/user")    /**注意：这里是项目模块名称 + 功能模块名  （这里必须有） 例如：/merchart/join  商家管理/入驻管理。*/
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/add")
	@ResponseBody
	public ResponseInfo add(@Valid UserDto dto, BindingResult result) throws GlobalException {
		if(result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		return new ResponseInfo();
	}
	
	@GetMapping(path = "/get")
	@ResponseBody
	public ResponseInfo getOne(Long id) throws GlobalException { 
		if(StringUtils.isEmpty(id)) {
			throw new GlobalException(UserExceptionEnum.USER_ID_NOT_NULL);
		}
		return new ResponseInfo(userService.getListUser());
	}
	
}
