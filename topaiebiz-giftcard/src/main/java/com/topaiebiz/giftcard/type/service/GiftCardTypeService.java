package com.topaiebiz.giftcard.type.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.type.dto.CardTypeDto;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;

public interface GiftCardTypeService {

	/**
	 * Description 礼卡经营类型列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardTypeEntity> selectType();

	/**
	 * Description 添加礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param type return throws Exception
	 */
	Integer saveType(CardTypeEntity type) throws GlobalException;

	/**
	 * Description 回显礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param id return 礼卡经营类型对象 throws Exception
	 */
	CardTypeEntity getTypeById(Long id) throws GlobalException;

	/**
	 * Description 修改礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param type return throws Exception
	 */
	void modifyTypeById(CardTypeEntity cardType) throws GlobalException;

	/**
	 * Description 查询礼卡经营类型 条件+分页
	 * 
	 * Author Murray.Li
	 * 
	 * param page param dto return throws Exception
	 * 
	 * @param page
	 */
	Page<CardTypeEntity> getTypePage(CardTypeDto dto, Page<CardTypeEntity> page);

	/**
	 * Description 礼卡经营类型的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param type return throws Exception
	 */
	void removeTypeOfBeach(CardTypeDto dto) throws GlobalException;

}
