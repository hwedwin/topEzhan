package com.topaiebiz.giftcard.label.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.label.dto.CardLabelDto;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;

public interface GiftCardLabelService {

	/**
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardLabelEntity(礼卡标签对象)
	 * 
	 * return Integer
	 * 
	 * throws Exception
	 */
	Integer saveLabel(CardLabelEntity cardLabel) throws GlobalException;

	/**
	 * Description 回显礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:id
	 * 
	 * return 礼卡标签对象
	 * 
	 * throws Exception
	 */
	CardLabelEntity getLabelById(Long id) throws GlobalException;

	/**
	 * Description 修改礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardLabelEntity
	 * 
	 * return Integer
	 */
	Integer modifyLabelById(CardLabelDto cardLabelDto) throws GlobalException;

	/**
	 * Description 查询礼卡标签 条件+分页
	 * 
	 * Author Murray.Li 
	 * 
	 * param:CardLabelDto
	 * 
	 * return 礼卡标签的集合
	 */
	Page<CardLabelEntity> getLabelPage(Page<CardLabelEntity> page, CardLabelDto dto);

	/**
	 * Description 礼卡标签信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long[] id
	 * 
	 * return null
	 * 
	 * throws Exception
	 */
	void removeLabelOfBeach(Long[] id) throws GlobalException;

}
