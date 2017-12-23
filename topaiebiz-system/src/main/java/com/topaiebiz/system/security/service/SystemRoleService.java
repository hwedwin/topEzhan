package com.topaiebiz.system.security.service;

import java.util.List;

import com.topaiebiz.system.security.entity.SystemRoleEntity;


public interface SystemRoleService {

	List<SystemRoleEntity> getList();

	Integer save(SystemRoleEntity entity);

	Integer modify(SystemRoleEntity entity);

}
