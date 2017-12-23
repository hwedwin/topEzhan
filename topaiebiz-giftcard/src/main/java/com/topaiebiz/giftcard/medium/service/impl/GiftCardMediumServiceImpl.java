package com.topaiebiz.giftcard.medium.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.medium.dao.GiftCardMediumDao;
import com.topaiebiz.giftcard.medium.dto.CardMediumDto;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.medium.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.medium.service.GiftCardMediumService;

/**
 * Description 描述礼卡介质的业务层实现类。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 上午9:44:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GiftCardMediumServiceImpl implements GiftCardMediumService {

	@Autowired
	private GiftCardMediumDao mediaDao;

	@Override
	@Transactional
	public List<CardMediumEntity> selectMedia() {
		return mediaDao.selectMedia();
	}

	@Override
	@Transactional
	public Integer saveMedia(CardMediumEntity cardMedium) throws GlobalException {
		/** 判断礼卡介质名称是否存在 */
		CardMediumEntity cardmedia = mediaDao.selectByName(cardMedium);
		if (cardmedia != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_NAME_NOT_REPETITION);
		}
		/** 添加礼卡介质信息 */
		/** 获取创建人编号 */
		cardMedium.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		return mediaDao.insert(cardMedium);
	}

	@Override
	@Transactional
	public CardMediumEntity getMediaById(Long id) throws GlobalException {
		CardMediumEntity cardMedia = mediaDao.selectById(id);
		/** 判断礼卡介质的ID是否存在 */
		if (cardMedia == null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_EXIST);
		}
		return cardMedia;
	}

	@Override
	@Transactional
	public Integer modifyMediaById(CardMediumEntity cardMedium) throws GlobalException {
		CardMediumEntity cardmedia = mediaDao.selectByName(cardMedium);
		/** 判断礼卡介质的名称是否存在 */
		if (cardmedia != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_NAME_NOT_REPETITION);
		}
		CardMediumEntity selectById = mediaDao.selectById(cardMedium.getId());
		if (selectById == null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_EXIST);
		}
		/** 获取修改时间 */
		cardMedium.setLastModifiedTime(new DateTime().toDate());
		/** 获取修改人编号 */
		cardMedium.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		Long version = selectById.getVersion();
		BeanUtils.copyProperties(cardMedium, selectById);
		selectById.setVersion(version);
		return mediaDao.updateById(selectById);
	}

	@Override
	@Transactional
	public Page<CardMediumEntity> getMediumPage(CardMediumDto dto, Page<CardMediumEntity> page) {
		page.setRecords(mediaDao.selectMediaPage(page, dto));
		return page;
	}

	@Override
	@Transactional
	public void removeMediaOfBeach(CardMediumEntity cardMedia) throws GlobalException {
		List<Long> list = cardMedia.getList();
		/** 判断礼卡介质的ID是否存在 */
		for (Long id : list) {
			CardMediumEntity selectById = mediaDao.selectById(id);
			if (selectById == null) {
				throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_EXIST);
			}
		}
		mediaDao.deleteMediaOfBeach(list);

	}

}
