package com.topaiebiz.promotion.mgmt.service;

import java.text.ParseException;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.promotion.mgmt.dto.CommodityMarketingDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEnrolDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEntryDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionSingleDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.system.moble.security.dto.TokenDto;

/**
 * 
 * Description： 营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午1:56:09
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface PromotionService {

	/**
	 * 
	 * Description 平台优惠码
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	Page<PromotionDto> getPromotionListCouponCode(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 平台优惠券
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	Page<PromotionDto> getPromotionListCoupon(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 获取单品活动列表（秒杀、单品折扣、一口价）
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	Page<PromotionDto> getPromotionListSingle(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description：创建营销活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 */
	Long savePromotionCoupon(PromotionEntity promotion);

	/**
	 * 
	 * Description：删除营销活动
	 * 
	 * Author Joe
	 * 
	 * @param ids
	 * @return
	 */
	Integer removePromotion(String ids);

	/**
	 * 
	 * Description： 修改平台优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @return
	 * @throws GlobalException
	 */
	Long modifyPromotionCoupon(PromotionDto promotionDto);

	/**
	 * 
	 * Description：查询全部活动
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	Page<PromotionDto> getPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 添加平台优惠码
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 */
	Long savePromotionCouponCode(PromotionEntity promotion);

	/**
	 * 
	 * Description 停止平台优惠活动
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	Integer modifyStopPromotion(Long id);

	/**
	 * 
	 * Description 添加秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	Long savePromotionSeckill(PromotionEntity promotion);

	/**
	 * 
	 * Description 添加秒杀活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 */
	void modifyPromotionSeckillGoods(List<PromotionGoodsDto> promotionGoodsDtoList) throws GlobalException;

	/**
	 * 
	 * Description 保存/发布秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void savePromotionSeckillGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 修改营销活动回显
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	PromotionDto findPromotionById(Long id);

	/**
	 * 
	 * Description 获取商品列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * 
	 * @param itemDto
	 * @return
	 */
	Page<ItemDto> getGoodsList(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * 
	 * Description 获取平台商品列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	Page<ItemDto> getPlatformGoodsList(Page<ItemDto> page, ItemDto itemDto);

	/**
	 * 
	 * Description 查询商品对应营销活动
	 * 
	 * Author Joe
	 * 
	 * @param tokenDetail
	 * 
	 * @return
	 */
	List<CommodityMarketingDto> getCommodityMarketing(TokenDto tokenDetail);

	/**
	 * 
	 * Description 根据商品查询所能使用的营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	List<PromotionDto> findPromotionByGoods(List<PromotionGoodsDto> goodsSkuDtoList, Long memberId);

	/**
	 * 
	 * Description 根据会员编号与活动ID查询该会员是否可用该活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	boolean isActivityAvailable(Long memberrId, Long promotionId, Long goodsSkuId);

	/**
	 * 
	 * Description 发布活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	void releasePromotion(Long id, Long typeId);

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
	Page<PromotionDto> getPromotionListReducePrice(Page<PromotionDto> page, PromotionDto promotionDto);

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
	Page<PromotionDto> getPromotionListStoreCoupon(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 添加单品折扣
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	Long addPromotionSingle(PromotionEntity promotion);

	/**
	 * 
	 * Description 修改单品级活动(单品折扣,一口价,秒杀)
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 */
	Long modifyPromotionSingle(PromotionSingleDto promotionSingleDto);

	/**
	 * 
	 * Description 修改单品折扣商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void modifyPromotionSingleGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 保存/发布单品折扣活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void savePromotionSingle(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 添加一口价
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	Long savePromotionPrice(PromotionEntity promotion);

	/**
	 * 
	 * Description 添加/修改一口价商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void modifyPromotionPriceGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 发布/保存一口价活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void savePromotionPriceGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 添加满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @return
	 */
	Long savePromotionReducePrice(PromotionEntity promotion);

	/**
	 * 
	 * Description 修改满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 */
	Long modifyPromotionReducePrice(PromotionDto promotionDto);

	/**
	 * 
	 * Description 添加/修改活动商品(满减,包邮,店铺优惠券,平台优惠券,平台优惠码)
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void modifyPromotionGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 修改平台优惠码活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	Long modifyPromotionCouponCode(PromotionDto promotionDto) throws GlobalException;

	/**
	 * 
	 * Description 添加店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 */
	Long savePromotionStoreCoupon(PromotionEntity promotion);

	/**
	 * 
	 * Description 修改店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @return
	 */
	Long modifyPromotionStoreCoupon(PromotionDto promotionDto);

	/**
	 * 
	 * Description 根据商品集合与会员编号查询单品活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	List<CommodityMarketingDto> getSinglePromotionList(Long storeId, Long memberId, List<Long> goodsSkuDtoList);

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
	Page<PromotionDto> getPromotionListFreeShipping(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 添加包邮活动
	 * 
	 * Author Joe
	 * 
	 * @param promotion
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	Long savePromotionFreeShipping(PromotionEntity promotion);

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
	Long modifyPromotionFreeShipping(PromotionSingleDto promotionSingleDto);

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
	boolean isActivityAvailablePlatform(Long memberId, Long promotionId);

	/**
	 * 
	 * Description 活动选择保存商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 */
	void savePromotionGoods(List<PromotionGoodsDto> promotionGoodsList);

	/**
	 * 
	 * Description 获取营销活动所选商品
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	Page<PromotionGoodsDto> getPromotionGoods(Page<PromotionGoodsDto> page, ItemDto itemDto);

	/**
	 * 
	 * Description 发布/保存活动(满减,包邮,店铺优惠券,平台优惠券)
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void savePromotion(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 保存/发布平台优惠码活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @throws GlobalException
	 */
	void savePromotionCouponCodeGoods(List<PromotionGoodsDto> promotionGoodsList) throws GlobalException;

	/**
	 * 
	 * Description 查询秒杀活动时间集合
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	List<PromotionDto> getSeckillStartTimeList();

	/**
	 * 
	 * Description 修改秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @return
	 */
	Long modifyPromotionSeckill(PromotionSingleDto promotionSingleDto);

	/**
	 * 
	 * Description 取消活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 */
	void removeGoods(Long promotionId, Long itemId);

	/**
	 * 
	 * Description 优惠券获取所选商品
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param itemDto
	 * @param token
	 * @return
	 */
	Page<PromotionGoodsDto> getPromotionCouponGoods(Page<PromotionGoodsDto> page, ItemDto itemDto, String token);

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
	List<PromotionGoodsDto> getPromotionGoodsByPromotionIdAndItemid(Long promotionId, Long itemId);

	/**
	 * 
	 * Description 定时任务（时间间隔：1小时）
	 * 
	 * Author Joe
	 *
	 */
	void timingTask();

	/**
	 * 
	 * Description C端查询店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param tokenDetail
	 * @param itemId
	 * @return
	 */
	List<PromotionDto> getPromotionStoreCouponListByStoreId(TokenDto tokenDetail, Long itemId);

	/**
	 * 
	 * Description 会员领取优惠券修改领取数量
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 */
	void modifyPromotionUsedAmountById(Long promotionId,Long memberId);

	/**
	 * 
	 * Description： 获取会员优惠券
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberCouponDto
	 * @throws GlobalException
	 */
	void saveMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException;

	/**
	 * 
	 * Description 活动商品验证
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsDto
	 * @throws GlobalException
	 */
	void PromotionGoodsException(PromotionGoodsDto promotionGoodsDto) throws GlobalException;

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
	Page<PromotionDto> getPlatformEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) throws ParseException;

	/**
	 * 
	 * Description 发起报名 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionEnrolDto
	 * @throws ParseException 
	 */
	void modifyInitiateEnrol(PromotionEnrolDto promotionEnrolDto) throws ParseException;

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
	Page<PromotionDto> getEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

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
	Page<PromotionEntryDto> getPromotionEnrolStoreList(Page<PromotionEntryDto> page, PromotionEntryDto promotionEntryDto) throws ParseException;

	/**
	 * 
	 * Description 报名活动商家不通过
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 */
	void modifyStoreAuditNonconformity(Long id);

	/**
	 * 
	 * Description 查看店铺报名详情
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	PromotionEntryDto getPromotionEnrolStore(Long id);

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
	Page<PromotionDto> selectStoreAllPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

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
	Page<PromotionDto> getStoreEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

}
