package com.topaiebiz.transaction.order.merchant.service.impl;

import com.topaiebiz.transaction.order.merchant.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description 订单详情（订单项）的业务接口 实现层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月2日 下午9:45:26 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

//	@Autowired
//	private OrderDetailsDao orderDetailsDao;
//
//	@Autowired
//	private StoreOrderDao storeOrderDao;
//
//	@Autowired
//	private DistrictDao districtDao;
//
//	@Autowired
//	private GoodsSkuDao goodsSkuDao;
//
//	@Override
//	public Map<String,Object> findByOrderId(Long id) throws GlobalException {
//
//		StoreOrderEntity storeOrderEntity=storeOrderDao.selectById(id);
//		if(storeOrderEntity == null) {
//			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
//		}
//		/**根据订单编号查询订单详情*/
//		OrderListDto orderListDto=storeOrderDao.selectOrderInfoById(id);
//		/**查看此订单中商品的数量*/
//		Long number=storeOrderDao.selectGoodsCountByOrderId(id);
//		orderListDto.setSumNum(number);
//		/**支付方式默认为在线支付*/
//		orderListDto.setPaymentWay("在线支付");
//		/**根据订单编号查询配送地址信息*/
//		OrderAddressEntity orderAddressEntity =storeOrderDao.selectAddressByOrderId(id);
//		/**查询该地址所在的省市县*/
//		Long districtId = orderAddressEntity.getDistrictId();
//		/**区对象*/
//		DistrictEntity district = districtDao.selectById(districtId);
//		String districtName = district.getFullName();
//		String cityName = district.getParentDistrictName();
//		/**市对象*/
//		DistrictEntity city = districtDao.selectById(district.getParentDistrictId());
//		String provinceName="";
//		if(city.getParentDistrictId() != 0) {
//			/**省名称*/
//			 provinceName = city.getParentDistrictName();
//		}
//		String address = orderAddressEntity.getAddress();
//		orderAddressEntity.setAddress(provinceName+cityName+districtName+address);
//		/**根据订单编号查询发票信息*/
//		OrderInvoiceEntity orderInvoiceEntity=orderDetailsDao.selectOrederInvoiceByOrderId(id);
//		/**根据订单编号查询商品信息*/
//		List<OrderDetailsEntity> selectByOrderId= orderDetailsDao.selectOrderDetailByOrderId(id);
//		/**订单的总优惠金额*/
//		Double sumDeduction=0.0;
//		/**订单总运费*/
//		Double totalFreight=0.0;
//		for(OrderDetailsEntity orderDetailsEntity:selectByOrderId) {
//			/**此商品的优惠金额*/
//			Double deduction = orderDetailsEntity.getDeduction();
//			sumDeduction=MathCountUtils.add(sumDeduction,deduction);
//			/**商品总运费*/
//			 totalFreight = orderDetailsEntity.getTotalFreight();
//			 if(null==totalFreight) {
//				 totalFreight=0.0;
//			 }
//			 /**通过商品skuId获取商品的itemId*/
//			 GoodsSkuEntity selectById = goodsSkuDao.selectById(orderDetailsEntity.getGoodsId());
//			 Long itemId = selectById.getItemId();
//			 orderDetailsEntity.setItemId(itemId);
//
//		}
//		/**订单实际支付*/
//		orderListDto.setActualPayMent(orderListDto.getLastPrice());
//		orderListDto.setSumDeduction(sumDeduction);
//		orderListDto.setSumPrice(orderListDto.getOrderTotal());
//		Map<String, Object> map = new HashMap<>();
//		map.put("orderInfo",orderListDto);
//		map.put("orderAddress",orderAddressEntity);
//		map.put("orderInvoice",orderInvoiceEntity);
//		map.put("goodsInfo",selectByOrderId);
//		return map;
//	}

}
