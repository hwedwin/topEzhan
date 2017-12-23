package com.topaiebiz.nebula.ssm.demo.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topaiebiz.nebula.ssm.demo.user.dao.UserDao;
import com.topaiebiz.nebula.ssm.demo.user.entity.UserEntity;
import com.topaiebiz.nebula.ssm.demo.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public Integer save(UserEntity entity) {
		return userDao.insert(entity);
	}

	@Override
	public List<UserEntity> getListUser() {
		return userDao.selectList();
	}

}
