package com.topaiebiz.giftcard.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.ExpenseCalendarDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardMemberDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;
import com.topaiebiz.giftcard.manage.entity.CardUseLogEntity;

/**
 * @Description 描述礼卡详细信息的接口，并向礼卡详细信息控制层提供相关的方法。
 * 
 * 
 * @Author Murray.Li
 * 
 * @Date 2017年9月2日 上午9:48:57
 * 
 * @Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface GiftCardDetailDao extends BaseDao<CardDetailEntity> {

	/**
	 * Description 礼卡标签列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardLabelEntity> selectLabel();

	/**
	 * Description 查询礼卡介质列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardMediumEntity> selectMedia();

	/**
	 * Description 礼卡经营类型列表
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardTypeEntity> selectType();

	/**
	 * Description 添加礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardLabelEntity
	 * 
	 * return null
	 */
	void insertLabel(CardLabelEntity label);

	/**
	 * Description 添加礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardMediumEntity
	 */
	void insertMedia(CardMediumEntity media);

	/**
	 * Description 添加礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardTypeEntity
	 */
	void insertType(CardTypeEntity type);

	/**
	 * Description 逻辑删除礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 */
	void deleteLabelFalse(Long id);

	/**
	 * Description 逻辑删除礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 */
	void deleteMediaFalse(Long id);

	/**
	 * Description 逻辑删除礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 */
	void deleteTypeFalse(Long id);

	/**
	 * Description 回显礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id return CardLabelEntity
	 */
	CardLabelEntity selectLabelById(Long id);

	/**
	 * Description 修改礼卡标签信息
	 * 
	 * Author Murray.Li
	 * 
	 * param CardLabelEntity
	 * 
	 */
	void updateLabelById(CardLabelEntity label);

	/**
	 * @Description 回显礼卡介质信息
	 * 
	 * @Author Murray.Li
	 * 
	 * @param id
	 * @return 卡介质信息对象
	 */
	CardMediumEntity selectMediaById(Long id);

	/**
	 * Description 修改礼卡介质信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardMediumEntity
	 */
	void updateMediaById(CardMediumEntity media);

	/**
	 * Description 回显礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id return 礼卡经营类型对象
	 */
	CardTypeEntity selectTypeById(Long id);

	/**
	 * Description 修改礼卡经营类型信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardTypeEntity
	 */
	void updateTypeById(CardTypeEntity type);

	/**
	 * Description 查询礼卡信息列表 。
	 * 
	 * Author Murray.Li
	 * 
	 * return 礼卡信息列表List。
	 */
	List<CardInfoEntity> selectInfo();

	/**
	 * Description 添加礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardInfoEntity
	 */
	void insertInfo(CardInfoEntity info);

	/**
	 * Description 逻辑删除礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 */
	void deleteInfofalse(Long id);

	/**
	 * Description 回显礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id return 礼卡信息的对象
	 */
	CardInfoEntity selectInfoById(Long id);

	/**
	 * Description 修改礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardInfoEntity
	 */
	void updateInfoById(CardInfoEntity info);

	/**
	 * 
	 * Description 查询礼卡详情信息列表 +条件查询
	 * 
	 * param:GiftCardInfoDto
	 * 
	 * Author Murray.Li
	 * 
	 * return 礼卡详细信息的列表
	 */
	List<GiftCardInfoDto> selectDetailPage(GiftCardInfoDto infoDto, Page<GiftCardInfoDto> page);

	/**
	 * Description 添加礼卡详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardDetailEntity
	 */
	void insertDetail(CardDetailEntity detail);

	/**
	 * Description 修改礼卡详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardDetailEntity
	 */
	void updateDetailById(CardDetailEntity detail);

	/**
	 * Description 查询当前数据库中最大编号的礼卡
	 * 
	 * Author Murray.Li
	 * 
	 * return 礼卡详情信息对象
	 */
	CardDetailEntity selectMaxCardNo();

	/**
	 * Description 查询礼卡介质模糊+分页
	 * 
	 * Author Murray.Li
	 * 
	 * param:GiftCardDto
	 * 
	 * return List
	 */
	List<CardMediumEntity> selectMediaPage(GiftCardDto dto);

	/**
	 * Description 查询礼卡标签条件+分页
	 * 
	 * Author Murray.Li
	 * 
	 * param:name param:creatorId return List
	 */
	List<CardLabelEntity> selectLabelPage(GiftCardDto dto);

	/**
	 * Description 查询礼卡经营类型条件+分页
	 * 
	 * Author Murray.Li
	 * 
	 * param:GiftCardDto return list
	 */
	List<CardTypeEntity> selectTypePage(GiftCardDto dto);

	/**
	 * Description 礼卡标签信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param:list return
	 */
	void deleteLabelOfBeach(ArrayList<Object> list);

	/**
	 * Description 礼卡介质信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param:list
	 */
	void deleteMediaOfBeach(ArrayList<Object> list);

	/**
	 * Description 礼卡经营类型信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param:list
	 */
	void deleteTypeOfBeach(ArrayList<Object> list);

	/**
	 * @Description 查询礼卡信息分页+条件查询
	 * 
	 * @Author Murray.Li
	 * 
	 * @param dto
	 * @return List
	 */
	List<CardInfoEntity> selectInfoPage(GiftCardDto dto);

	/**
	 * @Description 礼卡信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void deleteInfoOfBeach(ArrayList<Object> list);

	/**
	 * @Description 礼卡详细信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void deleteDetailOfBeach(Long[] list);

	/**
	 * @Description 礼卡下架
	 * 
	 * @Author Murray.Li
	 * 
	 * @param id
	 */
	void downLineOfInfo(Long id);

	/**
	 * @Description <美礼卡管理>——》 <查看> ——》<查看礼卡详情>
	 * 
	 * @Author Murray.Li
	 * 
	 * @param id
	 * @return
	 */
	GiftCardDto selectDetailByCardId(Long id);

	/**
	 * @Description 根据所属礼卡编号批量删除礼卡。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardInfo
	 */
	void deleteDetailByCardId(CardInfoEntity cardInfo);

	/***
	 * @Description 批量查询礼卡信息。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 * @return
	 */
	List<CardDetailEntity> selectDetailByIdBeach(Long[] list);

	/**
	 * @Description 添加礼卡详情表相应的订单编号。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardDetail
	 */
	void updateOrderNoById(CardDetailEntity cardDetail);

	/***
	 * @Description 批量更改礼卡详细信息中的订单号和礼卡状态。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void updateDetailByCardIdList(List<Long> list);

	/**
	 * @Description 根据订单编号找到对应的礼卡编号。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 * @return
	 */
	List<CardDetailEntity> selectDetailByOrderId(Long[] list);

	/**
	 * @Description 在礼卡详情表中找出此种礼卡中所有未销售的礼卡，任意取一张
	 * 
	 * @Author Murray.Li
	 * 
	 * @param giftCardOrderDto
	 * @return
	 */
	List<CardDetailEntity> selectDetailOfNoSealById(GiftCardOrderDto giftCardOrderDto);

	/**
	 * 
	 * @Description 礼卡激活。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param ids
	 * @param memo
	 */
	void cardActive(GiftCardDto giftcardDto);  

	/**
	 * 
	 * Description： 根据所属礼卡编号查询礼卡的详细信息。
	 * 
	 * Author Murray.Li
	 * 
	 * @param id
	 * @return
	 */
	CardDetailEntity selectDetailByInfoId(Long id);

	/***
	 * 
	 * Description： 冻结礼卡。
	 * 
	 * Author Murray.Li
	 * 
	 * @param id
	 */
	void blockDetail(Long id);

	/**
	 * 
	 * Description：查看礼卡的消费记录。
	 * 
	 * Author Murray.Li
	 * 
	 * @param id
	 * @param page 
	 * @return 
	 */
	List<ExpenseCalendarDto> expenseCalendar(Long id, Page<ExpenseCalendarDto> page);

	/**
	 * 
	 * Description： 根据会员编号和店铺编号查询礼卡信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param storeId
	 * @return
	 */ 
	GiftCardMemberDto selectGiftCardByStoreId(Long memberId, Long storeId);

	/**
	 * 
	 * Description： 根据会员编号和商品编号查询礼卡信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param goodsId
	 * @return 
	 */
	GiftCardMemberDto selectGiftCardByGoodsId(Long memberId, Long goodsId);

	/**
	 * 
	 * Description：根据会员编号查询自由卡信息。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId 
	 * @return
	 */
	GiftCardMemberDto selectGiftCardByMemberId(Long memberId);

	/**
	 * 
	 * Description： 根据会员编号及商品编号查询此会员所有品牌卡的余额信息.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param goodsId
	 * @param memberId
	 * @return
	 */
	List<GiftCardMemberDto> selectBrandByGoodsId(Long goodsId, Long memberId);
    
	/**
	 * 
	 * Description： 根据会员编号及店铺编号查询此会员所有联名卡的余额信息.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param storeId
	 * @return
	 */
	List<GiftCardMemberDto> selectStoreByStoreId(Long memberId, Long storeId);

	/**
	 * 
	 * Description：根据会员编号查询此会员所有自由卡的余额信息. 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @return
	 */
	List<GiftCardMemberDto> selectCardByMemberId(Long memberId);
    /**
     * 
     * Description：根据订单编号查询相关订单的信息，并将所扣礼卡金额返回.
     * 
     * Author Murray.Li 
     * 
     * @param orderId
     * @return
     */
	List<CardUseLogEntity> selectCardUseLogByOrderId(Long orderId);

	/**
	 * 
	 * Description： 删除此订单编号对应的礼卡消费记录。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 */
	void deleteCardUseLogByOrderId(Long orderId);

	/**
	 * 
	 * Description：我的礼卡（可用/不可用）.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param cardState
	 * @return
	 */ 
	List<GiftCardDto> selectCardByMemberIdAndCardState(Long memberId, Integer cardState);

	/***
	 * 
	 * Description： 将过期礼卡改为不可用（cardState=4)
	 * 
	 * Author Murray.Li 
	 */
	void updateCardByDate(); 

	/***
	 * 
	 * Description： 根据会员编号查询他所有礼卡的消费记录。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @return
	 */
	List<CardDetailEntity> getExpenseCalendarByMemberId(Long memberId);

	/**
	 * 
	 * Description：礼卡订单》查看卡密。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @return
	 */
	CardDetailEntity selectPasswordByCardId(Long cardId);

	/**
	 * 
	 * Description：礼卡订单》立即绑定。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @param memberId
	 */
	void bindingMember(CardDetailEntity cardDetailEntity);

	/***
	 * 
	 * Description： 查询所有未销售的此种礼卡
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	List<CardDetailEntity> selectDetailOfNoSealByCardId(Long id);

	/**
	 * 
	 * Description： 查询会员当前礼卡的总余额。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @return
	 */
	CardDetailEntity selectCurrentBalance(Long memberId);

	/**
	 * 
	 * Description： 根据礼卡账号和密码与会员进行绑定。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param cardNo
	 * @param password
	 */
	void bindingMemberByNumber(CardDetailEntity cardDetailEntity);

	/**
	 * 
	 * Description： 判断礼卡的卡号或密码是否正确。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardNo
	 * @param password
	 * @return
	 */
	CardDetailEntity getDetailByCardNoAndPassword(String cardNo, String password) throws GlobalException;

	/**
	 * 
	 * Description： 将过期礼卡信息状态改为下架。  
	 * 
	 * Author Murray.Li 
	 *
	 */
	void updateCardInfoByDate();

	List<CardDetailEntity> selectByCardIdAndOrderId(Long cardId, Long orderId);

	/**
	 * 
	 * Description： 根据商品ID查询商品名称和该商品所属店铺名称。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param goodsId
	 * @return
	 */
	ExpenseCalendarDto selectExpenseCalendarDtoByGoodsId(Long goodsId); 

	

	   

}
