package com.topaiebiz.giftcard.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.SaveInfoDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardInfoService;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;


/**
 * Description 描述礼卡信息的控制层 ，用于前后台数据交互。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 上午9:36:01
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Controller
@RequestMapping("/giftcard/cardinfo")
public class GiftCardInfoController {

	@Autowired
	private GiftCardInfoService infoService;

	/**
	 * Description 获取礼卡标签，经营类型，介质的列表信息
	 * 
	 * Author Murray.Li
	 * 
	 * return Map集合
	 */
	public Map<String, Object> getAllList() {
		/** 查询礼卡标签列表 */
		List<CardLabelEntity> labelList = infoService.getLabel();
		/** 查询礼卡介质列表 */
		List<CardMediumEntity> mediaList = infoService.getMedia();
		/** 查询礼卡经营类型 */
		List<CardTypeEntity> typeList = infoService.getType();
		Map<String, Object> map = new HashMap<>();
		map.put("labelList", labelList);
		map.put("mediumList", mediaList);
		map.put("typeList", typeList);
		return map;
	}

	/**
	 * Description 礼卡信息分页界面分页+条件查询 (美礼卡管理界面)
	 * 
	 * Author Murray.Li
	 * 
	 * param:Page
	 *
	 * 
	 * return ResponseInfo(map)
	 */
	@RequestMapping(path = "/getGiftCardInfoList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardInfoList(GiftCardDto cardInfoDto, Page<GiftCardDto> page) throws GlobalException {
    
		Page<GiftCardDto> list = infoService.getInfoPage(cardInfoDto, page);
		Map<String, Object> map = getAllList();
		map.put("infoList", list);
		return new ResponseInfo(map);
	}

	/**
	 * Description 给添加界面回显下拉框的信息。
	 * 
	 * Author Murray.Li
	 * 
	 * return ResponseInfo(map)
	 */
	@RequestMapping(path = "/getGiftCardInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardInfo() throws GlobalException {
		/** 获取礼卡标签，介质，经营类型 */
		Map<String, Object> map = getAllList();

		return new ResponseInfo(map);
	}

	/**
	 * Page<GiftCardDto> Description 添加礼卡信息
	 *
	 * Author Murray.Li
	 *
	 * param:saveInfoDto
	 *
	 * return ResponseInfo()
	 * 
	 * @throws Exception
	 */
	@RequestMapping(path = "/addGiftCardInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGiftCardInfo(@Valid SaveInfoDto saveInfoDto, BindingResult result) throws GlobalException {
       
		// 如果参数非法，抛出异常。
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		Date expirationTime = saveInfoDto.getExpirationTime(); 
		Date date = new Date(); 
		if(expirationTime.getTime()<date.getTime() || expirationTime.getTime()== date.getTime()) {
			throw new GlobalException(CardManageExceptionEnum.THE_EXPIRATIONTIME_IS_NOT_FUTURE);
		}
		CardInfoEntity cardInfo = new CardInfoEntity();
		BeanUtils.copyProperties(saveInfoDto, cardInfo);
		infoService.saveInfo(cardInfo);
		return new ResponseInfo();
	}

	/**
	 * Description 礼卡信息的批删
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long[] id;
	 * 
	 * return ResponseInfo();
	 * 
	 * @throws Exception
	 */
	@RequestMapping(path = "/cancelGiftCardInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGiftCardInfo(Long[] id, BindingResult result) throws GlobalException {

		/** 判断list集合中的ID是否为空 */
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_NULL);
		}
		infoService.removeInfoOfBeach(id);

		return new ResponseInfo();
	}

	/**
	 * Description 回显礼卡信息
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long cardId(礼卡信息的ID)
	 * 
	 * return ResponseInfo(map);
	 */
	@RequestMapping(path = "/getGiftCardInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardInfoById(Long cardId) throws GlobalException {
		if (null == cardId) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_NULL);
		}
		/** 获取礼卡标签，介质，经营类型 */
		Map<String, Object> map = getAllList();
		CardInfoEntity info = infoService.getInfoById(cardId);
		map.put("info", info);
		return new ResponseInfo(map);
	}

	/**
	 * Description 礼卡下架
	 * 
	 * Author Murray.Li
	 * 
	 * param：Long cardId(礼卡信息的ID)
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/giftCardSoldOut", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo giftCardSoldOut(Long cardId) throws GlobalException {
		/** 判断ID是否为空 */
		if (null == cardId) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_NULL);
		}
		infoService.giftCardSoldOut(cardId);
		return new ResponseInfo();
	}

	/**
	 * Description 礼卡批量上架
	 * 
	 * Author Murray.Li
	 * 
	 * param：Long[]cardId(礼卡信息的ID)
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/giftCardPutAway", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo giftCardPutAway(Long[] cardId) throws GlobalException {
		/** 判断ID是否为空 */
		if (null == cardId) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_NULL);
		}
		infoService.giftCardPutAway(cardId);
		return new ResponseInfo();
	}

	/**
	 * Description 美礼卡管理》》查看
	 * 
	 * Author Murray.Li
	 * 
	 * param:cardId(礼卡信息主键),cardNo(礼卡详细信息的编号),Page
	 * 
	 * return ResponseInfo(giftCardList);
	 */
	@RequestMapping(path = "/getGiftCardInfoByCardId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardInfoByCardId(GiftCardDto cardInfoDto, Page<GiftCardDto> page)
			throws GlobalException {
		/** 判断cradId是否为空 */
		if (null == cardInfoDto.getCardId()) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_NULL);
		}
		Page<GiftCardDto> giftCardList = infoService.getInfoBycardId(cardInfoDto, page);
		return new ResponseInfo(giftCardList);
	}

	/**
	 * 
	 * Description：获取当前已上架的数量不为0的礼卡列表
	 * 
	 * Author Murray.Li 
	 * 
	 * @param cardMedium
	 * @param cardId
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getCurrentCard", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCurrentCard(Long cardMedium, Long cardId) throws GlobalException {
		List<GiftCardDto> list = infoService.getMoreGiftCardBuy(cardMedium, cardId);
		return new ResponseInfo(list);
	}

}
