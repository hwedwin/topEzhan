package com.topaiebiz.giftcard.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dto.ExpenseCalendarDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardDetailService;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.goods.comment.exception.GoodsSkuCommentExceptionEnum;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.merchant.info.exception.MerchantInfoException;

/**
 * Description 描述礼卡详细信息的控制层，用于前后台的数据交互。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月2日 上午9:36:53
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/giftcard/carddetail")
public class GiftCardDetailController {

	@Autowired
	private GiftCardDetailService detailService;

	/**
	 * Description 获取礼卡标签，经营类型，介质的列表信息
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	public Map<String, Object> getAllList() {
		/** 查询礼卡标签列表 */
		List<CardLabelEntity> labelList = detailService.getLabel();
		/** 查询礼卡介质列表 */
		List<CardMediumEntity> mediaList = detailService.getMedia();
		/** 查询礼卡经营类型 */
		List<CardTypeEntity> typeList = detailService.getType();
		Map<String, Object> map = new HashMap<>();
		map.put("labelList", labelList);
		map.put("mediumList", mediaList);
		map.put("typeList", typeList);
		return map;
	}

	/**
	 * Description <美礼卡管理>——》<查看>——》<查看礼卡详情>
	 * 
	 * Author： Murray.Li
	 * 
	 * param:礼卡信息的ID
	 * 
	 * return 礼卡编号detail中的cardNo
	 */
	@RequestMapping(path = "/getDetailByCardId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDetailByCardId(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		GiftCardDto giftcardDto = detailService.getDetailByCardId(id);
		return new ResponseInfo(giftcardDto);
	}

	/**
	 * 
	 * Description：查看礼卡消费记录。
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long id
	 * 
	 * return ResponseInfo(list);
	 */
	@RequestMapping(path = "/getExpenseCalendar", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getExpenseCalendar(Long id, Page<ExpenseCalendarDto> page) throws GlobalException {
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		Page<ExpenseCalendarDto> list = detailService.getExpenseCalendar(id, page);
		return new ResponseInfo(list);
	}

	/***
	 * 
	 * Description： <美礼卡管理>——》<查看>——》<礼卡详细信息的冻结>。
	 * 
	 * Author Murray.Li
	 * 
	 * param:id return throws GlobalException
	 */
	@RequestMapping(path = "/blockDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo blockDetail(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		detailService.blockDetail(id);
		return new ResponseInfo();
	}

	/**
	 * Description 查询礼卡详细信息分页列表 +条件查询（激活管理界面）
	 * 
	 * Author Murray.Li
	 * 
	 * param:GiftCardInfoDto
	 * 
	 * return ResponseInfo(map);
	 */
	@RequestMapping(path = "/getDetailPage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDetailPage(GiftCardInfoDto giftCardInfoDto, Page<GiftCardInfoDto> page)
			throws GlobalException {
		Page<GiftCardInfoDto> list = detailService.getDetailPage(page, giftCardInfoDto);

		/** 获取礼卡经营类型，介质，标签的列表 */
		Map<String, Object> map = getAllList();
		map.put("detailList", list);

		return new ResponseInfo(map);
	}

	/**
	 * Description 礼卡详细的批量删除(<礼卡激活管理>》<删除>)
	 * 
	 * Author Murray.Li
	 * 
	 * param:Long[] id
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/cancelDetailOfBeach", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelDetailOfBeach(Long[] id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		detailService.removeDetailOfBeach(id);

		return new ResponseInfo();
	}

	/**
	 * Description 回显礼卡详细信息
	 *
	 * Author Murray.Li
	 *
	 * param:Long id
	 *
	 * return ResponseInfo(detail);
	 */
	@RequestMapping(path = "/getDetailById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDetailById(Long id) throws GlobalException {
		/** 判断ID是否为空 */
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		CardDetailEntity detail = detailService.getDetailById(id);

		return new ResponseInfo(detail);
	}

	/**
	 * Description 修改礼卡的详细信息
	 *
	 * Author Murray.Li
	 *
	 * param:CardDetailEntity;
	 *
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/editDetailById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editDetailById(CardDetailEntity detail) throws GlobalException {

		detailService.modifyDetailById(detail);

		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 礼卡批量激活
	 * 
	 * Author Murray.Li
	 * 
	 * param:GiftCardInfoDto
	 * 
	 * return ResponseInfo();
	 */
	@RequestMapping(path = "/cardActivate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cardActivate(GiftCardDto giftcarddto) throws GlobalException {
		if (null == giftcarddto) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		/** 判断ID是否为空 */
		if (null == giftcarddto.getList()) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		detailService.cardActive(giftcarddto);

		return new ResponseInfo();

	}

	/**
	 * 
	 * Description： 根据会员编号和店铺编号以及商品编号查询相应的礼卡信息。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getGiftCardByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardByMemberId(Long memberId, Long storeId, Long goodsId) throws GlobalException {
		/** 判断会员编号和店铺编号以及商品编号是否为空 */
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if (null == storeId) {
			throw new GlobalException(MerchantInfoException.MERCHANTINFO_ID_NOT_NULL);
		}
		if (null == goodsId) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_NULL);
		}
		Map<String, Object> map = detailService.getGiftCardByMemberId(memberId, storeId, goodsId);
		return new ResponseInfo(map);
	}

	/**
	 * 
	 * Description： 会员使用美礼卡购买商品。
	 * 
	 * Author Murray.Li
	 * 
	 * @param 商品编号：goodsId,店铺编号：storeId,会员编号：memberId,商品价值：goodsPrice,orderId：订单编号
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/buyGoodsOfGiftCard", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo buyGoodsOfGiftCard(Long storeId, Long memberId, Long[] goodsIds, Double goodsPrice,
			Long orderId) throws GlobalException {
		/** 判断会员编号和店铺编号以及商品编号是否为空 */
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if (null == storeId) {
			throw new GlobalException(MerchantInfoException.MERCHANTINFO_ID_NOT_NULL);
		}
		if (null == goodsIds) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_NULL);
		}
		if (null == goodsPrice) {
			throw new GlobalException(CardManageExceptionEnum.THE_GOODS_PRICE_IS_NOT_NULL);
		}
		if (null == orderId) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		detailService.buyGoodsOfGiftCard(storeId, memberId, goodsIds, goodsPrice, orderId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： 会员使用美礼卡购买商品的退款环节。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * 
	 * @return
	 */
	@RequestMapping(path = "/refund", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo refund(Long orderId) throws GlobalException {
		if (null == orderId) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		detailService.refundByOrder(orderId);
		return new ResponseInfo();
	}

}
