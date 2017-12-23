package com.topaiebiz.giftcard.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;

@Mapper
public interface GiftCardAddressDao extends BaseDao<CardAddressEntity> {
	/**
	 * Description 查询配送地址详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	List<CardAddressEntity> selectAddressList();

	/**
	 * Description 批量删除礼卡配送地址信息
	 * 
	 * Author Murray.Li
	 * 
	 * param ids
	 */
	void deleteAddressOfBeach(Long[] ids); 

	/**
	 * 
	 * Description： 根据订单编号查询收货人信息。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return CardAddressEntity
	 */
	CardAddressEntity selectByOrderId(Long orderId);

}
