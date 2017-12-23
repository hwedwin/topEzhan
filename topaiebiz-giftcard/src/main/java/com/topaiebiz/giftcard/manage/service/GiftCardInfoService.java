package com.topaiebiz.giftcard.manage.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;

/**
 * Description 礼卡信息的业务层接口，为controller层提供方法。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 下午4:17:06
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface GiftCardInfoService {

	/**
	 * Description 礼卡标签列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */

	List<CardLabelEntity> getLabel();

	/**
	 * Description 查询礼卡介质列表
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	List<CardMediumEntity> getMedia();

	/**
	 * Description 礼卡经营类型列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardTypeEntity> getType();

	/**
	 * Description 回显礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param id
	 * 
	 * return 礼卡信息对象
	 * 
	 * @throws GlobalException
	 */
	CardInfoEntity getInfoById(Long id) throws GlobalException;

	/**
	 * Description 礼卡信息分页列表
	 * 
	 * Author Murray.Li
	 * 
	 * param page param dto
	 * 
	 * return 分页对象Page
	 * 
	 * @param page
	 * @throws GlobalException
	 */
	Page<GiftCardDto> getInfoPage(GiftCardDto dto, Page<GiftCardDto> page) throws GlobalException;

	/**
	 * Description 礼卡信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param info return throws Exception
	 */
	void removeInfoOfBeach(Long[] id) throws GlobalException;

	/**
	 * Description 礼卡下架
	 * 
	 * Author Murray.Li
	 * 
	 * param cardInfo return throws Exception
	 */
	void giftCardSoldOut(Long cardId) throws GlobalException;

	/**
	 * Description 查询此种礼卡的限购数量。
	 * 
	 * Author Murray.Li
	 * 
	 * param id return throws Exception
	 */
	CardInfoEntity selectNumberById(Long id) throws GlobalException;

	/**
	 * 削减礼卡剩余数量 throws Exception
	 */
	void updateSurplusNumById(CardInfoEntity selectNumberById);

	/**
	 * Description 美礼卡管理》》查看》》礼卡详情
	 * 
	 * Author Murray.Li
	 * 
	 * param cardinfo return throws Exception
	 * 
	 * @param page
	 */
	Page<GiftCardDto> getInfoBycardId(GiftCardDto dto, Page<GiftCardDto> page) throws GlobalException;

	/***
	 * Description 礼卡上架
	 * 
	 * Author Murray.Li
	 * 
	 * param cardInfo
	 * 
	 * @throws GlobalException
	 */
	void giftCardPutAway(Long[] cardId) throws GlobalException;

	/**
	 * Description 添加礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param info param info return throws Exception
	 */
	void saveInfo(CardInfoEntity info) throws GlobalException;

	/***
	 * 
	 * Description： 点击购买更多礼卡。
	 * 
	 * Author Murray.Li 
	 * @param cardMedium 
	 * @param cardId 
	 * 
	 * @return
	 */ 
	List<GiftCardDto> getMoreGiftCardBuy(Long cardMedium, Long cardId);

	/**
	 * 
	 * Description： 立即购买。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @param number
	 * @param number2 
	 * @return
	 * @throws GlobalException 
	 */   
	List<GiftCardDto> purchaseNow(Long memberId,Long cardId, Long number) throws GlobalException;   

}
