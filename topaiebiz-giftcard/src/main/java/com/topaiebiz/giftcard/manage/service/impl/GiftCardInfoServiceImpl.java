package com.topaiebiz.giftcard.manage.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dao.GiftCardDetailDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardInfoDao;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardInfoService;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;

/**
 * Description 礼卡信息子模块的业务层，处理业务及逻辑运算
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月1日 下午8:32:55
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class GiftCardInfoServiceImpl implements GiftCardInfoService {

	@Autowired
	private GiftCardInfoDao infoDao;

	@Autowired
	private GiftCardDetailDao detailDao;

	@Override
	public List<CardLabelEntity> getLabel() {
		return infoDao.selectLabel();
	}

	@Override
	public List<CardMediumEntity> getMedia() {
		return infoDao.selectMedia();
	}

	@Override
	public List<CardTypeEntity> getType() {
		return infoDao.selectType();
	}

	public void insertInfoAndDetail(CardInfoEntity cardInfo) throws GlobalException {

		/** 主卡的数据信息 */
		Double value = cardInfo.getValue();
		Double price = cardInfo.getPrice();
		/** 副卡面值 */
		Double viceValue = cardInfo.getViceValue();
		/** 副卡平台补差 */
		Double vicePlatformPrice = cardInfo.getVicePlatformPrice();
		if (vicePlatformPrice == null) {
			vicePlatformPrice = 0.0;
		}
		/** 副卡店铺补差 */
		Double viceBrandPrice = cardInfo.getViceBrandPrice();
		if (viceBrandPrice == null) {
			viceBrandPrice = 0.0;
		}
		/** 副卡的品牌补差 */
		Double viceStorePrice = cardInfo.getViceStorePrice();
		if (viceStorePrice == null) {
			viceStorePrice = 0.0;
		}
		/** 求出礼卡的总和 */
		Double add = MathCountUtils.add(viceStorePrice, vicePlatformPrice);
		Double sum = MathCountUtils.add(add, viceBrandPrice);
		/** 判断是否为实体卡 */
		/** 若为实体卡 */
		if (cardInfo.getCardMedium() == 1) {
			/** 添加主卡的礼卡信息 */
			cardInfo.setSurplusNum(cardInfo.getTotalNum());
			cardInfo.setIsVice(0);
			/** 获取创建人编号 */
			/*
			 * cardInfo.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			 */
			infoDao.insert(cardInfo);
			/** 获取主卡ID */
			Long mainId = cardInfo.getId();
			/** 判断是否含有副卡 */

			if (!(viceValue == null || viceValue == 0)) {

				/** 判断副卡面值和副卡的平台补差是否相等 */
				/** 若不相等，抛出异常 */
				if (!viceValue.equals(sum)) {
					throw new GlobalException(CardManageExceptionEnum.VICE_CARD_COMPENSATION_ERROR);
				}

				/** 若相等，则进行添加副卡信息 */
				cardInfo.setIsVice(1);
				cardInfo.setValue(viceValue);
				cardInfo.setPrice(0.0);
				cardInfo.setPlatformPrice(vicePlatformPrice);
				cardInfo.setBrandPrice(0.0);
				cardInfo.setMainCardId(cardInfo.getId());
				cardInfo.setId(null);
				cardInfo.setStorePrice(0.0);

				/** 添加副卡信息 */
				infoDao.insert(cardInfo);
				/** 获取副卡ID */
				Long viceId = cardInfo.getId();
				/** 若相等，则进行添加礼卡详细信息，（每添加一个主卡就会相应添加一张副卡绑定起来） */
				for (long i = 0; i < cardInfo.getTotalNum(); i++) {

					/** 添加主卡的礼卡详细信息 */
					CardDetailEntity detail = new CardDetailEntity();
					detail.setPrice(price);
					detail.setValue(value);
					detail.setCardId(mainId);
					detail.setCardState(1);
					detail.setExpirationTime(cardInfo.getExpirationTime());
					detail.setBalance(value);
					detail.setCardState(1);
					detail.setCreatorId(cardInfo.getCreatorId());
					String prefix = cardInfo.getPrefix();
					/** 自动生成礼卡密码 */
					int randNum = (int) ((Math.random() * 9 + 1) * 100000);
					String password = String.valueOf(randNum);
					detail.setPassword(password);
					/** 添加礼卡详细信息 */
					detailDao.insert(detail);
					/** 添加此礼卡的编号 */
					Long id = detail.getId();
					detail.setCardNo(prefix + id);
					/** 修改此礼卡详细信息 */
					detailDao.updateById(detail);

					/** 添加副卡的礼卡详细信息 */
					CardDetailEntity cardDetail = new CardDetailEntity();
					BeanUtils.copyProperties(detail, cardDetail);
					cardDetail.setId(null);
					cardDetail.setCardId(viceId);
					cardDetail.setMainCardId(detail.getId());
					cardDetail.setValue(viceValue);
					cardDetail.setBalance(viceValue);
					cardDetail.setPrice(vicePlatformPrice);
					cardDetail.setPrice(0.0);
					detailDao.insert(cardDetail);
					/** 添加此礼卡的编号 */
					Long detailId = cardDetail.getId();
					cardDetail.setCardNo(prefix + detailId);
					/** 修改此礼卡详细信息 */
					detailDao.updateById(cardDetail);
				}
			} else {
				/** 若不含有副卡 */
				/** 添加主卡的详细信息 */
				for (long i = 0; i < cardInfo.getTotalNum(); i++) {
					/** 添加主卡的礼卡详细信息 */
					CardDetailEntity detail = new CardDetailEntity();
					detail.setPrice(price);
					detail.setValue(value);
					detail.setCardId(mainId);
					detail.setCardState(1);
					detail.setExpirationTime(cardInfo.getExpirationTime());
					detail.setBalance(value);
					detail.setCardState(1);
					detail.setCreatorId(cardInfo.getCreatorId());
					String prefix = cardInfo.getPrefix();
					/** 自动生成礼卡密码 */
					int randNum = (int) ((Math.random() * 9 + 1) * 100000);
					String password = String.valueOf(randNum);
					detail.setPassword(password);
					/** 添加礼卡详细信息 */
					detailDao.insert(detail);
					/** 添加此礼卡的编号 */
					Long id = detail.getId();
					detail.setCardNo(prefix + id);
					/** 修改此礼卡详细信息 */
					detailDao.updateById(detail);
				}
			}
		} else {
			/** 若为虚拟卡 */

			cardInfo.setSurplusNum(cardInfo.getTotalNum());
			cardInfo.setIsVice(0);
			/** 获取创建人编号 */
			/*
			 * cardInfo.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			 */
			infoDao.insert(cardInfo);

			/** 判断是否含有副卡 */
			if (!(viceValue == null || viceValue == 0)) {

				/** 判断副卡面值和副卡的平台补差是否相等 */
				/** 若不相等，抛出异常 */

				if (!viceValue.equals(sum)) {
					throw new GlobalException(CardManageExceptionEnum.VICE_CARD_COMPENSATION_ERROR);
				}

				/** 若相等，则进行添加副卡信息 */
				cardInfo.setIsVice(1);
				cardInfo.setValue(viceValue);
				cardInfo.setPrice(0.0);
				cardInfo.setPlatformPrice(vicePlatformPrice);
				cardInfo.setBrandPrice(0.0);
				cardInfo.setMainCardId(cardInfo.getId());
				cardInfo.setId(null);
				cardInfo.setBrandPrice(0.0);
				cardInfo.setStorePrice(0.0);

				/** 添加副卡信息 */
				infoDao.insert(cardInfo);
			}
		}

	}

	@Override
	public void saveInfo(CardInfoEntity cardInfo) throws GlobalException {

		/** 判断礼卡名称是否已经存在 */
		List<CardInfoEntity> cardinfoList = infoDao.selectByName(cardInfo);
		if (cardinfoList.size() != 0) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_NAME_NOT_REPETITION);
		}
		String memo = cardInfo.getMemo();
		if (!memo.equals("") && memo != null) {
			memo = memo.substring(memo.indexOf("<p>") + 3, memo.indexOf("</p>"));
			cardInfo.setMemo(memo);
		}
		/** 礼卡主卡面值 */
		Double value = cardInfo.getValue();
		/** 礼卡售价 */
		Double price = cardInfo.getPrice();
		/** 主卡平台补差 */
		Double platformPrice = cardInfo.getPlatformPrice();

		if (platformPrice == null) {
			platformPrice = 0.0;
		}
		/** 店铺补差 */
		Double storePrice = cardInfo.getStorePrice();
		if (storePrice == null) {
			storePrice = 0.0;
		}
		/** 品牌补差 */
		Double brandPrice = cardInfo.getBrandPrice();
		if (brandPrice == null) {
			brandPrice = 0.0;
		}
		/** 获取礼卡类型 */
		Long cardType = cardInfo.getCardType();
		/** 通过类型获取类型的编码 */
		CardTypeEntity cardtype = infoDao.selectTypeCodeById(cardType);
		String typeCode = cardtype.getTypeCode();

		/** 创建类型编码的常量 */
		String pt = "platform";
		String dp = "store";
		String pp = "brand";

		/** 初始化礼卡的状态为新建状态 */
		cardInfo.setState(1);
		/** 计算总额 */
		Double add = MathCountUtils.add(price, platformPrice);
		Double add2 = MathCountUtils.add(storePrice, brandPrice);
		Double sum = MathCountUtils.add(add, add2);

		if (pt == typeCode || pt.equals(typeCode) && sum.equals(value) || sum == value) {
			/** 自有卡---范围平台 */
			cardInfo.setRangeType(1);
			insertInfoAndDetail(cardInfo);
		} else if (dp == typeCode || dp.equals(typeCode) && sum.equals(value) || sum == value) {
			/** 联名卡---范围店铺 */
			cardInfo.setRangeType(2);
			/** 添加礼卡详细信息 */
			insertInfoAndDetail(cardInfo);
		} else if (pp == typeCode || pp.equals(typeCode) && sum.equals(value) || sum == value) {
			/** 品牌卡---范围品牌 */
			cardInfo.setRangeType(3);
			/** 添加礼卡详细信息 */
			insertInfoAndDetail(cardInfo);
		} else {
			throw new GlobalException(CardManageExceptionEnum.HOST_CARD_COMPENSATION_ERROR);
		}

	}

	@Override
	public CardInfoEntity getInfoById(Long id) throws GlobalException {
		/** 判断ID是否存在 */
		CardInfoEntity selectById = infoDao.selectById(id);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		return selectById;
	}

	@Override
	public Page<GiftCardDto> getInfoPage(GiftCardDto dto, Page<GiftCardDto> page) throws GlobalException {
		page.setRecords(infoDao.selectInfoPage(dto, page));
		return page;
	}

	@Override
	public void removeInfoOfBeach(Long[] list) throws GlobalException {
		/** 判断礼卡标签的ID是否存在 */
		for (Long id : list) {
			CardInfoEntity selectById = infoDao.selectById(id);
			if (selectById == null) {
				throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
			}
		}
		/** 批量查询 */
		List<CardInfoEntity> infoList = infoDao.selectInfoByIdBeach(list);
		Integer count = 0;
		for (CardInfoEntity cardinfo : infoList) {
			/** 判断是否全部为下架礼卡 */
			if (cardinfo.getState() != 3) {
				count = 1;
			}
		}
		/** 批量删除礼卡信息 */
		if (count == 0) {
			infoDao.deleteInfoOfBeach(list);
		} else {
			throw new GlobalException(CardManageExceptionEnum.CARD_IS_PUTAWAY_ALREADY);
		}
	}

	@Override
	public void giftCardSoldOut(Long cardId) throws GlobalException {
		CardInfoEntity cardinfo = infoDao.selectById(cardId);
		if (cardinfo == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		CardInfoEntity cardInfo = infoDao.selectBymainCardId(cardId);
		if (cardInfo != null) {
			infoDao.giftCardSoldOut(cardInfo.getId());
		}
		infoDao.giftCardSoldOut(cardId);
	}

	@Override
	public CardInfoEntity selectNumberById(Long id) throws GlobalException {
		CardInfoEntity selectNumberById = infoDao.selectNumberById(id);
		if (selectNumberById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		return selectNumberById;
	}

	@Override
	public void updateSurplusNumById(CardInfoEntity cardInfo) {

		infoDao.updateSurplusNumById(cardInfo);
	}

	@Override
	public Page<GiftCardDto> getInfoBycardId(GiftCardDto dto, Page<GiftCardDto> page) throws GlobalException {
		/** 判断礼卡ID是否存在 */
		CardInfoEntity cardinfo = infoDao.selectById(dto.getCardId());
		if (cardinfo == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		page.setRecords(infoDao.searchInfoById(dto, page));
		return page;
	}

	@Override
	public void giftCardPutAway(Long[] cardIds) throws GlobalException {
		List<CardInfoEntity> cardinfoList = infoDao.selectInfoByIdBeach(cardIds);
		for (CardInfoEntity infoEntity : cardinfoList) {
			if (infoEntity == null) {
				throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
			}
		}
		infoDao.giftCardPutAway(cardIds);
	}

	@Override
	public List<GiftCardDto> getMoreGiftCardBuy(Long cardMedium, Long cardId) {
		List<GiftCardDto> list = infoDao.getMoreGiftCardBuy(cardMedium, cardId);
		/** 判断礼卡是否含有副卡 */
		for (GiftCardDto giftCardDto : list) {
			CardInfoEntity infoEntity = infoDao.selectBymainCardId(giftCardDto.getCardId());
			if (null != infoEntity) {
				giftCardDto.setViceValue(infoEntity.getValue());
			}
		}
		return list;
	}

	@Override
	public List<GiftCardDto> purchaseNow(Long memberId, Long cardId, Long number) throws GlobalException {
		CardInfoEntity cardinfo = infoDao.selectById(cardId);
		/** 判断此礼卡是否存在 */
		if (cardinfo == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		/** 判断此礼卡剩余库存是否够数 */
		/** 若不够数 */
		if (cardinfo.getSurplusNum() < number) {
			throw new GlobalException(CardManageExceptionEnum.CARD_NUMBER_IS_NOT_ENOUGH);
		}
		/** 判断此会员购买的此礼卡数量是否已经达到会员限购数 */
		/** 查询此会员当前购买此种礼卡的数量 */
		Long num = infoDao.selectCountByMemberId(memberId, cardId);
		num = num + number;
		if (null != cardinfo.getBanMemberNo()) {
			if (num > cardinfo.getBanMemberNo()) {
				throw new GlobalException(CardManageExceptionEnum.BUY_CARD_NUMBER_IS_OVER_MEMBER);
			}
		}
		/** 获取此礼卡信息 */
		Long cardMedium = null;
		List<GiftCardDto> list = infoDao.getMoreGiftCardBuy(cardMedium, cardId);

		/** 判断礼卡是否含有副卡 */
		/** 定义需支金额价 */
		Double sumPrice = number * cardinfo.getValue();
		Double payMentPrice = number * cardinfo.getPrice();
		for (GiftCardDto giftCardDto : list) {
			giftCardDto.setPayMentPrice(payMentPrice);
			giftCardDto.setSumPrice(sumPrice);
			/** 每种礼卡数量 */
			giftCardDto.setNumber(number);
			giftCardDto.setNumbers(number);
			CardInfoEntity infoEntity = infoDao.selectBymainCardId(giftCardDto.getCardId());
			if (null != infoEntity) {
				giftCardDto.setNumbers(number * 2);
				giftCardDto.setViceValue(infoEntity.getValue());
			}
		}
		return list;
	}

}
