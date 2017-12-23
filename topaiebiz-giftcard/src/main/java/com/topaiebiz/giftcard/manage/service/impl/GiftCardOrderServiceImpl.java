package com.topaiebiz.giftcard.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.enums.CardMediumEnum;
import com.topaiebiz.giftcard.manage.dao.GiftCardAddressDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardDetailDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardInfoDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardOrderDao;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.dto.OrderAddressDto;
import com.topaiebiz.giftcard.manage.entity.*;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardOrderService;
import com.topaiebiz.member.address.dao.MemberAddressDao;
import com.topaiebiz.member.address.entity.MemberAddressEntity;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.order.merchant.dao.OrderInvoiceDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 礼卡订单的业务层，用于处理订单业务和逻辑运算。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月9日 上午9:33:04
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GiftCardOrderServiceImpl implements GiftCardOrderService {

	@Autowired
	private GiftCardOrderDao cardOrderDao;

	@Autowired
	private GiftCardAddressDao giftCardAddressDao;

	@Autowired
	private GiftCardInfoDao infoDao;

	@Autowired
	private MemberMgmtDao memberMgmtDao;

	@Autowired
	private GiftCardDetailDao detailDao;

	@Autowired
	private TotalOrderDao totalOrderDao;

	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private OrderInvoiceDao orderInvoiceDao;

	@Autowired
	private MemberAddressDao memberAddressDao;

	/**
	 * Description 生成订单号
	 * 
	 * Author Murray.Li
	 * 
	 * param userId(会员/用户编号) return String (订单编号：会员/用户编号+时间戳)
	 */
	public String getOrderNumber(String memberCode) {
		/** 默认会员编号为NO1 */
		if (memberCode == null || memberCode.equals("")) {
			memberCode = "NO1";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String format = simpleDateFormat.format(new Date());
		String OrderNumber = memberCode + format;
		return OrderNumber;
	}

	@Override
	public List<CardOrderEntity> getCardOreder() {
		return cardOrderDao.selectCardOrder();
	}

	@Override
	public Page<GiftCardOrderDto> getOrderOfPage(Page<GiftCardOrderDto> page, GiftCardOrderDto cardOrderDto)
			throws GlobalException {
		List<GiftCardOrderDto> selectOrderDtoList = cardOrderDao.selectOrderOfPage(cardOrderDto, page);
		for (GiftCardOrderDto giftCardOrderDto : selectOrderDtoList) {
			/** 根据订单编号查询其所对应的所有礼卡的详细信息。 */
			Long orderId = giftCardOrderDto.getOrderId();
			List<GiftCardDto> list = cardOrderDao.selectCardByOrderId(orderId);
			Long cardMedium = null;
			if (list != null) {
				for (GiftCardDto giftCardDto : list) {
					giftCardDto.setOrderId(orderId);
					cardMedium = giftCardDto.getCardMedium();
					/** 查询礼卡数量 */
					CardOrderEntity selectById = cardOrderDao.selectById(orderId);
					giftCardDto.setNumber(selectById.getNumber());
				}
			} else {
				selectOrderDtoList.remove(giftCardOrderDto);
			}
			/** 将礼卡信息放入dto对象中 */
			giftCardOrderDto.setList(list);
			giftCardOrderDto.setCardMedia(cardMedium);

		}
		/* page.setRecords(selectOrderDtoList); */
		page.setRecords(selectOrderDtoList);
		return page;
	}

	@Override
	public Map<String, Object> getOrderByOrderId(Long orderId) throws GlobalException {
		/** 判断礼卡订单编号是否存在 */
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}

		/** 根据订单编号查询订单信息 */
		CardOrderEntity cardOrder = cardOrderDao.selectById(orderId);
		cardOrder.setPaymentWay("在线支付");
		/** 根据订单编号查询订单交易号 */
		TotalOrderEntity totalOrderEntity = cardOrderDao.selectpayCallbackNoByOrderId(orderId);
		if (totalOrderEntity != null) {
			cardOrder.setPayCallbackNo(totalOrderEntity.getPayCallbackNo());
		}

		/** 根据订单编号查询发票信息 */
		CardOrderInvoiceEntity orderInvoice = cardOrderDao.selectOrderInvoice(orderId);

		/** 根据订单编号查询收货人信息 */
		CardAddressEntity cardAddress = giftCardAddressDao.selectByOrderId(orderId);
		/** 查询该地址所在的省市县 */
		Long districtId = cardAddress.getDistrictId();
		/** 区对象 */
		DistrictEntity district = districtDao.selectById(districtId);
		String districtName = district.getFullName();
		String cityName = district.getParentDistrictName();
		/** 市对象 */
		DistrictEntity city = districtDao.selectById(district.getParentDistrictId());
		String provinceName = "";
		if (city.getParentDistrictId() != 0) {
			/** 省名称 */
			provinceName = city.getParentDistrictName();
		}
		String address = cardAddress.getAddress();
		cardAddress.setAddress(provinceName + cityName + districtName + address);
		/** 根据订单编号查询礼卡信息 */
		List<GiftCardDto> giftcardList = cardOrderDao.selectCardByOrderId(orderId);
		/** 合计 */
		Double totalPrice = 0.0;
		/** 实际支付 */
		Double paymentPrice = 0.0;
		for (GiftCardDto giftCardDto : giftcardList) {
			giftCardDto.setOrderId(orderId);
			/** 查询礼卡数量 */
			Long number = cardOrder.getNumber();
			Double price = giftCardDto.getPrice();
			Double value = giftCardDto.getValue();
			Double sumPrice = price * number;
			Double sumValue = value * number;
			totalPrice += sumValue;
			paymentPrice += sumPrice;
			/** 单品的总价 */
			giftCardDto.setSumPrice(sumPrice);
			giftCardDto.setNumber(number);

		}
		Double discounts = MathCountUtils.subtract(totalPrice, paymentPrice);
		Map<String, Object> map = new HashMap<>();
		/** 订单总计 */
		map.put("totalPrice", totalPrice);
		/** 实际支付 */
		map.put("paymentPrice", paymentPrice);
		/** 总优惠 */
		map.put("discounts", discounts);
		map.put("cardOrder", cardOrder);
		map.put("cardAddress", cardAddress);
		map.put("giftcardOrderList", giftcardList);
		map.put("orderInvoice", orderInvoice);
		return map;
	}

	@Override
	public void removeOrderById(Long[] list) throws GlobalException {

		for (Long orderId : list) {
			CardOrderEntity selectById = cardOrderDao.selectById(orderId);
			/** 判断ID是否存在 */
			if (selectById == null) {
				throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
			}
			/** 判断订单是否已完成 */
			if (selectById.getState() != 5) {
				throw new GlobalException(CardManageExceptionEnum.ORDER_STATE_IS_UNFINISHED);
			}
		}
		/** 删除订单 */
		cardOrderDao.deleteOrderOfBeanch(list);
	}

	@Override
	public CardOrderInvoiceEntity getOrderInvoiceByOrderId(Long orderId) throws GlobalException {
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		return cardOrderDao.selectOrderInvoice(orderId);
	}

	@Override
	public CardAddressEntity getOrderAddressByOrderId(Long orderId) throws GlobalException {
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		CardAddressEntity cardAddressEntity = cardOrderDao.selectAddressByorderId(orderId);
		return cardAddressEntity;
	}

	@Override
	public void updateAddressByorderId(OrderAddressDto orderAddressDto) throws GlobalException {
		CardOrderEntity selectById = cardOrderDao.selectById(orderAddressDto.getOrderId());
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		/** 修改配送地址信息 */
		cardOrderDao.updateAddressByOrderId(orderAddressDto);
	}

	@Override
	public OrderAddressDto getDelivery(Long orderId) throws GlobalException {
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		OrderAddressDto orderAddressDto = cardOrderDao.selectDeliveryByOrderId(orderId);
		return orderAddressDto;
	}

	@Override
	public void markDeliveryByorderId(Long orderId, String logisticsNo, String logisticsType) throws GlobalException {
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		selectById.setLogisticsNo(logisticsNo);
		selectById.setLogisticsType(logisticsType);
		/** 将订单状态改为已发货 */
		selectById.setState(OrderStatusEnum.PENDING_RECEIVED.getCode());
		selectById.setLastModifiedTime(new Date());
		/*
		 * selectById.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().
		 * getId());
		 */
		cardOrderDao.updateById(selectById);
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public Map<String, Object> generateOrder(OrderInvoiceEntity orderInvoiceEntity) throws GlobalException {

		TokenDto tokenDetail = TokenUtil.getTokenDetail(orderInvoiceEntity.getToken());
		Long memberId = tokenDetail.getMemberId();
		Long number = orderInvoiceEntity.getNumber();
		CardInfoEntity cardInfoEntity = infoDao.selectById(orderInvoiceEntity.getCardId());
		if (cardInfoEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDINFO_ID_NOT_EXIST);
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (null == memberEntity) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		//电子卡不需要校验收货地址
		CardAddressEntity cardAddressEntity = new CardAddressEntity();
		if (cardInfoEntity.getCardMedium().intValue() == CardMediumEnum.SOLID_CARD.getMediumId()) {
			MemberAddressEntity addressEntity = memberAddressDao.selectById(orderInvoiceEntity.getAddressId());
			if (addressEntity == null) {
				throw new GlobalException(CardManageExceptionEnum.ADDRESS_ID_IS_NOT_EXIST);
			}
			/** 添加地址到礼卡地址表 */
			BeanUtils.copyProperties(addressEntity, cardAddressEntity);
			cardAddressEntity.setId(null);
			giftCardAddressDao.insert(cardAddressEntity);
		}
		/** 生成礼卡订单 */
		CardOrderEntity cardOrderEntity = new CardOrderEntity();
		cardOrderEntity.setMemberId(memberId);
		cardOrderEntity.setNumber(number);
		cardOrderEntity.setTotalPrice(number * cardInfoEntity.getPrice());
		cardOrderEntity.setOrderTime(new Date());
		/** 初始化状态为代付款 */
		cardOrderEntity.setState(OrderStatusEnum.CANCELLATION_OF_PAYMENT.getCode());
		cardOrderEntity.setCreatorId(memberId);
		cardOrderDao.insert(cardOrderEntity);
		/** 将订单ID添加入到地址表中进行关联 */
		if (null != cardAddressEntity.getId()) {
			CardAddressEntity selectById2 = giftCardAddressDao.selectById(cardAddressEntity.getId());
			selectById2.setOrderId(cardOrderEntity.getId());
			giftCardAddressDao.updateById(selectById2);
		}

		/** 若够数并且为达到会员限购数量则从剩余库存中减去购买的礼卡数 */
		CardInfoEntity cardinfo = infoDao.selectById(cardInfoEntity.getId());
		cardinfo.setSurplusNum(cardinfo.getSurplusNum() - number);
		/** 修改此种礼卡的剩余数量 */
		infoDao.updateById(cardinfo);
		/** 查看此卡是否含有副卡 */
		CardInfoEntity cardInfo = infoDao.selectBymainCardId(cardinfo.getId());
		if (cardInfo != null) {
			cardInfo.setSurplusNum(cardInfo.getSurplusNum() - number);
			infoDao.updateById(cardInfo);
		}
		/** 将订单编号存放入相应礼卡中 */
		checkGiftCard(cardOrderEntity.getId(), cardInfoEntity, number, memberId);
		/** 生成支付订单 */
		TotalOrderEntity totalOrderEntity = new TotalOrderEntity();
		totalOrderEntity.setMemberId(memberId);
		totalOrderEntity.setPayState(0);
		totalOrderEntity.setCreatorId(memberId);
		/** 根据订单编号查询礼卡信息 */
		List<GiftCardDto> giftcardList = cardOrderDao.selectCardDetailByOrderId(cardOrderEntity.getId());
		/** 合计 */
		Double totalPrice = 0.0;
		/** 实际支付 */
		Double paymentPrice = 0.0;
		/** 平台总优惠 */
		Double sumPlatformPrice = 0.0;
		for (GiftCardDto giftCardDto : giftcardList) {
			Double price = giftCardDto.getPrice();
			Double value = giftCardDto.getValue();
			Double platformPrice = giftCardDto.getPlatformPrice();
			totalPrice = totalPrice + value;
			paymentPrice = paymentPrice + price;
			sumPlatformPrice = sumPlatformPrice + platformPrice;
		}
		totalOrderEntity.setPlatformDeduction(sumPlatformPrice);
		totalOrderEntity.setTotalPrice(totalPrice);
		totalOrderEntity.setPayPrice(paymentPrice);
		totalOrderDao.insert(totalOrderEntity);
		/** 将支付单据号放入礼卡订单中 */
		CardOrderEntity selectById = cardOrderDao.selectById(cardOrderEntity.getId());
		selectById.setPayOrderId(totalOrderEntity.getId());
		cardOrderDao.updateById(selectById);
		/** 生成发票信息(发票与店铺订单绑定) */
		Long id = cardOrderEntity.getId();
		orderInvoiceEntity.setOrderId(id);
		orderInvoiceEntity.setStoreId(tokenDetail.getStoreId());
		orderInvoiceEntity.setSum(paymentPrice);
		orderInvoiceDao.insert(orderInvoiceEntity);
		Map<String, Object> map = new HashMap<>();
		map.put("cardOrderId", cardOrderEntity.getId());
		map.put("payOrderId", totalOrderEntity.getId());
		map.put("cardMedium", cardInfoEntity.getCardMedium());
		map.put("paymentPrice", paymentPrice);
		return map;
	}

	/**
	 * 
	 * Description： 选择对应礼卡给用户。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 *            订单编号。
	 * @param cardId
	 *            礼卡主键ID
	 * @param number
	 *            礼卡数量
	 * @param memberId
	 *            会员主键ID
	 */
	public void checkGiftCard(Long orderId, CardInfoEntity cardInfoEntity, Long number, Long memberId) {
		/** 判断此种礼卡有没有副卡 */
		CardInfoEntity cardinfo = infoDao.selectBymainCardId(cardInfoEntity.getId());

		/** 查询此种未销售的所有礼卡 */
		/** 若为实体卡 */
		if (cardInfoEntity.getCardMedium() == 1) {
			List<CardDetailEntity> cardDetailList = detailDao.selectDetailOfNoSealByCardId(cardInfoEntity.getId());
			for (CardDetailEntity cardDetailEntity : cardDetailList) {
				CardDetailEntity selectById = detailDao.selectById(cardDetailEntity.getId());
				selectById.setOrderId(orderId);
				selectById.setCardState(2);
				selectById.setLastModifiedTime(new Date());
				detailDao.updateById(selectById);
				number--;
				if (number == 0) {
					break;
				}
			}
			/** 若含副卡,则再添加副卡 */
			if (cardinfo != null) {
				List<CardDetailEntity> carddetailList = detailDao.selectDetailOfNoSealByCardId(cardinfo.getId());
				for (CardDetailEntity cardDetailEntity : carddetailList) {
					CardDetailEntity selectById = detailDao.selectById(cardDetailEntity.getId());
					selectById.setOrderId(orderId);
					selectById.setCardState(2);
					selectById.setLastModifiedTime(new Date());
					detailDao.updateById(selectById);
					number--;
					if (number == 0) {
						break;
					}
				}
			}
		} else {
			/** 若为虚拟卡,则添加相应礼卡与该会员绑定 */
			for (Long i = 0L; i < number; i++) {
				CardDetailEntity cardDetail = new CardDetailEntity();
				BeanUtils.copyProperties(cardInfoEntity, cardDetail);
				/** 填入礼卡相应的余额（当前即为礼卡面值） */
				cardDetail.setBalance(cardInfoEntity.getValue());
				/** 传入所属礼卡ID */
				cardDetail.setCardId(cardInfoEntity.getId());
				/** 传入状态为已销售 */
				cardDetail.setCardState(3);
				/** 传入订单ID */
				cardDetail.setOrderId(orderId);
				/** 将ID制空 */
				cardDetail.setId(null);
				/** 设置礼卡密码 */
				int randNum = (int) ((Math.random() * 9 + 1) * 100000);
				String password = String.valueOf(randNum);
				cardDetail.setPassword(password);
				/** 可激活时间 */
				cardDetail.setExpirationTime(cardInfoEntity.getExpirationTime());
				/** 生成此电子卡 */
				detailDao.insert(cardDetail);
				/** 添加礼卡编号 */
				CardDetailEntity selectById2 = detailDao.selectById(cardDetail.getId());
				selectById2.setCardNo(cardInfoEntity.getPrefix() + cardDetail.getId());
				detailDao.updateById(selectById2);

				/** 若含有副卡 */
				if (cardinfo != null) {
					CardDetailEntity carddetail = new CardDetailEntity();
					BeanUtils.copyProperties(cardinfo, carddetail);
					/** 填入礼卡相应的余额（当前即为礼卡面值） */
					carddetail.setBalance(cardinfo.getValue());
					/** 传入所属礼卡ID */
					carddetail.setCardId(cardinfo.getId());
					/** 传入状态为已销售 */
					carddetail.setCardState(3);
					/** 传入订单ID */
					carddetail.setOrderId(orderId);
					/** 将ID制空 */
					carddetail.setId(null);
					/** 设置礼卡密码 */
					int rand = (int) ((Math.random() * 9 + 1) * 100000);
					String pass = String.valueOf(rand);
					carddetail.setPassword(pass);
					/** 可激活时间 */
					carddetail.setExpirationTime(cardinfo.getExpirationTime());
					/** 生成此电子卡 */
					detailDao.insert(carddetail);
					/** 添加礼卡编号 */
					CardDetailEntity selectById = detailDao.selectById(carddetail.getId());
					selectById.setCardNo(cardinfo.getPrefix() + carddetail.getId());
					/** 存入绑定的主卡ID */
					selectById.setMainCardId(cardDetail.getId());
					detailDao.updateById(selectById);
				}
			}

		}

	}

	@Override
	public void cancelOrder(Long orderId) throws GlobalException {
		/** 判断此订单是否存在 */
		CardOrderEntity orderEntity = cardOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		/** 将订单状态改为已取消 */
		orderEntity.setState(OrderStatusEnum.ORDER_CANCELLATION.getCode());
		cardOrderDao.updateById(orderEntity);
		/** 根据订单编号查询相关礼卡及礼卡种类 */
		List<GiftCardDto> giftCardDtoList = cardOrderDao.selectCardByOrderId(orderId);

		for (GiftCardDto giftCardDto : giftCardDtoList) {
			/** 增加退回的礼卡数量 */
			Long cardId = giftCardDto.getCardId();
			Long number = cardOrderDao.selectCountByCardIdAndOrderId(cardId, orderId);
			CardInfoEntity selectById = infoDao.selectById(cardId);
			selectById.setSurplusNum(selectById.getSurplusNum() + number);
			infoDao.updateById(selectById);
			if (giftCardDto.getCardMedium() == 0) {
				/** 若为虚拟卡则直接根据订单编号删除礼卡详细信息 */
				infoDao.deleteByCardIdAndOrderId(cardId, orderId);
			} else {
				/** 重新生成此卡 */
				List<CardDetailEntity> cardDetailEntityList = detailDao.selectByCardIdAndOrderId(cardId, orderId);
				for (CardDetailEntity cardDetailEntity : cardDetailEntityList) {
					Long id = cardDetailEntity.getId();
					CardDetailEntity selectById2 = detailDao.selectById(id);
					selectById2.setId(null);
					selectById2.setOrderId(null);
					selectById2.setCardState(1);
					detailDao.insert(selectById2);
					/** 若为实体卡则根据订单编号将礼卡详细信息回复初始化状态 */
					infoDao.updateByCardIdAndOrderId(id);
				}
			}
		}

	}

	@Override
	public Map<String, Object> getCardOrderByOrderId(Long orderId) throws GlobalException {
		/** 判断礼卡订单编号是否存在 */
		CardOrderEntity selectById = cardOrderDao.selectById(orderId);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		/** 根据订单编号查询订单信息 */
		CardOrderEntity cardOrder = cardOrderDao.selectById(orderId);
		cardOrder.setPaymentWay("在线支付");

		/** 根据订单编号查询发票信息 */
		CardOrderInvoiceEntity orderInvoice = cardOrderDao.selectOrderInvoice(orderId);

		/** 根据订单编号查询收货人信息 */
		CardAddressEntity cardAddress = giftCardAddressDao.selectByOrderId(orderId);
		/** 查询该地址所在的省市县 */
		Long districtId = cardAddress.getDistrictId();
		/** 区对象 */
		DistrictEntity district = districtDao.selectById(districtId);
		String districtName = district.getFullName();
		String cityName = district.getParentDistrictName();
		/** 市对象 */
		DistrictEntity city = districtDao.selectById(district.getParentDistrictId());
		String provinceName = "";
		if (city.getParentDistrictId() != 0) {
			/** 省名称 */
			provinceName = city.getParentDistrictName();
		}
		String address = cardAddress.getAddress();
		cardAddress.setAddress(provinceName + cityName + districtName + address);
		/** 根据订单编号查询礼卡信息 */
		List<GiftCardDto> giftcardList = cardOrderDao.selectCardDetailByOrderId(orderId);
		Long cardMedium = null;
		for (GiftCardDto giftCardDto : giftcardList) {
			cardMedium = giftCardDto.getCardMedium();
		}
		/** 合计 */
		Double totalPrice = 0.0;
		/** 实际支付 */
		Double paymentPrice = 0.0;
		/** 平台总优惠 */
		Double sumPlatformPrice = 0.0;
		for (GiftCardDto giftCardDto : giftcardList) {
			Double price = giftCardDto.getPrice();
			Double value = giftCardDto.getValue();
			Double platformPrice = giftCardDto.getPlatformPrice();
			totalPrice = totalPrice + value;
			paymentPrice = paymentPrice + price;
			sumPlatformPrice = sumPlatformPrice + platformPrice;
		}

		Map<String, Object> map = new HashMap<>();
		map.put("cardOrder", cardOrder);
		map.put("orderInvoice", orderInvoice);
		map.put("cardAddress", cardAddress);
		map.put("totalPrice", totalPrice);
		map.put("paymentPrice", paymentPrice);
		map.put("sumPlatformPrice", sumPlatformPrice);
		map.put("giftcardList", giftcardList);
		map.put("cardMedium", cardMedium);
		return map;
	}

	@Override
	public void payCardOrder(Long orderId, Long memberId) throws GlobalException {
		/** 判断此订单是否存在 */
		CardOrderEntity orderEntity = cardOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		/** 判断此订单中是否为电子卡，若为电子卡则该订单已完成，实体卡则改为代发货 */
		List<CardInfoEntity> cardInfoEntityList = cardOrderDao.selectMediumByOrderId(orderId);
		Long cardMedium = null;
		for (CardInfoEntity cardInfoEntity : cardInfoEntityList) {
			cardMedium = cardInfoEntity.getCardMedium();
		}
		if (cardMedium == 1) {
			orderEntity.setState(OrderStatusEnum.PENDING_DELIVERY.getCode());
		} else {
			orderEntity.setState(OrderStatusEnum.ORDER_COMPLETION.getCode());
		}
		TotalOrderEntity selectById = totalOrderDao.selectById(orderEntity.getPayOrderId());
		selectById.setPayState(2);
		totalOrderDao.updateById(selectById);
		orderEntity.setLastModifiedTime(new Date());
		orderEntity.setLastModifierId(memberId);
		cardOrderDao.updateById(orderEntity);
	}

	@Override
	public void removeOrderByorderId(Long orderId, Long memberId) throws GlobalException {
		/** 判断此订单是否存在 */
		CardOrderEntity orderEntity = cardOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		/** 判断该订单是否已完成或已取消 */
		Integer orderState = orderEntity.getState();
		if (orderState != 6 && orderState != 0) {
			throw new GlobalException(StoreOrderExceptionEnum.THE_CARDORDER_IS_NOT_FINISH);
		}
		cardOrderDao.deleteOrderByOrderId(orderId);
	}

	@Override
	public void extendTheReceiving(Long orderId, Long memberId) throws GlobalException {
		/** 判断此订单是否存在 */
		CardOrderEntity orderEntity = cardOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		orderEntity.setState(OrderStatusEnum.PENDING_RECEIVED.getCode());
		orderEntity.setLastModifiedTime(new Date());
		orderEntity.setLastModifierId(memberId);
		cardOrderDao.updateById(orderEntity);
	}

	@Override
	public void confirmReceipt(Long orderId, Long memberId) throws GlobalException {
		/** 判断此订单是否存在 */
		CardOrderEntity orderEntity = cardOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_EXIST);
		}
		orderEntity.setState(OrderStatusEnum.ORDER_COMPLETION.getCode());
		orderEntity.setLastModifiedTime(new Date());
		orderEntity.setLastModifierId(memberId);
		cardOrderDao.updateById(orderEntity);
	}

}
