package com.topaiebiz.giftcard.label.controller;

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
import com.topaiebiz.giftcard.label.dto.CardLabelDto;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.label.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.label.service.GiftCardLabelService;

/**
 * Description 礼卡控制层 （与前台展示层进行数据交互）
 * 
 * Author Murray.Li
 * 
 * Date 2017年8月25日 上午10:58:19
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/giftcard/cardlabel")
public class GiftCardLabelController {

	@Autowired
	private GiftCardLabelService giftCardLabelService;

	/**
	 * Description： 礼卡标签查询分页+条件
	 * 
	 * Author： Murray.Li
	 * 
	 * param:page(礼卡标签的分页列表)
	 * 
	 * return list(礼卡标签的集合)
	 * 
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getGiftCardLabelList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardLabelList(CardLabelDto cardLabelDto, Page<CardLabelEntity> page)
			throws GlobalException {

		Page<CardLabelEntity> list = giftCardLabelService.getLabelPage(page, cardLabelDto);

		return new ResponseInfo(list);
	}

	/**
	 * Description： 添加礼卡标签信息
	 * 
	 * Author： Murray.Li
	 * 
	 * param:label（礼卡标签对象）
	 * 
	 * return ResponseInfo 对象
	 */
	@RequestMapping(path = "/addGiftCardLabel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGiftCardLabel(@Valid CardLabelDto cardLabelDto, BindingResult result)
			throws GlobalException {

		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		CardLabelEntity cardLabel = new CardLabelEntity();
		BeanUtils.copyProperties(cardLabelDto, cardLabel);
		giftCardLabelService.saveLabel(cardLabel);

		return new ResponseInfo();
	}

	/**
	 * Description： 礼卡标签信息的批删
	 * 
	 * Author： Murray.Li
	 * 
	 * param:Long[] id ()
	 * 
	 * return ResponseInfo()
	 */
	@RequestMapping(path = "/cancelGiftCardLabel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGiftCardLabel(Long[] id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(GiftCardExceptionEnum.CARDLABEL_ID_NOT_NULL);
		}
		giftCardLabelService.removeLabelOfBeach(id);
		return new ResponseInfo();
	}

	/**
	 * Description： 回显礼卡标签信息
	 * 
	 * Author： Murray.Li
	 * 
	 * param:id
	 * 
	 * return：ResponseInfo(cardLabel); 该ID对应的礼卡标签对象
	 */
	@RequestMapping(path = "/getGiftCardLabelById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardLabelById(Long id) throws GlobalException {
		/** 判断ID是否为空 */
		if (null == id) {
			throw new GlobalException(GiftCardExceptionEnum.CARDLABEL_ID_NOT_NULL);
		}

		CardLabelEntity cardLabel = giftCardLabelService.getLabelById(id);
		return new ResponseInfo(cardLabel);
	}

	/**
	 * Description： 修改礼卡标签信息
	 * 
	 * Author： Murray.Li
	 *
	 * param：label（礼卡标签对象）
	 *
	 *
	 * return ResponseInfo();
	 */

	@RequestMapping(path = "/editGiftCardLabelById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editGiftCardLabelById(@Valid CardLabelDto cardLabelDto, BindingResult result)
			throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		if (null == cardLabelDto.getId()) {
			throw new GlobalException(GiftCardExceptionEnum.CARDLABEL_ID_NOT_NULL);
		}

		giftCardLabelService.modifyLabelById(cardLabelDto);

		return new ResponseInfo();
	}

}
