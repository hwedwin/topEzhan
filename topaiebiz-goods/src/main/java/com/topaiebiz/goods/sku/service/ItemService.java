package com.topaiebiz.goods.sku.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.dto.ItemAppDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.ItemPictureDto;
import com.topaiebiz.goods.sku.dto.ItemSellDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;

/**
 * Description 商品sku接口
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午7:05:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public interface ItemService {

	/**
	 * Description 平台商品信息列表分页检索出售中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	Page<ItemDto> getListItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 平台商品信息列表分页检索仓库中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	Page<ItemDto> getListStoreItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商品item批量逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	Integer removeItems(Long[] id) throws GlobalException;

	/**
	 * Description 商品item批量上架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	Integer putItems(Long[] id) throws GlobalException;

	/**
	 * Description 商品item批量下架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	Integer outItems(Long[] id) throws GlobalException;

	/**
	 * Description 平台商品item信息添加
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	Integer saveItem(ItemEntity item, List<ItemPictureEntity> itemPictureEntities,
			List<GoodsSkuEntity> goodsSkuEntities) throws GlobalException;

	/**
	 * Description 商品item信息修改
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyItem(ItemDto itemDto, List<ItemPictureDto> itemPictureDtos, List<GoodsSkuDto> goodsSkuDtos)
			throws GlobalException;

	/**
	 * Description 根据id查询item
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	ItemDto findItemById(Long id) throws GlobalException;

	/**
	 * Description 根据id查询 商品属性
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	List<GoodsSkuDto> findGoodsSkuById(Long id) throws GlobalException;

	/**
	 * Description 根据id查询商品图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	List<ItemPictureDto> findItemPictureById(Long id) throws GlobalException;

	/**
	 * Description 商品item冻结
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	Integer freezeItem(Long id) throws GlobalException;

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
	Page<ItemDto> getListMerchantItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 平台查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategorysDto> getItemRecentlyCategoryList();

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
	Page<ItemDto> getListMarketItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 根据商品id和销售数量计算商品剩余库存
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品sku的id
	 * @param salesNumber
	 *            商品销售数量
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyGoodsSkuStockNumber(Long skuId, Long salesNumber) throws GlobalException;

	/**
	 * Description 根据商品id和销售数量计算商品库存
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品sku的id
	 * @param salesNumber
	 *            商品销售数量
	 * @return
	 * @throws GlobalException
	 */
	Integer addGoodsSkuStockNumber(Long memberId, Long skuId, Long salesNumber) throws GlobalException;

	/**
	 * Description 商家商品信息列表分页检索出售中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	Page<ItemDto> getMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商家商品信息列表分页检索仓库中的商品
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
	Page<ItemDto> getMerchantListStoreItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商家商品item信息添加
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	Integer saveMerchantItem(ItemEntity item, List<ItemPictureEntity> itemPictureEntities,
			List<GoodsSkuEntity> goodsSkuEntities) throws GlobalException;

	/**
	 * Description 商家查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategorysDto> getMerchantItemRecentlyCategoryList();

	/**
	 * Description 商家根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	ItemDto findMerchantItemById(Long id) throws GlobalException;

	/**
	 * Description app端根据年龄段,品牌，分类商家查询商品
	 * 
	 * Author Hedda
	 * 
	 * @param ageId
	 *            年龄段id
	 * @param belongStore
	 *            商家id
	 * @return
	 * @throws GlobalException
	 */
	Page<ItemDto> getItemListByItem(Page<ItemDto> page, ItemDto itemDto) throws GlobalException;

	/**
	 * Description app根据商品id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	ItemAppDto findAppItemById(String token,Long id) throws GlobalException;

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
	 * @throws GlobalException
	 */
	Page<ItemDto> getDistributionMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 根据itemId查询商品sku信息以及营销的信息
	 * 
	 * Author Hedda
	 * @param promotionId 
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	List<GoodsSkuDto> findPromotionByItemId(Long itemId, Long promotionId) throws GlobalException;

	/**
	 * Description 平台统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 */
	Page<ItemSellDto> getSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 商家统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 */
	Page<ItemSellDto> getMerchantSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 平台端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 */
	Page<ItemSellDto> getSellGoodsCategoryList(Page<ItemSellDto> page,ItemSellDto itemSellDto);

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
	Page<ItemDto> getItemAndDisList(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商家端商品信息中的平台池管理列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 */
	Page<ItemDto> getStoreDistributionGoods(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * Description 商家端端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 */
	Page<ItemSellDto> getStoreSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto);

	/**
	 * Description 修改商品item的类目
	 * 
	 * Author Hedda
	 * 
	 * @param itemId
	 *            商品的id
	 * @param belongCategory
	 *            商品后台第三级类目id
	 * @return
	 */
	Integer modifyItemCategory(Long itemId, Long belongCategory)throws GlobalException;

	/**
	 * Description 根据商品id查询对应商品评价  
	 * 
	 * Author Hedda  
	 * 
	 * @param id 商品id
	 * @return
	 */
	List<GoodsSkuCommentDto> findAppSkuCommentById(Long id,Integer type);

}
