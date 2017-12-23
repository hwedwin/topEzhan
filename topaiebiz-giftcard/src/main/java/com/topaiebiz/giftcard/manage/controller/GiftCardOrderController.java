package com.topaiebiz.giftcard.manage.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.dto.OrderAddressDto;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderInvoiceEntity;
import com.topaiebiz.giftcard.manage.exception.CardManageExceptionEnum;
import com.topaiebiz.giftcard.manage.service.GiftCardOrderService;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;

/**
 * @Description 礼卡订单的控制层，用于前后台交互。
 * 
 * 
 * @Author Murray.Li
 * 
 * @Date 2017年9月9日 上午9:30:27
 * 
 * @Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * @Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping("/giftcard/cardorder")
public class GiftCardOrderController {

	@Autowired
	private GiftCardOrderService cardOrderService;

	@Autowired
	private DistrictDao districtDao;

	/**
	 * @Description 查询订单列表。
	 * 
	 * @Author Murray.Li
	 * 
	 * @return
	 */
	@GetMapping("/getOrder")
	@ResponseBody
	public List<CardOrderEntity> getOrder() {
		List<CardOrderEntity> cardOrderList = cardOrderService.getCardOreder();
		return cardOrderList;
	}

	/**
	 * @Description 订单信息的查询 条件+分页
	 * 
	 * @Author Murray.Li
	 * 
	 * @param GiftCardOrderDto,Page
	 * 
	 * @return ResponseInfo(list);
	 */
	@RequestMapping(path = "/getOrderOfPage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderOfPage(GiftCardOrderDto cardOrderDto, Page<GiftCardOrderDto> page)
			throws GlobalException {
		Page<GiftCardOrderDto> list = cardOrderService.getOrderOfPage(page, cardOrderDto);
		return new ResponseInfo(list);
	}

	/**
	 * @Description 礼卡订单管理》》查看订单详情
	 * 
	 * @Author Murray.Li
	 * 
	 * @param Long
	 *            orderId(订单主键)
	 * 
	 * @return ResponseInfo(map);
	 */

	@RequestMapping(path = "/getOrderByOrderId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderByOrderId(Long orderId) throws GlobalException {
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		Map<String, Object> map = cardOrderService.getOrderByOrderId(orderId);
		return new ResponseInfo(map);
	}

	/**
	 * 
	 * Description：查看发票详情。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(path = "/getOrderInvoice", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderInvoice(Long orderId) throws GlobalException {
		if (orderId == null) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		CardOrderInvoiceEntity orderInvoice = cardOrderService.getOrderInvoiceByOrderId(orderId);
		return new ResponseInfo(orderInvoice);
	}

	/**
	 * 
	 * Description： <美礼卡订单管理>》<删除订单>
	 * 
	 * Author Murray.Li
	 * 
	 * @param Long[]
	 *            list
	 * 
	 * @return ResponseInfo();
	 * 
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelOrderById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOrderById(Long[] list) throws GlobalException {
		if (null == list) {
			throw new GlobalException(CardManageExceptionEnum.ORDER_ID_IS_NOT_NULL);
		}
		cardOrderService.removeOrderById(list);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description：根据订单编号查询订单地址。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getAddressByOrderId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAddressByOrderId(Long orderId) throws GlobalException {
		if (null == orderId) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		CardAddressEntity orderAddress = cardOrderService.getOrderAddressByOrderId(orderId);
		/** 根据区的ID查询省名》市名》区名 */
		DistrictEntity districtEntity = districtDao.selectById(orderAddress.getDistrictId());
		/** 存入市的编号 */
		orderAddress.setCityId(districtEntity.getParentDistrictId());
		DistrictEntity selectById = districtDao.selectById(districtEntity.getParentDistrictId());
		/** 存入省的编号 */
		orderAddress.setProvinceId(selectById.getParentDistrictId());
		return new ResponseInfo(orderAddress);
	}

	/**
	 * 
	 * Description： 根据订单编号修改订单配送地址。
	 * 
	 * Author Murray.Li
	 * 
	 * @return
	 */
	@RequestMapping(path = "/editGiftCardLabelByorderId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAddressByorderId(@Valid OrderAddressDto orderAddressDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		cardOrderService.updateAddressByorderId(orderAddressDto);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description： 根据订单编号查询需要标记发货
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getDelivery", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDelivery(Long orderId) throws GlobalException {
		if (null == orderId) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		OrderAddressDto orderAddressDto = cardOrderService.getDelivery(orderId);
		return new ResponseInfo(orderAddressDto);
	}

	/**
	 * 
	 * Description：根据订单编号标记发货。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @param logisticsNo
	 * @param logisticsType
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/markDeliveryByorderId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo markDeliveryByorderId(Long orderId, String logisticsNo, String logisticsType)
			throws GlobalException {
		if (orderId == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		if (logisticsNo == null || logisticsNo.equals("") ) {
			throw new GlobalException(CardManageExceptionEnum.LOGISTICSNO_IS_NOT_NULL);
		}
		if (logisticsType == null || logisticsType.equals("")) {
			throw new GlobalException(CardManageExceptionEnum.LOGISTICSTYPE_IS_NOT_NULL);
		}
		cardOrderService.markDeliveryByorderId(orderId, logisticsNo, logisticsType);
		return new ResponseInfo();
	}
}
