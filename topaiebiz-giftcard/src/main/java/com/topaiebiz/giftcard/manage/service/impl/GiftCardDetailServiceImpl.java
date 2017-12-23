package com.topaiebiz.giftcard.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.label.entity.CardLabelEntity;
import com.topaiebiz.giftcard.manage.dao.GiftCardDetailDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardInfoDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardOrderDao;
import com.topaiebiz.giftcard.manage.dao.GiftCardUseLogDao;
import com.topaiebiz.giftcard.manage.dto.ExpenseCalendarDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardInfoDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardMemberDto;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.entity.CardDetailEntity;
import com.topaiebiz.giftcard.manage.entity.CardInfoEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderEntity;
import com.topaiebiz.giftcard.manage.entity.CardUseLogEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardDetailService;
import com.topaiebiz.giftcard.medium.entity.CardMediumEntity;
import com.topaiebiz.giftcard.type.entity.CardTypeEntity;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.merchant.info.dao.MerchantInfoDao;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import com.topaiebiz.transaction.order.total.exception.PlatformOrderExceptionEnum;
import com.topaiebiz.transaction.refundOrder.dao.RefundOrderDao;
import com.topaiebiz.transaction.refundOrder.dao.RefundOrderDetailDao;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDetailDto;
import com.topaiebiz.transaction.refundOrder.entity.RefundOrderEntity;

