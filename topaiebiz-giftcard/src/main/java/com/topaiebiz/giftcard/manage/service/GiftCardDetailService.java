package com.topaiebiz.giftcard.manage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.ExpenseCalendarDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;

public interface GiftCardDetailService {

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
	 * Description 添加礼卡详情信息
	 * 
	 * Author Murray.Li
	 * 
	 * param detail
	 * 
	 * return
	 * 
	 * throws Exception
	 */
	/* void insertDetail(CardInfoEntity cardInfo) throws GlobalException; */

	/**
	 * Description 回显礼卡详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * param id return 礼卡详细信息对象CardDetail.
	 * 
	 * @throws GlobalException
	 */
	CardDetailEntity getDetailById(Long id) throws GlobalException;

	/**
	 * Description 修改礼卡详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * param detail return throws Exception
	 */
	void modifyDetailById(CardDetailEntity detail) throws GlobalException;

	/**
	 * Description 礼卡详细信息分页列表 +条件查询
	 * 
	 * Author Murray.Li
	 * 
	 * param page param detailDto return 礼卡详情信息分页对象Page throws Exception
	 */
	Page<GiftCardInfoDto> getDetailPage(Page<GiftCardInfoDto> page, GiftCardInfoDto detailDto) throws GlobalException;

	/**
	 * Description 礼卡详细信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param detail return throws Exception
	 */
	void removeDetailOfBeach(Long[] id) throws GlobalException;

	/**
	 * Description <美礼卡管理>——> <查看> ——><查看卡号 >
	 * 
	 * Author Murray.Li
	 * 
	 * param info return throws Exception
	 */
	GiftCardDto getDetailByCardId(Long id) throws GlobalException;

	/**
	 * throws Exception Description 添加订单号到礼卡详情表中。
	 * 
	 * Author Murray.Li
	 *
	 */
	void updateDetailOrderNoById(CardDetailEntity cardDetail) throws GlobalException;

	/***
	 * Description 批量更改礼卡详细信息中的订单号和礼卡状态。
	 * 
	 * Author Murray.Li
	 * 
	 * param list
	 */
	void updateDetailByCardIdList(List<Long> list);

	/***
	 * param list Description 根据订单编号（主键）查询所对应的编号。
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	List<CardDetailEntity> selectDetailByOrderId(Long[] list);

	/**
	 * Description 在礼卡详情表中找出此种礼卡中所有未销售的礼卡，任意取一张
	 * 
	 * Author Murray.Li
	 * 
	 * param giftCardOrderDto return
	 */
	List<CardDetailEntity> selectDetailOfNoSealByCardId(GiftCardOrderDto giftCardOrderDto);

	/**
	 * Description 生成电子卡。
	 * 
	 * Author Murray.Li
	 * 
	 * param cardDetail throws Exception
	 */
	void insertErpCard(CardDetailEntity cardDetail);

	/**
	 * 
	 * @Description 礼卡批量激活
	 * 
	 * @Author Murray.Li
	 * 
	 * @param ids
	 * @param memo
	 * @throws Exception
	 */
	void cardActive(GiftCardDto giftcardDto) throws GlobalException;

	/***
	 * 
	 * Description：冻结礼卡
	 * 
	 * Author Murray.Li
	 * 
	 * @param id
	 * @throws GlobalException
	 */
	void blockDetail(Long id) throws GlobalException;

	/**
	 * 
	 * Description： 查看礼卡消费记录
	 * 
	 * Author Murray.Li
	 * 
	 * @param id
	 * @param page 
	 * @return
	 * @throws GlobalException
	 */ 
	Page<ExpenseCalendarDto> getExpenseCalendar(Long id, Page<ExpenseCalendarDto> page) throws GlobalException;

	/***
	 * 
	 * Description： 根据会员编号和平台编号查看礼卡信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param storeId
	 * @param goodsId 
	 * @return
	 * @throws GlobalException    
	 */  
	Map<String,Object> getGiftCardByMemberId(Long memberId, Long storeId, Long goodsId) throws GlobalException;

	/**
	 * 
	 * Description： 会员使用美礼卡购买商品。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId 商品所属店铺ID
	 * @param memberId 会员ID
	 * @param goodsId 商品ID
	 * @param goodsPrice 商品总价
	 * @param orderId 订单ID
	 * @throws GlobalException  
	 */  
	void buyGoodsOfGiftCard(Long storeId, Long memberId, Long[] goodsIds, Double goodsPrice, Long orderId) throws GlobalException;

	/**
	 * 
	 * Description：获取该用户此品牌及店铺可用的金额。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @param memberId
	 * @param goodsIds
	 * @return
	 */
	Double getBalance(Long storeId, Long memberId, Long[] goodsIds);
	
	/**
	 * 
	 * Description： 根据退货订单号进行美礼卡的退款。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @throws GlobalException  
	 */ 
	void refundByOrder(Long orderId) throws GlobalException;
	
	/**
	 * 
	 * Description：根据总订单号进行美礼卡的退款。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param totalOrderId
	 * @throws GlobalException
	 */
	void refundByTotalOrderId(Long totalOrderId) throws GlobalException;

	/**
	 * 
	 * Description： 我的礼卡（可用/不可用）.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param cardState
	 * @return
	 * @throws GlobalException 
	 */ 
	List<GiftCardDto> getGiftCardInfoByMemberId(Long memberId, Integer cardState) throws GlobalException;

	/**
	 * 
	 * Description：根据会员编号查询他的礼卡的消费记录。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @return
	 * @throws GlobalException 
	 */ 
	List<CardDetailEntity> getExpenseCalendarByMemberId(Long memberId) throws GlobalException;

	/***
	 * 
	 * Description：查看卡密。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @return
	 * @throws GlobalException 
	 */ 
	CardDetailEntity getPasswordByCardId(Long cardId) throws GlobalException;

	/**
	 * 
	 * Description：礼卡订单》立即绑定。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @param memberId
	 * @throws GlobalException 
	 */
	void bindingMember(Long cardId, Long memberId) throws GlobalException;

	/**
	 * 
	 * Description： 我的礼卡当前余额。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @return
	 * @throws GlobalException 
	 */ 
	CardDetailEntity getCurrentBalance(Long memberId) throws GlobalException;

	/**
	 * 
	 * Description：根据礼卡账号和密码与会员进行绑定。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param cardNo
	 * @param password
	 * @throws GlobalException 
	 */  
	void bindingMemberByNumber(Long memberId, String cardNo, String password) throws GlobalException;

}  
