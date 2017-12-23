package com.topaiebiz.transaction.order.total.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.total.dto.PlatformPayOrderLogDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderPriceRangeDto;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;

/**
 * 
 * Description 平台订单的数据访问层
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月5日 上午11:25:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface TotalOrderDao extends BaseDao<TotalOrderEntity> {

	/**
	 * 
	 * Description 平台订单分页，查询列表
	 * 
	 * Author zhushuyong
	 * 
	 * @param platformPayOrderLog
	 * @return
	 */
	List<PlatformPayOrderLogDto> selectPlatformOrder(Page<PlatformPayOrderLogDto> page,
			PlatformPayOrderLogDto platformPayOrderLogDto);

	/**
	 * 
	 * Description： 根据支付营销级活动id查询对应平台总订单的条数
	 * 
	 * Author zhushuyong
	 * 
	 * @param platformPromotion
	 *            传入的营销活动id
	 * @return
	 */
	Integer selectPlatformPromotionCount(@Param("platformPromotion") Long platformPromotion);

	/**
	 * 
	 * Description 根据平台订单id查询指定表的指定字段
	 * 
	 * Author zhushuyong
	 * 
	 * @param id
	 *            平台订单id
	 * @return
	 */
	PlatformPayOrderLogDto selectPlatformOrderId(@Param("id") Long id);

	/**
	 * 
	 * Description 根据平台订单id查询数据
	 * 
	 * Author Joe
	 * 
	 * @param id
	 *            平台订单id
	 * @return
	 */
	PlatformPayOrderLogDto findTotalOrderById(Long id);

	/**
	 * Description 改变订单状态
	 * 
	 * Author Aaron.Xue
	 * 
	 * @param payState
	 * @param id
	 * @return
	 */
	Integer updateTotalOrderStatus(@Param(value = "payState") Integer payState, @Param(value = "id") Long id);

	/**
	 * 
	 * Description：根据条件查询总订单
	 * 
	 * Author hxpeng
	 * 
	 * @param days
	 * @return
	 */
	List<TotalOrderEntity> getTotalOrderByParams(TotalOrderParamDto totalOrderParamDto);

	/**
	 * 
	 * Description：获取价格各个区间的件数
	 * 
	 * Author hxpeng
	 * 
	 * @param totalOrderParamDto
	 * @return
	 */
	List<TotalOrderPriceRangeDto> getTotalPriceRange(TotalOrderParamDto totalOrderParamDto);

	/**
	 * 
	 * Description： 获取{beforDate-afterDate}日期之间的订单数
	 * 
	 * Author hxpeng
	 * 
	 * @param beforDate
	 * @param afterDate
	 * @return
	 */
	List<TotalOrderEntity> getBeforDaysDataComparison(@Param(value = "beforDate") Date beforDate,
			@Param(value = "afterDate") Date afterDate, @Param(value = "orderStroeType") int orderStroeType);
}
