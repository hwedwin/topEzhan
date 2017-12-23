/**
 *
 */
package com.topaiebiz.thirdparty.pay.client;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.topaiebiz.thirdparty.config.WeChatConfig;
import com.topaiebiz.thirdparty.dto.WechatRefundDto;
import com.topaiebiz.thirdparty.dto.WechatResultDto;
import com.topaiebiz.thirdparty.enumdata.WecahtEnum;
import com.topaiebiz.thirdparty.pay.dto.WeChatPayDto;
import com.topaiebiz.thirdparty.pay.util.LocalWXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description： 微信支付客户端
 * <p>
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017年11月5日 下午2:46:25
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class ThirdPartyWeChatClient {

	private final static Logger logger = LoggerFactory.getLogger(ThirdPartyWeChatClient.class);


	/**
	 * Description: 调用微信统一下单接口，返回预付单信息
	 * <p>
	 * Author: hxpeng
	 * createTime: 2017/11/13
	 *
	 * @param:
	 **/
	public static Map<String, String> payParameterSignature(WeChatPayDto weChatPayDto) {

		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("appid", WeChatConfig.APP_ID);
		paramsMap.put("mch_id", WeChatConfig.MCH_ID);
		paramsMap.put("device_info", WeChatConfig.DEVICE_INFO);
		paramsMap.put("nonce_str", LocalWXPayUtil.generateNonceStr());
		paramsMap.put("sign_type", WeChatConfig.SIGN_TYPE);
		// 下面是业务参数
		if (StringUtils.isEmpty(weChatPayDto.getBody())) {
			return null;
		}
		paramsMap.put("body", weChatPayDto.getBody());
		if (StringUtils.isEmpty(weChatPayDto.getDetail())) {
			return null;
		}
		paramsMap.put("detail", weChatPayDto.getDetail());
		if (StringUtils.isEmpty(weChatPayDto.getAttach())) {
			return null;
		}
		paramsMap.put("attach", weChatPayDto.getAttach());
		if (StringUtils.isEmpty(weChatPayDto.getOut_trade_no())) {
			return null;
		}
		paramsMap.put("out_trade_no", weChatPayDto.getOut_trade_no());
		if (StringUtils.isEmpty(weChatPayDto.getTotal_fee())) {
			return null;
		}
		paramsMap.put("total_fee", weChatPayDto.getTotal_fee());
//		paramsMap.put("total_fee", "1");
		if (StringUtils.isEmpty(weChatPayDto.getSpbill_create_ip())) {
			return null;
		}
		paramsMap.put("spbill_create_ip", weChatPayDto.getSpbill_create_ip());

		Date currentDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// 交易起始时间
		paramsMap.put("time_start", simpleDateFormat.format(currentDate));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.HOUR, 2);
		Date overdueDate = calendar.getTime();
		// 交易结束时间
		paramsMap.put("time_expire", simpleDateFormat.format(calendar.getTime()));

		paramsMap.put("notify_url", WeChatConfig.PAY_NOTIFY_URL);
		paramsMap.put("trade_type", "JSAPI");
		paramsMap.put("openid", weChatPayDto.getOpenId());

		try {
			// 请求返回报文
			String result = LocalWXPayUtil.httpRequest(WeChatConfig.WECHAT_PAY, LocalWXPayUtil.generateSignedXml(paramsMap, WeChatConfig.APP_KEY), false);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("微信支付返回报文");
			logger.info(result);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			if(LocalWXPayUtil.isSignatureValid(result, WeChatConfig.APP_KEY)){
				Map<String, String> map = LocalWXPayUtil.xmlToMap(result);
				String return_code = map.get("return_code");
				if(return_code.equals("SUCCESS")){
					String result_code = map.get("result_code");
					if(result_code.equals("SUCCESS")){
						// 再次签名创建预付单 调用微信js sdk
						Map<String, String> payMap = new HashMap<>();
						payMap.put("appId", WeChatConfig.APP_ID);
						payMap.put("timeStamp", String.valueOf(LocalWXPayUtil.getCurrentTimestamp()));
						payMap.put("nonceStr", LocalWXPayUtil.generateNonceStr());
						payMap.put("package", "prepay_id="+map.get("prepay_id"));
						payMap.put("signType","MD5");
						payMap.put("paySign", LocalWXPayUtil.generateSignature(payMap, WeChatConfig.APP_KEY, WXPayConstants.SignType.MD5));
						return payMap;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	*
	* Description: 校验签名
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	public static boolean checkSign(Map<String, String> map) throws Exception {
		return WXPayUtil.isSignatureValid(map, WeChatConfig.APP_KEY);
	}

	/**
	*
	* Description: 回复微信操作结果的应答报文
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	public static String getPayResultXmlStr(boolean falg) throws Exception {
		Map<String, String> map = new HashMap<>();
		if(falg){
			map.put("return_code","SUCCESS");
		}else{
			map.put("return_code","FAIL");
		}
		return WXPayUtil.mapToXml(map);
	}

	/**
	*
	* Description: 退款
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	public static WechatResultDto refundOrder(WechatRefundDto wechatRefundDto) {
		WechatResultDto wechatResultDto = new WechatResultDto();
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("appid", WeChatConfig.APP_ID);
		paramsMap.put("mch_id", WeChatConfig.MCH_ID);
		paramsMap.put("nonce_str", LocalWXPayUtil.generateNonceStr());
		paramsMap.put("sign_type", WeChatConfig.SIGN_TYPE);

		if(!StringUtils.isEmpty(wechatRefundDto.getOutTradeNo())){
			paramsMap.put("out_trade_no", wechatRefundDto.getOutTradeNo());
		}else {
			if(!StringUtils.isEmpty(wechatRefundDto.getTransactionId())){
				paramsMap.put("transaction_id", wechatRefundDto.getTransactionId());
			}
		}
		paramsMap.put("out_refund_no",wechatRefundDto.getOutRefundNo());

		// 订单总金额
		Integer totalFee = new BigDecimal(wechatRefundDto.getTotalFee()).multiply(new BigDecimal(100)).intValue();
		// 退款总金额
		Integer refundFee = new BigDecimal(wechatRefundDto.getRefundFee()).multiply(new BigDecimal(100)).intValue();
//		Integer refundFee = wechatRefundDto.getRefundFee().intValue();
		paramsMap.put("total_fee",totalFee.toString());
		paramsMap.put("refund_fee",refundFee.toString());
		paramsMap.put("refund_desc",wechatRefundDto.getRefundDesc());

		try{
			paramsMap.put("sign",LocalWXPayUtil.generateSignature(paramsMap, WeChatConfig.APP_KEY, WXPayConstants.SignType.MD5));
			String result = LocalWXPayUtil.httpRequest(WeChatConfig.REFUND_PAY, LocalWXPayUtil.generateSignedXml(paramsMap, WeChatConfig.APP_KEY), true);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("微信退款返回报文");
			logger.info(result);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			if(LocalWXPayUtil.isSignatureValid(result, WeChatConfig.APP_KEY)){
				Map<String, String> resultMap = LocalWXPayUtil.xmlToMap(result);
				String return_code = resultMap.get("return_code");
				if(return_code.equals("SUCCESS")){
					String result_code = resultMap.get("result_code");
					if(result_code.equals("SUCCESS")){
						// 商户退款单号
//						String outRefundNo = resultMap.get("out_refund_no");
						// 微信退款单号
						String refundId = resultMap.get("refund_id");
						// 微信退款金额
						int wxRefundFee = Integer.parseInt(resultMap.get("refund_fee"));
						if(wxRefundFee == refundFee){
							wechatResultDto.setTradeNo(refundId);
							wechatResultDto.setResultCode(WecahtEnum.ORDER_REFUND_IS_SUCCESSED.getCode());
						}else{
							logger.info("=======退款金额不一致！！！");
							wechatResultDto.setResultCode(WecahtEnum.ORDER_REFUND_AMOUNT_IS_DISSIMILARITY.getCode());
							wechatResultDto.setMsg(WecahtEnum.ORDER_REFUND_AMOUNT_IS_DISSIMILARITY.getValue());
						}
					}else{
						wechatResultDto.setResultCode(paramsMap.get("err_code"));
						wechatResultDto.setMsg(paramsMap.get("err_code_des"));
					}
				}else{
					wechatResultDto.setResultCode(paramsMap.get("return_code"));
					wechatResultDto.setMsg(paramsMap.get("return_msg"));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			wechatResultDto.setResultCode(WecahtEnum.ORDER_REFUND_FAIL.getCode());
			wechatResultDto.setMsg(e.getMessage());
		}
		return wechatResultDto;
	}

	public static void main(String[] args) {
		WechatRefundDto wechatRefundDto = new WechatRefundDto();
		wechatRefundDto.setOutTradeNo("931938330560442370");
		wechatRefundDto.setTotalFee(1.0);
		wechatRefundDto.setRefundDesc("测试测试");
		wechatRefundDto.setRefundFee(1.0);
		wechatRefundDto.setOutRefundNo("111111");
		refundOrder(wechatRefundDto);
	}

}
