package com.topaiebiz.promotion.mgmt.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.promotion.mgmt.dao.PromotionDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionEntryDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionGoodsDao;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEntryDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionPriceDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntryEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionGoodsEntity;
import com.topaiebiz.promotion.mgmt.exception.PromotionExceptionEnum;
import com.topaiebiz.promotion.mgmt.service.PromotionGoodsService;
import com.topaiebiz.promotion.mgmt.service.PromotionService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Description 营销活动商品
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月13日 下午4:43:29
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class PromotionGoodsServiceImpl implements PromotionGoodsService {

	// 营销活动商品
	@Autowired
	private PromotionGoodsDao promotionGoodsDao;

	// 营销活动数据层
	@Autowired
	private PromotionDao promotionDao;

	// 营销活动业务层
	@Autowired
	private PromotionService promotionService;

	// 商品sku
	@Autowired
	private GoodsSkuDao goodsSkuDao;

	// 商品item
	@Autowired
	private ItemDao itemDao;

	// 店铺订单详情
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	// 营销活动商家报名
	@Autowired
	private PromotionEntryDao promotionEntryDao;
	
	/**
	 * 
	 * Description 根据订单级活动查询优惠信息
	 * 
	 * Author Joe   
	 * 
	 * @param goodsSkuList
	 * 			   商品集合
	 * @param promotionStoreId
	 * 				活动ID
	 * @param memberId
	 * 			会员ID
	 * @return
	 */
	@Override
	public PromotionPriceDto getStorePromotionGoods(List<GoodsSkuDto> goodsSkuList, Long promotionStoreId, Long memberId) {
		/** 判断该会员是否可用该活动*/
		if (memberId != null) {
			boolean activityAvailablePlatform = promotionService.isActivityAvailablePlatform(memberId, promotionStoreId);
			if (!activityAvailablePlatform) {
				return null;
			}
		} else {
			throw new GlobalException(PromotionExceptionEnum.MEMBER_ID_NOT_NULL);
		}
		PromotionPriceDto promotionPriceDto = new PromotionPriceDto();
		// 参加店铺活动商品
		List<PromotionGoodsDto> promotionGoodsList = new ArrayList<PromotionGoodsDto>();
		// 原价
		Double originalPrice = 0.00;
		// 活动商品总价
		Double promotionGoodsProce = 0.00;
		// 现价
		Double presentPrice = 0.00;
		// 优惠金额
		Double preferentialAmount = 0.00;
		for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
			if (goodsSkuDto.getPrice() != 0) {
				// 原价
				originalPrice += goodsSkuDto.getPrice();
			}
			Long goodsSkuId = goodsSkuDto.getId();
			PromotionEntity promotion = promotionDao.selectById(promotionStoreId);
			if (promotion != null) {
				if (promotion.getIsGoodsArea() == 1) {
					// 计算参与活动商品总价格
					promotionGoodsProce += goodsSkuDto.getPrice();
				} else {
					PromotionGoodsDto promotionGoods = promotionGoodsDao.getPromotionGoods(promotionStoreId, goodsSkuId);
					if (null != promotionGoods) {
						promotionGoodsList.add(promotionGoods);
						// 计算参与活动商品总价格
						promotionGoodsProce += goodsSkuDto.getPrice();
					}
				}
			}
		}
		promotionPriceDto.setOriginalPrice(originalPrice);
		if(promotionGoodsProce == 0.00) {
			promotionPriceDto.setPromotionGoodsProce(promotionGoodsProce);
			promotionPriceDto.setPreferentialAmount(preferentialAmount);
			promotionPriceDto.setPresentPrice(originalPrice);
			return promotionPriceDto;
		}
		promotionPriceDto.setPromotionGoodsProce(promotionGoodsProce);
		promotionPriceDto.setPreferentialAmount(preferentialAmount);
		promotionPriceDto.setPresentPrice(presentPrice);
		PromotionPriceDto promotionPrice = getDiscountPromotionGoods(promotionPriceDto, promotionStoreId);
		promotionPrice.setPromotionGoodsList(promotionGoodsList);
		return promotionPrice;
	}

	
	/**
	 * 
	 * Description 根据支付级活动查询优惠信息
	 * 
	 * Author Joe
	 * 
	 * @param goodsSkuList
	 *            商品集合
	 * @param promotionStoreId
	 *            店铺活动id
	 * @param promotionPlatformId
	 *            平台活动id
	 * @return
	 */
	public PromotionPriceDto getPlatformPromotionGoods(List<GoodsSkuDto> goodsSkuList, Long promotionStoreId, Long promotionPlatformId, Long memberId) {
		if (memberId != null) {
			if (promotionPlatformId == null) {
				boolean activityAvailablePlatform = promotionService.isActivityAvailablePlatform(memberId, promotionStoreId);
				if (!activityAvailablePlatform) {
					return null;
				}
			} else {
				boolean activityAvailablePlatform = promotionService.isActivityAvailablePlatform(memberId, promotionPlatformId);
				if (!activityAvailablePlatform) {
					return null;
				}
			}
		} else {
			throw new GlobalException(PromotionExceptionEnum.MEMBER_ID_NOT_NULL);
		}
		// 活动价格信息
		PromotionPriceDto promotionPriceDto = new PromotionPriceDto();
		// 活动商品总价
		Double promotionGoodsProce = 0.00;
		// 优惠金额
		Double preferentialAmount = 0.00;
		// 店铺优惠活动后的优惠信息
		PromotionPriceDto storePromotionGoods = getStorePromotionGoods(goodsSkuList, promotionStoreId, memberId);
		if(storePromotionGoods != null) {
			List<PromotionGoodsDto> promotionGoodsList = storePromotionGoods.getPromotionGoodsList();
			// 店铺活动默认选中所有商品时
			if(promotionGoodsList.size() == 0 || promotionGoodsList == null) {
				for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
					if (goodsSkuDto.getPrice() != 0 && null != goodsSkuDto.getOrderGoodsNum()) {
						// 计算单个商品在总价格中的比例
						Double divide = MathCountUtils.divide(goodsSkuDto.getPrice(), storePromotionGoods.getOriginalPrice());
						// 单个商品的优惠金额
						Double multiply2 = MathCountUtils.multiply(divide, storePromotionGoods.getPreferentialAmount());
						// 单个商品的优惠后价格
						Double subtract = MathCountUtils.subtract(goodsSkuDto.getPrice(), multiply2);
						goodsSkuDto.setPrice(subtract);
					}
				}
			}else {
				// 店铺活动所选商品活动总价格
				Double storePromotionGoodsPrice = 0.00;
				// 循环店铺活动所选商品
				for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
					for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
						if(promotionGoodsDto.getGoodsSkuId() == goodsSkuDto.getId()) {
							if (goodsSkuDto.getPrice() != 0 && null != goodsSkuDto.getOrderGoodsNum()) {
								storePromotionGoodsPrice += goodsSkuDto.getPrice();
							}
						}
					}
				}
				// 计算单个商品店铺优惠活动后金额
				for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
					for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
						if(promotionGoodsDto.getGoodsSkuId() == goodsSkuDto.getId()) {
							if (goodsSkuDto.getPrice() != 0 && null != goodsSkuDto.getOrderGoodsNum()) {
								// 计算单个商品在总价格中的比例
								Double divide = MathCountUtils.divide(goodsSkuDto.getPrice(), storePromotionGoodsPrice);
								// 单个商品的优惠金额
								Double multiply2 = MathCountUtils.multiply(divide, storePromotionGoods.getPreferentialAmount());
								// 单个商品的优惠后价格
								Double subtract = MathCountUtils.subtract(goodsSkuDto.getPrice(), multiply2);
								goodsSkuDto.setPrice(subtract);
							}
						}
					}
				}
			}
		}
		
		// 计算参与活动商品总价格
		for (GoodsSkuDto goodsSkuDto : goodsSkuList) {
			Long goodsSkuId = goodsSkuDto.getId();
			PromotionEntity promotion = promotionDao.selectById(promotionPlatformId);
			if (promotion != null) {
				if (promotion.getIsGoodsArea() == 1) {
					promotionGoodsProce += goodsSkuDto.getPrice();
				} else {
					PromotionGoodsDto promotionGoods = promotionGoodsDao.getPromotionGoods(promotionStoreId, goodsSkuId);
					if (null != promotionGoods) {
						promotionGoodsProce += goodsSkuDto.getPrice();
					}
				}
			}

		}
		promotionPriceDto.setOriginalPrice(storePromotionGoods.getPresentPrice());
		promotionPriceDto.setPromotionGoodsProce(promotionGoodsProce);
		promotionPriceDto.setPreferentialAmount(preferentialAmount);
		promotionPriceDto.setPresentPrice(storePromotionGoods.getPresentPrice());
		if (null != promotionPlatformId) {
			PromotionPriceDto promotionPriceStore = getDiscountPromotionGoods(promotionPriceDto, promotionPlatformId);
			BeanUtils.copyProperties(promotionPriceStore, promotionPriceDto);
		}
		return promotionPriceDto;
	}

	/**
	 * 
	 * Description 根据商品与营销活动查询商品信息
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@Override
	public PromotionPriceDto getDiscountPromotionGoods(PromotionPriceDto promotionPriceDto, Long promotionId) {
		// 活动商品总价
		Double promotionGoodsProce = promotionPriceDto.getPromotionGoodsProce();
		// 优惠金额
		Double preferentialAmount = promotionPriceDto.getPreferentialAmount();
		// 现价
		Double presentPrice = promotionPriceDto.getOriginalPrice();
		// 是否包邮
		boolean freeShipping = false;
		// 获取营销活动信息
		PromotionEntity promotion = promotionDao.selectById(promotionId);
		if (promotion != null) {
			// 比较参与活动商品总价格是否符合优惠值
			if (promotionGoodsProce >= promotion.getCondValue()) {
				// 判断优惠条件类型
				if (promotion.getCondType() != null && promotion.getCondType() == 1) {// 满
					if (promotion.getDiscountType() != null && promotion.getDiscountType() == 1) {// 折扣
						Double discountValue = promotion.getDiscountValue();
						Double discount = discountValue / 10;
						// 优惠金额
						preferentialAmount = MathCountUtils.multiply(discount, promotionGoodsProce);
						// 现价
						presentPrice = MathCountUtils.subtract(promotionPriceDto.getOriginalPrice(), preferentialAmount);
					} else if (promotion.getDiscountType() != null && promotion.getDiscountType() == 2) {// 满减
						// 优惠金额
						preferentialAmount = promotion.getDiscountValue();
						// 现价
						presentPrice = MathCountUtils.subtract(promotionPriceDto.getOriginalPrice(), preferentialAmount);
					} else if (promotion.getDiscountType() != null && promotion.getDiscountType() == 3) {
						presentPrice = promotionPriceDto.getOriginalPrice();
						freeShipping = true;
					}
				} else if (promotion.getCondType() != null && promotion.getCondType() == 2) {// 每满
					if (promotion.getDiscountType() != null && promotion.getDiscountType() == 1) {// 折扣
						Double discountValue = promotion.getDiscountValue();
						// 折扣
						Double discount = discountValue / 10;
						// 打多少次折
						Integer discountQuantity = (int) (promotionGoodsProce / promotion.getCondValue());
						Double multiply = MathCountUtils.multiply(discount, promotion.getCondValue());
						Double subtract = MathCountUtils.subtract(promotion.getCondValue(), multiply);
						// 优惠金额
						preferentialAmount = MathCountUtils.multiply(discountQuantity.doubleValue(), subtract);
						// 现价
						presentPrice = MathCountUtils.subtract(promotionPriceDto.getOriginalPrice(), preferentialAmount);
					} else if (promotion.getDiscountType() != null && promotion.getDiscountType() == 2) {// 满减
						// 优惠金额
						Double discountValue = promotion.getDiscountValue();
						// 优惠次数
						Integer discountQuantity = (int) (promotionGoodsProce / promotion.getCondValue());
						// 优惠金额
						preferentialAmount = MathCountUtils.multiply(discountQuantity.doubleValue(), discountValue);
						// 现价
						presentPrice = MathCountUtils.subtract(promotionPriceDto.getOriginalPrice(), preferentialAmount);
					} else if (promotion.getDiscountType() != null && promotion.getDiscountType() == 3) {
						presentPrice = promotionPriceDto.getOriginalPrice();
						freeShipping = true;
					}
				}
			}
		}
		promotionPriceDto.setPromotionId(promotionId);
		promotionPriceDto.setOriginalPrice(promotionPriceDto.getOriginalPrice());
		promotionPriceDto.setPresentPrice(presentPrice);
		promotionPriceDto.setPreferentialAmount(preferentialAmount);
		promotionPriceDto.setFreeShipping(freeShipping);
		return promotionPriceDto;
	}

	/**
	 * 
	 * Description 校验该单品活动该会员与该商品是否可用
	 * 
	 * Author Joe
	 * 
	 * @param memberId
	 * @param promotionId
	 * @param goodsSkuId
	 * @return
	 */
	@Override
	public PromotionGoodsDto getPromotionGoods(Long memberId, Long promotionId, Long goodsSkuId) {
		boolean activityAvailable = promotionService.isActivityAvailable(memberId, promotionId, goodsSkuId);
		if (activityAvailable) {
			PromotionGoodsDto promotionGoods = promotionGoodsDao.getPromotionGoods(promotionId, goodsSkuId);
			return promotionGoods;
		}
		return null;
	}

	/**
	 * 
	 * Description 查询首页秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param tokenDetail
	 * @param page
	 * @param startTime
	 * @return
	 * @throws ParseException
	 */
	@Override
	public Page<PromotionGoodsDto> getHomePageSeckill(String token, Page<PromotionGoodsDto> page) throws ParseException {

		Long storeId = null;
		/** 返回商品列表 */
		List<PromotionGoodsDto> promotionGoodsDtoList = new ArrayList<PromotionGoodsDto>();
		PromotionGoodsDto promotionGoodsDto = new PromotionGoodsDto();
		List<PromotionDto> promotionList = promotionDao.selectPromotionHomePageSeckill();
		if (promotionList.size() != 0) {
			Long promotionId = promotionList.get(0).getId();
			Date promotionEndTime = promotionList.get(0).getEndTime();
			promotionGoodsDto.setPromotionId(promotionId);
			if (null != token) {
				TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
				if (null != tokenDetail) {
					storeId = tokenDetail.getStoreId();
				} else {
					storeId = (long) 1;
				}
			} else {
				storeId = (long) 1;
			}
			promotionGoodsDto.setStoreId(storeId);
			List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectPromotionGoodsList(page, promotionGoodsDto);
			for (PromotionGoodsDto promotionGoods : promotionGoodsList) {
				GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(promotionGoods.getGoodsSkuId());
				ItemEntity itemEntity = itemDao.selectById(promotionGoods.getItemId());
				promotionGoods.setGoodsPrice(goodsSkuEntity.getPrice());
				promotionGoods.setSaleImage(goodsSkuEntity.getSaleImage());
				promotionGoods.setPromotionEndTime(promotionEndTime);
				promotionGoods.setGoodsName(itemEntity.getName());
				promotionGoodsDtoList.add(promotionGoods);
			}
		}
		page.setRecords(promotionGoodsDtoList);
		return page;
	}

	/**
	 * 
	 * Description 根据营销活动id查询活动商品分页列表
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @param page
	 * @param promotionId
	 * @return
	 */
	@Override
	public Page<PromotionGoodsDto> getPromotionSeckillList(String token, Page<PromotionGoodsDto> page, Long promotionId) {

		/** 所属店铺 */
		Long storeId = null;
		/** 返回商品列表 */
		List<PromotionGoodsDto> promotionGoodsDtoList = new ArrayList<PromotionGoodsDto>();
		/** 列表查询条件 */
		PromotionGoodsDto promotionGoodsDto = new PromotionGoodsDto();
		promotionGoodsDto.setPromotionId(promotionId);
		PromotionEntity promotionEntity = promotionDao.selectById(promotionId);
		if (null != token) {
			TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
			if (null != tokenDetail) {
				storeId = tokenDetail.getStoreId();
			} else {
				storeId = (long) 1;
			}
		} else {
			storeId = (long) 1;
		}
		promotionGoodsDto.setStoreId(storeId);
		List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectPromotionGoodsList(page, promotionGoodsDto);
		for (PromotionGoodsDto promotionGoods : promotionGoodsList) {
			Double goodsNum = 0.00;
			GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(promotionGoods.getGoodsSkuId());
			ItemEntity itemEntity = itemDao.selectById(promotionGoods.getItemId());
			List<OrderDetailsEntity> orderDetailsList = orderDetailsDao.findOneByPromotionAndSkuId(promotionId, promotionGoods.getGoodsSkuId());
			for (OrderDetailsEntity orderDetailsEntity : orderDetailsList) {
				goodsNum += orderDetailsEntity.getGoodsNum();
			}
			Double sellGoodsQuantity = 0.00;
			if (goodsNum == 0) {
				sellGoodsQuantity = 0.00;
			} else {
				sellGoodsQuantity = MathCountUtils.divide(goodsNum, promotionGoods.getPromotionNum().doubleValue());
			}
			promotionGoods.setSellGoodsQuantity(sellGoodsQuantity);
			promotionGoods.setGoodsPrice(goodsSkuEntity.getPrice());
			promotionGoods.setSaleImage(goodsSkuEntity.getSaleImage());
			promotionGoods.setPromotionEndTime(promotionEntity.getEndTime());
			promotionGoods.setGoodsName(itemEntity.getName());
			promotionGoodsDtoList.add(promotionGoods);
		}
		page.setRecords(promotionGoodsDtoList);
		return page;
	}

	/**
	 * 
	 * Description 商家报名商品
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	@Override
	public Page<PromotionGoodsDto> getStoreEnrolGoodsList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto) {
		boolean equal = false;
		List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectStoreEnrolGoodsList(page, promotionGoodsDto);
		for (PromotionGoodsDto promotionGoods : promotionGoodsList) {
			/** 库存*/
			Long repertoryNum = promotionGoods.getRepertoryNum();
			Long promotionId = promotionGoods.getPromotionId();
			Long storeId = promotionGoods.getStoreId();
			Long itemId = promotionGoods.getItemId();
			if(itemId != null && storeId != null && promotionId != null) {
				List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreIdAndItemId(promotionId,storeId,itemId);
				for (PromotionGoodsDto promotionGoodsDto2 : promotionGoodsDtoList) {
					/** 商品库存*/
					if(promotionGoodsDto2.getRepertoryNum() != null) {
						repertoryNum += promotionGoodsDto2.getRepertoryNum();
					}
					/** 判断该item商品下所有的sku商品设置的活动价格与活动数量和id限购是否一致*/
					if(promotionGoodsDto2.getPromotionPrice()!=null&&promotionGoodsDto2.getPromotionNum()!=null&&promotionGoodsDto2.getConfineNum()!=null) {
						for(PromotionGoodsDto promotionGoodsDto3 : promotionGoodsDtoList) {
							if(promotionGoodsDto2.getPromotionPrice() == promotionGoodsDto3.getPromotionPrice()) {
								if(promotionGoodsDto2.getPromotionNum() == promotionGoodsDto3.getPromotionNum()) {
									if(promotionGoodsDto2.getConfineNum() == promotionGoodsDto3.getConfineNum()) {
										equal = true;
									}
								}
							}
						}
					}
					if(equal) {
						promotionGoods.setPromotionPrice(promotionGoodsDto2.getPromotionPrice());
						promotionGoods.setPromotionNum(promotionGoodsDto2.getPromotionNum());
						promotionGoods.setConfineNum(promotionGoodsDto2.getConfineNum());
					}
				}
				promotionGoods.setRepertoryNum(repertoryNum);
			}
		}
		return page.setRecords(promotionGoodsList);
	}

	/**
	 * 
	 * Description 报名商家商品审核列表   
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	@Override
	public Page<PromotionGoodsDto> getStoreGoodsAuditList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto) {
		PromotionEntity promotionEntity = promotionDao.selectById(promotionGoodsDto.getPromotionId());
		/** 判断item商品下参与活动的sku商品的数值是否一直*/
		boolean equal = false;
		List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectStoreGoodsAuditList(page, promotionGoodsDto);
		for (PromotionGoodsDto promotionGoods : promotionGoodsDtoList) {
			/** 通过*/
			Integer adopt = 0;
			/** 不通过*/ 
			Integer unAdopt = 0;
			/** 审核*/
			Integer audit = 0;
			/** item商品库存*/
			Long itemStockNum = itemDao.selecStockNumbertById(promotionGoods.getItemId());
			List<PromotionGoodsDto> goodsByItemIdList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreIdAndItemId(promotionGoodsDto.getPromotionId(),promotionGoodsDto.getStoreId(),promotionGoods.getItemId());
			for (PromotionGoodsDto promotionGoodsDto2 : goodsByItemIdList) {
				/** 判断该item商品下所有的sku商品设置的活动价格与活动数量和id限购是否一致*/
				if(promotionGoodsDto2.getPromotionPrice()!=null&&promotionGoodsDto2.getPromotionNum()!=null&&promotionGoodsDto2.getConfineNum()!=null) {
					for(PromotionGoodsDto promotionGoodsDto3 : promotionGoodsDtoList) {
						if(promotionGoodsDto2.getPromotionPrice() == promotionGoodsDto3.getPromotionPrice()) {
							if(promotionGoodsDto2.getPromotionNum() == promotionGoodsDto3.getPromotionNum()) {
								if(promotionGoodsDto2.getConfineNum() == promotionGoodsDto3.getConfineNum()) {
									equal = true;
								}
							}
						}
					}
				}
				if(equal) {
					promotionGoods.setPromotionPrice(promotionGoodsDto2.getPromotionPrice());
					promotionGoods.setPromotionNum(promotionGoodsDto2.getPromotionNum());
					promotionGoods.setConfineNum(promotionGoodsDto2.getConfineNum());
				}
				if(promotionGoodsDto2.getState() == 1) {
					adopt++;
				}else if(promotionGoodsDto2.getState() == 2) {
					unAdopt++;
				}else {
					audit++;
				}
			}
			if(goodsByItemIdList.size() != 0) {
				/** 当通过的次数等于商品数量，那么该item商品通过审核*/
				if(adopt == goodsByItemIdList.size()) {
					promotionGoods.setState(1);
				}else if(unAdopt == goodsByItemIdList.size()) {
					promotionGoods.setState(2);
				}else if(audit == goodsByItemIdList.size()) {
					promotionGoods.setState(0);
				}else {
					promotionGoods.setState(3);
				}
			}
			promotionGoods.setRepertoryNum(itemStockNum);
		}
		promotionGoodsDtoList.get(0).setPromotionTypeId(promotionEntity.getTypeId());
		return page.setRecords(promotionGoodsDtoList);
	}


	/**
	 * 
	 * Description 审核sku商品 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsId
	 * @param state
	 */
	@Override
	public void modifyAuditSingleSkuGoods(Long promotionGoodsId, Integer state) {
		PromotionGoodsEntity promotionGoods = promotionGoodsDao.selectById(promotionGoodsId);
		promotionGoods.setState(state);
		promotionGoodsDao.updateById(promotionGoods);
	}


	/**
	 * 
	 * Description sku商品审核完成 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsList
	 */
	@Override
	public void modifyAuditSkuGoods(List<PromotionGoodsDto> promotionGoodsList) {
		for (PromotionGoodsDto promotionGoodsDto : promotionGoodsList) {
			if(null == promotionGoodsDto.getId()) {
				throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
			}
			if(null == promotionGoodsDto.getState()) {
				throw new GlobalException(PromotionExceptionEnum.PRODUCT_AUDIT_STATE_CANNOT_BE_EMPTY);
			}
			modifyAuditSingleSkuGoods(promotionGoodsDto.getId(),promotionGoodsDto.getState());
		}
	}

	/**
	 * 
	 * Description 审核item商品通过/不通过    
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsDto
	 */
	@Override
	public void modifyAuditItemGoods(PromotionGoodsDto promotionGoodsDto) {
		List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreIdAndItemId(promotionGoodsDto.getPromotionId(),promotionGoodsDto.getStoreId(),promotionGoodsDto.getItemId());
		for (PromotionGoodsDto promotionGoods : promotionGoodsDtoList) {
			modifyAuditSingleSkuGoods(promotionGoods.getId(),promotionGoodsDto.getState());
		}
	}


	/**
	 * 
	 * Description 商家商品审核完成   
	 * 
	 * Author Joe    
	 * 
	 * @param promotionGoodsDto
	 */
	@Override
	public void modifyAuditGoods(PromotionGoodsDto promotionGoodsDto) {
		/** 通过*/
		Integer adopt = 0;
		/** 不通过*/ 
		Integer unAdopt = 0;
		/** 审核*/
		Integer audit = 0;
		/** 查询该店铺报名该活动的所有商品*/
		List<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionGoodsDto.getPromotionId(), promotionGoodsDto.getStoreId());
		/** 将未审核的商品状态改为未通过*/
		for (PromotionGoodsDto promotionGoods : promotionGoodsDtoList) {
			if(promotionGoods.getState() != 1 && promotionGoods.getState() != 2) {
				modifyAuditSingleSkuGoods(promotionGoods.getId(),2);
			}
		}
		/** 查询该店铺报名该活动已经审核完成的所有商品*/
		List<PromotionGoodsDto> promotionGoodsList = promotionGoodsDao.selectGoodsByPromotionIdAndStoreId(promotionGoodsDto.getPromotionId(), promotionGoodsDto.getStoreId());
		/** 计算已通过和未通过与待审核的商品数量*/
		for (PromotionGoodsDto promotionGoodsDto2 : promotionGoodsList) {
			switch (promotionGoodsDto2.getState()) {
				case 1:adopt++;break;
				case 2:unAdopt++;break;
				default:audit++;break;
			}
		}
		PromotionEntryDto promotionEntryDto = promotionEntryDao.selectPromotionEntryByPromotionIdAndStoreId(promotionGoodsDto.getPromotionId(), promotionGoodsDto.getStoreId());
		Long id = promotionEntryDto.getId();
		PromotionEntryEntity promotionEntryEntity = promotionEntryDao.selectById(id);
		BeanUtils.copyProperties(promotionEntryDto, promotionEntryEntity);
		promotionEntryEntity.setId(id);
		if(audit != 0) {/** 未审核商品数量不为零,该店铺待审核状态*/
			promotionEntryEntity.setState(0);
		}else if(adopt != 0) {/** 已通过商品数量不为零,该店铺已通过状态*/
			promotionEntryEntity.setState(1);
		}else if(unAdopt != 0 && unAdopt == promotionGoodsList.size()) {/** 未通过商品数量不为零且全部商品未通过,该店铺未通过状态*/
			promotionEntryEntity.setState(2);
		}
		promotionEntryDao.updateById(promotionEntryEntity);
	}

}
