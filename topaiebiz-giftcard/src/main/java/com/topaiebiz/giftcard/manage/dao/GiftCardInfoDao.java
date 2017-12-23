package com.topaiebiz.giftcard.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;

@Mapper
public interface GiftCardInfoDao extends BaseDao<CardInfoEntity> {

	/**
	 * @Description 礼卡标签列表
	 * 
	 * @Author Murray.Li
	 * 
	 * @return List
	 */
	List<CardLabelEntity> selectLabel();

	/**
	 * @Description 查询礼卡介质列表
	 * 
	 * @Author Murray.Li
	 * 
	 * @return
	 */
	List<CardMediumEntity> selectMedia();

	/**
	 * @Description 礼卡经营类型列表
	 * 
	 * @Author Murray.Li
	 * 
	 * @return List
	 */
	List<CardTypeEntity> selectType();

	/**
	 * @Description 查询礼卡信息分页+条件查询
	 * 
	 * @Author Murray.Li
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	List<GiftCardDto> selectInfoPage(GiftCardDto dto, Page<GiftCardDto> page);

	/**
	 * @Description 礼卡信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void deleteInfoOfBeach(Long[] list);

	/**
	 * @Description 礼卡下架
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardId
	 */
	void giftCardSoldOut(Long cardId);

	/**
	 * @Description 批量查询礼卡信息，查看是否含有已上架礼卡
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardIds
	 * @return
	 */
	List<CardInfoEntity> selectInfoByIdBeach(Long[] cardIds);

	/**
	 * @Description 根据经营类型获取编码
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardType
	 * @return
	 */
	CardTypeEntity selectTypeCodeById(Long cardType);

	/**
	 * @Description 查询此种礼卡的ID限购数量
	 * 
	 * @Author Murray
	 * 
	 * @param id
	 * @return
	 */
	CardInfoEntity selectNumberById(Long id);

	/**
	 * @Description 削减此种礼卡的数量
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardInfo
	 */
	void updateSurplusNumById(CardInfoEntity cardInfo);

	/**
	 * @Description 美礼卡管理》》查看
	 * 
	 * @Author Murray.Li
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	List<GiftCardDto> searchInfoById(GiftCardDto dto, Page<GiftCardDto> page);

	/**
	 * @Description 礼卡上线
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardIds
	 */
	void giftCardPutAway(Long[] cardIds);

	/**
	 * 
	 * Description：根据礼卡名称查询礼卡信息对象。
	 * 
	 * Author Murray.Li
	 * 
	 * @param cardInfo
	 * @return
	 */
	List<CardInfoEntity> selectByName(CardInfoEntity cardInfo);

	/**
	 * 
	 * Description： 点击购买更多礼卡。
	 * 
	 * Author Murray.Li 
	 * @param cardMedium 
	 * @param cardId 
	 * 
	 * @return
	 */ 
	List<GiftCardDto> getMoreGiftCardBuy(@Param("cardMedium") Long cardMedium,@Param("cardId") Long cardId);
  
	/***
	 * 
	 * Description： 判断此礼卡是否含有副卡。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	CardInfoEntity selectBymainCardId(Long id);

	/**
	 * 
	 * Description： 查看此会员当前购买此礼卡的数量。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param cardId
	 * @return
	 */
	Long selectCountByMemberId(Long memberId, Long cardId);

	/**
	 * 
	 * Description： 若为虚拟卡则直接根据订单编号删除礼卡详细信息.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @param orderId
	 */
	void deleteByCardIdAndOrderId(Long cardId, Long orderId);

	/**
	 * 
	 * Description： 根据礼卡主键ID修改礼卡状态为删除。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 */
	void updateByCardIdAndOrderId(Long id);

	
}
