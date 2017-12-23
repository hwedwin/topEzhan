package com.topaiebiz.giftcard.manage.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;

public interface GiftCardAddressService {
	/**
	 * 
	 * Description 查询配送地址的列表信息
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	List<CardAddressEntity> getAddressList();

	/**
	 * 
	 * Description 添加配送地址信息。
	 * 
	 * Author Murray.Li
	 * 
	 * param:cardAddress throws Exception
	 */
	void saveAddress(CardAddressEntity cardAddressEntity);

	/**
	 * 
	 * Description 根据ID查询配送地址。
	 * 
	 * Author Murray.Li
	 * 
	 * param:id return throws Exception
	 */
	CardAddressEntity getAddressById(Long id) throws GlobalException;

	/**
	 * 
	 * Description 修改配送地址信息。
	 * 
	 * Author Murray.Li
	 * 
	 * param:cardAddress return throws Exception
	 */
	Integer modifyAddressById(CardAddressEntity cardAddressEntity);

	/**
	 * 
	 * Description 批量删除配送地址。
	 * 
	 * Author Murray.Li
	 * 
	 * param:cardAddress throws Exception
	 */
	void removeAddressOfBeach(Long[] id) throws GlobalException; 

}
