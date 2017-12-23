package com.topaiebiz.transaction.cart.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.PromoTionGoodsDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.point.dao.MemberCouponDao;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.transaction.cart.dao.GoodsFavoriteDao;
import com.topaiebiz.transaction.cart.dao.GoodsFootprintDao;
import com.topaiebiz.transaction.cart.dao.GoodsShareDao;
import com.topaiebiz.transaction.cart.dao.ShoppingCartDao;
import com.topaiebiz.transaction.cart.dao.StoreFreightDao;
import com.topaiebiz.transaction.cart.dto.CardStoreListDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartEntityDto;
import com.topaiebiz.transaction.cart.dto.StoreFreightInfoDto;
import com.topaiebiz.transaction.cart.dto.GoodsCartNextDto;
import com.topaiebiz.transaction.cart.dto.GoodsFavoriteDto;
import com.topaiebiz.transaction.cart.dto.MemberCardDto;
import com.topaiebiz.transaction.cart.dto.ModifyShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartAppDto;
import com.topaiebiz.transaction.cart.entity.ShoppingCartEntity;
import com.topaiebiz.transaction.cart.entity.StoreFreightEntity;
import com.topaiebiz.transaction.cart.entity.GoodsFavoriteEntity;
import com.topaiebiz.transaction.cart.entity.GoodsFootprintEntity;
import com.topaiebiz.transaction.cart.entity.GoodsShareEntity;
import com.topaiebiz.transaction.cart.exception.ShoppingCartExceptionEnum;
import com.topaiebiz.transaction.cart.service.ShoppingCartService;
import com.topaiebiz.transaction.common.util.ConstantUtils;
import com.topaiebiz.transaction.common.util.ResponseUtils;

