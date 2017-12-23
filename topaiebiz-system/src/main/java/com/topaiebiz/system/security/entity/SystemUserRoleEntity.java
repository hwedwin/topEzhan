package com.topaiebiz.system.security.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description： 系统用户角色映射实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月27日 下午2:48:15
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_sys_system_user_role")
public class SystemUserRoleEntity extends BaseEntity<Long> {

	/**
	 * 版本序列号。
	 */
	private static final long serialVersionUID = 797641246134328641L;

	/** 用户主键。 */
	private Long userId;

	/** 角色主键。 */
	private Long roleId;

	/**
	 * 授予系统账户的权限类型。它有0和1两个值，0表示操作权限，1表示管理权限。当值为0时，表示当前系统账户拥有的角色权限不可以再授权给其他系统账户；当值为1时，表示当前系统账户拥有的角色权限可以再授权给其他系统账户。
	 */
	private Integer permissionType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	
	
}
