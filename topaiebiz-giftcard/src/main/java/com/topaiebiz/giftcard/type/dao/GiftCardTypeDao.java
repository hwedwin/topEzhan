package com.topaiebiz.giftcard.type.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.type.dto.CardTypeDto;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;

/**
 * @Description 描述礼卡经营类型的接口，并向礼卡经营类型控制层提供相关的方法。
 * 
 * 
 * @Author Murray
 *    
 * @Date 2017年9月2日 上午9:51:07 
 * 
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface GiftCardTypeDao extends BaseDao<CardTypeEntity>{  
   
	

   /**
	 * @Description 查询礼卡经营类型条件+分页   
	 * 
	 * @Author Murray  
	 * 
	 * @param dto
     * @param page 
	 * @return
	 */  
	List<CardTypeEntity> selectTypePage(CardTypeDto dto, Page<CardTypeEntity> page);    
	 
	/**
	 * @Description 礼卡经营类型信息的批删
	 * 
	 * @Author Murray  
	 * 
	 * @param list
	 */
	void deleteTypeOfBeach(List<Long> list);

	/**
	 * @Description 查询礼卡经营类型列表。  
	 * 
	 * @Author Murray  
	 * 
	 * @return
	 */
	List<CardTypeEntity> selectType();

	/**
	 * 
	 * Description： 根据礼卡经营类型名称查询礼卡信息对象。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardType
	 * @return
	 */
	CardTypeEntity selectByName(CardTypeEntity cardType);   

	
}
