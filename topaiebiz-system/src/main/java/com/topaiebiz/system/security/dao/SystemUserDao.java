package com.topaiebiz.system.security.dao;


import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.security.dto.SecurityMemberDto;
import com.topaiebiz.system.security.entity.SystemUserEntity;

/**
 * 描述：系统权限用户持久层接口。
 * 
 * @author Created by Amir Wang on 2017年10月30日。
 * 
 * @since 1.1.2
 */
@Mapper
public interface SystemUserDao extends BaseDao<SystemUserEntity> {

	/**
	 * 描述：根据系统用户的用户名或者手机号和密码查询用户。
	 * 
	 * @param entity
	 *            系统用户实体对象。
	 * 
	 * @return 满足条件的系统用户。
	 * 
	 * @author Created by Amir Wang on 2017年10月30日。
	 */
	SystemUserEntity selectUserByUsernameOrTelephone(SystemUserEntity entity);
	
	/**
	 * 描述：根据系统用户的手机号或用户名查询用户。
	 * 
	 * @param mobilePhone
	 *            系统用户电话。
	 * 
	 * @return 满足条件的系统用户。
	 * 
	 * @author Created by Aaron.Xue on 2017年10月30日。
	 */
	SystemUserEntity selectUserByTelephone(String mobilePhone);
	
	SecurityMemberDto selectUserByMember(String username);
	
	SystemUserEntity selectUserByUsername(String username);

}
