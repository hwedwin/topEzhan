package com.topaiebiz.nebula.ssm.demo.user.service;

import java.util.List;

import com.topaiebiz.nebula.ssm.demo.user.entity.UserEntity;

public interface UserService {
	
	Integer save(UserEntity entity);
	
	List<UserEntity> getListUser();

}
