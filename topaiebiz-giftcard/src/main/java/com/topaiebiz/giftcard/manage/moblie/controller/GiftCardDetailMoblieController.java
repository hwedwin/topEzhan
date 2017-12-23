package com.topaiebiz.giftcard.manage.moblie.controller;

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
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardDetailService;
import com.topaiebiz.giftcard.manage.service.GiftCardInfoService;
import com.topaiebiz.giftcard.manage.service.GiftCardOrderService;
import com.topaiebiz.goods.comment.exception.GoodsSkuCommentExceptionEnum;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.merchant.info.exception.MerchantInfoException;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

/**
 * 
 * Description： 美礼卡移动端的控制层。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年10月17日 下午1:13:08
 * 
 * Copyright：Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/app/giftcard/carddetail/moblie")
public class GiftCardDetailMoblieController {

	@Autowired
	private GiftCardDetailService detailService;

	@Autowired
	private GiftCardOrderService cardOrderService;

	@Autowired
	private GiftCardInfoService giftCardInfoService;

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
	public ResponseInfo getGiftCardByMemberId(Long memberId, Long storeId, Long goodsId, String token)
			throws GlobalException {
		TokenUtil.verifyToken(token);
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
			Long orderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
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

	/**
	 * 
	 * Description：我的礼卡（可用/不可用）
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getGiftCardInfoByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCardInfoByMemberId(Integer cardState, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		List<GiftCardDto> giftcardList = detailService.getGiftCardInfoByMemberId(memberId, cardState);
		return new ResponseInfo(giftcardList);
	}

	/**
	 * 
	 * Description： C端礼卡订单。
	 * 
	 * Author Murray.Li
	 * 
	 * @param memberId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getCardOrderByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCardOrderByMemberId(String token, Integer orderState, Page<GiftCardOrderDto> page)
			throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();

		GiftCardOrderDto giftCardOrderDto = new GiftCardOrderDto();
		giftCardOrderDto.setMemberId(memberId);
		giftCardOrderDto.setOrderState(orderState);
		Page<GiftCardOrderDto> list = cardOrderService.getOrderOfPage(page, giftCardOrderDto);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description：美礼卡订单详情。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(path = "/getCardOrderByOrderId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCardOrderByOrderId(Long orderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		Map<String, Object> map = cardOrderService.getCardOrderByOrderId(orderId);
		return new ResponseInfo(map);
	}

	/**
	 * 
	 * Description： 礼卡订单》查看卡密。
	 * 
	 * Author Murray.Li
	 * 
	 * @param id(礼卡主键ID)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getPasswordByCardId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPasswordByCardId(Long id, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (null == id) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		CardDetailEntity CardDetailEntity = detailService.getPasswordByCardId(id);
		return new ResponseInfo(CardDetailEntity);
	}

	/**
	 * 
	 * Description：礼卡订单》立即绑定。
	 * 
	 * Author Murray.Li
	 * 
	 * @param id(礼卡主键ID)，memberId(会员主键ID)
	 * @param memberId
	 * @return
	 */
	@RequestMapping(path = "/bindingMember", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo bindingMember(Long id, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (id == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}

		detailService.bindingMember(id, memberId);
		return new ResponseInfo();
	}

	/***
	 * 
	 * Description：会员查看消费记录。
	 * 
	 * Author Murray.Li
	 * 
	 * @param memberId
	 * @return
	 */
	@RequestMapping(path = "/getExpenseCalendarByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getExpenseCalendarByMemberId(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		List<CardDetailEntity> list = detailService.getExpenseCalendarByMemberId(memberId);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description：点击购买更多礼卡。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getMoreGiftCardBuy", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMoreGiftCardBuy(Long cardMedium, Long cardId, String token) throws GlobalException {
		List<GiftCardDto> list = giftCardInfoService.getMoreGiftCardBuy(cardMedium, cardId);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description： 点击购买礼卡。
	 * 
	 * Author Murray.Li
	 * 
	 * @param memberId
	 * @param cardId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/buyGiftCard", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo buyGiftCard(Long cardMedium, Long cardId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (cardId == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		List<GiftCardDto> list = giftCardInfoService.getMoreGiftCardBuy(cardMedium, cardId);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description： 立即购买。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/purchaseNow", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo purchaseNow(Long cardId, Long number, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (cardId == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}
		List<GiftCardDto> list = giftCardInfoService.purchaseNow(memberId, cardId, number);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description： 点击去支付，生成订单（礼卡订单，支付订单，状态为待支付）。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/generateOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo generateOrder(OrderInvoiceEntity orderInvoiceEntity) throws GlobalException {
		TokenUtil.verifyToken(orderInvoiceEntity.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(orderInvoiceEntity.getToken());
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if (orderInvoiceEntity.getCardId() == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_NULL);
		}

		Map<String, Object> map = cardOrderService.generateOrder(orderInvoiceEntity);
		return new ResponseInfo(map);
	}

	/**
	 * 
	 * Description：完成订单支付，将订单状态改为代发货
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(path = "/payCardOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo payCardOrder(Long orderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.payCardOrder(orderId, memberId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： 取消订单。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * 
	 * @return
	 */
	@RequestMapping(path = "/cancelOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOrder(Long orderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (null == orderId) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.cancelOrder(orderId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description：我的礼卡当前余额。
	 * 
	 * Author Murray.Li
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(path = "/getCurrentBalance", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCurrentBalance(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		CardDetailEntity cardDetailEntity = detailService.getCurrentBalance(memberId);
		return new ResponseInfo(cardDetailEntity);
	}

	/**
	 * 
	 * Description：根据礼卡的账号和密码与会员进行绑定。
	 * 
	 * Author Murray.Li
	 * 
	 * @param token
	 * @param cardNo
	 * @param password
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/bindingMemberByNumber", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo bindingMemberByNumber(String token, String cardNo, String password) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		detailService.bindingMemberByNumber(memberId, cardNo, password);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： <美礼卡订单管理>》<删除订单>
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * 
	 * @return ResponseInfo();
	 * 
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelOrderById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOrderById(String token, Long orderId) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (null == orderId) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.removeOrderByorderId(orderId, memberId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： 延长收货。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/extendTheReceiving", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo extendTheReceiving(String token, Long orderId) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.extendTheReceiving(orderId, memberId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： 确认收货。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/confirmReceipt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo confirmReceipt(String token, Long orderId) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (null == tokenDetail) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long memberId = tokenDetail.getMemberId();
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.confirmReceipt(orderId, memberId);
		return new ResponseInfo();
	}

}
