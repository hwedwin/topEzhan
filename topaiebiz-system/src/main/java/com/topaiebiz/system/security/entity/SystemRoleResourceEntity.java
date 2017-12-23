package com.topaiebiz.system.security.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * 描述：系统资源实体类。
 * 
 * @author Created by Amir Wang on 2017年10月7日。
 * 
 * @since 1.1.1
 */
@TableName(value = "t_sys_system_role_resource")
public class SystemRoleResourceEntity extends BaseEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 3673645134441652356L;

	/**'角色主键。'*/
	private Long roleId;

	/**'资源主键。'*/
	private Long resourceId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
}
