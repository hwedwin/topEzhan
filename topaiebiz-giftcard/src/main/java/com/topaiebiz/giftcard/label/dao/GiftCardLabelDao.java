package com.topaiebiz.giftcard.label.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.label.dto.CardLabelDto;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;

/**
 * @Description 描述礼卡标签的接口，并向礼卡标签控制层提供相关的方法。
 * 
 * 
 * @Author Murray.Li
 * 
 * @Date 2017年9月2日 上午9:50:18
 * 
 * @Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface GiftCardLabelDao extends BaseDao<CardLabelEntity> {

	/**
	 * @param page
	 * @Description 查询礼卡标签条件+分页
	 * 
	 * @Author Murray.Li
	 * 
	 * @param dto
	 * 
	 * @return 含有礼卡标签对象的List集合
	 */
	List<CardLabelEntity> selectLabelPage(CardLabelDto dto, Page<CardLabelEntity> page);

	/**
	 * @Description 礼卡标签信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param Long[]
	 *            id
	 * @return
	 */
	void deleteLabelOfBeach(Long[] id);

	/***
	 * 
	 * Description： 根据礼卡标签的名称查询礼卡标签信息对象
	 * 
	 * Author Murray.Li
	 * 
	 * @param cardLabel
	 * @return 礼卡标签对象
	 */
	CardLabelEntity selectByName(CardLabelEntity cardLabel);  

	/***
	 * 
	 * Description：判断此礼卡标签是否已存在于创建的礼卡中。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	List<GiftCardInfoDto> selectCardInfoByLabelId(Long[] id);

}
