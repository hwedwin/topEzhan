/**
 *
 */
package com.topaiebiz.alipay.client;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.topaiebiz.alipay.config.AlipayConfig;
import com.topaiebiz.alipay.dto.AlipayPayDto;
import com.topaiebiz.alipay.dto.AlipayRefundDto;
import com.topaiebiz.alipay.dto.AlipayResultDto;
import com.topaiebiz.alipay.enumdata.AlipayEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Description： 支付宝支付客户端
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

public class ThirdPartyAlipayClient {

	private final static Logger logger = LoggerFactory.getLogger(ThirdPartyAlipayClient.class);

	/**
	 * Description： 支付---参数签名
	 * <p>
	 * Author hxpeng
	 *
	 * @return
	 */
	public static String payParameterSignature(AlipayPayDto alipayPayDto, String returnUrl) {
		if(StringUtils.isEmpty(returnUrl)){
			returnUrl = AlipayConfig.RETURN_URL;
		}
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
		alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);//在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent(JSONObject.toJSONString(alipayPayDto));
		try {
			String form = AlipayConfig.alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			return form;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Description： 验证签名
	 * <p>
	 * Author hxpeng
	 *
	 * @param map
	 * @return
	 * @throws AlipayApiException
	 */
	public static boolean checkResponseSign(Map<String, String[]> map) throws AlipayApiException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = map;
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			StringBuffer valueString = new StringBuffer("");
			for (int i = 0; i < values.length; i++) {
				valueString = (i == values.length - 1) ? valueString.append(values[i]) : valueString.append(values[i]).append(",");
			}

			valueStr = valueString.toString();
//			try {
////				valueStr = new String(valueString.toString().getBytes("ISO-8859-1"), "utf-8");
////				valueStr = new String(valueString.toString().getBytes("ISO-8859-1"), "utf-8");
//				valueStr = valueString.toString();
//			} catch (UnsupportedEncodingException e) {
//				/**
//				 */
//			}
			params.put(name, valueStr);
		}
		return AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
	}

	/**
	 * Description: 查询支付订单信息
	 * <p>
	 * Author: hxpeng
	 * createTime: 2017/11/9
	 *
	 * @param:
	 **/
	public static void queryOrderInfo() {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.POST_WAY, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
				AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("out_trade_no", "20150320010101001");
		jsonObject.put("trade_no", "2014112611001004680073956707");
		request.setBizContent(jsonObject.toJSONString());//设置业务参数
		AlipayTradeQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.print(response.getBody());
	}


	/**
	 * Description: 退款
	 * <p>
	 * Author: hxpeng
	 * createTime: 2017/11/9
	 *
	* @param:
	 **/
	public static AlipayResultDto refundOrder(AlipayRefundDto alipayRefundDto) {
		AlipayResultDto alipayResultDto = new AlipayResultDto();
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.POST_WAY, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
				AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类

		JSONObject jsonObject = new JSONObject();

		if(!StringUtils.isEmpty(alipayRefundDto.getOutTradeNo())){
			jsonObject.put("out_trade_no", alipayRefundDto.getOutTradeNo());
		}else {
			if(!StringUtils.isEmpty(alipayRefundDto.getTradeNo())){
				jsonObject.put("trade_no", alipayRefundDto.getTradeNo());
			}
		}
		jsonObject.put("out_request_no", alipayRefundDto.getRefundTotalOrderId());
		Double refundAmount = alipayRefundDto.getRefundAmount();
		jsonObject.put("refund_amount", refundAmount);
		request.setBizContent(jsonObject.toJSONString());//设置业务参数
		AlipayTradeRefundResponse response = null;//通过alipayClient调用API，获得对应的response类
		try {
			response = alipayClient.execute(request);
			logger.info("打印退款请求应答报文：" + response.getBody());
			if(response.getCode().equals("10000") && response.getMsg().equals("Success")){
				if(refundAmount.compareTo(Double.parseDouble(response.getRefundFee())) == 0){
					String fundChange = response.getFundChange();
					if(fundChange.equals("Y")){
						alipayResultDto.setResultCode(AlipayEnum.ORDER_REFUND_IS_SUCCESSED.getCode());
						alipayResultDto.setTradeNo(response.getTradeNo());
					}else if(fundChange.equals("N")){
						alipayResultDto.setResultCode(AlipayEnum.ORDER_REFUND_HAD_SUCCESSED.getCode());
						alipayResultDto.setMsg(AlipayEnum.ORDER_REFUND_HAD_SUCCESSED.getValue());
					}
				}else{
					logger.info("=======退款金额不一致！！！");
					alipayResultDto.setResultCode(AlipayEnum.ORDER_REFUND_AMOUNT_IS_DISSIMILARITY.getCode());
					alipayResultDto.setMsg(AlipayEnum.ORDER_REFUND_AMOUNT_IS_DISSIMILARITY.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			alipayResultDto.setResultCode(AlipayEnum.ORDER_REFUND_FAIL.getCode());
			alipayResultDto.setMsg(e.getMessage());
		}
		return alipayResultDto;
	}


	/**
	 * Description: 获取下载账单下载的地址
	 * <p>
	 * Author: hxpeng
	 * createTime: 2017/11/9
	 *
	 * @param:
	 **/
	public static String getBillDownloadUrl() {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.POST_WAY, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json",
				AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();//创建API对应的request类

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("bill_type", "trade");
		jsonObject.put("bill_date", "2016-04-05");
		request.setBizContent(jsonObject.toJSONString());//设置业务参数
		AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.print(response.getBody());
//根据response中的结果继续业务逻辑处理
		return "";
	}


	/**
	 * Description: 下载对账账单
	 * <p>
	 * Author: hxpeng
	 * createTime: 2017/11/9
	 *
	 * @param:
	 **/
	public static void downloadAliPayBill(String urlStr) {
		//将接口返回的对账单下载地址传入urlStr
//		String urlStr = "http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=X&userId=X&fileType=X&bizDates=X&downloadFileName=X&fileId=X";
		//指定希望保存的文件路径
		String filePath = "/Users/fund_bill_20160405.csv";
		URL url = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			url = new URL(urlStr);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setConnectTimeout(5 * 1000);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
			httpUrlConnection.connect();
			fis = httpUrlConnection.getInputStream();
			byte[] temp = new byte[1024];
			int b;
			fos = new FileOutputStream(new File(filePath));
			while ((b = fis.read(temp)) != -1) {
				fos.write(temp, 0, b);
				fos.flush();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
				httpUrlConnection.disconnect();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//		List<String> refundIds = new ArrayList<>();
//		refundIds.add("930614769958907908");
//		refundIds.add("930622113069518851");
//		refundIds.add("930625234684465153");
//		refundIds.add("930629992925118465");
//		refundIds.add("930631443365773315");
//		refundIds.add("930613280850964484");
//		refundIds.add("930635748361670660");
//		refundIds.add("931033818690465793");
//		for(String s : refundIds){
//			AlipayRefundDto alipayRefundDto = new AlipayRefundDto();
//			alipayRefundDto.setRefundAmount(new Double("0.01"));
//			alipayRefundDto.setOutTradeNo(s);
//			alipayRefundDto.setRefundReason(null);
//			refundOrder(alipayRefundDto);
//		}
//	}
}