/**
 * Description： 描述礼卡详细信息的业务层实现类。
 * 
 * 
 * Author： Murray.Li
 * 
 * Date 2017年9月2日 上午9:45:16
 * 
 * Copyright：Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class GiftCardDetailServiceImpl implements GiftCardDetailService {

	@Autowired
	private GiftCardDetailDao detailDao;

	/** 调用会员模块的业务层接口 */
	@Autowired
	private MemberMgmtDao memberMgmtDao;

	/** 注入礼卡消费记录的dao层接口 */
	@Autowired
	private GiftCardUseLogDao giftCardUseLogDao;

	@Autowired
	private GiftCardInfoDao giftCardInfoDao;

	/** 调用店铺模块的业务层接口 */
	@Autowired
	private MerchantInfoDao merchantInfoDao;

	@Autowired
	private RefundOrderDetailDao refundOrderDetailDao;

	/** 调用商品模块的业务层接口 */
	@Autowired
	private ItemDao itemDao;

	/** 调用交易模块的根据订单ID查看平台订单接口 */
	@Autowired
	private TotalOrderDao totalOrderDao;

	@Autowired
	private GoodsSkuDao goodsSkuDao;

	/** 调用退单dao层 */
	@Autowired
	private RefundOrderDao refundOrderDao;

	@Autowired
	private GiftCardOrderDao giftCardOrderDao;

	@Autowired
	private StoreOrderDao storeOrderDao;

	@Autowired
	private OrderDetailsDao orderDetailsDao;

	@Override
	public List<CardLabelEntity> getLabel() {
		return detailDao.selectLabel();
	}

	@Override
	public List<CardMediumEntity> getMedia() {
		return detailDao.selectMedia();
	}

	@Override
	public List<CardTypeEntity> getType() {
		return detailDao.selectType();
	}

	@Override
	@Transactional
	public CardDetailEntity getDetailById(Long id) throws GlobalException {
		CardDetailEntity selectDetailById = detailDao.selectById(id);
		if (selectDetailById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}

		return detailDao.selectById(id);
	}

	@Override
	@Transactional
	public void modifyDetailById(CardDetailEntity detail) throws GlobalException {
		/** 判断是否已经出售，或是否还处于新建状态 */
		if (detail.getCardState() == 1) {
			/** 获取更改人编号 */
			/*
			 * detail.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId())
			 * ;
			 */
			/** 获取修改时间 */
			detail.setLastModifiedTime(new DateTime().toDate());
			detailDao.updateById(detail);
		} else {
			throw new GlobalException(CardManageExceptionEnum.DETAIL_CARD_IS_NOT_NEW);
		}
	}

	@Override
	public Page<GiftCardInfoDto> getDetailPage(Page<GiftCardInfoDto> page, GiftCardInfoDto infoDto) {
		page.setRecords(detailDao.selectDetailPage(infoDto, page));
		return page;
	}

	@Override
	public void removeDetailOfBeach(Long[] list) throws GlobalException {

		/** 判断ID集合中的ID是否全部存在 */
		for (Long id : list) {
			CardDetailEntity selectById = detailDao.selectById(id);
			if (selectById == null) {
				throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
			}
		}
		/** 判断礼卡是否已经激活或已经出售 */
		/** 批量查询 */
		List<CardDetailEntity> detailList = detailDao.selectDetailByIdBeach(list);
		Integer count = 0;
		for (CardDetailEntity cardDetail : detailList) {
			/** 判断是否全部为新建礼卡 */
			if (cardDetail.getCardState() != 1) {
				count = 1;
			}
		}
		if (count == 0) {
			/** 批量删除礼卡 */
			detailDao.deleteDetailOfBeach(list);
		} else {
			throw new GlobalException(CardManageExceptionEnum.DETAIL_CARD_IS_NOT_NEW);
		}
	}

	@Override
	public GiftCardDto getDetailByCardId(Long id) throws GlobalException {

		CardDetailEntity selectById = detailDao.selectById(id);
		/** 判断ID是否存在 */
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		GiftCardDto giftcardDto = detailDao.selectDetailByCardId(id);
		return giftcardDto;
	}

	@Override
	public void updateDetailOrderNoById(CardDetailEntity cardDetail) throws GlobalException {
		detailDao.updateOrderNoById(cardDetail);
	}

	@Override
	public List<CardDetailEntity> selectDetailByOrderId(Long[] list) {
		return detailDao.selectDetailByOrderId(list);
	}

	@Override
	public List<CardDetailEntity> selectDetailOfNoSealByCardId(GiftCardOrderDto giftCardOrderDto) {
		return detailDao.selectDetailOfNoSealById(giftCardOrderDto);
	}

	@Override
	public void insertErpCard(CardDetailEntity cardDetail) {
		/** 生成礼卡的密码 */
		cardDetail.setPassword("111111");
		/** 获取创建人编号 */
		/*
		 * cardDetail.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		 */
		/** 添加礼卡详细信息 */
		detailDao.insert(cardDetail);
		/** 添加礼卡信息的编号 */
		cardDetail.setCardNo("");
		detailDao.updateById(cardDetail);
	}

	@Override
	public void cardActive(GiftCardDto giftcardDto) throws GlobalException {
		/** 判断ID是否存在 */
		List<CardDetailEntity> detailByIdBeach = detailDao.selectDetailByIdBeach(giftcardDto.getList());
		for (CardDetailEntity cardDetail : detailByIdBeach) {
			if (cardDetail == null) {
				throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
			}
			giftcardDto.setId(cardDetail.getId());	 		
			detailDao.cardActive(giftcardDto);
		}

		

	}

	public void updateDetailByCardIdList(List<Long> list) {
		detailDao.updateDetailByCardIdList(list);

	}

	@Override
	public void blockDetail(Long id) throws GlobalException {
		/** 判断ID是否存在 */
		CardDetailEntity selectById = detailDao.selectById(id);
		if (null == selectById) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		/** 冻结礼卡 */
		detailDao.blockDetail(id);
	}

	@Override
	public Page<ExpenseCalendarDto> getExpenseCalendar(Long id, Page<ExpenseCalendarDto> page) throws GlobalException {
		/** 判断ID是否存在 */
		CardDetailEntity selectById = detailDao.selectById(id);
		if (null == selectById) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		Double valu = selectById.getValue();
		List<ExpenseCalendarDto> expenseCalendarList = detailDao.expenseCalendar(id, page);
		for(ExpenseCalendarDto expenseCalendarDto: expenseCalendarList) {
			Long goodsId = expenseCalendarDto.getGoodsId();
			ExpenseCalendarDto expenseCalendar=detailDao.selectExpenseCalendarDtoByGoodsId(goodsId);
			Long goodsNum = expenseCalendar.getGoodsNum();
			Double salePrice= expenseCalendar.getSalePrice();
			Double deduction=goodsNum * salePrice;
			if(deduction>valu || deduction==valu) {
				deduction=valu;
			}
			valu-=deduction;
			expenseCalendarDto.setDeduction(deduction);
			expenseCalendarDto.setStoreName(expenseCalendar.getStoreName());
			expenseCalendarDto.setGoodsName(expenseCalendar.getGoodsName());  
		}
		page.setRecords(expenseCalendarList);
		/** 获取此礼卡的面值 */
		Double value = selectById.getValue();
		for (ExpenseCalendarDto expenseCalendarDto : expenseCalendarList) {
			/** 计算此礼卡当前的余额 */
			value -= expenseCalendarDto.getDeduction();
			if (value < 0) {
				value=0.0;
			}
			expenseCalendarDto.setBalance(value);
		}
		return page;
	}

	@Override
	public Map<String, Object> getGiftCardByMemberId(Long memberId, Long storeId, Long goodsId) throws GlobalException {
		/** 判断会员编号和店铺编号以及商品编号,订单编号是否存在 */
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}

		ItemEntity itemEntity = itemDao.selectById(goodsId);
		if (itemEntity == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		Map<String, Object> map = new HashMap<>();
		Double storeBalance = 0.0;
		if (storeId != null) {
			StoreInfoEntity storeInfoEntity = merchantInfoDao.selectByStoreId(storeId);
			if (storeInfoEntity == null) {
				throw new GlobalException(CardManageExceptionEnum.THE_STORE_ID_IS_EXIST);
			}
			/** 查看该用户在该店铺处于可用状态（激活）的礼卡信息 */
			GiftCardMemberDto selectGiftCardByStoreId = detailDao.selectGiftCardByStoreId(memberId, storeId);
			if (selectGiftCardByStoreId != null) {
				storeBalance = selectGiftCardByStoreId.getBalance();
			}

		}

		/** 查看该用户该品牌处于可用状态（激活）的礼卡信息 */
		GiftCardMemberDto selectGiftCardByGoodsId = detailDao.selectGiftCardByGoodsId(memberId, goodsId);
		if (selectGiftCardByGoodsId == null) {
			selectGiftCardByGoodsId = new GiftCardMemberDto();
			selectGiftCardByGoodsId.setBalance(0.0);
		}
		/** 根据用户编号查询处于可用状态（激活）自由卡信息 */
		GiftCardMemberDto selectGiftCardByMemberId = detailDao.selectGiftCardByMemberId(memberId);
		if (selectGiftCardByMemberId == null) {
			selectGiftCardByMemberId = new GiftCardMemberDto();
			selectGiftCardByMemberId.setBalance(0.0);
		}
		Double memberBalance = selectGiftCardByMemberId.getBalance();
		Double goodsBalance = selectGiftCardByGoodsId.getBalance();
		Double add = MathCountUtils.add(storeBalance, memberBalance);
		Double sumBalance = MathCountUtils.add(add, goodsBalance);

		selectGiftCardByGoodsId.setSumBalance(sumBalance);
		selectGiftCardByMemberId.setSumBalance(sumBalance);
		if (storeId != null) {
			GiftCardMemberDto selectGiftCardByStoreId = detailDao.selectGiftCardByStoreId(memberId, storeId);
			if (selectGiftCardByStoreId == null) {
				selectGiftCardByStoreId = new GiftCardMemberDto();
			}
			selectGiftCardByStoreId.setSumBalance(sumBalance);
			selectGiftCardByStoreId.setBalance(storeBalance);
			map.put("selectGiftCardByStoreId", selectGiftCardByStoreId);
		}

		map.put("selectGiftCardByGoodsId", selectGiftCardByGoodsId);
		map.put("selectGiftCardByMemberId", selectGiftCardByMemberId);
		return map;
	}

	@Override
	public void buyGoodsOfGiftCard(Long storeId, Long memberId, Long[] goodsIds, Double goodsPrice, Long orderId)
			throws GlobalException {
		/** 判断此订单是否存在 */
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(orderId);
		if (totalOrderEntity == null) {
			throw new GlobalException(PlatformOrderExceptionEnum.PLATFORMORDER_ID_NOT_EXIST);
		}
		for (Long goodsId : goodsIds) {
			Map<String, Object> map = getGiftCardByMemberId(memberId, storeId, goodsId);
			GiftCardMemberDto giftCardMemberDto = (GiftCardMemberDto) map.get("selectGiftCardByGoodsId");
			GiftCardMemberDto giftCardMember = (GiftCardMemberDto) map.get("selectGiftCardByMemberId");
			Double storeBalance = 0.0;
			List<GiftCardMemberDto> storeList = null;
			if (storeId != null) {
				GiftCardMemberDto giftCardMemberdto = (GiftCardMemberDto) map.get("selectGiftCardByStoreId");
				/** 获取该会员处于可用状态（激活）的店铺卡余额信息 */
				storeBalance = giftCardMemberdto.getBalance();
				/** 查询此会员的所有联名卡 */
				storeList = detailDao.selectStoreByStoreId(memberId, storeId);
			}
			/** 获取该会员处于可用状态（激活）的品牌卡余额信息 */
			Double brandBalance = giftCardMemberDto.getBalance();
			/** 获取该会员所有礼卡的总余额 */
			Double sumBalance = giftCardMember.getSumBalance();
			/** 查询此会员的所有品牌卡 */
			List<GiftCardMemberDto> brandList = detailDao.selectBrandByGoodsId(memberId, goodsId);

			/** 查询此会员的所有自由卡 */
			List<GiftCardMemberDto> memberList = detailDao.selectCardByMemberId(memberId);
			/** 判断该会员的所有礼卡总余额是否足以支付该商品 */
			if (sumBalance == goodsPrice || sumBalance > goodsPrice) {
				/** 如果品牌卡余额足以支付，则只在品牌卡中扣除商品金额 */
				if (brandList != null) {
					if (brandBalance > goodsPrice || brandBalance == goodsPrice) {
						withhold(goodsPrice, brandList, memberId, orderId);
					} else {
						/** 如果品牌卡余额不足以支付，则将剩余商品价钱在该会员的联名卡中扣除 */
						/** 制空此会员的所有品牌卡余额 */
						for (GiftCardMemberDto brandCardInfo : brandList) {

							CardDetailEntity selectById = detailDao.selectById(brandCardInfo.getId());
							selectById.setBalance(0.0);
							selectById.setCardState(4);
							detailDao.updateById(selectById);
							/** 添加礼卡消费记录 */
							CardUseLogEntity cardUseLogEntity = new CardUseLogEntity();
							cardUseLogEntity.setMemberId(memberId);
							cardUseLogEntity.setCardId(brandCardInfo.getId());
							cardUseLogEntity.setOrderId(orderId);
							cardUseLogEntity.setPrice(brandCardInfo.getBalance());
							/*
							 * cardUseLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().
							 * getId());
							 */
							cardUseLogEntity.setCreatedTime(new Date());
							cardUseLogEntity.setMeilikaType(brandCardInfo.getCardType());
							giftCardUseLogDao.insert(cardUseLogEntity);
						}
					}
				}
				Double brandSubtract = MathCountUtils.subtract(goodsPrice, brandBalance);
				/** 如果此商品是店铺商品 */
				if (storeId != null) {
					if (brandSubtract == storeBalance || storeBalance > brandSubtract) {
						/** 若联名卡足以扣除余下的商品金额 */
						withhold(brandSubtract, storeList, memberId, orderId);
					} else {
						/** 如果联名卡余额不足以支付，则将剩余商品价钱在该会员的自由卡卡中扣除 */
						/** 将该会员的联名卡余额制空 */
						for (GiftCardMemberDto storeCardInfo : storeList) {
							CardDetailEntity selectById = detailDao.selectById(storeCardInfo.getId());
							selectById.setBalance(0.0);
							selectById.setCardState(4);
							detailDao.updateById(selectById);
							/** 添加礼卡消费记录 */
							CardUseLogEntity cardUseLogEntity = new CardUseLogEntity();
							cardUseLogEntity.setMemberId(memberId);
							cardUseLogEntity.setCardId(storeCardInfo.getId());
							cardUseLogEntity.setOrderId(orderId);
							cardUseLogEntity.setPrice(storeCardInfo.getBalance());
							/*
							 * cardUseLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().
							 * getId());
							 */
							cardUseLogEntity.setCreatedTime(new Date());
							cardUseLogEntity.setMeilikaType(storeCardInfo.getCardType());
							giftCardUseLogDao.insert(cardUseLogEntity);
						}

						Double storeSubtract = MathCountUtils.subtract(brandSubtract, storeBalance);
						/** 在自由卡中扣除余下的需要支付的金额 */
						withhold(storeSubtract, memberList, memberId, orderId);
					}
				} else {
					withhold(brandSubtract, memberList, memberId, orderId);
				}

			} else {
				/** 提示该会员，您的礼卡余额不足以支付此商品 */
				throw new GlobalException(CardManageExceptionEnum.CARD_BALANCE_IS_NOT_ENOUGH);
			}
		}
	}

	/**
	 * 
	 * Description： 礼卡扣款。
	 * 
	 * Author Murray.Li
	 * 
	 * param:price 需扣金额，List 礼卡集合。
	 *
	 */

	public void withhold(Double price, List<GiftCardMemberDto> list, Long memberId, Long orderId) {
		for (GiftCardMemberDto storeCardInfo : list) {
			Double balance = storeCardInfo.getBalance();
			Long id = storeCardInfo.getId();
			Long cardType = storeCardInfo.getCardType();
			Double subtract = MathCountUtils.subtract(price, balance);
			CardDetailEntity selectById = detailDao.selectById(id);
			if (subtract < 0) {
				selectById.setBalance(MathCountUtils.subtract(balance, price));
				detailDao.updateById(selectById);
				/** 添加礼卡消费记录 */
				CardUseLogEntity cardUseLogEntity = new CardUseLogEntity();
				cardUseLogEntity.setMemberId(memberId);
				cardUseLogEntity.setCardId(id);
				cardUseLogEntity.setOrderId(orderId);
				cardUseLogEntity.setPrice(price);
				/*
				 * cardUseLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().
				 * getId());
				 */
				cardUseLogEntity.setCreatedTime(new Date());
				cardUseLogEntity.setMeilikaType(cardType);
				giftCardUseLogDao.insert(cardUseLogEntity);
				break;
			} else {
				/** 添加礼卡消费记录 */
				CardUseLogEntity cardUseLogEntity = new CardUseLogEntity();
				cardUseLogEntity.setMemberId(memberId);
				cardUseLogEntity.setCardId(id);
				cardUseLogEntity.setOrderId(orderId);
				cardUseLogEntity.setPrice(balance);
				/*
				 * cardUseLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().
				 * getId());
				 */
				cardUseLogEntity.setCreatedTime(new Date());
				cardUseLogEntity.setMeilikaType(cardType);
				giftCardUseLogDao.insert(cardUseLogEntity);
				/** 将此礼卡余额制空，并将此礼卡状态改为不可用 */
				selectById.setBalance(0.0);
				selectById.setCardState(4);
				detailDao.updateById(selectById);
				price = MathCountUtils.subtract(price, balance);

			}
		}
	}

	/**
	 * orderId 退货订单编号。
	 */
	@Override
	public void refundByOrder(Long orderId) throws GlobalException {

		/** 根据退单编号查询退货订单的详细信息及其订单信息 */
		RefundOrderEntity refundOrder = refundOrderDao.selectById(orderId);
		/** 根据退货订单编号查询退货订单详情 */
		List<RefundOrderDetailDto> refundOrderDetailList = refundOrderDetailDao
				.selectRefundOrderDetailByOrderId(orderId);
		/** 获取此退货订单所对应的总订单订单号及所属店铺ID */
		Long storeOrderId = refundOrder.getStoreOrderId();
		/** 店铺订单 */
		StoreOrderEntity storeOrder = storeOrderDao.selectById(storeOrderId);
		/** 支付时总订单 */
		Long totalOrderNo = storeOrder.getTotalOrderNo();
		/** 店铺id */
		Long storeId = refundOrder.getStoreId();
		/** 根据总订单编号查询总订单信息 */
		TotalOrderEntity totalOrder = totalOrderDao.selectById(totalOrderNo);
		/** 获取美礼卡支付金额 */
		Double cardPrice = totalOrder.getCardPrice();

		/** 根据总订单编号查询礼卡使用记录表中所对应的礼卡 */
		List<CardUseLogEntity> cardUseLogList = giftCardUseLogDao.selectbyOrderId(totalOrderNo);
		if (cardUseLogList != null && cardUseLogList.size() != 0) {
			for (RefundOrderDetailDto refundOrderDetailDto : refundOrderDetailList) {

				/** 获取商品的skuid */
				Long goodsId = refundOrderDetailDto.getGoodsId();
				/** 根据商品skuId和店铺id查询店铺订单详情中此商品的单品优惠后的金额salePrice */
				OrderDetailsDto orderDetails = orderDetailsDao.selectOrderDetailsByOrderIdAndSkuId(storeOrderId,
						goodsId);
				/** 单品优惠后商品单价 */
				Double salePrice = orderDetails.getSalePrice();
				/** 商品数量 */
				Long goodsNum = orderDetails.getGoodsNum();
				/** 商品总价格 */
				Double productTotal = storeOrder.getProductTotal();
				/** 比列 */
				Double divide = MathCountUtils.divide(salePrice * goodsNum, productTotal, 2);
				/** 获取此商品需退金额 */
				GoodsSkuEntity goodsSku = goodsSkuDao.selectById(goodsId);
				ItemEntity goodsEntity = itemDao.selectById(goodsSku.getItemId());
				/** 查询出此商品所属品牌 */
				if (goodsEntity == null) {
					throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
				}
				Long belongBrand = goodsEntity.getBelongBrand();

				/** 计算出此商品所对应的礼卡需退还的金额 */
				Double needPrice = MathCountUtils.multiply(divide, cardPrice);

				/** 查看所有符合支付此商品的礼卡 */
				for (CardUseLogEntity cardUseLogEntity : cardUseLogList) {
					/** 获取此礼卡所抵扣金额的 */
					Double deductionPrice = cardUseLogEntity.getPrice();
					CardDetailEntity cardDetail = detailDao.selectById(cardUseLogEntity.getCardId());
					CardInfoEntity cardInfo = giftCardInfoDao.selectById(cardDetail.getCardId());
					Long brandId = cardInfo.getBrandId();
					CardUseLogEntity selectById = giftCardUseLogDao.selectById(cardUseLogEntity.getId());
					/** 若为品牌卡 */
					if (belongBrand == brandId) {
						/** 若购买此商品的钱只来源于此品牌美礼卡 */
						if (deductionPrice > needPrice || deductionPrice == needPrice) {
							CardUseLogEntity cardUseLog = new CardUseLogEntity();
							BeanUtils.copyProperties(selectById, cardUseLog);
							cardUseLog.setPrice(needPrice * (-1));
							cardUseLog.setId(null);
							cardUseLog.setCreatedTime(new Date());
							giftCardUseLogDao.insert(cardUseLog);
							cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), needPrice));
							cardDetail.setCardState(3);
							detailDao.updateById(cardDetail);
							break;
						} else {
							/** 若购买此商品钱不止是来源于品牌礼卡 */
							needPrice = MathCountUtils.subtract(needPrice, deductionPrice);
							CardUseLogEntity cardUseLog = new CardUseLogEntity();
							BeanUtils.copyProperties(selectById, cardUseLog);
							cardUseLog.setPrice(deductionPrice * (-1));
							cardUseLog.setId(null);
							cardUseLog.setCreatedTime(new Date());
							giftCardUseLogDao.insert(cardUseLog);
							cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), deductionPrice));
							cardDetail.setCardState(3);
							detailDao.updateById(cardDetail);
							continue;
						}
					}

					if (storeId != null) {// 若为店铺卡
						if (cardInfo.getStoreId() == storeId) {
							if (deductionPrice > needPrice || deductionPrice == needPrice) {
								CardUseLogEntity cardUseLog = new CardUseLogEntity();
								BeanUtils.copyProperties(selectById, cardUseLog);
								cardUseLog.setPrice(needPrice * (-1));
								cardUseLog.setId(null);
								cardUseLog.setCreatedTime(new Date());
								giftCardUseLogDao.insert(cardUseLog);
								cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), needPrice));
								cardDetail.setCardState(3);
								detailDao.updateById(cardDetail);
								break;
							} else {
								needPrice = MathCountUtils.subtract(needPrice, deductionPrice);
								CardUseLogEntity cardUseLog = new CardUseLogEntity();
								BeanUtils.copyProperties(selectById, cardUseLog);
								cardUseLog.setPrice(deductionPrice * (-1));
								cardUseLog.setId(null);
								cardUseLog.setCreatedTime(new Date());
								giftCardUseLogDao.insert(cardUseLog);
								cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), deductionPrice));
								cardDetail.setCardState(3);
								detailDao.updateById(cardDetail);
								continue;
							}

						} else {// 若为自由卡
							if (deductionPrice > needPrice || deductionPrice == needPrice) {
								CardUseLogEntity cardUseLog = new CardUseLogEntity();
								BeanUtils.copyProperties(selectById, cardUseLog);
								cardUseLog.setPrice(needPrice * (-1));
								cardUseLog.setId(null);
								cardUseLog.setCreatedTime(new Date());
								giftCardUseLogDao.insert(cardUseLog);
								cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), needPrice));
								cardDetail.setCardState(3);
								detailDao.updateById(cardDetail);
								break;
							} else {
								needPrice = MathCountUtils.subtract(needPrice, deductionPrice);
								CardUseLogEntity cardUseLog = new CardUseLogEntity();
								BeanUtils.copyProperties(selectById, cardUseLog);
								cardUseLog.setPrice(deductionPrice * (-1));
								cardUseLog.setId(null);
								cardUseLog.setCreatedTime(new Date());
								giftCardUseLogDao.insert(cardUseLog);
								cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), deductionPrice));
								cardDetail.setCardState(3);
								detailDao.updateById(cardDetail);
								continue;
							}
						}
					} else {// 若为自由卡
						if (deductionPrice > needPrice || deductionPrice == needPrice) {
							CardUseLogEntity cardUseLog = new CardUseLogEntity();
							BeanUtils.copyProperties(selectById, cardUseLog);
							cardUseLog.setPrice(needPrice * (-1));
							cardUseLog.setId(null);
							cardUseLog.setCreatedTime(new Date());
							giftCardUseLogDao.insert(cardUseLog);
							cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), needPrice));
							cardDetail.setCardState(3);
							detailDao.updateById(cardDetail);
							break;
						} else {
							needPrice = MathCountUtils.subtract(needPrice, deductionPrice);
							CardUseLogEntity cardUseLog = new CardUseLogEntity();
							BeanUtils.copyProperties(selectById, cardUseLog);
							cardUseLog.setPrice(deductionPrice * (-1));
							cardUseLog.setId(null);
							cardUseLog.setCreatedTime(new Date());
							giftCardUseLogDao.insert(cardUseLog);
							cardDetail.setBalance(MathCountUtils.add(cardDetail.getBalance(), deductionPrice));
							cardDetail.setCardState(3);
							detailDao.updateById(cardDetail);
							continue;
						}
					}
				}
			}
		}
	}

	@Override
	public List<GiftCardDto> getGiftCardInfoByMemberId(Long memberId, Integer cardState) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}

		List<GiftCardDto> giftCardList = detailDao.selectCardByMemberIdAndCardState(memberId, cardState);
		return giftCardList;
	}

	@Override
	public List<CardDetailEntity> getExpenseCalendarByMemberId(Long memberId) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		List<CardDetailEntity> list = detailDao.getExpenseCalendarByMemberId(memberId);
		return list;
	}

	@Override
	public CardDetailEntity getPasswordByCardId(Long cardId) throws GlobalException {
		CardDetailEntity selectById = detailDao.selectById(cardId);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		CardOrderEntity selectById2 = giftCardOrderDao.selectById(selectById.getOrderId());
		CardDetailEntity cardDetailEntity = null;
		/** 若此订单已支付才可以查看卡密 */
		if (selectById2.getState() > 1) {
			cardDetailEntity = detailDao.selectPasswordByCardId(cardId);
		}
		return cardDetailEntity;
	}

	@Override
	public void bindingMember(Long cardId, Long memberId) throws GlobalException {
		CardDetailEntity selectById = detailDao.selectById(cardId);
		if (selectById == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		/** 判断该礼卡是否已经激活，若激活才可以绑定 */
		if (selectById.getCardState() != 3) {
			throw new GlobalException(CardManageExceptionEnum.THE_CURRENT_CARD_HAS_NOT_YET_BEEN_ACTIVATED);
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		/** 判断此礼卡是否已绑定 */
		if (selectById.getMemberId() != null) {
			throw new GlobalException(CardManageExceptionEnum.THE_CARD_IS_BINDING_YET);
		}
		CardOrderEntity selectById2 = giftCardOrderDao.selectById(selectById.getOrderId());
		/** 若此订单已支付才可以绑定 */
		if (selectById2.getState() > 1) {
			CardDetailEntity cardDetailEntity = new CardDetailEntity();
			cardDetailEntity.setId(cardId);
			cardDetailEntity.setMemberId(memberId);
			detailDao.bindingMember(cardDetailEntity);
		}
	}

	@Override
	public CardDetailEntity getCurrentBalance(Long memberId) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}

		CardDetailEntity cardDetailEntity = detailDao.selectCurrentBalance(memberId);
		return cardDetailEntity;
	}

	@Override
	public void bindingMemberByNumber(Long memberId, String cardNo, String password) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		/** 判断卡号和密码是否正确 */
		CardDetailEntity cardDetail = detailDao.getDetailByCardNoAndPassword(cardNo, password);
		if (cardDetail == null) {
			throw new GlobalException(CardManageExceptionEnum.CARDDETAIL_ID_NOT_EXIST);
		}
		/** 礼卡未激活不可以绑定 */
		if (cardDetail.getCardState() != 3) {
			throw new GlobalException(CardManageExceptionEnum.THE_CARD_IS_NOT_ACTIVE);
		}
		/** 判断此礼卡是否已经绑定 */
		if (cardDetail.getMemberId() != null) {
			throw new GlobalException(CardManageExceptionEnum.THE_CARD_IS_BINDING_YET);
		}
		CardDetailEntity cardDetailEntity = new CardDetailEntity();
		cardDetailEntity.setMemberId(memberId);
		cardDetailEntity.setPassword(password);
		cardDetailEntity.setCardNo(cardNo);

		detailDao.bindingMemberByNumber(cardDetailEntity);
	}

	@Override
	public Double getBalance(Long storeId, Long memberId, Long[] goodsIds) {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}

		Double storeBalance = 0.0;
		if (storeId != null) {
			StoreInfoEntity storeInfoEntity = merchantInfoDao.selectByStoreId(storeId);
			if (storeInfoEntity == null) {
				throw new GlobalException(CardManageExceptionEnum.THE_STORE_ID_IS_EXIST);
			}
			/** 查看该用户在该店铺处于可用状态（激活）的礼卡信息 */
			GiftCardMemberDto selectGiftCardByStoreId = detailDao.selectGiftCardByStoreId(memberId, storeId);
			if (null == selectGiftCardByStoreId) {
				storeBalance = 0.0;
			} else {
				storeBalance = selectGiftCardByStoreId.getBalance();
			}
		}
		Double goodsBalance = 0.0;
		for (Long goodsId : goodsIds) {
			ItemEntity itemEntity = itemDao.selectById(goodsId);
			if (itemEntity == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			/** 查看该用户该品牌处于可用状态（激活）的礼卡信息 */
			GiftCardMemberDto selectGiftCardByGoodsId = detailDao.selectGiftCardByGoodsId(memberId, goodsId);
			if (null == selectGiftCardByGoodsId) {
				goodsBalance = 0.0;
			} else {
				goodsBalance = MathCountUtils.add(goodsBalance, selectGiftCardByGoodsId.getBalance());
			}
		}
		/** 根据用户编号查询处于可用状态（激活）自由卡信息 */
		Double platformBalance = 0.0;
		GiftCardMemberDto selectGiftCardByMemberId = detailDao.selectGiftCardByMemberId(memberId);
		if (null != selectGiftCardByMemberId) {
			platformBalance = selectGiftCardByMemberId.getBalance();
		}
		Double add = MathCountUtils.add(storeBalance, goodsBalance);
		Double sumBalance = MathCountUtils.add(add, platformBalance);
		return sumBalance;
	}

	@Override
	public void refundByTotalOrderId(Long totalOrderId) throws GlobalException {
		/** 判断此订单是否存在 */
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
		if (totalOrderEntity == null) {
			throw new GlobalException(PlatformOrderExceptionEnum.PLATFORMORDER_ID_NOT_EXIST);
		}
		/** 根据订单编号查询相关订单的信息，并将所扣礼卡金额返回 */
		List<CardUseLogEntity> cardUseLogList = detailDao.selectCardUseLogByOrderId(totalOrderId);
		for (CardUseLogEntity cardUseLogEntity : cardUseLogList) {
			/** 获取礼卡主键 */
			Long cardId = cardUseLogEntity.getCardId();
			/** 获取抵扣金额 */
			Double price = cardUseLogEntity.getPrice();
			CardDetailEntity selectById = detailDao.selectById(cardId);
			/** 在当前礼卡余额的基础上加上抵扣的金额 */
			Double balance = selectById.getBalance();
			Double add = MathCountUtils.add(balance, price);
			/** 添加退回的礼卡金额，并将状态改为可用（即激活状态） */
			selectById.setCardState(3);
			selectById.setBalance(add);
			/** 修改礼卡信息 */
			detailDao.updateById(selectById);
		}
		/** 删除此订单编号对应的订单 */
		detailDao.deleteCardUseLogByOrderId(totalOrderId);

	}
}
