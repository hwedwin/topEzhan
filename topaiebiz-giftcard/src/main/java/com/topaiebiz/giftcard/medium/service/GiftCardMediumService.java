package com.topaiebiz.giftcard.medium.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.medium.dto.CardMediumDto;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;

public interface GiftCardMediumService {

	/**
	 * Description 查询礼卡介质列表
	 * 
	 * Author Murray.Li
	 * 
	 * return throws GlobException
	 */
	List<CardMediumEntity> selectMedia();

	/**
	 * Description 添加礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param media return throws GlobException
	 * 
	 * @throws Exception
	 */
	Integer saveMedia(CardMediumEntity media) throws GlobalException;

	/**
	 * Description 回显礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param id return 卡介质信息对象 throws GlobException
	 */
	CardMediumEntity getMediaById(Long id) throws GlobalException;

	/**
	 * Description 修改礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param media return throws GlobException
	 */
	Integer modifyMediaById(CardMediumEntity media) throws GlobalException;

	/**
	 * Description 礼卡介质信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param media return throws GlobException
	 */
	void removeMediaOfBeach(CardMediumEntity media) throws GlobalException;

	/**
	 * Description 查询礼卡介质 条件+分页
	 * 
	 * Author Murray.Li
	 * 
	 * param page param name 礼卡介质名称 param creatorId 创建人编号 return page throws
	 * GlobException
	 */
	Page<CardMediumEntity> getMediumPage(CardMediumDto dto, Page<CardMediumEntity> page);

}
