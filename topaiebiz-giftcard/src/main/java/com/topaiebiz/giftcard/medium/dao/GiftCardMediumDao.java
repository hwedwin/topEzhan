package com.topaiebiz.giftcard.medium.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.medium.dto.CardMediumDto;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;

/**
 * @Description 描述礼卡介质的接口，并向礼卡介质控制层提供相关的方法。
 * 
 * 
 * @Author Murray.Li
 * 
 * @Date 2017年9月2日 上午9:50:44
 * 
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface GiftCardMediumDao extends BaseDao<CardMediumEntity> {

	/**
	 * @param page
	 * @Description 查询礼卡介质模糊+分页
	 * 
	 * @Author Murray.Li
	 * 
	 * @param name
	 * @param creatorId
	 * @return
	 */
	List<CardMediumEntity> selectMediaPage(Page<CardMediumEntity> page, CardMediumDto dto);

	/**
	 * @Description 礼卡介质信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void deleteMediaOfBeach(List<Long> list);

	/**
	 * @Description 查询礼卡介质列表
	 * 
	 * @Author Murray.Li
	 * 
	 * @return
	 */
	List<CardMediumEntity> selectMedia();

	/**
	 * 
	 * Description：根据介质名称查询介质对象。
	 * 
	 * Author Murray.Li
	 * 
	 * @param cardMedium
	 * @return
	 */
	CardMediumEntity selectByName(CardMediumEntity cardMedium);

}
