package com.topaiebiz.giftcard.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.manage.dao.GiftCardAddressDao;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardAddressService;

/**
 * Description 配送的详细地址的业务层，用于实现业务处理和逻辑运算。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月8日 下午4:07:31
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class GiftCardAddressServiceImpl implements GiftCardAddressService {

	@Autowired
	private GiftCardAddressDao cardAddressDao;

	@Override
	@Transactional
	public List<CardAddressEntity> getAddressList() {
		return cardAddressDao.selectAddressList();
	}

	@Override
	@Transactional
	public void saveAddress(CardAddressEntity cardAddressEntity) {
		/** 获取当前时间为创建时间 */
		cardAddressEntity.setCreatedTime(new Date());
		/** 添加礼卡配送地址 */
		cardAddressDao.insert(cardAddressEntity);

	}

	@Override
	@Transactional
	public CardAddressEntity getAddressById(Long id) throws GlobalException {
		CardAddressEntity selectById = cardAddressDao.selectById(id);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ADDRESS_ID_IS_NOT_EXIST);
		}
		return selectById;
	}

	@Override
	@Transactional
	public Integer modifyAddressById(CardAddressEntity cardAddressEntity) {
		Integer updateById = cardAddressDao.updateById(cardAddressEntity);
		return updateById;
	}

	@Override
	@Transactional
	public void removeAddressOfBeach(Long[] ids) throws GlobalException {
		/**判断ID是否存在*/
		for(Long id:ids) {
			CardAddressEntity selectById = cardAddressDao.selectById(id);
			if(null == selectById) {
				throw new GlobalException(CardManageExceptionEnum.ADDRESS_ID_IS_NOT_EXIST);
			}
		}
		cardAddressDao.deleteAddressOfBeach(ids); 
	}

}