/**
 * 
 * Description 购物车接口实现层
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月8日 上午10:22:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private GoodsSkuDao goodsSkuDao;
	@Autowired
	private MemberMgmtDao memberMgmtDao;
	@Autowired
	private GoodsFavoriteDao goodsFavoriteDao;
	@Autowired
	private StoreFreightDao storeFreightDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private GoodsShareDao goodsShareDao;
	@Autowired
	private GoodsFootprintDao goodsFootprintDao;
	@Autowired
	private MemberCouponDao memberCouponDao;

	@Override
	public Integer saveGoodsCart(ShoppingCartEntityDto shoppingCartEntityDto) throws GlobalException {
		Integer i = null;
		// 判断skuId是否存在
		GoodsSkuEntity goodsAttrEntity = goodsSkuDao.selectById(shoppingCartEntityDto.getGoodsId());
		if (goodsAttrEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
		}
		// 判断会员memberId是否存在
		MemberEntity memberEntity = memberMgmtDao.selectById(shoppingCartEntityDto.getMemberId());
		if (memberEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_MEMBER_NOT_NULL);
		}
		if (!ResponseUtils.isMatches(shoppingCartEntityDto.getGoodsNum().toString())) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSNUM_NOT_NULL);
		}
		// 判断库存是否充足
		if (shoppingCartEntityDto.getGoodsNum() > goodsAttrEntity.getStockNumber()) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_STOCKNUM);
		}
		ShoppingCartEntity goodsCartEntity = shoppingCartDao.selectByMemberSkuId(shoppingCartEntityDto);
		// 购物车已存在的情况
		if (goodsCartEntity != null) {
			Long goodsNum = goodsCartEntity.getGoodsNum() + shoppingCartEntityDto.getGoodsNum();
			goodsCartEntity.setGoodsNum(goodsNum);
			i = shoppingCartDao.updateById(goodsCartEntity);
		} else {
			ShoppingCartEntity entity = new ShoppingCartEntity();
			BeanUtils.copyProperties(shoppingCartEntityDto, entity);
			entity.setCreatorId(entity.getMemberId());
			entity.setCreatedTime(new Date());
			i = shoppingCartDao.insert(entity);
		}
		return i;
	}

	@Override
	public Integer addGoodsFavorite(Long memberId, Long[] itemIds) throws GlobalException {
		Integer i = null;
		if (itemIds == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_GOODSID_NOT_NULL);
		}
		for (Long itemId : itemIds) {
			ItemEntity itemEntity = itemDao.selectById(itemId);
			if (itemEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
			}
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_MEMBER_NOT_NULL);
		}
		List<GoodsFavoriteEntity> goodsFavoriteEntities = goodsFavoriteDao.selectGoodsFavoriteByMemberId(memberId);
		if (goodsFavoriteEntities.size() == 200) {
			throw new GlobalException(ShoppingCartExceptionEnum.FAVORITE_FULL);
		}
		for (Long itemId : itemIds) {
			List<ShoppingCartEntity> shoppingCartEntities = shoppingCartDao.selectShoppingCartByItemIds(memberId,
					itemId);
			if (!(shoppingCartEntities == null || shoppingCartEntities.size() == 0)) {
				for (ShoppingCartEntity shoppingCartEntity : shoppingCartEntities) {
					shoppingCartDao.deleteGoodsCartById(shoppingCartEntity.getId());
				}
			}
			GoodsFavoriteEntity goodsFavoriteEntity = goodsFavoriteDao.selectByMemberItemId(memberId, itemId);
			// 收藏夹不存在的情况
			if (goodsFavoriteEntity == null) {
				GoodsFavoriteEntity goodsFavoriteEntity1 = new GoodsFavoriteEntity();
				goodsFavoriteEntity1.setGoodsId(itemId);
				goodsFavoriteEntity1.setMemberId(memberId);
				goodsFavoriteEntity1.setCreatorId(memberId);
				goodsFavoriteEntity1.setCreatedTime(new Date());
				i = goodsFavoriteDao.insert(goodsFavoriteEntity1);
			}
		}
		return i;
	}

	@Override
	public Integer saveGoodssharing(Long memberId, Long[] itemIds) throws GlobalException {
		Integer i = null;
		if (itemIds == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_GOODSID_NOT_NULL);
		}
		for (Long itemId : itemIds) {
			ItemEntity itemEntity = itemDao.selectById(itemId);
			if (itemEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
			}
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_MEMBER_NOT_NULL);
		}
		for (Long itemId : itemIds) {
			GoodsShareEntity goodsShareEntity = goodsShareDao.selectGoodsShareByMemberItemId(memberId, itemId);
			if (goodsShareEntity == null) {
				GoodsShareEntity goodsShareEntity1 = new GoodsShareEntity();
				goodsShareEntity1.setCreatedTime(new Date());
				goodsShareEntity1.setCreatorId(memberId);
				goodsShareEntity1.setGoodsId(itemId);
				goodsShareEntity1.setMemberId(memberId);
				i = goodsShareDao.insert(goodsShareEntity1);
			}
		}
		return i;
	}

	@Override
	public ShoppingCartEntity findBySkuId(ShoppingCartEntityDto shoppingCartEntityDto) {
		return shoppingCartDao.selectByMemberSkuId(shoppingCartEntityDto);
	}

	@Override
	public Integer modifyGoodsNum(Long goodsNum, Long memberId, Long goodsId) {
		return shoppingCartDao.updateGoodsNum(goodsNum, memberId, goodsId);
	}

	@Override
	public List<ShoppingCartDto> queryProductCart(Long belongStore, Long memberId) {
		return shoppingCartDao.selectGoodsCart(belongStore, memberId);
	}

	@Override
	public Integer removeGoodsCart(Long[] id) throws GlobalException {
		for (Long long1 : id) {
			ShoppingCartEntity goodsCartEntity = shoppingCartDao.selectById(long1);
			if (goodsCartEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_NOT_EXIST);
			}
		}
		Integer i = shoppingCartDao.deleteGoodsCart(id);
		return i;
	}

	@Override
	public Integer removelGoodsFavorite(Long[] id) throws GlobalException {
		if (id == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_ID_NOT_NULL);
		}
		for (Long long1 : id) {
			GoodsFavoriteEntity goodsFavoriteEntity = goodsFavoriteDao.selectById(long1);
			if (goodsFavoriteEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_ID_NOT_EXIST);
			}
		}
		Integer i = goodsFavoriteDao.deleteGoodsFavorite(id);
		return i;
	}

	@Override
	public Integer removelGoodsFootprint(Long[] id) {
		if (id == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_ID_NOT_NULL);
		}
		for (Long long1 : id) {
			GoodsFootprintEntity goodsFootprintEntity = goodsFootprintDao.selectById(long1);
			if (goodsFootprintEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_ID_NOT_EXIST);
			}
		}
		return goodsFootprintDao.deleteGoodsFootprint(id);
	}

	@Override
	public List<CardStoreListDto> queryStoreCart(ShoppingCartDto shoppingCartDto) {
		List<CardStoreListDto> storeList = shoppingCartDao.selectStore(shoppingCartDto);
		if (storeList.size() > 0) {
			for (CardStoreListDto s : storeList) {
				s.setProductCartDtoList(shoppingCartDao.selectGoodsCart(s.getBelongStore(), s.getMemberId()));
			}
		}
		return storeList;
	}

	@Override
	public Integer modifyGoodsCartNum(ModifyShoppingCartDto modifyShoppingCartDto) throws GlobalException {
		ShoppingCartEntity goodsCartEntity = shoppingCartDao.selectById(modifyShoppingCartDto.getId());
		if (goodsCartEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_NOT_EXIST);
		}
		BeanUtils.copyProperties(modifyShoppingCartDto, goodsCartEntity);
		GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(goodsCartEntity.getGoodsId());
		if (modifyShoppingCartDto.getGoodsNum() > goodsSkuEntity.getStockNumber()) {
			goodsCartEntity.setGoodsNum(goodsSkuEntity.getStockNumber());
		}
		return shoppingCartDao.updateById(goodsCartEntity);
	}

	@Override
	public GoodsCartNextDto shoppingCartStatistics(ShoppingCartDto shoppingCartDto) {
		GoodsCartNextDto pcnd = new GoodsCartNextDto();
		/** 把购物车的店铺部分统计出来 */
		List<CardStoreListDto> cardStoreList = shoppingCartDao.selectStore(shoppingCartDto);
		/** 初始化美礼卡金额 */
		double cardTotal = 0.0;
		/** 初始化总运费金额 */
		double firght = 0.0;
		/** 初始化商品总金额 */
		double productAmount = 0.0;
		if (cardStoreList.size() > 0) {
			for (CardStoreListDto s : cardStoreList) {
				double itemTotalFirght = 0.0;
				double goodsTotal = 0.0;
				/** 把购物车的店铺部分下面的购物车商品查出来 */
				List<ShoppingCartDto> shoppingCarList = shoppingCartDao.selectGoodsCart(s.getBelongStore(),
						s.getMemberId());
				if (shoppingCarList.size() > 0) {
					for (ShoppingCartDto p : shoppingCarList) {
						if (itemFirght(p, shoppingCartDto.getDistrictId()).equals(ConstantUtils.NO_GOODS.getName())) {
							/** 表示会员当前位置无货的 */
							p.setNoGoods(ConstantUtils.NO_GOODS.getName());
						} else {
							/** 通过封装的方法获取每个商品对应的运费 */
							itemTotalFirght += Double.valueOf(itemFirght(p, shoppingCartDto.getDistrictId()));
						}
						goodsTotal += p.getPrice();
						/** 通过封装的方法获取能够使用的美礼卡金额 */
						cardTotal += queryMemberCardDto(p);
					}
				}
				/** 计算商品的总价钱 */
				productAmount += goodsTotal;
				/** 计算总的运费 */
				firght += itemTotalFirght;
				/** 算出了购物车中对应店铺的商品总价格 */
				s.setProductTotal(goodsTotal);
				/** 算出了购物车中对应店铺的总运费 */
				s.setItemTotalFirght(itemTotalFirght);
				/** 根据店铺划分购物车中店铺下面指定的商品 */
				s.setProductCartDtoList(shoppingCartDao.selectGoodsCart(s.getBelongStore(), s.getMemberId()));
			}
		}
		/** 购物车下的所有商品的金额 */
		pcnd.setProductAmount(productAmount);
		/** 购物车下的对应店铺的总运费 */
		pcnd.setFirght(firght);
		/** 购物车下的对应的店铺 */
		pcnd.setCardStoreList(cardStoreList);
		/** 可使用的美礼卡金额 */
		pcnd.setUsedCardPrice(cardTotal);
		return pcnd;
	}

	/**
	 * 
	 * Description 计算每个店铺的运费
	 * 
	 * Author zhushuyong
	 * 
	 * @param productCart
	 *            查询出来的购物车中单个商品的dto
	 * @param districtId
	 *            会员的收货地址所在地区编码(必传)
	 * @return
	 */
	private String itemFirght(ShoppingCartDto shoppingCartDto, Long districtId) {
		if (districtId != null) {
			String firght = queryStoreFreightInfoDto(shoppingCartDto.getLogisticsId(), shoppingCartDto.getGoodsNum(),
					districtId.toString());
			/** 如果条件成立的话，表示这个商品选择的运费模板是指定区域的配送，但是会员的收货地址没有复合条件，故给其显示无货 */
			if (firght.equals(ConstantUtils.NO_GOODS.getName())) {
				return ConstantUtils.NO_GOODS.getName();
			} else {
				return firght;
			}
		}
		return "0.0";
	}

	@Override
	public Double queryMemberCardDto(ShoppingCartDto shoppingCartDto) {
		double price = 0.0;
		List<MemberCardDto> memberCard = shoppingCartDao.selectMemberCardDto(shoppingCartDto);
		for (MemberCardDto mcd : memberCard) {
			price += memberCard(mcd, shoppingCartDto);
		}
		return price;
	}

	private Double memberCard(MemberCardDto memberCardDto, ShoppingCartDto shoppingCartDto) {
		double price = 0.0;
		if (memberCardDto != null) {
			/** 此卡属于平台卡（自由卡） */
			if (memberCardDto.getInfoRangeType() == ConstantUtils.RANGETYPE_ONE.getIndex()) {
				/** 直接获取美礼卡的余额 */
				price = memberCardDto.getBalance();
			}
			/** 此卡属于店铺卡（联名卡） */
			if (memberCardDto.getInfoRangeType() == ConstantUtils.RANGETYPE_TWO.getIndex()) {
				if (shoppingCartDto.getBelongStore() == memberCardDto.getInfoStoreId()) {
					price = memberCardDto.getBalance();
				}
			}
			/** 此卡属于品牌卡 */
			if (memberCardDto.getInfoRangeType() == ConstantUtils.RANGETYPE_THREE.getIndex()) {
				if (shoppingCartDto.getBelongBrand() == memberCardDto.getInfoBrandId()) {
					price = memberCardDto.getBalance();
				}
			}
		}
		return price;
	}

	@Override
	public String queryStoreFreightInfoDto(Long infoId, Long productNum, String district) {
		/** 传进来的会员收货地址区域为空时，默认运费0.0 */
		if (StringUtils.isEmpty(district)) {
			List<StoreFreightInfoDto> list = shoppingCartDao.selectStoreFreightInfoDto(infoId);
			StoreFreightEntity freightInf = storeFreightDao.selectById(infoId);
			boolean matching = true;
			for (StoreFreightInfoDto storeFreightInfo : list) {
				/** 用对应的运费模板的配送区域集合去匹配传进来的会员收货地址的区域 */
				/** 在指定的配送区域集合去匹配传进来的会员收货地址的区域 */
				if (storeFreightInfo.getDistrictIdList().contains(district)) {
					matching = false;
					return freight(storeFreightInfo, productNum).toString();
				}
			}
			if (matching) {
				/** 判断运费模板是否仅配送特定特定区域 true：代表是指定区域 */
				if (freightInf.getOnlyThis()) {
					/**
					 * 到这一步就已经判断了指定区域是否跟会员收货地址中区域是否存在一致的情况，此成立时表示没有指定区域复合条件 默认返回一个指定字符
					 */
					return ConstantUtils.NO_GOODS.getName();
				} else {
					for (StoreFreightInfoDto storeFreightInfo : list) {
						/** 取出默认运费的数据对象 */
						if (storeFreightInfo.getIsDefault()) {
							return freight(storeFreightInfo, productNum).toString();
						}
					}
				}
			}
		}
		return freight(null, null).toString();
	}

	/**
	 * 
	 * Description 计算运费
	 * 
	 * Author zhushuyong
	 * 
	 * @param storeFreightInfoDto
	 *            得到的使用哪个运费
	 * @param productNum
	 *            对应商品数量
	 * @return
	 */
	private Double freight(StoreFreightInfoDto storeFreightInfoDto, Long productNum) {
		double freight = 0.0;
		if (storeFreightInfoDto != null || productNum != null) {
			if (productNum > storeFreightInfoDto.getFirstNum()) {
				freight = storeFreightInfoDto.getFirstPrice()
						+ (((int) ((productNum - storeFreightInfoDto.getFirstNum()) / storeFreightInfoDto.getAddNum()))
								* storeFreightInfoDto.getAddPrice());
			} else {
				freight = storeFreightInfoDto.getFirstPrice();
			}
		}
		return freight;
	}

	@Override
	public ShoppingCartAppDto getShoppingCartByMemberId(Long memberId, Long storeId) {
		ShoppingCartAppDto shoppingCartAppDto = new ShoppingCartAppDto();
		List<ItemDto> itemDtos = itemDao.selectShoppingCartByMemberId(memberId);
		if (!(itemDtos == null || itemDtos.size() == 0)) {
			for (ItemDto itemDto : itemDtos) {
				Long skuId = itemDto.getSkuId();
				/** 根据该商品查询对应的包邮活动。 */
				List<PromoTionGoodsDto> storePinkagePromoTionGoodsDtos = itemDao
						.selectStorePinkagePromoTionGoodsDtos(skuId);
				/** 店铺级单品营销活动。 */
				List<PromoTionGoodsDto> storePromoTionGoodsDtos = itemDao.selectPromoTionBySkuId(skuId, storeId);
				;
				if (!(storePromoTionGoodsDtos == null || storePromoTionGoodsDtos.size() == 0)) {
					for (PromoTionGoodsDto promoTionGoodsDto : storePromoTionGoodsDtos) {
						if (promoTionGoodsDto.getTypeId() == 1) {
							// 商品总价格
							Double price = promoTionGoodsDto.getPromotionPrice() + promoTionGoodsDto.getDiscountValue();
							// 打折
							Double discount = promoTionGoodsDto.getPromotionPrice() / price;
							Double discounts = discount * 10;
							DecimalFormat df = new DecimalFormat(".#");
							String st = df.format(discounts);
							promoTionGoodsDto.setDiscount(st);
						}

					}
				}
				/** 平台级单品营销活动。 */
				List<PromoTionGoodsDto> adminPromoTionGoodsDtos = itemDao.selectPromotionDtoBySkuId(skuId);
				if (!(adminPromoTionGoodsDtos == null || adminPromoTionGoodsDtos.size() == 0)) {
					itemDto.setSkuPrice(adminPromoTionGoodsDtos.get(0).getPromotionPrice());
					itemDto.setPromotionId(adminPromoTionGoodsDtos.get(0).getPromotionId());
				}
				/** 店铺优惠券。 */
				List<PromoTionGoodsDto> storeCouponPromoTionGoodsDtos = itemDao
						.selectStoreCouponPromoTionGoodsDtos(storeId, skuId);
				List<PromoTionGoodsDto> delStoreCouponPromoTionGoodsDtos = new ArrayList<PromoTionGoodsDto>();
				if (!(storeCouponPromoTionGoodsDtos == null || storeCouponPromoTionGoodsDtos.size() == 0)) {
					for (PromoTionGoodsDto promoTionGoodsDto : storeCouponPromoTionGoodsDtos) {
						boolean b = this.promoTionGoodsUse(memberId, promoTionGoodsDto);
						if (b == false) {
							delStoreCouponPromoTionGoodsDtos.add(promoTionGoodsDto);
						}
					}
					storeCouponPromoTionGoodsDtos.removeAll(delStoreCouponPromoTionGoodsDtos);
				}
				/** 平台优惠券。 */
				List<PromoTionGoodsDto> adminCouponPromoTionGoodsDtos = itemDao
						.selectAdminCouponPromoTionGoodsDtos(skuId);
				List<PromoTionGoodsDto> delAdminCouponPromoTionGoodsDtos = new ArrayList<PromoTionGoodsDto>();
				if (!(adminCouponPromoTionGoodsDtos == null || adminCouponPromoTionGoodsDtos.size() == 0)) {
					for (PromoTionGoodsDto promoTionGoodsDto : adminCouponPromoTionGoodsDtos) {
						boolean b = this.promoTionGoodsUse(memberId, promoTionGoodsDto);
						if (b == false) {
							delAdminCouponPromoTionGoodsDtos.add(promoTionGoodsDto);
						}
					}
					adminCouponPromoTionGoodsDtos.removeAll(delAdminCouponPromoTionGoodsDtos);
				}
				/** 店铺级活动（满减）。 */
				List<PromoTionGoodsDto> storeMoneyOffPromoTionGoodsDtos = itemDao
						.selectStoreMoneyOffPromoTionGoodsDtos(storeId, skuId);
				/** 平台级活动（满减）。 */
				List<PromoTionGoodsDto> adminMoneyOffPromoTionGoodsDtos = itemDao
						.selectAdminMoneyOffPromoTionGoodsDtos(skuId);
				itemDto.setStorePinkagePromoTionGoodsDtos(storePinkagePromoTionGoodsDtos);
				itemDto.setStorePromoTionGoodsDtos(storePromoTionGoodsDtos);
				itemDto.setAdminPromoTionGoodsDtos(adminPromoTionGoodsDtos);
				itemDto.setAdminCouponPromoTionGoodsDtos(adminCouponPromoTionGoodsDtos);
				itemDto.setStoreMoneyOffPromoTionGoodsDtos(storeMoneyOffPromoTionGoodsDtos);
				itemDto.setStoreCouponPromoTionGoodsDtos(storeCouponPromoTionGoodsDtos);
				itemDto.setAdminMoneyOffPromoTionGoodsDtos(adminMoneyOffPromoTionGoodsDtos);
			}
		}
		// 商家商品。
		List<ItemDto> itemStoreDtos = new ArrayList<ItemDto>();
		// 平台商品。
		List<ItemDto> itemAdminDtos = new ArrayList<ItemDto>();
		// 失效商品。
		List<ItemDto> itemLosaDtos = new ArrayList<ItemDto>();
		// 判断此商品是商家商品，平台商品，失效商品。
		if (!(itemDtos == null || itemDtos.size() == 0)) {
			for (ItemDto itemDto : itemDtos) {
				if (itemDto.getStatus() == 2) {
					if (itemDto.getBelongStore() == null) {
						itemAdminDtos.add(itemDto);
					} else {
						Long belongStore = itemDto.getBelongStore();
						// 查询店铺名称
						String storeName = itemDao.selectStoreNameById(belongStore);
						itemDto.setStoreName(storeName);
						itemStoreDtos.add(itemDto);
					}
				} else {
					itemLosaDtos.add(itemDto);
				}
			}
		}
		shoppingCartAppDto.setItemAdminDtos(itemAdminDtos);
		shoppingCartAppDto.setItemLosaDtos(itemLosaDtos);
		shoppingCartAppDto.setItemStoreDtos(itemStoreDtos);
		return shoppingCartAppDto;
	}

	// 判断店铺（或者平台）是否领取这个优惠券
	public boolean promoTionGoodsUse(Long memberId, PromoTionGoodsDto promoTionGoodsDto) {
		Boolean b = false;
		// 已领取完毕的优惠券
		if (promoTionGoodsDto.getAmount() != null && promoTionGoodsDto.getUsedAmount() != null) {
			if (promoTionGoodsDto.getUsedAmount() < promoTionGoodsDto.getAmount()) {
				b = true;
				MemberEntity member = memberMgmtDao.selectById(memberId);
				// 判断该会员是否存在
				if (member != null) {
					List<MemberCouponDto> memberCouponList = memberCouponDao
							.selectCouponByMemberIdAndPromotionId(memberId, promoTionGoodsDto.getId());
					if (memberCouponList.size() == 0) {
						b = true;
					} else {
						// 判断是否领取完毕
						Long confineAmount = promoTionGoodsDto.getConfineAmount();
						if (confineAmount == null || confineAmount == 0) {
							b = true;
						} else if (memberCouponList.size() < confineAmount) {
							b = true;
						} else {
							b = false;
						}
					}
				}
			} else {
				b = false;
			}
		} else {
			b = true;
		}
		return b;
	}

	// 判断店铺（或者平台）是否可以使用这个优惠券
	/*
	 * public boolean promoTionGoodsUse(Long memberId, PromoTionGoodsDto
	 * promoTionGoodsDto) { boolean b = false; MemberEntity member =
	 * memberMgmtDao.selectById(memberId); // 判断该会员是否存在 if (member != null) { if
	 * (null == promoTionGoodsDto.getMemberGradeId() && null ==
	 * promoTionGoodsDto.getMemberTypeId()) { if (promoTionGoodsDto.getUsedAmount()
	 * == null || promoTionGoodsDto.getUsedAmount() == 0) { b = true; } else if
	 * (promoTionGoodsDto.getUsedAmount() < promoTionGoodsDto.getAmount()) { b =
	 * true; } } else { // 判断活动是否限制该会员的等级与类型 if (member.getGradeId() ==
	 * promoTionGoodsDto.getMemberGradeId() || member.getTypeId() ==
	 * promoTionGoodsDto.getMemberTypeId()) { b = false; } else { if
	 * (promoTionGoodsDto.getUsedAmount() == null ||
	 * promoTionGoodsDto.getUsedAmount() == 0) { b = true; } else if
	 * (promoTionGoodsDto.getUsedAmount() < promoTionGoodsDto.getAmount()) { b =
	 * true; } } } } return b;
	 */
	/*
	 * public boolean isActivityAvailablePlatform(Long memberId, Long promotionId,
	 * Long goodsSkuId) { boolean available = false; MemberEntity member =
	 * memberMgmtDao.selectById(memberId); PromoTionGoodsDto promotion =
	 * itemDao.selectPromotionById(promotionId); // 判断会员信息与活动信息是否属实 if (null !=
	 * member && null != promotion) { // 判断是否有会员限制 if (null ==
	 * promotion.getMemberGradeId() && null == promotion.getMemberTypeId()) {
	 * available = true; } else { // 判断活动是否限制该会员的等级与类型 if (member.getGradeId() ==
	 * promotion.getMemberGradeId() || member.getTypeId() ==
	 * promotion.getMemberTypeId()) { available = false; } else { //
	 * 根据活动ID与商品sku查询限购次数 PromoTionGoodsDto promotionGoods =
	 * itemDao.selectPromotionGoods(promotionId, goodsSkuId); //
	 * 根据活动ID，商品sku和会员编号查询购买次数 List<OrderDetailsDto> orderDetailsList =
	 * orderDetailsDao.selectPromotionGoodsNum(promotionId, goodsSkuId, memberId);
	 * if (orderDetailsList.size() != 0) { int goodsNum = 0; for (OrderDetailsDto
	 * orderDetailsDto : orderDetailsList) { goodsNum +=
	 * orderDetailsDto.getGoodsNum(); } if (promotionGoods.getConfineNum() >
	 * goodsNum) { available = true; } } } } } return available; }
	 */
	@Override
	public Page<ItemDto> getGoodsFavoriteListByMemberId(Page<ItemDto> page, Long memberId) {
		page.setRecords(itemDao.selectGoodsFavoriteListByMemberId(page, memberId));
		return page;
	}

	@Override
	public Page<ItemDto> getGoodsFootprintListByMemberId(Page<ItemDto> page, ItemDto itemDto, Long memberId) {
		itemDto.setMemberId(memberId);
		page.setRecords(itemDao.selectGoodsFootprintListByMemberId(page, itemDto));
		return page;
	}

	@Override
	public Integer modfiyAppShoppingCart(Long memberId, Long oldSkuId, Long newSkuId) {
		Integer i = null;
		// 判断oldSkuId是否存在
		GoodsSkuEntity goodsAttrEntity = goodsSkuDao.selectById(oldSkuId);
		if (goodsAttrEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
		}
		// 判断newSkuId是否存在
		GoodsSkuEntity goodsAttrEntity1 = goodsSkuDao.selectById(newSkuId);
		if (goodsAttrEntity1 == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
		}
		// 判断会员memberId是否存在
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_MEMBER_NOT_NULL);
		}
		// 旧的skuId
		ShoppingCartEntityDto shoppingCartEntityDtoOld = new ShoppingCartEntityDto();
		shoppingCartEntityDtoOld.setGoodsId(oldSkuId);
		shoppingCartEntityDtoOld.setMemberId(memberId);
		ShoppingCartEntity shoppingCartEntityOld = shoppingCartDao.selectByMemberSkuId(shoppingCartEntityDtoOld);
		// 新的skuId
		ShoppingCartEntityDto shoppingCartEntityDtoNew = new ShoppingCartEntityDto();
		shoppingCartEntityDtoNew.setGoodsId(newSkuId);
		shoppingCartEntityDtoNew.setMemberId(memberId);
		ShoppingCartEntity shoppingCartEntityNew = shoppingCartDao.selectByMemberSkuId(shoppingCartEntityDtoNew);
		if (shoppingCartEntityNew != null) {
			if (shoppingCartEntityNew.getId().equals(shoppingCartEntityOld.getId())) {
				i = shoppingCartDao.updateById(shoppingCartEntityNew);
			} else {
				shoppingCartEntityNew
						.setGoodsNum(shoppingCartEntityNew.getGoodsNum() + shoppingCartEntityOld.getGoodsNum());
				i = shoppingCartDao.updateById(shoppingCartEntityNew);
				shoppingCartDao.deleteGoodsCartById(shoppingCartEntityOld.getId());
			}

		} else {
			if (shoppingCartEntityOld != null) {
				shoppingCartEntityOld.setGoodsId(newSkuId);
				i = shoppingCartDao.updateById(shoppingCartEntityOld);
			}
		}
		return i;
	}

	@Override
	public Integer addsaveGoodsFootprint(Long memberId, Long[] itemIds) throws GlobalException {
		Integer i = null;
		if (itemIds == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSFAVOR_GOODSID_NOT_NULL);
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if (memberEntity == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_MEMBER_NOT_NULL);
		}
		List<GoodsFootprintEntity> goodsFootprintEntities = goodsFootprintDao.selectGoodsFootprintByMemberId(memberId);
		if (goodsFootprintEntities.size() == 3) {
			for (GoodsFootprintEntity goodsFootprintEntity : goodsFootprintEntities) {
				i = goodsFootprintDao.deleteGoodsFootprints(goodsFootprintEntity.getId());
			}

		}
		for (Long itemId : itemIds) {
			ItemEntity itemEntity = itemDao.selectById(itemId);
			if (itemEntity == null) {
				throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_GOODSATTR_NOT_NULL);
			}
			GoodsFootprintEntity goodsFootprintEntity = goodsFootprintDao
					.selectGoodsFootprintByGoodsIdAndMemberId(memberId, itemId);
			if (goodsFootprintEntity == null) {
				GoodsFootprintEntity goodsFootprintEntity1 = new GoodsFootprintEntity();
				goodsFootprintEntity1.setGoodsId(itemId);
				goodsFootprintEntity1.setMemberId(memberId);
				goodsFootprintEntity1.setCreatedTime(new Date());
				goodsFootprintEntity1.setCreatorId(memberId);
				i = goodsFootprintDao.insert(goodsFootprintEntity1);
			}
		}
		return i;
	}

	@Override
	public GoodsFavoriteDto findGoodsFavorite(Long memberId, Long itemId) {
		GoodsFavoriteDto favoriteDto = goodsFavoriteDao.selectGoodsFavoriteByMemberIdAndItemId(memberId, itemId);
		return favoriteDto;
	}

}
