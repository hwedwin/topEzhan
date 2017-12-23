package com.topaiebiz.distribution.merchant.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionLogDto;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionEntity;

/**
 * Description： 商品分销接口。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:11:41
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface GoodsDistributionAllocationService {

	/**
	 * Description： 添加商品分销。
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * 
	 * @param goodsDistributionAllocationDtos
	 *            商品分销信息
	 * @return
	 * @throws GlobalException
	 */
	Integer saveGoodsDistributionAllocation(Long[] itemId,
			List<GoodsDistributionEntity> goodsDistributionAllocationEntitis) throws GlobalException;

	/**
	 * Description： 平台店铺分销记录分页列表加搜索
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto。
	 * @return
	 */
	Page<GoodsDistributionLogDto> getPlatformGoodsDistributionList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description： 定时计算店铺分润记录总金额/分润金额。
	 * 
	 * Author Harry
	 * 
	 * @param goodsDistributionLogEntitis
	 * @return
	 * @throws GlobalException
	 */
	Integer saveGoodsDistributionLog() throws GlobalException;

	/**
	 * Description： 商家店铺分销记录分页列表加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto。
	 * @return
	 */
	Page<GoodsDistributionLogDto> getMerchantGoodsDistributionLogList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description： 通过skuId查询会员购买的分销记录。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param goodsDistributionLogDto
	 * @return
	 */
	Page<GoodsDistributionLogDto> findMemberDistributionListByskuId(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto);

	/**
	 * Description：根据itemId商品分销比例。
	 * 
	 * Author Harry
	 * 
	 * @param itemId
	 * @return
	 * @throws GlobalException
	 */
	List<GoodsDistributionDto> findGoodsDistributionAllocationByItemId(Long itemId) throws GlobalException;

	/**
	 * Description：通过StoreID查看会员记录分销金额列表加分页。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	Page<MemberDistributionLogDto> findMemberDistributionListByStoreId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：通过会员id查看会员购买的商品列表。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	Page<MemberDistributionLogDto> findGoodsMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description： 商家端选售商品
	 * 
	 * Author Harry
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	Integer saveStoreDistributionGoods(Long[] id) throws GlobalException;

	/**
	 * Description：商家端取消选售商品  
	 * 
	 * Author Hedda  
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException 
	 */
	Integer removeStoreDistributionGoods(Long id) throws GlobalException;

}
