package com.topaiebiz.promotion.mgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;

/**
 * 
 * Description： 营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午2:03:19
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface PromotionDao extends BaseDao<PromotionEntity> {

	/**
	 * 
	 * Description：营销活动列表
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @return
	 */
	List<PromotionDto> selectPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 根据商品sku编号查询所属营销活动
	 * 
	 * Author Joe
	 * 
	 * @param goodsSkuId
	 * @return
	 */
	List<PromotionDto> findPromotionByGoodsSkuId(@Param("goodsSkuId") Long goodsSkuId);

	/**
	 * 
	 * Description 查询最接近该时间的数据
	 * 
	 * Author Joe
	 * 
	 * @param startDate
	 * @return
	 */
	List<PromotionDto> selectPromotionHomePageSeckill();

	/**
	 * 
	 * Description 查询秒杀活动时间集合
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	List<PromotionDto> selectSeckillStartTimeList();

	/**
	 * 
	 * Description 定时任务活动开始（时间间隔：1小时）
	 * 
	 * Author Joe
	 *
	 */
	List<PromotionDto> selectPromotionByDate();

	/**
	 * 
	 * Description C端查询店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param storeId
	 * @return
	 */
	List<PromotionDto> selectPromotionStoreCouponListByStoreId(Long storeId);

	/**
	 * 
	 * Description 定时任务活动结束（时间间隔：1小时）
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	List<PromotionDto> selectPromotionEnd();

	/**
	 * 
	 * Description 平台报名活动列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	List<PromotionDto> selectPlatformEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

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
	List<PromotionDto> selectEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

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
	List<PromotionDto> selectStoreAllPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

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
	List<PromotionDto> selectStoreEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto);

	/**
	 * 
	 * Description 定时任务报名开始（时间间隔：1小时）
	 * 
	 * Author Joe   
	 * 
	 * @return
	 */
	List<PromotionDto> selectPromotionApplyStart();

	/**
	 * 
	 * Description 定时任务报名结束（时间间隔：1小时）
	 * 
	 * Author Joe   
	 * 
	 * @return
	 */
	List<PromotionDto> selectPromotionApplyEnd();
	

}
