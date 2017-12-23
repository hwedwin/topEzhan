package com.topaiebiz.giftcard.type.controller;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.giftcard.type.dto.CardTypeDto;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.giftcard.type.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.type.service.GiftCardTypeService;

/**
 * @Description 描述礼卡经营类型的控制层，用于前后台数据交互。
 * 
 * 
 * @Author Murray
 * 
 * @Date 2017年9月2日 上午9:38:00
 * 
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/cardtype")
public class GiftCardTypeController {

	@Autowired
	private GiftCardTypeService typeService;

	/**
	 * @Description 添加礼卡经营类型信息
	 * 
	 * @Author Murray
	 *
	 * @param type
	 *
	 * @return 重定向到礼卡经营信息列表界面
	 */

	@RequestMapping(path = "/addType",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addType(@Valid CardTypeDto cardTypeDto, BindingResult result) throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		CardTypeEntity cardType = new CardTypeEntity();
		BeanUtils.copyProperties(cardTypeDto, cardType);
		typeService.saveType(cardType);
		return new ResponseInfo(null);
	}

	/**
	 * @Description 礼卡经营类型信息分页界面
	 * 
	 * @Author Murray
	 * 
	 * @param cardTypeDto
	 * @return
	 */
	@RequestMapping(path = "/getTypePage",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getTypePage(CardTypeDto cardTypeDto, Page<CardTypeEntity> page) throws GlobalException {
		Page<CardTypeEntity> list = typeService.getTypePage(cardTypeDto, page);
		return new ResponseInfo(list);
	}

	/**
	 * @Description 礼卡经营类型信息的批删
	 * 
	 * @Author Murray
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(path = "/cancelTypeOfBeach",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelTypeOfBeach(CardTypeDto cardTypeDto) throws GlobalException {
		if (null == cardTypeDto.getList()) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_NULL);
		}
		typeService.removeTypeOfBeach(cardTypeDto);

		return new ResponseInfo(null);
	}

	/**
	 * @Description 回显礼卡经营类型信息
	 * 
	 * @Author Murray
	 * 
	 * @param id
	 *
	 * @return 返回此ID对应的礼卡类型对象（json数据）
	 */
	@RequestMapping(path = "/getTypeById",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getTypeById(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_NULL);
		}

		CardTypeDto saveMediaDto = new CardTypeDto();
		CardTypeEntity cardType = typeService.getTypeById(id);
		BeanUtils.copyProperties(cardType, saveMediaDto);
		return new ResponseInfo(saveMediaDto);
	}

	/**
	 * @Description 修改礼卡经营类型信息
	 *
	 * @Author Murray
	 *
	 * @param type
	 *
	 * @return 重定向到礼卡经营类型界面
	 */
	@RequestMapping(path = "/editTypeById",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editTypeById(@Valid CardTypeDto cardTypeDto, BindingResult result) throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		if (null == cardTypeDto.getId()) {
			throw new GlobalException(GiftCardExceptionEnum.CARDTYPE_ID_NOT_NULL);
		}

		CardTypeEntity cardType = new CardTypeEntity();
		BeanUtils.copyProperties(cardTypeDto, cardType);
		typeService.modifyTypeById(cardType);

		return new ResponseInfo(null);
	}

}
