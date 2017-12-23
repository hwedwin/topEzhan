package com.topaiebiz.giftcard.medium.controller;

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
import com.topaiebiz.giftcard.medium.exception.GiftCardExceptionEnum;
import com.topaiebiz.giftcard.medium.dto.CardMediumDto;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.medium.service.GiftCardMediumService;

/**
 * @Description 礼卡介质的控制层，用于前后台数据交互的类。
 * 
 * 
 * @Author Murray.Li
 * 
 * @Date 2017年9月2日 上午9:34:40
 * 
 * @Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/cardmedium")
public class GiftCardMediumController {

	@Autowired
	private GiftCardMediumService mediaService;

	/**
	 * @Description 礼卡介质查询分页+条件
	 * 
	 * @Author Murray.Li
	 * 
	 * @param Page
	 *
	 * @return ResponseInfo(list);
	 */
	@RequestMapping(path = "/getMediumPage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMediumPage(CardMediumDto cardMediumDto, Page<CardMediumEntity> page) throws GlobalException {
		Page<CardMediumEntity> list = mediaService.getMediumPage(cardMediumDto, page);
		return new ResponseInfo(list);
	}

	/**
	 * @Description 添加礼卡介质信息
	 * 
	 * @Author Murray.Li
	 * 
	 * @param CardMediumDto
	 *
	 * @return ResponseInfo();
	 */
	@RequestMapping(path = "/addMedium", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMedium(@Valid CardMediumDto saveMediaDto, BindingResult result) throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		CardMediumEntity giftmedia = new CardMediumEntity();
		BeanUtils.copyProperties(saveMediaDto, giftmedia);
		mediaService.saveMedia(giftmedia);
		return new ResponseInfo();
	}

	/**
	 * @Description 礼卡介质信息的批删
	 * 
	 * @Author Murray.Li
	 * 
	 * @param CardMediumDto
	 * 
	 * @return ResponseInfo();
	 */
	@RequestMapping(path = "/cancelMediumOfBeach", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMediumOfBeach(CardMediumDto cardMediumDto) throws GlobalException {
		if (cardMediumDto.getList() == null) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_NULL);
		}
		CardMediumEntity cardMedia = new CardMediumEntity();
		BeanUtils.copyProperties(cardMediumDto, cardMedia);
		mediaService.removeMediaOfBeach(cardMedia);
		return new ResponseInfo();
	}

	/**
	 * @Description 回显礼卡介质信息
	 * 
	 * @Author Murray.Li
	 *
	 * @param Long
	 *            id
	 * 
	 * @return ResponseInfo(saveMediaDto);
	 */

	@RequestMapping(path = "/getMediumById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getMediumById(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_NULL);
		}
		CardMediumEntity cardMedia = null;
		CardMediumDto saveMediaDto = new CardMediumDto();
		cardMedia = mediaService.getMediaById(id);
		BeanUtils.copyProperties(cardMedia, saveMediaDto);
		return new ResponseInfo(saveMediaDto);
	}

	/**
	 * @Description 修改礼卡介质信息
	 *
	 * @Author Murray.Li
	 * 
	 * @param CardMediumDto
	 * 
	 * @return ResponseInfo();
	 */
	@RequestMapping(path = "/editMediaById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMediumById(@Valid CardMediumDto cardMediumDto, BindingResult result)
			throws GlobalException {
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		if (null == cardMediumDto.getId()) {
			throw new GlobalException(GiftCardExceptionEnum.CARDMEDIUM_ID_NOT_NULL);
		}
		CardMediumEntity media = new CardMediumEntity();
		BeanUtils.copyProperties(cardMediumDto, media);
		mediaService.modifyMediaById(media);
		return new ResponseInfo();
	}

}
