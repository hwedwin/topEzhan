package com.topaiebiz.promotion.mgmt.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryAttrDao;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dao.ItemPictureDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.ItemPictureDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.point.dao.MemberCouponDao;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.member.point.entity.MemberCouponEntity;
import com.topaiebiz.member.point.exception.MemberCouponExceptionEnum;
import com.topaiebiz.merchant.enter.dao.StoreInfoDao;
import com.topaiebiz.merchant.grade.dao.MerchantGradeDao;
import com.topaiebiz.merchant.grade.entity.MerchantGradeEntity;
import com.topaiebiz.merchant.info.dao.MerchantInfoDao;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.promotion.mgmt.dao.PromotionCodeDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionEntryDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionGoodsDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionPlatformUsageLogDao;
import com.topaiebiz.promotion.mgmt.dto.CommodityMarketingDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEnrolDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEntryDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionSingleDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionCodeEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntryEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionGoodsEntity;
import com.topaiebiz.promotion.mgmt.exception.PromotionExceptionEnum;
import com.topaiebiz.promotion.mgmt.service.PromotionService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.cart.dao.ShoppingCartDao;
import com.topaiebiz.transaction.cart.dto.ShoppingCartDto;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;
import com.topaiebiz.transaction.order.merchant.dto.StoreOrderDto;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.dto.PlatformPayOrderLogDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Description： 营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午1:56:29
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

	// 营销活动
	@Autowired
	private PromotionDao promotionDao;

	// 平台活动使用记录
	@Autowired
	private PromotionPlatformUsageLogDao promotionPlatformUsageLogDao;

	// 总订单
	@Autowired
	private TotalOrderDao totalOrderDao;

	// 平台优惠码
	@Autowired
	private PromotionCodeDao promotionCodeDao;

	// 商品信息
	@Autowired
	private ItemDao itemDao;

	// 营销活动商品
	@Autowired
	private PromotionGoodsDao promotionGoodsDao;

	// 购物车
	@Autowired
	private ShoppingCartDao shoppingCartDao;

	// 店铺活动使用记录
	@Autowired
	private OrderDetailsDao orderDetailsDao;

	// 店铺订单
	@Autowired
	private StoreOrderDao storeOrderDao;

	// 会员信息
	@Autowired
	private MemberMgmtDao memberMgmtDao;

	// 商品sku
	@Autowired
	private GoodsSkuDao goodsSkuDao;

	// 商品图片
	@Autowired
	private ItemPictureDao itemPictureDao;

	// 商品后台类目dao
	@Autowired
	private BackendCategoryAttrDao backendCategoryAttrDao;

	// 会员优惠券领取记录
	@Autowired
	private MemberCouponDao memberCouponDao;
	
	// 营销活动商家报名
	@Autowired
	private PromotionEntryDao promotionEntryDao;
	
	// 店铺等级
	@Autowired
	private MerchantGradeDao merchantGradeDao;
	
	// 店铺信息
	@Autowired
	private StoreInfoDao storeInfoDao;
	
	// 商家信息
	@Autowired
	private MerchantInfoDao merchantInfoDao;

	/**
	 * 
	 * Description 查询所有活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> selectPromotionList = promotionDao.selectPromotionList(page, promotionDto);
		page.setRecords(selectPromotionList);
		return page;
	}

	/**
	 * 
	 * Description： 获取单品活动列表（秒杀、单品折扣、一口价）
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionListSingle(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			Long promotionId = promotion.getId();
			// 下单商品数量
			long goodsNum = 0;
			// 下单总金额
			Double totalAmount = 0.00;
			List<OrderDetailsDto> OrderDetailsDtoList = orderDetailsDao.selectPromotionCount(promotionId);
			for (OrderDetailsDto orderDetailsDto : OrderDetailsDtoList) {
				// 下单商品数量
				goodsNum += orderDetailsDto.getGoodsNum();
				Double multiply = MathCountUtils.multiply(orderDetailsDto.getGoodsNum().doubleValue(), orderDetailsDto.getSalePrice());
				totalAmount += multiply;
			}
			promotion.setGoodsNum(goodsNum);
			promotion.setTotalAmount(totalAmount);
			// 查询支付买家数
			List<StoreOrderDto> storeOrderList = storeOrderDao.selectStoreOrder(promotionId);
			Integer buyersNum = storeOrderList.size();
			promotion.setBuyersNum(buyersNum);
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description 添加单品折扣
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	@Override
	public Long addPromotionSingle(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		// 根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotion.setSponsorType(storeId);
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 1);
		promotion.setTypeId((long) 1);
		promotion.setDiscountType(1);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 添加/修改单品折扣商品
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void modifyPromotionSingleGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		Long promotionId = null;
		if (promotionGoodsList.size() != 0) {
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				promotionId = promotionGoodsDto.getPromotionId();
				PromotionGoodsException(promotionGoodsDto);
				// 验证活动数量
				if (null == promotionGoodsDto.getPromotionNum()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
				}
				// 验证活动数量是否大于原库存
				if (promotionGoodsDto.getPromotionNum() > promotionGoodsDto.getRepertoryNum()) {
					throw new GlobalException(PromotionExceptionEnum.ACTIVITY_NUMBER_GREATER_THAN_STOCK);
				}
				// 验证活动价格
				if (null == promotionGoodsDto.getPromotionPrice()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
				}
				PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectPromotionGoodsByPromotionId(promotionId, promotionGoodsDto.getGoodsSkuId());
				Long promotionGoodsId = promotionGoods.getId();
				BeanUtils.copyProperties(promotionGoodsDto, promotionGoods);
				promotionGoods.setId(promotionGoodsId);
				// 优惠折扣
				Double discountValue = promotionGoodsDto.getPromotionPrice();
				Double discount = discountValue / 10;
				// 活动价格
				Double promotionPrice = MathCountUtils.multiply(discount, promotionGoodsDto.getGoodsPrice());
				// 优惠值
				Double subtract = MathCountUtils.subtract(promotionGoodsDto.getGoodsPrice(), promotionPrice);
				promotionGoods.setDiscountValue(subtract);
				// 活动价格
				promotionGoods.setPromotionPrice(promotionPrice);
				promotionGoods.setDiscountType(1);
				promotionGoods.setLastModifierId(creatorId);
				promotionGoods.setLastModifiedTime(new Date());
				promotionGoodsDao.updateById(promotionGoods);
			}
			// 删除未选中sku商品
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				Long itemId = promotionGoodsDto.getItemId();
				List<PromotionGoodsDto> promotionGoodslist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoods : promotionGoodslist) {
					if(promotionGoods.getPromotionPrice() == null || promotionGoods.getPromotionNum() == null || promotionGoods.getConfineNum() == null) {
						promotionGoodsDao.deleteGoodsByGoodsSkuIdAndPromotionId(promotionGoods.getGoodsSkuId(),promotionId);
					}
				}
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		// 是否指定商品可用
		promotion.setIsGoodsArea(0);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 保存/发布单品折扣活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void savePromotionSingle(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 活动ID */
		Long promotionId = promotionGoodsList.get(0).getPromotionId();
		/** 活动状态 */
		Integer marketState = promotionGoodsList.get(0).getMarketState();
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			/** 商品item */
			Long itemId = promotionGoodsDto.getItemId();
			if (itemId != null) {
				/** 活动中该item商品下所有的sku商品 */
				List<PromotionGoodsDto> promotionGoodsDtolist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoodsDto2 : promotionGoodsDtolist) {
					GoodsSkuEntity goodsSku = goodsSkuDao.selectById(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setGoodsPrice(goodsSku.getPrice());
					promotionGoodsDto.setGoodsSkuId(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setRepertoryNum(promotionGoodsDto2.getRepertoryNum());
					BeanUtils.copyProperties(promotionGoodsDto, promotionGoodsDto2);
				}
				if (promotionGoodsDtolist.size() != 0) {
					modifyPromotionSingleGoods(promotionGoodsDtolist);
				}
			}
		}
		/** 不点击批量设置 */
		if (marketState != 5) {
			if (marketState == 1) {
				/** 校验商品是否配置完全 */
				List<PromotionGoodsDto> promotionGoodsALLList = promotionGoodsDao.selectAllPromotionGoodsList(promotionId);
				if (promotionGoodsALLList.size() != 0) {
					for (PromotionGoodsDto promotionGoodsDto : promotionGoodsALLList) {
						/** 验证活动数量 */
						if (promotionGoodsDto.getPromotionNum() == null) {
							throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
						}
						/** 验证活动价格 */
						if (promotionGoodsDto.getPromotionPrice() == null) {
							throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
						}
						/** ID限购不得为空 */
						if (promotionGoodsDto.getConfineNum() == null) {
							throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
						}
					}
				} else {
					throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
				}
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		if (promotion != null) {
			/** 当前时间 */
			Date nowDate = new Date();
			if (marketState == 1) {
				if (nowDate.getTime() > promotion.getEndTime().getTime()) {
					/** 发布时间过期 */
					throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
				}
			}
			if(marketState == 5) {
				promotion.setMarketState(0);
			}else {
				promotion.setMarketState(marketState);
			}
			promotion.setLastModifierId(creatorId);
			promotion.setLastModifiedTime(new Date());
			promotionDao.updateById(promotion);
		} else {
			throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
		}
	}

	/**
	 * 
	 * Description 添加一口价
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	@Override
	public Long savePromotionPrice(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		// 根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotion.setSponsorType(storeId);
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 1);
		promotion.setTypeId((long) 2);
		promotion.setDiscountType(2);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 添加/修改一口价商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void modifyPromotionPriceGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		Long promotionId = null;
		if (promotionGoodsList.size() != 0) {
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				promotionId = promotionGoodsDto.getPromotionId();
				PromotionGoodsException(promotionGoodsDto);
				// 验证活动数量
				if (null == promotionGoodsDto.getPromotionNum()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
				}
				// 验证活动数量是否大于原库存
				if (promotionGoodsDto.getPromotionNum() > promotionGoodsDto.getRepertoryNum()) {
					throw new GlobalException(PromotionExceptionEnum.ACTIVITY_NUMBER_GREATER_THAN_STOCK);
				}
				// 验证活动价格
				if (null == promotionGoodsDto.getPromotionPrice()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
				}
				// 验证ID限购
				if (null == promotionGoodsDto.getConfineNum()) {
					throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
				}
				PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectPromotionGoodsByPromotionId(promotionId, promotionGoodsDto.getGoodsSkuId());
				Long promotionGoodsId = promotionGoods.getId();
				BeanUtils.copyProperties(promotionGoodsDto, promotionGoods);
				promotionGoods.setId(promotionGoodsId);
				Double discountValue = MathCountUtils.subtract(promotionGoodsDto.getGoodsPrice(), promotionGoods.getPromotionPrice());
				// 优惠值
				promotionGoods.setDiscountValue(discountValue);
				promotionGoods.setDiscountType(2);
				promotionGoods.setLastModifierId(creatorId);
				promotionGoods.setLastModifiedTime(new Date());
				promotionGoodsDao.updateById(promotionGoods);
			}
			// 删除未选中sku商品
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				Long itemId = promotionGoodsDto.getItemId();
				List<PromotionGoodsDto> promotionGoodslist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoods : promotionGoodslist) {
					if(promotionGoods.getPromotionPrice() == null || promotionGoods.getPromotionNum() == null || promotionGoods.getConfineNum() == null) {
						promotionGoodsDao.deleteGoodsByGoodsSkuIdAndPromotionId(promotionGoods.getGoodsSkuId(),promotionId);
					}
				}
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		// 是否指定商品可用
		promotion.setIsGoodsArea(0);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 发布/保存一口价活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void savePromotionPriceGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		Long promotionId = promotionGoodsList.get(0).getPromotionId();
		Integer marketState = promotionGoodsList.get(0).getMarketState();
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			Long itemId = promotionGoodsDto.getItemId();
			if (itemId != null) {
				List<PromotionGoodsDto> promotionGoodsDtolist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoodsDto2 : promotionGoodsDtolist) {
					GoodsSkuEntity goodsSku = goodsSkuDao.selectById(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setGoodsPrice(goodsSku.getPrice());
					promotionGoodsDto.setGoodsSkuId(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setRepertoryNum(promotionGoodsDto2.getRepertoryNum());
					BeanUtils.copyProperties(promotionGoodsDto, promotionGoodsDto2);
				}
				if (promotionGoodsDtolist.size() != 0) {
					modifyPromotionPriceGoods(promotionGoodsDtolist);
				}
			}
		}
		/** 不点击批量设置 */
		if (marketState != 5) {
			if (marketState == 1) {
				/** 校验商品是否配置完全 */
				List<PromotionGoodsDto> promotionGoodsALLList = promotionGoodsDao.selectAllPromotionGoodsList(promotionId);
				if (promotionGoodsALLList.size() != 0) {
					for (PromotionGoodsDto promotionGoodsDto : promotionGoodsALLList) {
						/** 验证活动数量 */
						if (promotionGoodsDto.getPromotionNum() == null) {
							throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
						}
						/** 验证活动价格 */
						if (promotionGoodsDto.getPromotionPrice() == null) {
							throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
						}
						/** ID限购不得为空 */
						if (promotionGoodsDto.getConfineNum() == null) {
							throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
						}
					}
				} else {
					throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
				}
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		if (promotion != null) {
			/** 当前时间 */
			Date nowDate = new Date();
			if (marketState == 1) {
				if (nowDate.getTime() > promotion.getEndTime().getTime()) {
					/** 发布时间过期 */
					throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
				}
			}
			if(marketState == 5) {
				promotion.setMarketState(0);
			}else {
				promotion.setMarketState(marketState);
			}
			promotion.setLastModifierId(creatorId);
			promotion.setLastModifiedTime(new Date());
			promotionDao.updateById(promotion);
		} else {
			throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
		}
	}

	/**
	 * 
	 * Description 添加秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	@Override
	public Long savePromotionSeckill(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // 设置当前日期
		c.add(Calendar.HOUR, 1);
		Date date = c.getTime(); // 结果
		if (date.getTime() > promotion.getStartTime().getTime()) {
			// 限制活动时间
			// 测试改为十分钟-待调试throw new
			// GlobalException(PromotionExceptionEnum.PLEASE_ADJUST_THE_START_TIME);
		}
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 1);
		promotion.setTypeId((long) 6);
		// 是否指定商品可用
		promotion.setIsGoodsArea(0);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 添加/修改秒杀活动商品
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public void modifyPromotionSeckillGoods(List<PromotionGoodsDto> promotionGoodsDtoList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		Long promotionId = null;
		// 插入营销活动商品信息数据
		if (promotionGoodsDtoList.size() != 0) {
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsDtoList) {
				promotionId = promotionGoodsDto.getPromotionId();
				PromotionGoodsException(promotionGoodsDto);
				// 验证活动价格
				if (null == promotionGoodsDto.getPromotionPrice()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
				}
				// 验证活动数量
				if (null == promotionGoodsDto.getPromotionNum()) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
				}
				// 验证活动数量是否大于原库存
				if (promotionGoodsDto.getPromotionNum() > promotionGoodsDto.getRepertoryNum()) {
					throw new GlobalException(PromotionExceptionEnum.ACTIVITY_NUMBER_GREATER_THAN_STOCK);
				}
				// 验证ID限购
				if (null == promotionGoodsDto.getConfineNum()) {
					throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
				}
				PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectPromotionGoodsByPromotionId(promotionId, promotionGoodsDto.getGoodsSkuId());
				Long id = promotionGoods.getId();
				BeanUtils.copyProperties(promotionGoodsDto, promotionGoods);
				promotionGoods.setId(id);
				promotionGoods.setDiscountType(2);
				Double discountValue = MathCountUtils.subtract(promotionGoodsDto.getGoodsPrice(), promotionGoods.getPromotionPrice());
				promotionGoods.setDiscountValue(discountValue);
				promotionGoods.setPromotionId(promotionId);
				promotionGoods.setLastModifierId(creatorId);
				promotionGoods.setLastModifiedTime(new Date());
				promotionGoodsDao.updateById(promotionGoods);
			}
			// 删除未选中sku商品
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsDtoList) {
				Long itemId = promotionGoodsDto.getItemId();
				List<PromotionGoodsDto> promotionGoodslist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoods : promotionGoodslist) {
					if(promotionGoods.getPromotionPrice() == null || promotionGoods.getPromotionNum() == null || promotionGoods.getConfineNum() == null) {
						promotionGoodsDao.deleteGoodsByGoodsSkuIdAndPromotionId(promotionGoods.getGoodsSkuId(),promotionId);
					}
				}
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		// 是否指定商品可用
		promotion.setIsGoodsArea(0);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 保存/发布秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void savePromotionSeckillGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Long promotionId = promotionGoodsList.get(0).getPromotionId();
		Integer marketState = promotionGoodsList.get(0).getMarketState();
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			Long itemId = promotionGoodsDto.getItemId();
			if (itemId != null) {
				List<PromotionGoodsDto> promotionGoodsDtolist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
				for (PromotionGoodsDto promotionGoodsDto2 : promotionGoodsDtolist) {
					GoodsSkuEntity goodsSku = goodsSkuDao.selectById(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setGoodsPrice(goodsSku.getPrice());
					promotionGoodsDto.setGoodsSkuId(promotionGoodsDto2.getGoodsSkuId());
					promotionGoodsDto.setRepertoryNum(promotionGoodsDto2.getRepertoryNum());
					BeanUtils.copyProperties(promotionGoodsDto, promotionGoodsDto2);
				}
				if (promotionGoodsDtolist.size() != 0) {
					modifyPromotionSeckillGoods(promotionGoodsDtolist);
				}
			}
		}
		if(marketState != null) {
			/** 不点击批量设置 */
			if (marketState != 5) {
				if (marketState == 1) {
					/** 校验商品是否配置完全 */
					List<PromotionGoodsDto> promotionGoodsALLList = promotionGoodsDao.selectAllPromotionGoodsList(promotionId);
					if (promotionGoodsALLList.size() != 0) {
						for (PromotionGoodsDto promotionGoodsDto : promotionGoodsALLList) {
							/** 验证活动数量 */
							if (promotionGoodsDto.getPromotionNum() == null) {
								throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
							}
							/** 验证活动价格 */
							if (promotionGoodsDto.getPromotionPrice() == null) {
								throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
							}
							/** ID限购不得为空 */
							if (promotionGoodsDto.getConfineNum() == null) {
								throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
							}
						}
					} else {
						throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
					}
				}
			}
			PromotionEntity promotion = promotionDao.selectById(promotionId);
			/** 当前时间 */
			Date nowDate = new Date();
			if (marketState == 1) {
				if (nowDate.getTime() > promotion.getEndTime().getTime()) {
					/** 发布时间过期 */
					throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
				}
			}
			if (promotion != null) {
				if(marketState == 5) {
					promotion.setMarketState(0);
				}else {
					promotion.setMarketState(marketState);
				}
				promotion.setLastModifierId(creatorId);
				promotion.setLastModifiedTime(new Date());
				promotionDao.updateById(promotion);
			} else {
				throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
			}
		}else {
			PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotionId,storeId);
			if(promotionEntryDto == null) {
				PromotionEntryEntity promotionEntryEntity = new PromotionEntryEntity();
				promotionEntryEntity.setStoreId(storeId);
				promotionEntryEntity.setPromotionId(promotionId);
				promotionEntryEntity.setState(0);
				promotionEntryEntity.setCreatorId(creatorId);
				promotionEntryEntity.setCreatedTime(new Date());
				promotionEntryDao.insert(promotionEntryEntity);
			}
		}
	}

	/**
	 * 
	 * Description 修改单品级活动(单品折扣,一口价,秒杀)
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 */
	@Override
	public Long modifyPromotionSingle(PromotionSingleDto promotionSingleDto) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotionEntity = promotionDao.selectById(promotionSingleDto.getId());
		BeanUtils.copyProperties(promotionSingleDto, promotionEntity);
		promotionEntity.setLastModifierId(creatorId);
		promotionEntity.setLastModifiedTime(new Date());
		promotionEntity.setIsGoodsArea(1);
		promotionDao.updateById(promotionEntity);
		return promotionEntity.getId();
	}

	/**
	 * 
	 * Description 获取满减活动列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionListReducePrice(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			// 下单量
			long placeOrderNum = 0;
			// 下单商品数量
			long goodsNum = 0;
			// 买家数
			long buyersNum = 0;
			// 客单价
			Double perCustomerTransaction = 0.00;
			// 下单总金额
			Double totalAmount = 0.00;
			// 计算优惠总额
			Double totalDiscount = 0.00;
			// 根据营销活动查询店铺订单使用
			List<StoreOrderDto> storeOrderList = storeOrderDao.findStoreOederByUsedPromotion(promotion.getId());
			if (storeOrderList.size() != 0) {
				placeOrderNum = storeOrderList.size();
				for (StoreOrderDto storeOrder : storeOrderList) {
					Double salePrice = storeOrder.getSalePrice();
					if (salePrice != null) {
						// 计算下单总金额
						totalAmount += storeOrder.getSalePrice();
					}
					Double storeDeduction = storeOrder.getStoreDeduction();
					if (storeDeduction != null) {
						// 计算优惠总额
						totalDiscount += storeOrder.getStoreDeduction();
					}
					List<OrderDetailsEntity> orderDetailsList = orderDetailsDao.selectOrderDetailByOrderId(storeOrder.getId());
					for (OrderDetailsEntity orderDetails : orderDetailsList) {
						goodsNum += orderDetails.getGoodsNum();
					}
				}
				// 店铺订单使用营销活动查询买家
				List<StoreOrderDto> storeOrderNum = storeOrderDao.selectStoreOrderBuyersNum(promotion.getId());
				if (storeOrderNum.size() != 0) {
					buyersNum = storeOrderNum.size();
				}
				// 计算客单价
				perCustomerTransaction = MathCountUtils.divide(totalAmount, (double) buyersNum, 2);
			}
			promotion.setPlaceOrderNum(placeOrderNum);
			promotion.setGoodsNum(goodsNum);
			promotion.setPerCustomerTransaction(perCustomerTransaction);
			promotion.setTotalAmount(totalAmount);
			promotion.setTotalDiscount(totalDiscount);
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description 添加满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	@Override
	public Long savePromotionReducePrice(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		// 根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotion.setSponsorType(storeId);
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 2);
		promotion.setTypeId((long) 3);
		promotion.setDiscountType(2);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 修改满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 */
	@Override
	public Long modifyPromotionReducePrice(PromotionDto promotionDto) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
		BeanUtils.copyProperties(promotionDto, promotion);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		// 是否指定商品可用
		promotion.setIsGoodsArea(1);
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 添加店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 */
	@Override
	public Long savePromotionStoreCoupon(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		// 根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotion.setSponsorType(storeId);
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 2);
		promotion.setTypeId((long) 7);
		promotion.setDiscountType(2);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 修改店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 */
	@Override
	public Long modifyPromotionStoreCoupon(PromotionDto promotionDto) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
		BeanUtils.copyProperties(promotionDto, promotion);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		// 是否指定商品可用
		promotion.setIsGoodsArea(1);
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 添加/修改活动商品(满减,包邮,店铺优惠券,平台优惠券,平台优惠码)
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public void modifyPromotionGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Long itemId = null;
		Long promotionId = null;
		if (promotionGoodsList.size() != 0) {
			itemId = promotionGoodsList.get(0).getItemId();
			promotionId = promotionGoodsList.get(0).getPromotionId();
			promotionGoodsDao.deleteGoods(promotionId, itemId);
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				itemId = promotionGoodsDto.getItemId();
				promotionId = promotionGoodsDto.getPromotionId();
				PromotionEntity promotionEntity = promotionDao.selectById(promotionId);
				// 验证活动商品
				PromotionGoodsException(promotionGoodsDto);
				PromotionGoodsEntity promotionGoods = new PromotionGoodsEntity();
				BeanUtils.copyProperties(promotionGoodsDto, promotionGoods);
				promotionGoods.setDiscountType(1);
				promotionGoods.setCreatorId(creatorId);
				promotionGoods.setCreatedTime(new Date());
				if(promotionEntity.getSponsorType() == null) {
					if(storeId == null ) {
						promotionGoods.setState(1);
					}else {
						promotionGoods.setState(0);
					}
				}
				promotionGoodsDao.insert(promotionGoods);
			}
		}
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		promotion.setIsGoodsArea(0);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 发布/保存活动(满减,包邮,店铺优惠券,平台优惠券)
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void savePromotion(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Long promotionId = promotionGoodsList.get(0).getPromotionId();
		Integer marketState = promotionGoodsList.get(0).getMarketState();
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			Long itemId = promotionGoodsDto.getItemId();
			List<PromotionGoodsDto> promotionGoodsDtolist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
			if (promotionGoodsDtolist.size() != 0) {
				modifyPromotionGoods(promotionGoodsDtolist);
			}
		}
		if(marketState != null) {
			PromotionEntity promotion = promotionDao.selectById(promotionId);
			/** 当前时间 */
			Date nowDate = new Date();
			if (marketState == 1) {
				if (nowDate.getTime() > promotion.getEndTime().getTime()) {
					/** 发布时间过期 */
					throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
				}
			}
			if (promotion != null) {
				if(marketState == 5) {
					promotion.setMarketState(0);
				}else {
					promotion.setMarketState(marketState);
				}
				promotion.setLastModifierId(creatorId);
				promotion.setLastModifiedTime(new Date());
				promotionDao.updateById(promotion);
			} else {
				throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
			}
		}else {
			PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotionId,storeId);
			if(promotionEntryDto == null) {
				PromotionEntryEntity promotionEntryEntity = new PromotionEntryEntity();
				promotionEntryEntity.setStoreId(storeId);
				promotionEntryEntity.setPromotionId(promotionId);
				promotionEntryEntity.setState(0);
				promotionEntryEntity.setCreatorId(creatorId);
				promotionEntryEntity.setCreatedTime(new Date());
				promotionEntryDao.insert(promotionEntryEntity);
			}
		}
	}

	/**
	 * 
	 * Description 获取包邮活动列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionListFreeShipping(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			// 下单量
			long placeOrderNum = 0;
			// 下单商品数量
			long goodsNum = 0;
			// 买家数
			long buyersNum = 0;
			// 客单价
			Double perCustomerTransaction = 0.00;
			// 下单总金额
			Double totalAmount = 0.00;
			// 计算优惠总额
			Double totalDiscount = 0.00;
			// 根据营销活动查询店铺订单使用
			List<StoreOrderDto> storeOrderList = storeOrderDao.findStoreOederByFreightPromotion(promotion.getId());
			if (storeOrderList.size() != 0) {
				placeOrderNum = storeOrderList.size();
				for (StoreOrderDto storeOrder : storeOrderList) {
					PlatformPayOrderLogDto PlatformPayOrder = totalOrderDao.findTotalOrderById(storeOrder.getToalOrderNo());
					// 计算下单总金额
					totalAmount += PlatformPayOrder.getPayPrice();
					List<OrderDetailsEntity> orderDetailsList = orderDetailsDao.selectOrderDetailByOrderId(storeOrder.getId());
					for (OrderDetailsEntity orderDetails : orderDetailsList) {
						goodsNum += orderDetails.getGoodsNum();
					}
					// 店铺订单使用营销活动查询买家
					List<StoreOrderDto> storeOrderNum = storeOrderDao.selectStoreOrderFreightPromotionBuyersNum(promotion.getId());
					if (storeOrderNum.size() != 0) {
						buyersNum = storeOrderNum.size();
					}
					// 计算客单价
					perCustomerTransaction = MathCountUtils.divide(totalAmount, (double) buyersNum, 2);
					// 计算优惠总额
					totalDiscount += MathCountUtils.subtract(storeOrder.getTotalFreight(), storeOrder.getActualFreight());
				}
			}
			promotion.setPlaceOrderNum(placeOrderNum);
			promotion.setGoodsNum(goodsNum);
			promotion.setPerCustomerTransaction(perCustomerTransaction);
			promotion.setTotalAmount(totalAmount);
			promotion.setTotalDiscount(totalDiscount);
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description 添加包邮活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public Long savePromotionFreeShipping(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		// 获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotion.setSponsorType(storeId);
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 2);
		promotion.setTypeId((long) 8);
		promotion.setCondType(1);
		promotion.setDiscountType(3);
		promotion.setIsGoodsArea(0);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 修改包邮活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public Long modifyPromotionFreeShipping(PromotionSingleDto promotionSingleDto) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(promotionSingleDto.getId());
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description： 获取营销活动平台优惠码列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	public Page<PromotionDto> getPromotionListCouponCode(Page<PromotionDto> page, PromotionDto promotionDto) {
		// 优惠总额
		Double totalDiscount = 0.00;
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			// 获取面值金额
			Double discountValue = promotion.getDiscountValue();
			if (discountValue != null && totalDiscount != null) {
				Long marketId = promotion.getId();
				// 获取已使用数量
				Integer promotionUse = promotionPlatformUsageLogDao.selectCountUse(marketId);
				// 计算优惠总额
				totalDiscount = MathCountUtils.multiply(discountValue, promotionUse.doubleValue());
				promotion.setUsedQuantity(promotionUse);
				promotion.setTotalDiscount(totalDiscount);
			}
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description 添加平台优惠码
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public Long savePromotionCouponCode(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		promotion.setCreatorId(creatorId);
		promotion.setGradeId((long) 3);
		promotion.setTypeId((long) 5);
		promotion.setCreatedTime(new Date());
		promotion.setDiscountType(2);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		Long promotionId = promotion.getId();
		return promotionId;
	}

	/**
	 * 
	 * Description 修改平台优惠码活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public Long modifyPromotionCouponCode(PromotionDto promotionDto) throws GlobalException {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
		BeanUtils.copyProperties(promotionDto, promotion);
		// 删除活动对应优惠码
		promotionCodeDao.deletePromotionCode(promotion.getId());
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 获取营销活动平台优惠券列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionListCoupon(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			// 获取面值金额
			Double discountValue = promotion.getDiscountValue();
			Long marketId = promotion.getId();
			// 查询已使用数
			Integer usedQuantity = totalOrderDao.selectPlatformPromotionCount(marketId);
			// 计算优惠总额
			Double totalDiscount = MathCountUtils.multiply(discountValue, usedQuantity.doubleValue());
			promotion.setUsedQuantity(usedQuantity);
			promotion.setTotalDiscount(totalDiscount);
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description： 添加平台优惠券
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	public Long savePromotionCoupon(PromotionEntity promotion) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		promotion.setCreatorId(creatorId);
		promotion.setCreatedTime(new Date());
		promotion.setGradeId((long) 3);
		promotion.setTypeId((long) 4);
		promotion.setDiscountType(2);
		promotion.setIsGoodsArea(1);
		promotionDao.insert(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 修改平台优惠券
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public Long modifyPromotionCoupon(PromotionDto promotionDto) {
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
		BeanUtils.copyProperties(promotionDto, promotion);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 获取店铺优惠券活动列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> getPromotionListStoreCoupon(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionDtoList = promotionDao.selectPromotionList(page, promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			// 计算优惠总额
			Double totalDiscount = 0.00;
			// 查询已使用数
			Integer usedQuantity = 0;
			// 根据营销活动查询店铺订单使用
			List<StoreOrderDto> storeOrderList = storeOrderDao.findStoreOederByUsedPromotion(promotion.getId());
			if (storeOrderList.size() != 0) {
				usedQuantity = storeOrderList.size();
				for (StoreOrderDto storeOrder : storeOrderList) {
					// 计算优惠总额
					totalDiscount += storeOrder.getStoreDeduction();
				}
			}
			promotion.setUsedQuantity(usedQuantity);
			promotion.setTotalDiscount(totalDiscount);
		}
		page.setRecords(promotionDtoList);
		return page;
	}

	/**
	 * 
	 * Description 保存/发布平台优惠码活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	@Override
	public void savePromotionCouponCodeGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		// 活动id
		Long promotionId = promotionGoodsList.get(0).getPromotionId();
		Integer marketState = promotionGoodsList.get(0).getMarketState();
		// 插入活动商品
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			Long itemId = promotionGoodsDto.getItemId();
			List<PromotionGoodsDto> promotionGoodsDtolist = promotionGoodsDao.selectPromotionGoodsByItemId(promotionId, itemId);
			if (promotionGoodsDtolist.size() != 0) {
				modifyPromotionGoods(promotionGoodsDtolist);
			}
		}
		if(marketState != null) {
			PromotionEntity promotion = promotionDao.selectById(promotionId);
			/** 当前时间 */
			Date nowDate = new Date();
			if (marketState == 1) {
				if (nowDate.getTime() > promotion.getEndTime().getTime()) {
					/** 发布时间过期 */
					throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
				}
			}
			if (promotion == null) {
				throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
			}
			// 生成优惠码
			if (promotion.getMarketState() == 1) {
				// 发放数量
				Long amount = promotion.getAmount();
				for (int i = 0; i < amount; i++) {
					// 活动优惠码对象
					PromotionCodeEntity promotionCode = new PromotionCodeEntity();
					promotionCodeDao.insert(promotionCode);
					// 获取刚刚插入的活动优惠码ID
					Long promotionCodeId = promotionCode.getId();
					PromotionCodeEntity couponCodeEntity = promotionCodeDao.selectById(promotionCodeId);
					// 优惠码
					String couponCode = "PROMOTION" + promotionCode.getId();
					couponCodeEntity.setPromotionId(promotion.getId());
					couponCodeEntity.setPromotionCode(couponCode);
					couponCodeEntity.setCreatorId(creatorId);
					promotionCodeDao.updateById(couponCodeEntity);
				}
			}
			if(marketState == 5) {
				promotion.setMarketState(0);
			}else {
				promotion.setMarketState(marketState);
			}
			promotion.setLastModifierId(creatorId);
			promotion.setLastModifiedTime(new Date());
			promotionDao.updateById(promotion);
		}else {
			PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotionId,storeId);
			if(promotionEntryDto == null) {
				PromotionEntryEntity promotionEntryEntity = new PromotionEntryEntity();
				promotionEntryEntity.setStoreId(storeId);
				promotionEntryEntity.setPromotionId(promotionId);
				promotionEntryEntity.setState(0);
				promotionEntryEntity.setCreatorId(creatorId);
				promotionEntryEntity.setCreatedTime(new Date());
				promotionEntryDao.insert(promotionEntryEntity);
			}
		}
	}

	/**
	 * 
	 * Description 修改营销活动回显
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public PromotionDto findPromotionById(Long id) {
		PromotionEntity promotion = promotionDao.selectById(id);
		PromotionDto promotionDto = new PromotionDto();
		BeanUtils.copyProperties(promotion, promotionDto);
		if(promotionDto.getStoreGrade() != null) {
			MerchantGradeEntity merchantGradeEntity = merchantGradeDao.selectById(promotionDto.getStoreGrade());
			if(merchantGradeEntity != null) {
				promotionDto.setStoreGradeName(merchantGradeEntity.getName());
			}
		}
		promotionDto.setId(id);
		return promotionDto;
	}

	/**
	 * 
	 * Description 停止平台优惠活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Integer modifyStopPromotion(Long id) {
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(id);
		promotion.setMarketState(4);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		return promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 删除营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	public Integer removePromotion(String ids) {
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		String[] arrStr = ids.split(",");
		for (String string : arrStr) {
			Long id = Long.parseLong(string);
			PromotionEntity promotion = promotionDao.selectById(id);
			promotion.setDeleteFlag((byte) 1);
			promotion.setLastModifierId(creatorId);
			promotion.setLastModifiedTime(new Date());
			promotionDao.updateById(promotion);
		}
		return null;
	}

	/**
	 * 
	 * Description 获取商家商品列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Page<ItemDto> getGoodsList(Page<ItemDto> page, ItemDto itemDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		List<ItemDto> itemList = itemDao.selectListMarketItemDto(page, itemDto);
		page.setRecords(itemList);
		return page;
	}

	/**
	 * 
	 * Description 获取平台商品列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public Page<ItemDto> getPlatformGoodsList(Page<ItemDto> page, ItemDto itemDto) {
		List<ItemDto> itemList = itemDao.selectListMarketItemDto(page, itemDto);
		page.setRecords(itemList);
		return page;
	}

	/**
	 * 
	 * Description 查询商品对应营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public List<CommodityMarketingDto> getCommodityMarketing(TokenDto tokenDetail) {
		List<CommodityMarketingDto> commodityMarketingList = new ArrayList<CommodityMarketingDto>();
		List<ShoppingCartDto> shoppingCartList = new ArrayList<ShoppingCartDto>();
		if (tokenDetail != null) {
			Long memberId = tokenDetail.getMemberId();
			if (memberId != null) {
				shoppingCartList = shoppingCartDao.getGoodsIdList(memberId);
			}
		}
		if (shoppingCartList.size() != 0) {
			for (ShoppingCartDto shoppingCartDto : shoppingCartList) {
				CommodityMarketingDto commodityMarketingDto = new CommodityMarketingDto();
				// 商品sku编号
				Long goodsSkuId = shoppingCartDto.getGoodsSkuId();
				List<PromotionDto> promotionList = promotionDao.findPromotionByGoodsSkuId(goodsSkuId);
				commodityMarketingDto.setPromotionList(promotionList);
				commodityMarketingList.add(commodityMarketingDto);
			}
		}
		return commodityMarketingList;
	}

	/**
	 * 
	 * Description 根据商品查询所能使用的订单级与支付级营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public List<PromotionDto> findPromotionByGoods(List<PromotionGoodsDto> goodsSkuDtoList, Long memberId) {
		List<PromotionDto> promotionList = new ArrayList<PromotionDto>();
		// 将所有活动全部放进集合中
		for (PromotionGoodsDto promotionGoodsDto : goodsSkuDtoList) {
			List<PromotionDto> findPromotionByGoodsSkuId = promotionDao.findPromotionByGoodsSkuId(promotionGoodsDto.getGoodsSkuId());
			for (PromotionDto promotionDto : findPromotionByGoodsSkuId) {
				promotionList.add(promotionDto);
			}
		}
		List<PromotionDto> deleteList = new ArrayList<PromotionDto>();
		// 去重
		for (int i = 0; i < promotionList.size() - 1; i++) {
			for (int j = promotionList.size() - 1; j > i; j--) {
				if (promotionList.get(j).getId().equals(promotionList.get(i).getId())) {
					deleteList.add(promotionList.get(j));
				}
			}
		}
		promotionList.removeAll(deleteList);
		List<PromotionDto> deleteNotAvailableList = new ArrayList<PromotionDto>();
		for (PromotionDto promotionDto : promotionList) {
			/** 优惠后商品总价 */
			Double saleTotalPrice = new Double(0.00);
			Long promotionId = promotionDto.getId();
			boolean activityAvailablePlatform = isActivityAvailablePlatform(memberId, promotionId);
			if (activityAvailablePlatform) {
				if (promotionDto.getIsGoodsArea() == 1) {
					// 计算商品总价
					for (PromotionGoodsDto promotionGoodsDto : goodsSkuDtoList) {
						if (promotionGoodsDto.getPromotionPrice() != null) {
							saleTotalPrice += promotionGoodsDto.getPromotionPrice();
						}
					}
					if (promotionDto.getCondValue() != null) {
						if (promotionDto.getCondValue() > saleTotalPrice) {
							// 不可用活动
							deleteNotAvailableList.add(promotionDto);
						}
					}
				} else {
					for (PromotionGoodsDto promotionGoodsDto : goodsSkuDtoList) {
						// 判断商品优惠后价格与商品skuId
						if (promotionGoodsDto.getPromotionPrice() != null && promotionGoodsDto.getGoodsSkuId() != null) {
							PromotionGoodsEntity promotionGoodsEntity = promotionGoodsDao.selectPromotionGoodsByPromotionId(promotionId, promotionGoodsDto.getGoodsSkuId());
							if (promotionGoodsEntity != null) {
								saleTotalPrice += promotionGoodsDto.getPromotionPrice();
							}
						}
					}
					if (promotionDto.getCondValue() != null) {
						if (promotionDto.getCondValue() > saleTotalPrice) {
							// 不可用活动
							deleteNotAvailableList.add(promotionDto);
						}
					}
				}
			} else {
				// 不可用活动
				deleteNotAvailableList.add(promotionDto);
			}
		}
		promotionList.removeAll(deleteNotAvailableList);
		return promotionList;
	}

	/**
	 * 
	 * Description 根据商品集合与会员编号查询单品活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public List<CommodityMarketingDto> getSinglePromotionList(Long storeId, Long memberId, List<Long> goodsSkuDtoList) {
		List<CommodityMarketingDto> CommodityMarketingDtoList = new ArrayList<CommodityMarketingDto>();
		for (Long goodsSkuId : goodsSkuDtoList) {
			CommodityMarketingDto commodityMarketingDto = new CommodityMarketingDto();
			// 查询商品sku对应的单品活动集合
			List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.findPromotionSingleByGoodsSkuId(goodsSkuId);
			List<PromotionGoodsDto> delList = new ArrayList<PromotionGoodsDto>();
			// 查询该活动该会员是否可用，不可用去除
			for (int i = 0; i < promotionGoodsList.size(); i++) {
				boolean activityAvailable = isActivityAvailable(memberId, promotionGoodsList.get(i).getPromotionId(), goodsSkuId);
				if (!activityAvailable) {
					delList.add(promotionGoodsList.get(i));
				}
			}
			promotionGoodsList.removeAll(delList);
			commodityMarketingDto.setGoodsSkuId(goodsSkuId);
			commodityMarketingDto.setPromotionSingleList(promotionGoodsList);
			CommodityMarketingDtoList.add(commodityMarketingDto);
		}
		return CommodityMarketingDtoList;
	}

	/**
	 * 
	 * Description 校验该活动会员是否可用（订单级与支付级）
	 * 
	 * Author Joe
	 * 
	 * @param memberId
	 * @param promotionId
	 * @param goodsSkuId
	 * @return
	 */
	@Override
	public boolean isActivityAvailablePlatform(Long memberId, Long promotionId) {
		boolean available = false;
		MemberEntity member = memberMgmtDao.selectById(memberId);
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		// 判断会员信息与活动信息是否属实
		if (null != member && null != promotion) {
			// 判断是否有会员限制
			if (null == promotion.getMemberGradeId() && null == promotion.getMemberTypeId()) {
				available = true;
			} else {
				// 该会员使用活动次数
				List<StoreOrderDto> StoreOrderDtoList = storeOrderDao.selectStoreOrderByUsedPromotion(memberId, promotionId);
				// 该会员使用过该活动
				if (StoreOrderDtoList.size() != 0) {
					// 该活动不限制使用次数
					if (promotion.getConfineAmount() == 0 || promotion.getConfineAmount() == null) {
						available = true;
					} else {
						// 使用次数没超过限制次数
						if (promotion.getConfineAmount() >= StoreOrderDtoList.size()) {
							available = true;
						}
					}
				} else {
					available = true;
				}
			}
		}
		return available;
	}

	/**
	 * 
	 * Description 根据会员编号与活动ID查询该会员是否可用该活动(单品级)
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public boolean isActivityAvailable(Long memberId, Long promotionId, Long goodsSkuId) {
		boolean available = false;
		MemberEntity member = memberMgmtDao.selectById(memberId);
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		// 判断会员信息与活动信息是否属实
		if (null != member && null != promotion) {
			// 判断是否有会员限制
			if (null == promotion.getMemberGradeId() && null == promotion.getMemberTypeId()) {
				available = true;
			} else {
				// 判断活动是否限制该会员的等级与类型
				if (member.getGradeId() == promotion.getMemberGradeId() || member.getTypeId() == promotion.getMemberTypeId()) {
					available = false;
				} else {
					// 根据活动ID与商品sku查询限购次数
					PromotionGoodsDto promotionGoods = promotionGoodsDao.getPromotionGoods(promotionId, goodsSkuId);
					// 根据活动ID,商品sku和会员编号查询购买次数
					List<OrderDetailsDto> orderDetailsList = orderDetailsDao.selectPromotionGoodsNum(promotionId, goodsSkuId, memberId);
					if (orderDetailsList.size() != 0) {
						int goodsNum = 0;
						for (OrderDetailsDto orderDetailsDto : orderDetailsList) {
							goodsNum += orderDetailsDto.getGoodsNum();
						}
						if (promotionGoods.getConfineNum() > goodsNum) {
							available = true;
						}
					}
				}
			}
		}
		return available;
	}

	/**
	 * 
	 * Description 发布活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public void releasePromotion(Long id, Long typeId) {
		// 当前用户ID(创建人编号)
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		PromotionEntity promotion = promotionDao.selectById(id);
		// 校验活动是否存在
		if (promotion == null) {
			throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
		}
		/** 当前时间 */
		Date nowDate = new Date();
		if (nowDate.getTime() > promotion.getEndTime().getTime()) {
			/** 发布时间过期 */
			throw new GlobalException(PromotionExceptionEnum.TIME_EXPIRED);
		}
		if (promotion.getGradeId() == 1) {
			/** 校验商品是否配置完全 */
			List<PromotionGoodsDto> promotionGoodsALLList = promotionGoodsDao.selectAllPromotionGoodsList(id);
			if (promotionGoodsALLList.size() == 0) {
				throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
			}
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsALLList) {
				/** 验证活动数量 */
				if (promotionGoodsDto.getPromotionNum() == null) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_QUANTITY_CANNOT_BE_EMPTY);
				}
				/** 验证活动价格 */
				if (promotionGoodsDto.getPromotionPrice() == null) {
					throw new GlobalException(PromotionExceptionEnum.PROMOTION_PRICE_CANNOT_BE_EMPTY);
				}
				/** ID限购不得为空 */
				if (promotionGoodsDto.getConfineNum() == null) {
					throw new GlobalException(PromotionExceptionEnum.ID_RESTRICTION_CANNOT_BE_EMPTY);
				}
			}
		}
		if (typeId == 5) {// 平台优惠码
			// 发放数量
			Long amount = promotion.getAmount();
			for (int i = 0; i < amount; i++) {
				// 活动优惠码对象
				PromotionCodeEntity promotionCode = new PromotionCodeEntity();
				promotionCodeDao.insert(promotionCode);
				// 获取刚刚插入的活动优惠码ID
				Long promotionCodeId = promotionCode.getId();
				PromotionCodeEntity couponCodeEntity = promotionCodeDao.selectById(promotionCodeId);
				// 优惠码
				String couponCode = "PROMOTION" + promotionCode.getId();
				couponCodeEntity.setPromotionId(id);
				couponCodeEntity.setPromotionCode(couponCode);
				couponCodeEntity.setCreatorId(creatorId);
				promotionCodeDao.updateById(couponCodeEntity);
			}
		}
		promotion.setMarketState(1);
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 活动选择保存商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 */
	@Override
	public void savePromotionGoods(List<PromotionGoodsDto> promotionGoodsList) {
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		if(promotionGoodsList.size() != 0) {
			PromotionEntity promotionEntity = promotionDao.selectById(promotionGoodsList.get(0).getPromotionId());
			if(promotionEntity.getMaxProductNum() != null) {
				if(promotionGoodsList.size() > promotionEntity.getMaxProductNum()) {
					/** 报名商品数量超过了最大报名商品数*/
					throw new GlobalException(PromotionExceptionEnum.GOODS_NUMBER_SHALL_NOT_BE_GREATER_THAN_MAXIMUM);
				}
			}
			if(promotionEntity.getMixProductNum() != null) {
				if(promotionGoodsList.size() < promotionEntity.getMixProductNum()) {
					/** 报名商品数量低于最少报名商品数*/
					throw new GlobalException(PromotionExceptionEnum.GOODS_NUMBER_SHALL_NOT_BE_LESS_THAN_MINIMUM);
				}
			}
			
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				List<GoodsSkuDto> goodsSkuList = goodsSkuDao.selectGoodsSkuById(promotionGoodsDto.getItemId());
				ItemEntity itemEntity = itemDao.selectById(promotionGoodsDto.getItemId());
				for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
					/** 查询该活动是否已选中该商品*/
					PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectPromotionGoodsByPromotionId(promotionGoodsDto.getPromotionId(), goodsSkuDto.getId());
					if(promotionGoods == null) {
						PromotionGoodsEntity promotionGoodsEntity = new PromotionGoodsEntity();
						promotionGoodsEntity.setStoreId(itemEntity.getBelongStore());
						promotionGoodsEntity.setPromotionId(promotionGoodsDto.getPromotionId());
						promotionGoodsEntity.setItemId(promotionGoodsDto.getItemId());
						promotionGoodsEntity.setGoodsSkuId(goodsSkuDto.getId());
						promotionGoodsEntity.setRepertoryNum(goodsSkuDto.getStockNumber());
						promotionGoodsEntity.setCreatorId(creatorId);
						promotionGoodsEntity.setCreatedTime(new Date());
						if(promotionEntity.getSponsorType() == null) {
							if(storeId == null ) {
								promotionGoodsEntity.setState(1);
							}else {
								promotionGoodsEntity.setState(0);
							}
						}
						promotionGoodsDao.insert(promotionGoodsEntity);
					}
				}
			}
		}
	}

	/**
	 * 
	 * Description 获取营销活动所选商品
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Page<PromotionGoodsDto> getPromotionGoods(Page<PromotionGoodsDto> page, ItemDto itemDto) {
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		/** 根据营销活动查询所选商品(根据所属item去重) */
		List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.findPromotionGoodsByPromotionId(page, itemDto);
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			Long selecStockNumbertById = itemDao.selecStockNumbertById(promotionGoodsDto.getItemId());
			promotionGoodsDto.setRepertoryNum(selecStockNumbertById);
			List<ItemPictureDto> selectItemPictureById = itemPictureDao.selectItemPictureById(promotionGoodsDto.getItemId());
			for (ItemPictureDto itemPicture : selectItemPictureById) {
				if (itemPicture.getIsMain() != null && itemPicture.getIsMain() == 1) {
					promotionGoodsDto.setSaleImage(itemPicture.getName());
				}
			}
			promotionGoodsDto.setStoreId(storeId);
		}
		page.setRecords(promotionGoodsList);
		return page;
	}

	/**
	 * 
	 * Description 查询秒杀活动时间集合
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public List<PromotionDto> getSeckillStartTimeList() {
		return promotionDao.selectSeckillStartTimeList();
	}

	/**
	 * 
	 * Description 修改秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	@Override
	public Long modifyPromotionSeckill(PromotionSingleDto promotionSingleDto) {
		PromotionEntity promotion = promotionDao.selectById(promotionSingleDto.getId());
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		// 当前用户ID
		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		promotion.setLastModifierId(creatorId);
		promotion.setLastModifiedTime(new Date());
		promotionDao.updateById(promotion);
		return promotion.getId();
	}

	/**
	 * 
	 * Description 取消活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 */
	@Override
	public void removeGoods(Long promotionId, Long itemId) {
		promotionGoodsDao.deleteGoods(promotionId, itemId);
	}

	/**
	 * 
	 * Description 优惠券获取所选商品
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param itemDto
	 * @return
	 */
	@Override
	public Page<PromotionGoodsDto> getPromotionCouponGoods(Page<PromotionGoodsDto> page, ItemDto itemDto, String token) {
		/** 根据营销活动查询所选商品(根据所属item去重) */
		List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectPromotionCouponGoods(itemDto);
		if (!(promotionGoodsList == null || promotionGoodsList.size() == 0)) {
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				// 查询每件商品的销售量
				Long salesVolome = itemDao.selectSalesVolomeById(promotionGoodsDto.getItemId());
				if (salesVolome != null) {
					promotionGoodsDto.setSalesVolome(salesVolome);
				} else {
					promotionGoodsDto.setSalesVolome(0L);
				}
				Long selecStockNumbertById = itemDao.selecStockNumbertById(promotionGoodsDto.getItemId());
				promotionGoodsDto.setRepertoryNum(selecStockNumbertById);
				List<ItemPictureDto> selectItemPictureById = itemPictureDao.selectItemPictureById(promotionGoodsDto.getItemId());
				for (ItemPictureDto itemPictureDto : selectItemPictureById) {
					if (itemPictureDto.getIsMain() != null && itemPictureDto.getIsMain() == 1) {
						promotionGoodsDto.setSaleImage(itemPictureDto.getName());
					}
				}
			}
		} else {
			TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
			if (tokenDetail != null) {
				Long storeId = tokenDetail.getStoreId();
				if (storeId != null) {
					itemDto.setBelongStore(storeId);
					List<ItemDto> selectItemListByItem = itemDao.selectItemListByItem(itemDto);
					for (ItemDto itemDto2 : selectItemListByItem) {
						PromotionGoodsDto promotionGoodsDto = new PromotionGoodsDto();
						// 查询每件商品的销售量
						Long salesVolome = itemDao.selectSalesVolomeById(itemDto2.getId());
						if (salesVolome != null) {
							promotionGoodsDto.setSalesVolome(salesVolome);
						} else {
							promotionGoodsDto.setSalesVolome(0L);
						}
						promotionGoodsDto.setItemId(itemDto2.getId());
						promotionGoodsDto.setItemCode(itemDto2.getItemCode());
						promotionGoodsDto.setGoodsName(itemDto2.getName());
						promotionGoodsDto.setGoodsPrice(itemDto2.getDefaultPrice());
						Long selecStockNumbertById = itemDao.selecStockNumbertById(promotionGoodsDto.getItemId());
						promotionGoodsDto.setRepertoryNum(selecStockNumbertById);
						List<ItemPictureDto> selectItemPictureById = itemPictureDao.selectItemPictureById(itemDto2.getId());
						for (ItemPictureDto itemPictureDto : selectItemPictureById) {
							if (itemPictureDto.getIsMain() != null && itemPictureDto.getIsMain() == 1) {
								promotionGoodsDto.setSaleImage(itemPictureDto.getName());
							}
						}
						promotionGoodsList.add(promotionGoodsDto);
					}
				}
			}
		}
		if (itemDto.getSales() != null) {
			// 为倒叙排列
			if (itemDto.getSales() == 0) {
				Collections.sort(promotionGoodsList, Collections.reverseOrder());
			}
			// 为正叙排列
			if (itemDto.getSales() == 1) {
				Collections.sort(promotionGoodsList);
			}
		}
		// 获得分页数据在list集合中的索引
		int firstIndex = (page.getCurrent() - 1) * page.getSize();
		int toIndex = page.getCurrent() * page.getSize();
		if (toIndex > promotionGoodsList.size()) {
			toIndex = promotionGoodsList.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
		}
		page.setTotal(promotionGoodsList.size());
		page.setRecords(promotionGoodsList);
		return page;
	}

	/**
	 * 
	 * Description 根据活动id和itmId回显商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	@Override
	public List<PromotionGoodsDto> getPromotionGoodsByPromotionIdAndItemid(Long promotionId, Long itemId) {
		List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectPromotionGoodsByPromotionIdAndItemid(promotionId, itemId);
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsDtoList) {
			String saleFieldValue = promotionGoodsDto.getSaleFieldValue();
			String saleFieldValue1 = "";
			String[] strss = saleFieldValue.split(",");
			for (int i = 0; i < strss.length; i++) {
				String[] strs1 = strss[i].split(":");
				BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
				String name = selectById.getName();
				String value = strs1[1];
				saleFieldValue1 = name + ":" + value + "  " + saleFieldValue1;
			}
			promotionGoodsDto.setSaleFieldValue(saleFieldValue1);
		}
		return promotionGoodsDtoList;
	}

	/**
	 * 
	 * Description 定时任务（时间间隔：1小时）
	 * 
	 * Author Joe
	 *
	 */
	@Override
	public void timingTask() {
		/** 审核通过*/
		Integer through = 0;
		/** 开始活动 */
		List<PromotionDto> promotionDtoStartList = promotionDao.selectPromotionByDate();
		if (promotionDtoStartList.size() != 0) {
			for (PromotionDto promotionDto : promotionDtoStartList) {
				PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
				List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectAllPromotionGoodsList(promotion.getId());
				/** 该活动没有参与活动的商品且没有选中全平台商品,活动异常*/
				if(promotionGoodsList.size() == 0 && promotion.getIsGoodsArea() != 1) {
					promotion.setMarketState(5);
					promotion.setLastModifiedTime(new Date());
					promotionDao.updateById(promotion);
				}else{
					/** 是否为平台发布商品*/
					if(promotion.getSponsorType() == null) {
						for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
							if(promotionGoodsDto.getState() == 1) {
								through++;
							}
						}
						/** 商品审核通过数量为0,活动异常*/
						if(through == 0) {
							promotion.setMarketState(5);
							promotion.setLastModifiedTime(new Date());
							promotionDao.updateById(promotion);
						}else {
							promotion.setMarketState(2);
							promotion.setLastModifiedTime(new Date());
							promotionDao.updateById(promotion);
						}
					}else {
						promotion.setMarketState(2);
						promotion.setLastModifiedTime(new Date());
						promotionDao.updateById(promotion);
					}
					
				}
			}
		}
		/** 结束活动 */
		List<PromotionDto> promotionDtoEndList = promotionDao.selectPromotionEnd();
		if (promotionDtoEndList.size() != 0) {
			for (PromotionDto promotionDto : promotionDtoEndList) {
				PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
				promotion.setMarketState(3);
				promotion.setLastModifiedTime(new Date());
				promotionDao.updateById(promotion);
			}
		}
		/** 报名开始*/
		List<PromotionDto> promotionDtoApplyStartList = promotionDao.selectPromotionApplyStart();
		if(promotionDtoApplyStartList.size() != 0) {
			for (PromotionDto promotionDto : promotionDtoApplyStartList) {
				PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
				promotion.setState(1);
				promotion.setLastModifiedTime(new Date());
				promotionDao.updateById(promotion);
			}
		}
		/** 报名结束,并且所有未审核商品全部为未通过*/
		List<PromotionDto> promotionDtoApplyEndList = promotionDao.selectPromotionApplyEnd();
		for (PromotionDto promotionDto : promotionDtoApplyEndList) {
			List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectAllPromotionGoodsList(promotionDto.getId());
			for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
				PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectById(promotionGoodsDto.getId());
				if(promotionGoods.getState() == null || promotionGoods.getState() == 0) {
					promotionGoods.setState(2);
					promotionGoodsDao.updateById(promotionGoods);
				}
			}
			PromotionEntity promotion = promotionDao.selectById(promotionDto.getId());
			promotion.setState(2);
			promotion.setLastModifiedTime(new Date());
			promotionDao.updateById(promotion);
		}
	}

	// ***********************************************************************************************************************//
	/**
	 * 
	 * Description 活动商品验证
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsDto
	 * @throws GlobalException
	 */
	@Override
	public void PromotionGoodsException(PromotionGoodsDto promotionGoodsDto) throws GlobalException {
		if (null == promotionGoodsDto.getPromotionId()) {// 营销活动ID
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		} else if (null == promotionGoodsDto.getGoodsSkuId()) {// 商品sku
			throw new GlobalException(PromotionExceptionEnum.PRODUCT_SKU_NOT_NULL);
		} else if (null == promotionGoodsDto.getRepertoryNum()) {// 商品原库存
			throw new GlobalException(PromotionExceptionEnum.ORIGINAL_STOCK_MUST_NOT_NULL);
		} else if (null == promotionGoodsDto.getGoodsSkuId()) {

		}
	}

	/**
	 * 
	 * Description C端查询店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public List<PromotionDto> getPromotionStoreCouponListByStoreId(TokenDto tokenDetail, Long itemId) {
		List<PromotionDto> promotionDtoList = new ArrayList<PromotionDto>();
		Long storeId = tokenDetail.getStoreId();
		if (storeId != null) {
			promotionDtoList = promotionDao.selectPromotionStoreCouponListByStoreId(storeId);
			List<PromotionDto> delList = new ArrayList<PromotionDto>();
			if (promotionDtoList.size() != 0) {
				for (PromotionDto promotionDto : promotionDtoList) {
					/** 删除已领取完毕的优惠券 */
					if (promotionDto.getAmount() != null && promotionDto.getUsedAmount() != null) {
						if (promotionDto.getUsedAmount() >= promotionDto.getAmount()) {
							delList.add(promotionDto);
							continue;
						}
					}
					/** 查询是否已领取 */
					Long memberId = tokenDetail.getMemberId();
					if (memberId != null) {
						List<MemberCouponDto> memberCouponList = memberCouponDao.selectCouponByMemberIdAndPromotionId(memberId, promotionDto.getId());
						if (memberCouponList.size() == 0) {
							promotionDto.setUsedState(0);
						} else {
							/** 领取限制 */
							Long confineAmount = promotionDto.getConfineAmount();
							if (confineAmount == null || confineAmount == 0) {
								promotionDto.setUsedState(0);
							} else if (memberCouponList.size() < confineAmount) {
								promotionDto.setUsedState(0);
							} else {
								promotionDto.setUsedState(1);
							}
						}
					}
					/** 当店铺优惠券不包括所有商品时 */
					if (promotionDto.getIsGoodsArea() != 1) {
						/** 根据商品itemId和营销活动id查询数据 */
						List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectPromotionGoodsByItemId(promotionDto.getId(), itemId);
						if (promotionGoodsDtoList.size() == 0) {
							delList.add(promotionDto);
						}
					}
				}
				promotionDtoList.removeAll(delList);
			}
		}
		return promotionDtoList;
	}

	/**
	 * 
	 * Description 会员领取优惠券修改领取数量
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 */
	@Override
	public void modifyPromotionUsedAmountById(Long promotionId,Long memberId) {
		/** 判断活动id */
		if (promotionId != null) {
			PromotionEntity promotion = promotionDao.selectById(promotionId);
			if (promotion != null) {
				boolean activityAvailablePlatform = isActivityAvailablePlatform(memberId,promotionId);
				if(activityAvailablePlatform) {
					// 验证优惠券是否限制会员领取次数
					if(promotion.getConfineAmount() != null && promotion.getConfineAmount() != 0) {
						List<MemberCouponDto> memberCouponList = memberCouponDao.selectCouponByMemberIdAndPromotionId(memberId, promotionId);
						if(memberCouponList.size() != 0) {
							if(promotion.getConfineAmount() <= memberCouponList.size()) {
								/** 优惠券已领取*/
								throw new GlobalException(PromotionExceptionEnum.THE_COUPONS_HAVE_BEEN_TAKEN);
							}
						}
					}
					Long usedAmount = promotion.getUsedAmount();
					// 发放数量为0或空表示不限制发放数量
					if (promotion.getAmount() == null || promotion.getAmount() == 0) {
						// 领取数量
						if (usedAmount == null) {
							promotion.setUsedAmount((long) 1);
						} else {
							Long lastUsedAmount = usedAmount + 1;
							promotion.setUsedAmount(lastUsedAmount);
						}
						promotionDao.updateById(promotion);
					} else {
						// 领取数量为空或0
						if (usedAmount == null || usedAmount == 0) {
							promotion.setUsedAmount((long) 1);
						} else if (usedAmount < promotion.getAmount()) {
							Long lastUsedAmount = usedAmount + 1;
							promotion.setUsedAmount(lastUsedAmount);
						} else {
							/** 优惠券不可领取 */
							throw new GlobalException(PromotionExceptionEnum.COUPONS_ARE_NOT_TO_BE_OBTAINED);
						}
						promotionDao.updateById(promotion);
					}
				}else {
					/** 优惠券不可领取*/
					throw new GlobalException(PromotionExceptionEnum.COUPONS_ARE_NOT_TO_BE_OBTAINED);
				}
			} else {
				/** 活动不存在 */
				throw new GlobalException(PromotionExceptionEnum.ACTIVITY_DOES_NOT_EXIST);
			}
		} else {
			/** 活动id不可为空 */
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
	}

	@Override
	public void saveMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException {
		TokenUtil.verifyToken(memberCouponDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberCouponDto.getToken());
		Long storeId = tokenDetail.getStoreId();
		if (null == memberCouponDto.getCouponId()) {
			throw new GlobalException(MemberCouponExceptionEnum.MEMBERCONPON_ID_NOT_NULL);
		}
		if (null == memberCouponDto.getMemberId()) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		/** 会员领取优惠券修改领取数量 */
		modifyPromotionUsedAmountById(memberCouponDto.getCouponId(),memberCouponDto.getMemberId());
		MemberCouponEntity memberCoupon = new MemberCouponEntity();
		memberCouponDto.setUsageState(0);
		memberCouponDto.setCreatedTime(new Date());
		memberCouponDto.setCreatorId(memberCouponDto.getMemberId());
		memberCouponDto.setStoreId(storeId);
		memberCouponDto.setReceiverTime(new Date());
		BeanUtils.copyProperties(memberCouponDto, memberCoupon);
		memberCouponDao.insert(memberCoupon);
	}

	/**
	 * 
	 * Description 平台报名活动列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Page<PromotionDto> getPlatformEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) throws ParseException {
		/** 已审核商家数量*/
		Integer auditStoreNum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!(promotionDto.getPromotionStart() == null || "".equals(promotionDto.getPromotionStart()))) {
			Date startDate = sdf.parse(promotionDto.getPromotionStart());
			promotionDto.setStartTime(startDate);
		}
		if(!(promotionDto.getPromotionEnd() == null || "".equals(promotionDto.getPromotionEnd()))) {
			Date endDate = sdf.parse(promotionDto.getPromotionEnd());
			promotionDto.setEndTime(endDate);
		}
		/** 平台报名商家列表*/
		List<PromotionDto> promotionDtoList = promotionDao.selectPlatformEnrolPromotionList(page,promotionDto);
		for (PromotionDto promotion : promotionDtoList) {
			/** 根据活动id查询商家报名信息*/
			List<PromotionEntryDto> promotionEntryList = promotionEntryDao.selectPromotionEntryByPromotionId(promotion.getId());
			if(promotionEntryList.size() != 0) {
				for (PromotionEntryDto promotionEntryDto : promotionEntryList) {
					if(promotionEntryDto.getState() != null && promotionEntryDto.getState() != 0) {
						auditStoreNum++;
					}
				}
				promotion.setStoreEnrolNum(promotionEntryList.size());
			}else {
				promotion.setStoreEnrolNum(0);
			}
			promotion.setAuditStoreNum(auditStoreNum);
		}
		return page.setRecords(promotionDtoList);
	}

	/**
	 * 
	 * Description 发起报名 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionEnrolDto
	 * @throws ParseException 
	 */
	@Override
	public void modifyInitiateEnrol(PromotionEnrolDto promotionEnrolDto) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionEnrolDto.getPromotionEnrolStart());
		Date endDate = sdf.parse(promotionEnrolDto.getPromotionEnrolEnd());
		promotionEnrolDto.setApplyStartTime(startDate);
		promotionEnrolDto.setApplyEndTime(endDate);
		PromotionEntity promotion = promotionDao.selectById(promotionEnrolDto.getId());
		/** 比较报名截止时间和活动开始时间*/
		if(endDate.getTime() > promotion.getStartTime().getTime()) {
			throw new GlobalException(PromotionExceptionEnum.PLEASE_ADJUST_THE_ENROL_END_TIME);
		}
		BeanUtils.copyProperties(promotionEnrolDto, promotion);
		promotion.setState(0);
		promotionDao.updateById(promotion);
	}

	/**
	 * 
	 * Description 发起报名获取活动列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> getEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		List<PromotionDto> promotionList = promotionDao.selectEnrolPromotionList(page, promotionDto);
		return page.setRecords(promotionList);
	}

	/**
	 * 
	 * Description 营销活动报名商家列表
	 * 
	 * Author Joe    
	 * 
	 * @param page
	 * @param promotionEntryDto
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Page<PromotionEntryDto> getPromotionEnrolStoreList(Page<PromotionEntryDto> page, PromotionEntryDto promotionEntryDto) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!(promotionEntryDto.getPromotionEnrolStart() == null || "".equals(promotionEntryDto.getPromotionEnrolStart()))) {
			Date startDate = sdf.parse(promotionEntryDto.getPromotionEnrolStart());
			promotionEntryDto.setApplyStartTime(startDate);
		}
		if(!(promotionEntryDto.getPromotionEnrolEnd() == null || "".equals(promotionEntryDto.getPromotionEnrolEnd()))) {
			Date endDate = sdf.parse(promotionEntryDto.getPromotionEnrolEnd());
			promotionEntryDto.setApplyEndTime(endDate);
		}
		List<PromotionEntryDto> promotionEntryDtoList = promotionEntryDao.selectPromotionEnrolStoreList(page, promotionEntryDto);
		for (PromotionEntryDto promotionEntry : promotionEntryDtoList) {
			if(promotionEntry.getPromotionId() != null && promotionEntry.getStoreId() != null) {
				/** 根据活动id和店铺id查询商品信息*/
				List<PromotionGoodsDto> promotionGooodsList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionEntry.getPromotionId(),promotionEntry.getStoreId());
				/** 商家报名商品数量*/
				if(promotionGooodsList.size() == 0) {
					promotionEntry.setEnrolGoodsNum(0);
				}else {
					promotionEntry.setEnrolGoodsNum(promotionGooodsList.size());
				}
			}else {
				promotionEntry.setEnrolGoodsNum(0);
			}
		}
		return page.setRecords(promotionEntryDtoList);
	}

	/**
	 * 
	 * Description 报名活动商家不通过
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 */
	@Override
	public void modifyStoreAuditNonconformity(Long id) {
		PromotionEntryEntity promotionEntry = promotionEntryDao.selectById(id);
		List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionEntry.getPromotionId(),promotionEntry.getStoreId());
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsDtoList) {
			PromotionGoodsEntity promotionGoodsEntity = promotionGoodsDao.selectById(promotionGoodsDto.getId());
			BeanUtils.copyProperties(promotionGoodsDto, promotionGoodsEntity);
			promotionGoodsEntity.setState(2);
			promotionGoodsDao.updateById(promotionGoodsEntity);
		}
		promotionEntry.setState(2);
		promotionEntryDao.updateById(promotionEntry);
	}

	/**
	 * 
	 * Description 查看店铺报名详情
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PromotionEntryDto getPromotionEnrolStore(Long id) {
		PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEnrolStore(id);
		/** 根据活动id和店铺id查询商品信息*/
		List<PromotionGoodsDto> promotionGooodsList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionEntryDto.getPromotionId(),promotionEntryDto.getStoreId());
		promotionEntryDto.setEnrolGoodsNum(promotionGooodsList.size());
		return promotionEntryDto;
	}

	/**
	 * 
	 * Description 商家营销活动报名列表(全部活动)
	 * 
	 * Author Joe    
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> selectStoreAllPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		List<PromotionDto> promotionDtoList = promotionDao.selectStoreAllPromotionList(page, promotionDto);
		List<PromotionDto> delList = new ArrayList<PromotionDto>();
		for (PromotionDto promotion : promotionDtoList) {
			if(storeId != null) {
				PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotion.getId(), storeId);
				if(promotionEntryDto != null) {
					delList.add(promotion);
				}
			}
			if(promotion.getStoreGrade() != null) {
				MerchantGradeEntity merchantGradeEntity = merchantGradeDao.selectById(promotion.getStoreGrade());
				StoreInfoEntity storeInfo = storeInfoDao.selectById(storeId);
				if(storeInfo != null) {
					if(storeInfo.getMerchantId() != null) {
						MerchantInfoEntity merchantInfo = merchantInfoDao.selectById(storeInfo.getMerchantId());
						if(merchantInfo != null) {
							if(merchantInfo.getMerchantGradeId() != null) {
								MerchantGradeEntity merchantGradey = merchantGradeDao.selectById(merchantInfo.getMerchantGradeId());
								if(merchantGradeEntity.getIntegralValue() <= merchantGradey.getIntegralValue()) {
									/** 该商家可报名*/ 
									promotion.setWhetherEnrol(0);
								}else {
									/** 该商家不可报名*/
									promotion.setWhetherEnrol(1);
								}
							}
						}
					}
					
				}
			}
		}
		promotionDtoList.removeAll(delList);
		page.setTotal(page.getTotal() - delList.size());
		return page.setRecords(promotionDtoList);
	}

	/**
	 * 
	 * Description 商家营销活动报名列表(已报名活动)
	 * 
	 * Author Joe    
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@Override
	public Page<PromotionDto> getStoreEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		/** 所属店铺*/
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		promotionDto.setStoreId(storeId);
		List<PromotionDto> promotionDtoList = promotionDao.selectStoreEnrolPromotionList(page, promotionDto);
		List<PromotionDto> promotionList = new ArrayList<PromotionDto>();
		Integer del = 0;
		for (PromotionDto promotion : promotionDtoList) {
			if(storeId != null) {
				PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotion.getId(), storeId);
				if(promotionEntryDto != null) {
					promotionList.add(promotion);
				}else {
					del++;
				}
			}
			
		}
		for (PromotionDto promotionDto2 : promotionList) {
			/** 该店铺在该活动中所报名的商品*/
			List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionDto2.getId(), storeId);
			if(promotionGoodsList.size() != 0) {
				PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotionDto2.getId(), storeId);
				if(promotionEntryDto != null) {
					if(promotionEntryDto.getState() == 1) {
						for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
							if(promotionGoodsDto.getState() != null) {
								if(promotionGoodsDto.getState() == 2) {
									promotionDto2.setAuditState(3);
								}
							}
						}
					}
				}
			}
		}
		page.setTotal(page.getTotal()-del);
		page.setRecords(promotionList);
		return page;
	}
}