package com.topaiebiz.giftcard.type.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.type.dao.GiftCardTypeDao;
import com.topaiebiz.giftcard.type.dto.CardTypeDto;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.type.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.type.service.GiftCardTypeService;

/**
 * Description 描述礼卡类型的业务层实现类。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 上午9:43:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GiftCardTypeServiceImpl implements GiftCardTypeService {

	@Autowired
	private GiftCardTypeDao typeDao;

	@Override
	@Transactional
	public List<CardTypeEntity> selectType() {
		return typeDao.selectType();

	}

	@Override
	@Transactional
	public Integer saveType(CardTypeEntity cardType) throws GlobalException {
		CardTypeEntity cardtype = typeDao.selectByName(cardType);
		/** 判断礼卡经营类型名称是否存在 */
		if (cardtype != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_NAME_NOT_REPETITION);
		}
		/** 添加礼卡经营类型信息 */
		/** 获取创建人编号 */
		cardType.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		return typeDao.insert(cardType);

	}

	@Override
	public CardTypeEntity getTypeById(Long id) throws GlobalException {
		/** 根据ID查询礼卡经营类型对象 */
		CardTypeEntity cardType = typeDao.selectById(id);
		/** 判断ID是否存在 */
		if (cardType == null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_EXIST);
		}
		return cardType;
	}

	@Override
	@Transactional
	public void modifyTypeById(CardTypeEntity cardType) throws GlobalException {
		CardTypeEntity cardtype = typeDao.selectByName(cardType);
		if (cardtype != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_NAME_NOT_REPETITION);
		}
		CardTypeEntity selectById = typeDao.selectById(cardType.getId());
		if (selectById == null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_EXIST);
		}
		/** 获取修改时间 */
		cardType.setLastModifiedTime(new DateTime().toDate());
		/** 获取修改人编号 */
		cardType.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		Long version = selectById.getVersion();
		BeanUtils.copyProperties(cardType, selectById);
		selectById.setVersion(version);
		typeDao.updateById(cardType);
	}

	@Override
	public void removeTypeOfBeach(CardTypeDto dto) throws GlobalException {
		/** 遍历ID的集合，判断ID是否都存在 */
		List<Long> list = dto.getList();
		for (Long id : list) {
			CardTypeEntity selectById = typeDao.selectById(id);
			if (selectById == null) {
				throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_EXIST);
			}
		}
		typeDao.deleteTypeOfBeach(list);
	}

	@Override
	public Page<CardTypeEntity> getTypePage(CardTypeDto dto, Page<CardTypeEntity> page) {
		/** 查询礼卡经营类型的数据 */
		page.setRecords(typeDao.selectTypePage(dto, page));
		return page;
	}

}
