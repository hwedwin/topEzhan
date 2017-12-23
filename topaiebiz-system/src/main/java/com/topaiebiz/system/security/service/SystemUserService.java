package com.topaiebiz.system.security.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.security.dto.SystemUserDto;
import com.topaiebiz.system.security.entity.SystemUserEntity;

/**
 * 描述：系统用户业务层接口。
 * 
 * @author Created by Amir Wang on 2017年10月30日。
 * 
 * @since 1.1.2
 */
public interface SystemUserService {

	/**
	 * 描述：登录的业务逻辑接口。
	 * 
	 * @param systemUserDto 系统用户信息数据传输对象。
	 * 
	 * @return 登陆后的用户的信息数据对象。
	 * 
	 * @throws GlobalException 当出现异常时，抛出统一的GlobalException全局异常。
	 * 
	 * @author Created by Amir Wang on 2017年10月30日。
	 */
	SystemUserEntity login(SystemUserDto systemUserDto) throws GlobalException;
	
	/**
	 * 描述：根据手机号改变用户的类型（入驻成功以后，变成商家类型）
	 * 
	 * @param 
	 * 		telephone 手机号
	 * 		type 用户类型
	 * 
	 * @return true为成功，false为失败
	 * 
	 * @throws GlobalException 当出现异常时，抛出统一的GlobalException全局异常。
	 * 
	 * @author Created by Aaron Xue on 2017年12月22日。
	 */
	Boolean modifySystemUser(String telephone, Integer type) throws GlobalException;

	void userTest(Long userId);


}
