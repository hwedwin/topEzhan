package com.topaiebiz.goods.sku.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dto.ItemAppDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.ItemSellDto;
import com.topaiebiz.goods.sku.dto.PromoTionGoodsDto;
import com.topaiebiz.goods.sku.dto.StoreDistributionGoodsSkuDto;
import com.topaiebiz.goods.sku.entity.FreightTempleteDetailSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;

/**
 * Description 商品信息dao
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午7:11:01
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface ItemDao extends BaseDao<ItemEntity> {

	/**
	 * Description 商品信息列表分页检索出售中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	List<ItemDto> selectListItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商品信息列表分页检索仓库中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	List<ItemDto> selectListStoreItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 逻辑删除商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	Integer deleteItem(Long[] id);

	/**
	 * Description 对商品批量上架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	Integer updatePutItem(Long[] id);

	/**
	 * Description 对商品批量下架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	Integer updateOutItem(Long[] id);

	/**
	 * Description 根据商品item编号查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param itemCode
	 *            商品item编号
	 * @return
	 */
	ItemEntity selectItemByItemCode(ItemEntity item);

	/**
	 * Description 根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	ItemDto selectItemById(Long id);

	/**
	 * Description 根据id和编号查询商品
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品dto
	 * @return
	 */
	ItemEntity selectItemByItemCodeAndId(ItemDto itemDto);

	/**
	 * Description 商品item冻结
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	Integer freezeItem(Long id);

	/**
	 * Description 商家商品信息列表分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	List<ItemDto> selectListMerchantItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 营销商品信息列表分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	List<ItemDto> selectListMarketItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	ItemDto selectItemByItemId(Long long1);

	/**
	 * Description 根据skuId查询商品名称
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	ItemDto selectItemNameBySkuId(Long skuId);

	/**
	 * Description 根据类目id查询是否有有item商品
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	List<ItemEntity> selectItemByBelongCategory(Long id);

	/**
	 * Description 根据商品id查询商品销售数量
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	Long selectSalesVolomeById(Long id);

	/**
	 * Description 商家根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	ItemDto selectMerchantItemById(Long id);

	/**
	 * Description app端根据年龄段和商家查询商品
	 * 
	 * Author Hedda
	 * 
	 * @param ageId
	 *            年龄段id
	 * @param belongStore
	 *            商家id
	 * @return
	 */
	List<ItemDto> selectItemListByItem(ItemDto itemDto);

	/**
	 * Description 根据商品id查询商品库存
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	Long selecStockNumbertById(Long id);

	/**
	 * Description 根据会员id查询购物车信息
	 * 
	 * Author Hedda
	 * 
	 * @param goodsId
	 * @return
	 */
	List<ItemDto> selectShoppingCartByMemberId(Long goodsId);

	/**
	 * Description 根据会员id查询收藏夹商品
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	List<ItemDto> selectGoodsFavoriteListByMemberId(Page<ItemDto> page, Long memberId);

	/**
	 * Description app端根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @return
	 */
	ItemAppDto selectAppItemById(Long id);

	/**
	 * Description 商家端分销商品管理平台商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	List<ItemDto> selectDistributionMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 根据店铺id查询店铺名称
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 * @return
	 */
	String selectStoreNameById(Long belongStore);

	/**
	 * Description 平台统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 * @param itemSellDto
	 * @return
	 */
	List<ItemSellDto> selectSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 根据商品goodsId查询销售数量
	 * 
	 * Author Hedda
	 * 
	 * @param goodsId
	 *            商品skuId
	 * @return
	 */
	List<ItemSellDto> selectSaleNumber(Long goodsId);

	/**
	 * Description 根据商品goodsId查询购买人数
	 * 
	 * Author Hedda
	 * 
	 * @param goodsId
	 *            商品skuId
	 * @return
	 */
	Integer selectPaymentPeoples(Long goodsId);

	/**
	 * Description 平台统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 * @param itemSellDto
	 * @return
	 */
	List<ItemSellDto> selectMerchantSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 装修时查询商家商品和分销平台的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品dto
	 * @return
	 */
	List<ItemDto> selectItemAndDisList(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商家端商品信息中的平台池商品管理列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	List<ItemDto> selectStoreDistributionGoods(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 平台端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param itemSellDto
	 * @return
	 */
	List<ItemSellDto> selectSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 商家端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 * @param itemSellDto
	 * @return
	 */
	List<ItemSellDto> selectStoreSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description： 根据商品ID主键集合 查询商品
	 * 
	 * Author hxpeng
	 * 
	 * @param ids
	 * @return
	 */
	List<ItemDto> selectItemsByIds(ItemDto itemDto);

	/**
	 * Description 根据id查询商品图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @return
	 */
	List<ItemPictureEntity> selectPictureNameById(Long id);

	/**
	 * Description app端根据item的id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            item的id
	 * @return
	 */
	ItemEntity selectAppItemByItemId(Long id);

	/**
	 * Description 根据运费模板id查询运费模板详情
	 * 
	 * Author Hedda
	 * 
	 * @param logisticsId
	 *            运费模板id
	 * @return
	 */
	List<FreightTempleteDetailSkuEntity> selectOneByFreightId(Long logisticsId);

	/**
	 * Description 根据会员id查询足迹表
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 * @param page
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	List<ItemDto> selectGoodsFootprintListByMemberId(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 根据商品id和店铺id查询商品的单品活动
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品id
	 * @param belongStore
	 *            店铺id
	 * @return
	 */
	List<PromoTionGoodsDto> selectPromoTionBySkuId(@Param("skuId") Long skuId, @Param("storeId") Long storeId);

	/**
	 * Description 根据商品id查询营销活动
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @param long1
	 * @return
	 */
	List<PromoTionGoodsDto> selectPromotionByItemId(Long id);

	/**
	 * Description 根据skuId查询商品是否为秒杀
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	List<PromoTionGoodsDto> selectPromotionDtoBySkuId(Long skuId);

	/**
	 * Description 通过营销id和skuId查询商品销售数量
	 * 
	 * Author Hedda
	 * 
	 * @param promotionId
	 *            营销id
	 * @param skuId
	 *            skuId
	 * @return
	 */
	Long selectKillSales(@Param("promotionId") Long promotionId, @Param("skuId") Long skuId);

	/**
	 * Description 根据商品id和会员id查询收藏商品
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @param memberId
	 *            会员id
	 * @return
	 */
	Long selectFavoriteByMemberAndGoodsId(@Param("id") Long id, @Param("memberId") Long memberId);

	/**
	 * Description 根据该商品查询对应的包邮活动
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	List<PromoTionGoodsDto> selectStorePinkagePromoTionGoodsDtos(Long skuId);

	/**
	 * Description 店铺优惠券
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            店铺id
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	List<PromoTionGoodsDto> selectStoreCouponPromoTionGoodsDtos(@Param("storeId") Long storeId,
			@Param("skuId") Long skuId);

	/**
	 * Description 平台优惠券
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	List<PromoTionGoodsDto> selectAdminCouponPromoTionGoodsDtos(Long skuId);

	/**
	 * Description 店铺级活动（满减）
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            店铺id
	 * @param skuId
	 *            商品skuId
	 * @return
	 */
	List<PromoTionGoodsDto> selectStoreMoneyOffPromoTionGoodsDtos(@Param("storeId") Long storeId,
			@Param("skuId") Long skuId);

	/**
	 * Description 平台级活动（满减）
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * 
	 * @return
	 */
	List<PromoTionGoodsDto> selectAdminMoneyOffPromoTionGoodsDtos(Long skuId);

	/**
	 * Description 查询下架的商品是否有分销的商品
	 * 
	 * Author Hedda
	 * 
	 * @param itemId
	 *            商品id
	 * @return
	 */
	List<StoreDistributionGoodsSkuDto> selectStoreDistributionGoodsDto(Long itemId);

	/**
	 * Description 删除分销商品
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            分销商品id
	 * @return
	 */
	Integer deleteStoreDistributionGoodsSkuDtos(Long id);

}
