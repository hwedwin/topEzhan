package com.topaiebiz.giftcard.label.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.label.dao.GiftCardLabelDao;
import com.topaiebiz.giftcard.label.dto.CardLabelDto;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.label.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.label.service.GiftCardLabelService;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;
import com.topaiebiz.system.security.util.SecurityContextUtils;

/**
 * Description 描述礼卡标签的业务层实现类。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 上午9:46:10
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GiftCardLabelServiceImpl implements GiftCardLabelService {

	@Autowired
	private GiftCardLabelDao labelDao;

	@Override
	@Transactional
	public Integer saveLabel(CardLabelEntity cardLabel) throws GlobalException {

		/** 判断礼卡的标签名称是否存在 */
		CardLabelEntity cardlabel = labelDao.selectByName(cardLabel);
		if (cardlabel != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDLABEL_NAME_NOT_REPETITION);
		}

		/** 添加礼卡标签信息 */
		/** 获取创建人编号 */
		cardLabel.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		Integer insert = labelDao.insert(cardLabel);
		return insert;
	}

	@Override
	@Transactional
	public Integer modifyLabelById(CardLabelDto cardLabelDto) throws GlobalException {
		/** 判断礼卡的标签名称是否存在 */
		CardLabelEntity cardLabelEntity = new CardLabelEntity();
		BeanUtils.copyProperties(cardLabelDto,cardLabelEntity); 
		CardLabelEntity cardlabel = labelDao.selectByName(cardLabelEntity); 
		if (cardlabel != null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDLABEL_NAME_NOT_REPETITION);
		}
		CardLabelEntity selectById = labelDao.selectById(cardLabelDto.getId());
		if (selectById == null) {
			throw new GlobalException(GiftCardExceptionEnum.GIFTCARD_ID_NOT_EXIST);
		}

		/** 获取修改时间 */
		cardLabelDto.setLastModifiedTime(new DateTime().toDate());
		/** 获取修改人编号 */
		cardLabelDto.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		Long version = selectById.getVersion();
		String description = cardLabelDto.getDescription();
		if(description == null || description=="") {
			cardLabelDto.setDescription(" "); 
		}
		BeanUtils.copyProperties(cardLabelDto, selectById);
		selectById.setVersion(version);
		return labelDao.updateById(selectById);
	}

	@Override
	@Transactional
	public Page<CardLabelEntity> getLabelPage(Page<CardLabelEntity> page, CardLabelDto dto) {
		page.setRecords(labelDao.selectLabelPage(dto, page));
		return page;
	}

	@Override
	@Transactional
	public void removeLabelOfBeach(Long[] id) throws GlobalException {
		/** 判断礼卡标签的ID是否存在 */
		for (Long ids : id) {
			CardLabelEntity selectById = labelDao.selectById(ids);
			if (selectById == null) {
				throw new GlobalException(GiftCardExceptionEnum.GIFTCARD_ID_NOT_EXIST);
			}
		}
		/**判断此礼卡标签是否已经存在于已创建的礼卡中*/
		List<GiftCardInfoDto> infoDtoList=labelDao.selectCardInfoByLabelId(id); 
		if(infoDtoList.size()>0) {
			throw new GlobalException(GiftCardExceptionEnum.THE_CARDLABEL_IS_EXIST_CARDINFO);
		}
		labelDao.deleteLabelOfBeach(id);
	}

	@Override
	@Transactional
	public CardLabelEntity getLabelById(Long id) throws GlobalException {
		CardLabelEntity cardLabel = labelDao.selectById(id);
		/** 判断礼卡标签的编号是否存在 */
		if (cardLabel == null) {
			throw new GlobalException(GiftCardExceptionEnum.GIFTCARD_ID_NOT_EXIST);
		}
		if(cardLabel.getImage()==null) {
			cardLabel.setImage(" "); 
		}
		return cardLabel;
	}

}
