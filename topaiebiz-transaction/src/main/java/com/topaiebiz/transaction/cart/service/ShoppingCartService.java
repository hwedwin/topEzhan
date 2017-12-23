package com.topaiebiz.transaction.cart.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.transaction.cart.dto.CardStoreListDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartEntityDto;
import com.topaiebiz.transaction.cart.entity.ShoppingCartEntity;
import com.topaiebiz.transaction.cart.dto.GoodsCartNextDto;
import com.topaiebiz.transaction.cart.dto.GoodsFavoriteDto;
import com.topaiebiz.transaction.cart.dto.ModifyShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartAppDto;

/**
 * 
 * Description 购物车接口
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月8日 上午10:19:16
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface ShoppingCartService {

	/**
	 * 
	 * Description sku最小单元商品添加到购物车
	 * 
	 * Author zhushuyong
	 * 
	 * @param productCart
	 * @return
	 * @throws GlobalException
	 */
	Integer saveGoodsCart(ShoppingCartEntityDto shoppingCartEntityDto) throws GlobalException;

	/**
	 * 
	 * Description： sku最小单元商品添加到收藏夹
	 * 
	 * Author zhushuyong
	 * 
	 * @return
	 * @throws GlobalException
	 */
	Integer addGoodsFavorite(Long memberId, Long[] itemIds) throws GlobalException;

	/**
	 * 
	 * Description 根据会员id，sku最小单元商品id，查询当前会员购物车中是否存在此商品
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartEntityDto
	 * @return
	 */
	ShoppingCartEntity findBySkuId(ShoppingCartEntityDto shoppingCartEntityDto);

	/**
	 * 
	 * Description 在购物车中修改对应商品的数量
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartEntityDto
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyGoodsCartNum(ModifyShoppingCartDto modifyShoppingCartDto) throws GlobalException;

	/**
	 * 
	 * Description 修改对应的购物车中的数量
	 * 
	 * Author zhushuyong
	 * 
	 * @param goodsNum
	 *            需要修改的数量值
	 * @param memberId
	 *            传入的会员id
	 * @param goodsId
	 *            传入的商品sku的id
	 * @return
	 */
	Integer modifyGoodsNum(Long goodsNum, Long memberId, Long goodsId);

	/**
	 * 
	 * Description 根据会员id查询购物车中的列表详细信息
	 * 
	 * Author zhushuyong
	 * 
	 * @param productCartDto
	 * @return
	 */
	List<ShoppingCartDto> queryProductCart(Long belongStore, Long memberId);

	/**
	 * 
	 * Description 根据id删除购物车中指定的商品
	 * 
	 * Author zhushuyong
	 * 
	 * @param ids
	 * @return
	 * @throws GlobalException
	 */
	Integer removeGoodsCart(Long[] id) throws GlobalException;

	/**
	 * 
	 * Description 查询购物车
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartDto
	 * @return
	 */
	List<CardStoreListDto> queryStoreCart(ShoppingCartDto shoppingCartDto);

	/**
	 * 
	 * Description TODO (取出会员下所有的美礼卡)
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartDto,此对象一般是查询出来的，包含(memberId:传入的是会员id,belongStore:店铺id
	 *            belongBrand:品牌id)
	 * @return
	 */
	Double queryMemberCardDto(ShoppingCartDto shoppingCartDto);

	/**
	 * 
	 * Description 经过统计的购物车
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartDto
	 * @return
	 */
	GoodsCartNextDto shoppingCartStatistics(ShoppingCartDto shoppingCartDto);

	/**
	 * 
	 * Description 根据运费模板id查询出运费模板的Dto指定数据
	 * 
	 * Author zhushuyong
	 * 
	 * @param infoId
	 *            传入的运费模板id
	 * @param productNum
	 *            传入的购物车中商品对应的加入数量
	 * @param district
	 *            会员的收货地址区域
	 * @return
	 */
	String queryStoreFreightInfoDto(Long infoId, Long productNum, String district);

	/**
	 * Description app端根据会员id查询购物车中的列表详细信息
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 * @param storeId
	 * @return
	 */
	ShoppingCartAppDto getShoppingCartByMemberId(Long memberId, Long storeId);

	/**
	 * Description app端收藏夹列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 * 
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	Page<ItemDto> getGoodsFavoriteListByMemberId(Page<ItemDto> page, Long memberId);

	/**
	 * Description app端删除收藏夹
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	Integer removelGoodsFavorite(Long[] id) throws GlobalException;

	/**
	 * Description app端将商品分享
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 * @param itemIds
	 * @return
	 * @throws GlobalException
	 */
	Integer saveGoodssharing(Long memberId, Long[] itemIds) throws GlobalException;

	/**
	 * Description app端购物车中的编辑中的确定
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param oldSkuId
	 *            旧的skuId
	 * @param newSkuId
	 *            新的skuId
	 * @return
	 * @throws GlobalException
	 */
	Integer modfiyAppShoppingCart(Long memberId, Long oldSkuId, Long newSkuId);

	/**
	 * Description app端我的足迹添加
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @param itemIds
	 *            商品id
	 * @return
	 */
	Integer addsaveGoodsFootprint(Long memberId, Long[] itemIds) throws GlobalException;

	/**
	 * Description 我的足迹列表
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
	Page<ItemDto> getGoodsFootprintListByMemberId(Page<ItemDto> page, ItemDto itemDto, Long memberId);

	/**
	 * Description 删除足迹
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @return
	 */
	Integer removelGoodsFootprint(Long[] id);

	/**
	 * Description 查询此商品是否被选为收藏
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @param itemId
	 *            商品id
	 * @return
	 */
	GoodsFavoriteDto findGoodsFavorite(Long memberId, Long itemId);

}
