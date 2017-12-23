package com.topaiebiz.distribution.merchant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionLogDto;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionLogEntity;

/**
 * Description： 商品分润记录Dao。
 * 
 * Author Harry
 * 
 * Date 2017年10月7日 上午9:27:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface GoodsDistributionLogDao extends BaseDao<GoodsDistributionLogEntity> {

	/**
	 * Description： 通过StoreID查看商拼列表加分页
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto。
	 * @return
	 */
	List<GoodsDistributionLogDto> findGoodsDistributionListByStoreId(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description： 平台店铺分销记录分页列表加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto。
	 * @return
	 */
	List<GoodsDistributionLogDto> getPlatformGoodsDistributionList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description： 查看购买人数
	 * 
	 * Author Harry
	 * 
	 * @param skuId  商品SkuId
	 * @return
	 */
	Integer findGoodsPeopleBuy(Long skuId);

	/**
	 * Description： 商家店铺分销记录分页列表加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page  分页单位
	 * @param goodsDistributionLogDto   店铺分销记录Dto
	 * @return
	 */
	List<GoodsDistributionLogDto> getMerchantGoodsDistributionLogList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description：通过skuId查询会员购买的分销记录。
	 * 
	 * Author Harry
	 * 
	 * @param page  分页单位
	 * @param goodsDistributionLogDto    店铺分销记录Dto
	 * @return
	 */
	List<GoodsDistributionLogDto> findMemberDistributionListByskuId(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description： 根据商品id查询商家的分销金额
	 * 
	 * Author Harry
	 * 
	 * @param storeId  店铺id
	 * @return
	 */
	List<GoodsDistributionLogEntity> selectGoodsDistributionLogByStoreId(Long storeId);
	
	/**
	 * Description：根据店铺id,skuId查询分销平台商品的每一个分销金额。 
	 * 
	 * Author Harry 
	 * 
	 * @param storeId  店铺id
	 * @param skuId   商品skuid
	 * @return
	 */
	Double getStoreDisPriceByStoreAndSkuId(@Param("storeId")Long storeId, @Param("skuId")Long skuId);
	
	/**
	 * Description： 根据店铺Id,skuId查询分销平台商品的每一个分销比例  
	 * 
	 * Author Harry 
	 * 
	 * @param storeId  店铺id
	 * @param skuId    商品skuId
	 * @return
	 */
	Double getStoreDisRatioByStoreAndSkuId(@Param("storeId")Long storeId, @Param("skuId")Long skuId);

}
