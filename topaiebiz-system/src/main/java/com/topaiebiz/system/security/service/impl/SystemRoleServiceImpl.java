package com.topaiebiz.system.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topaiebiz.system.security.dao.SystemRoleDao;
import com.topaiebiz.system.security.entity.SystemRoleEntity;
import com.topaiebiz.system.security.service.SystemRoleService;



@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
	private SystemRoleDao systemRoleDao;

	public List<SystemRoleEntity> getList() {
		List<SystemRoleEntity> systemRoleList = systemRoleDao.selectList();
		return systemRoleList;
	}

	public Integer save(SystemRoleEntity entity) {
		return systemRoleDao.insert(entity);
	}

	public Integer modify(SystemRoleEntity entity) {
		return systemRoleDao.updateById(entity);
	}

}
