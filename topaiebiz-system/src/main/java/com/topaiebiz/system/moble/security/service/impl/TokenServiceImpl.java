package com.topaiebiz.system.moble.security.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.moble.security.dao.TokenDao;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.entity.TokenEntity;
import com.topaiebiz.system.moble.security.exception.MobleSecurityExceptionEnum;
import com.topaiebiz.system.moble.security.service.TokenService;
import com.topaiebiz.system.moble.security.util.MD5Util;
@Service
public class TokenServiceImpl implements TokenService{
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public String login(String telephone) throws GlobalException {
		if(StringUtils.isEmpty(telephone)) {
			throw new GlobalException(MobleSecurityExceptionEnum.TELEPHONE_CANNOT_BE_EMPTY);
		}
		//获取token
		String token = getToken();
		//查看token表中是否有token
		TokenEntity tokenEntity = tokenDao.selectByTelephone(telephone);
		if(tokenEntity == null) { //没有token,创建一个token
			tokenEntity = new TokenEntity();
			tokenEntity.setTelephone(telephone);
			tokenEntity.setAppToken(token);
			tokenEntity.setLastModifiedTime(new Date());
			tokenDao.insert(tokenEntity);
		}else { //有token更改token的值，并且更改时间
			tokenEntity.setAppToken(token);
			tokenEntity.setLastModifiedTime(new Date());
			tokenDao.updateById(tokenEntity);
		}
		return token;
	}
	
	@Override
	public TokenDto getTokenDetail(String token) {
		return tokenDao.selectDetailByToken(token);
	}
	
	@Override
	public void verifyToken(String token) throws GlobalException {
		TokenEntity tokenEntity = tokenDao.selectByToken(token);
		if(tokenEntity == null) {
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
	}
	
	private String getToken() {
		return MD5Util.encode(UUID.randomUUID().toString()+UUID.randomUUID().toString());
	}

}
