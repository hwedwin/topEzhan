package com.topaiebiz.transaction.cart.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.cart.dto.CardStoreListDto;
import com.topaiebiz.transaction.cart.dto.MemberCardDto;
import com.topaiebiz.transaction.cart.dto.ModifyShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartEntityDto;
import com.topaiebiz.transaction.cart.dto.StoreFreightInfoDto;
import com.topaiebiz.transaction.cart.entity.ShoppingCartEntity;

/**
 * 
 * Description 购物车的数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月7日 下午9:55:55 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface ShoppingCartDao extends BaseDao<ShoppingCartEntity> {
	
	/**
	 * 
	 * Description 根据会员id，sku最小单元商品id，查询当前会员购物车中是否存在此商品  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param productCart
	 * @return
	 */
	ShoppingCartEntity selectByMemberSkuId(ShoppingCartEntityDto shoppingCartEntityDto);
	
	/**
	 * 
	 * Description 在购物车中修改对应商品的数量  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param shoppingCartEntityDto
	 * @return
	 */
	Integer updateGoodsCartNum(ModifyShoppingCartDto modifyShoppingCartDto);
	
	/**
	 * 
	 * Description 修改对应的购物车中的数量   
	 * 
	 * Author zhushuyong   
	 * 
	 * @param goodsNum 
	 * 				需要修改的数量值
	 * @param memberId 
	 * 				传入的会员id
	 * @param goodsId 
	 * 				传入的商品sku的id
	 * @return
	 */
	Integer updateGoodsNum(@Param("goodsNum")Long goodsNum,
			@Param("memberId")Long memberId,@Param("goodsId")Long goodsId);
	
	/**
	 * 
	 * Description 根据会员id和店铺id查询购物车中的列表详细信息 
	 * 
	 * Author zhushuyong   
	 * 
	 * @param productCartDto
	 * @return
	 */
	List<ShoppingCartDto> selectGoodsCart(@Param("belongStore")Long belongStore,
			@Param("memberId")Long memberId);
	
	/**
	 * 
	 * Description 根据id删除购物车中指定的商品   
	 * 
	 * Author zhushuyong   
	 * 
	 * @param ids
	 * @return
	 */
	Integer deleteGoodsCart(Long[] id);
	
	/**
	 * 
	 * Description 查询购物车中有几个店铺  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param shoppingCartDto
	 * @return
	 */
	List<CardStoreListDto> selectStore(ShoppingCartDto shoppingCartDto);
	
	/**
	 * 
	 * Description (取出会员下所有的美礼卡)  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param shoppingCartDto
	 * @return
	 */
	List<MemberCardDto> selectMemberCardDto(ShoppingCartDto shoppingCartDto);
	
	/**
	 * 
	 * Description TODO 根据运费模板id查询出运费模板的Dto指定数据  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param storeFreightInfoDto
	 * @return
	 */
	List<StoreFreightInfoDto> selectStoreFreightInfoDto(@Param("infoId")Long infoId);

	/**
	 * 
	 * Description 根据会员编号获取购物车中的有效商品编号  
	 * 
	 * Author Administrator   
	 * 
	 * @param creatorId
	 * @return
	 */
	List<ShoppingCartDto> getGoodsIdList(@Param("memberId")Long memberId);

	/**
	 * Description app端根据会员id和商品id查询购物车商品  
	 * 
	 * Author Hedda  
	 * 
	 * @param memberId
	 * @param itemId
	 * @return
	 */
	List<ShoppingCartEntity> selectShoppingCartByItemIds(@Param("memberId")Long memberId,@Param("itemId")Long itemId);

	/**
	 * Description 根据id删除购物车中指定的商品   
	 * 
	 * Author Hedda  
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteGoodsCartById(Long id);

	/**
	 *
	 * Description 获取创建订单购物车中被购买的商品
	 *
	 * Author hxpeng
	 *
	 * @param memberId
	 * @param goodId
	 * @return
	 */
	ShoppingCartEntity getByMemberIdAndSkuId(@Param(value = "memberId")Long memberId, @Param(value = "goodsId")Long goodsId);

}
