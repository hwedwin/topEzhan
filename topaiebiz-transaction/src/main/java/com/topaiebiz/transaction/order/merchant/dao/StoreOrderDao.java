package com.topaiebiz.transaction.order.merchant.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentEntity;
import com.topaiebiz.transaction.order.merchant.dto.*;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * Description 店铺订单的数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年8月31日 下午3:32:45 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface StoreOrderDao extends BaseDao<StoreOrderEntity> {


	/**
	*
	* Description: PC：获取所有订单
	*
	* Author: hxpeng
	* createTime: 2017/11/28
	*
	* @param: orderListParamsDto 商家/平台 订单列表查询参数DTO
	**/
	List<PCOrderListResultDto> getOrderListInPc(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto);


	/**
	*
	* Description: APP：获取所有订单
	*
	* Author: hxpeng
	* createTime: 2017/11/29
	*
	* @param: memberId 当前登录用户
	* @param: orderStateArray 订单状态数组
	**/
	List<APPOrderListResultDto> getOrderListInAPP(@Param("memberId") Long memberId, @Param("orderStateArray") List<Integer> orderStateArray);


	/**
	 * ===================================================================================================
	 * ===================================================================================================
	 * ===================================================================================================
	 * ===================================================================================================
	 * ===================================================================================================
	 * ===================================================================================================
	 * ===================================================================================================
	 */

	/**
	 * 
	 * Description 根据订单id查询出dto中指定订单相关属性字段  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param id
	 * @return
	 */
	OrderListDto selectByIdDto(@Param("id")Long id);
	
	/**
	 * 
	 * Description：订单列表dto分页 
	 * 
	 * Author zhushuyong   
	 * @param page 
	 * 
	 * @param orderListDto
	 * @return
	 */
	List<OrderListDto> selectByOrderListDtoList(Page<OrderListDto> page, OrderListDto orderListDto);
	 
	/**
	 * 
	 * Description 根据订单id修改订单详情中的收货地址跟发票类型  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param consigneeName
	 * 			传入的收货人姓名
	 * @param address
	 * 			传入的收货人地址
	 * @param addressMemo
	 * 			传入的收货人备注
	 * @param invoiceType
	 * 			传入的发票类型
	 * @param orderId
	 * 			订单id
	 * @return
	 */
	Integer updateAddressInvoice(OrderAddressInvoiceDto orderAddressInvoiceDto);
	
	/**
	 * 
	 * Description 批量修改逻辑删除标识。 
	 * 
	 * Author zhushuyong   
	 * 
	 * @param ids
	 * 			订单id数组
	 * @return
	 */
	void updateDeletedFlag(Long[] ids);

	/**
	 * 
	 * Description 根据店铺订单详情表使用营销活动查询买家
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrder(Long detailUsedPromotion);
	
	/**
	 * Description 根据总订单号 查询店铺订单列表
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param totalOrderId
	 * @return
	 */
	List<StoreOrderEntity> selectByTotalOrderId(Long totalOrderId);
	
	/**
	 * Description 改变订单状态
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param orderState
	 * @param id
	 * @return
	 */
	Integer updateStoreOrderStatus(@Param(value = "orderState")Integer orderState, @Param(value = "id")Long id);

	/**
	 * 
	 * Description： 根据订单编号查询配送地址信息. 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	OrderAddressEntity selectAddressByOrderId(Long id);

	/**
	 * 
	 * Description 根据营销活动查询店铺订单使用
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	List<StoreOrderDto> findStoreOederByUsedPromotion(Long usedPromotion);
	
	/**
	 * 
	 * Description 根据包邮营销活动查询店铺订单使用
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	List<StoreOrderDto> findStoreOederByFreightPromotion(Long freightPromotion);

	/**
	 * 
	 * Description 店铺订单使用营销活动查询买家
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrderBuyersNum(Long usedPromotion);

	/**
	 * 
	 * Description 店铺订单使用包邮营销活动查询买家
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrderFreightPromotionBuyersNum(Long freightPromotion);
	
	/**
	 * 
	 * Description： 根据订单编号查看订单详情。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	OrderListDto selectOrderInfoById(Long id);

	/**
	 * 
	 * Description： 根据订单编号查询需要标记发货的订单信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	OrderAddressEntity selectDeliveryByOrderId(Long orderId);

	/**
	 * 
	 * Description： 标记发货。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param orderId2 
	 * @param logisticsNo
	 * @param logisticsType
	 */
	void markDeliveryByorderId(Long goodsId, Long orderId, String logisticsNo, String logisticsType);
 
	/**
	 * 
	 * Description 根据会员编号和活动id查询使用次数
	 * 
	 * Author Joe   
	 * 
	 * @param memberId
	 * @param promotionId
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrderByUsedPromotion(@Param("memberId")Long memberId, @Param("usedPromotion")Long usedPromotion);

	/**
	 * 
	 * Description：删除订单。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 */
	void deleteStoreOrderByOrderId(Long orderId);

	/**
	 * 
	 * Description：查询会员的订单。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderListDto
	 * @return
	 */
	List<OrderListDto> selectStoreOrderByMemberId(@Param("memberId")Long memberId,@Param("orderState")Integer orderState);

	/**
	 * 
	 * Description： 根据订单主键ID和商品ID查询此商品是否已评价。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	GoodsSkuCommentEntity getGoodsSkuCommentEntity(GoodsSkuCommentEntity goodsSku); 
 
	/**
	 * 
	 * Description： 查询需要标记发货的商品名称和商品主键ID。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderDetailsEntity> selectList(Long orderId);

	/**
	 * 
	 * Description： 查看数据库所有已收货的订单。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @return
	 */
	List<StoreOrderEntity> selectReceivedOrder();

	/**
	 * 
	 * Description： 根据商品名称查询订单编号。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param goodsName
	 * @return
	 */
	List<Long> selectOrderIdByGoodName(String goodsName);

	/***
	 * 
	 * Description： 查询该用户已完成的订单集合.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param memberId
	 * @param i
	 * @return
	 */
	List<OrderListDto> selectStoreOrderByMember(@Param("memberId") Long memberId,@Param("orderState") Integer orderState);
   
	/**
     * 
     * Description： 平台系统的店铺订单管理。
     * 
     * Author Murray.Li 
     * 
     * @param page
     * @param orderListDto
     * @return
     */
	List<OrderListDto> selecttoreOrderList(Page<OrderListDto> page, OrderListDto orderListDto);


	/**
	 *
	 * Description: 获取当天订单列表
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/22
	 *
	 * @param:
	 **/
	List<StoreOrderEntity> getTodayOrderList();

	/**
	 * 
	 * Description： 根据店铺ID查询店铺信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	StoreDto selectStoreByStoreId(Long storeId);

	/**
	 * 
	 * Description： 根据商家id查询商家信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param merchantId
	 * @return
	 */
	StoreDto selectMerchantByMerchantId(Long merchantId);

	/**
	 * 
	 * Description：查询所有订单的数量。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	Integer getOrderAllCount(@Param("storeId") Long storeId);

	/**
	 * 
	 * Description：查询代付款的订单的数量.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	Integer getOrderObligationCount(@Param("storeId") Long storeId);

	/**
	 * 
	 * Description：查询代发货的订单的数量.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	Integer getOrderShipmentCount(@Param("storeId") Long storeId);

	/**
	 * 
	 * Description： 查询待收货的订单的数量.
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	Integer getOrderReceivedCount(@Param("storeId") Long storeId);

	/**
	 * 
	 * Description： 查看此订单的商品数量。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	Long selectGoodsCountByOrderId(Long id); 

	/**
	*
	* Description: 根据店铺订单id查询订单详情列表
	*
	* Author: hxpeng
	* createTime: 2017/11/29
	*
	* @param:
	**/
	List<OrderDetailsEntity> selectByOrderId(Long orderId);

}
