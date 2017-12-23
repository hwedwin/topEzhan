package com.topaiebiz.giftcard.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.dto.OrderAddressDto;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderInvoiceEntity;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;

@Mapper
public interface GiftCardOrderDao extends BaseDao<CardOrderEntity> {

	/**
	 * @Description 查询礼卡订单。
	 * 
	 * @Author Murray.Li
	 * 
	 * @return
	 */
	List<CardOrderEntity> selectCardOrder();

	/**
	 * @Description 查询订单 条件+分页。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param cardOrderDto
	 * @param page
	 * @return
	 */
	List<GiftCardOrderDto> selectOrderOfPage(GiftCardOrderDto cardOrderDto, Page<GiftCardOrderDto> page);

	/** 根据订单的ID查询addressid的集合 */
	List<CardAddressEntity> selectAddressOfBeach(Long[] list);

	/**
	 * @Description 批量删除礼卡订单信息
	 * 
	 * @Author Murray.Li
	 * 
	 * @param list
	 */
	void deleteOrderOfBeanch(Long[] list);

	/**
	 * @Description 获取此会员当前购买的数量。
	 * 
	 * @Author Murray.Li
	 * 
	 * @param giftCardOrderDto
	 * @return
	 */
	GiftCardOrderDto selectSumNumber(GiftCardOrderDto giftCardOrderDto);

	/**
	 * @Description 根据订单编号查询其所对应的所有礼卡信息
	 * 
	 * @Author Murray.Li
	 * 
	 * @param long1
	 * @return
	 */
	List<GiftCardDto> selectCardByOrderId(Long orderId);

	/**
	 * @Description 根据礼卡订单编号查看礼卡详细信息
	 * 
	 * @Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 */
	List<GiftCardDto> selectOrderByOrderId(Long orderId);

	/***
	 * 
	 * Description： 查询礼卡数量。
	 * 
	 * Author Murray.Li
	 * 
	 * @param giftCardDto
	 * @return
	 */
	GiftCardDto selectDetailCount(GiftCardDto giftCardDto);

	/**
	 * 
	 * Description：查询礼卡的发票在信息。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 */
	CardOrderInvoiceEntity selectOrderInvoice(Long orderId);

	/**
	 * 
	 * Description： 根据订单编号查询配送地址。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	CardAddressEntity selectAddressByorderId(Long orderId);

	/**
	 * 
	 * Description：根据订单编号修改配送地址信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderAddressDto
	 */
	void updateAddressByOrderId(OrderAddressDto orderAddressDto);

	/**
	 * 
	 * Description：根据订单编号查询需要标记发货的订单信息。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	OrderAddressDto selectDeliveryByOrderId(Long orderId);

	/**
	 * 
	 * Description： 根据订单编号删除订单。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 */
	void deleteOrderByOrderId(Long orderId);

	/**
	 * 
	 * Description： 根据订单编号删除配送地址信息。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 */
	void deleteAddressByOrderId(Long orderId);

	/***
	 * 
	 * Description：根据订单编号和所属礼卡的ID查询礼卡的数量。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardId
	 * @param orderId
	 * @return
	 */
	Long selectCountByCardIdAndOrderId(Long cardId, Long orderId);

	/**
	 * 
	 * Description：根据订单编号查询礼卡详细信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	List<GiftCardDto> selectCardDetailByOrderId(Long orderId);

	/**
	 * 
	 * Description：根据订单编号查看此订单中的礼卡介质。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	List<CardInfoEntity> selectMediumByOrderId(Long orderId); 

	/***
	 * 
	 * Description： 根据订单编号查询交易单号。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	TotalOrderEntity selectpayCallbackNoByOrderId(Long orderId);


}
