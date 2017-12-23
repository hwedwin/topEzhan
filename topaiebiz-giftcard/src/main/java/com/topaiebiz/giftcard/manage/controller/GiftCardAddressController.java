package com.topaiebiz.giftcard.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardAddressService;

/**
 * Description 配送地址详细信息的控制层，用于前后台的数据交互。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月8日 下午4:04:33
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
public class GiftCardAddressController {

	@Autowired
	private GiftCardAddressService cardAddressService;

	/**
	 * Description
	 * 
	 * Author Murray.Li
	 * 
	 * return List
	 */
	@RequestMapping(path = "/getAddress", method = RequestMethod.GET)
	public List<CardAddressEntity> getAddress() {
		List<CardAddressEntity> addressList = cardAddressService.getAddressList();
		return addressList;
	}

	/**
	 * Description 添加配送地址的详细信息。
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardAddressEntity;
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/addAddress", method = RequestMethod.POST)
	public ResponseInfo addAddress(CardAddressEntity cardAddressEntity) throws GlobalException {
		cardAddressService.saveAddress(cardAddressEntity);
		return new ResponseInfo();

	}

	/**
	 * Description 回显详细地址对象信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 * 
	 * return ResponseInfo(cardAddressEntity);
	 */

	@RequestMapping(path = "/getAddressById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getAddressById(Long id) throws GlobalException {
		if (id == null) {
			throw new GlobalException(CardManageExceptionEnum.ADDRESS_ID_IS_NOT_NULL);
		}
		CardAddressEntity cardAddressEntity = cardAddressService.getAddressById(id);

		return new ResponseInfo(cardAddressEntity);
	}

	/**
	 * Description 修改详细地址信息。
	 * 
	 * Author Murray.Li
	 * 
	 * param:CardAddressEntity
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/editAddressById", method = RequestMethod.POST)
	public ResponseInfo editAddressById(CardAddressEntity cardAddressEntity) throws GlobalException {
		cardAddressService.modifyAddressById(cardAddressEntity);
		return new ResponseInfo();
	}

	/**
	 * Description 批量删除地址详细信息。
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long[]id
	 * 
	 * return
	 */
	@RequestMapping(path = "/cancelAddressOfBach", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelAddressOfBeach(Long[] ids) throws GlobalException {
		/** 判断id是否为空 */
		if (null == ids) {
			throw new GlobalException(CardManageExceptionEnum.ADDRESS_ID_IS_NOT_NULL);
		}
		cardAddressService.removeAddressOfBeach(ids);

		return new ResponseInfo();
	}
}
