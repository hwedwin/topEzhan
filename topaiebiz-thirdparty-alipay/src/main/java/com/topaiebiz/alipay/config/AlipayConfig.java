package com.topaiebiz.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/13 13:27
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class AlipayConfig {

	// 支付宝网关（固定）
	public final static String POST_WAY = "https://openapi.alipay.com/gateway.do";

	// 应用ID
	public final static String APP_ID = "2017101609328636";

	// 编码格式
	public final static String CHARSET = "utf-8";

	// 应用私钥
	public final static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCGzhDetkmC6ZmjOdGJwN/"
			+ "8EIlsxH6FCMZVEhGQ3ZxHTMrm1YTlf0/X70jAlLyQPvBmh6Ksags+tdaMPvHCSyOmCKkjAVZ+EhOU9g0S5gdivk/INALcl9o2YuOSF"
			+ "p8nMZDPNoITkzVQVBfc1/FDi7Ti8MgKNQ5MiItUw3MsfyGC34v73xengsYyLGZ7FTshceIDy3jdkuPft6NDwQdpF84gJThtKvmnoY"
			+ "npqNtYoTwgcwbe9BvonmR1LytltbKQToUG/1OStsx1ZUMXvb5Sct0gOYEGjeI1DFJk0oP3QSARP11u5YnoRiGS2soVgoHxMtT1OA"
			+ "33mVfxkxzEU+ggjbqjAgMBAAECggEAH4GH1dV6xtr76nc37t8iOlJPHBmsjEEpwXYwns/8l3luLpcWsWCY85ncQg5BJ+Fd5EitD2j"
			+ "VafmrxClt7YAMO+DoTuMgKOTmqLj9Az652S0rjloBlTb0bCOelmUtG0btVlCiBBsyqayNyzK5E5VSWHv39NLDncWH8u6fO3YXCfQH"
			+ "AiidZhc3eBRYqaOAMbUu7eHAKttoA+4XAE0K+ZFRA/PZxiqNcTxTR738eJTjnfo9CiAJ1EXrB/BVKyuEoX3G4oQTqU6thwnnIhywk"
			+ "tbPq9qU/aDBAQPoabqQu+DvYMtKzlYko0qJgeYfEU8y7XyWvlCdZ+ZwZIJcAoDbzANd4QKBgQDfI85Kkorr/CUkIUrfdh10li7YCpo"
			+ "W/kuZnAfgPVF8XKBlzin4IWMUKDRsk38dBEEYfp07fB7JcTPwezSrcYsha3f0I8MFhIists7Do76PZ38KzZEWSOka1XLCFbH2CINH5t"
			+ "gYrg7TXWF0sgxQLf4Yxqa+E3208AGJh9VWowpxsQKBgQCaqBqz7t4wGLdOawk4XfCwB7thUcz60foetSS3WQekfaipNHM/vEQInZ3g5"
			+ "ZFuCIaVo3WUfUhyL91lE1+nhOAp0wBbyJBrGuwYOajr8vSkUdHDUXoafwiaIBiiRL4Oz/s3rumCQEv7wQ3iIBXWXKTC89SfMBK9g7hx"
			+ "JKWBcpASkwKBgQDEjwPvSLs7OTd2ZiVQRvmXc5v8DwrBamwp+fouG5WmGOnqA/uQacifPvM/GYaFhvPYod/bQHDWPfPkyIMS6sgGPmb"
			+ "CFqZ6O88xZS8pwxnftOmCGKbPnJZKXu/h676B3enW8pA03U6N/doVM6oJDxqftI25Le4z7p9MCO1yjdhrYQKBgQCSYXYUxIwMnfgyAu"
			+ "FRNqjvFAJpFJGc/U1t7yn3WA8Bw+X1cS18L+d8+CJMkTzbFrq7u2E40HPXkDJOzs2EsyGwtIPsW6NFnM/GBh06ZlTjioWuBto5Ze9wY"
			+ "dyvCb2SK4ytoaJmpBDHEmzfkFra8AkKdNrUaR3zvk6+2F7GbPasfQKBgHbXwU3w+sitkmK2ubMZiCAYwjau+hAWk0AoLpPYpxH7NMJf"
			+ "7NQ4UML9dV8cuwiQWeFWrCcmFjic46kXkW8oLvSUBqfv+zUybfWu08eP5EHMJl1D80TEDE1MgVfoKOzyGc2xZtIgBNTyM7IPUdTSHHz"
			+ "/Dqmkq8v2DJML1/aQ68/2";

	// 支付宝公钥
	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtTPqb0CYZLsEguVtlvMi8U4av"
			+ "TEdmLpY+8HLHbZll5gJEAvi3LvP00BqJjgM+eQfwZf8Z19f72bu/vFr1aCPTx9ODWM3zabZyHyDlpVdPfI7pSQZAewRJn6FxqcTuinV"
			+ "EUc2/rVOdu0ci2zB56CWnwVhhj7gS+4v0fq89KVir7ptd0GOcqVxDPRVu/3vaPOJ4A7r6oB+/5Z7WVSWUrnfjSrHmdPHugPOoL8PNRN"
			+ "v6y3XP7wK/lgWNKsqLk3UH8FCJ17PC7vwXTPwZCWLGrZtEibxOok88Ur7KA/S1FXz053O0pPS+k1Xw94CJx/aniEBywx47u8eQuTVp3"
			+ "Dyyr/NwIDAQAB";

	// 签名算法类型
	public final static String SIGN_TYPE = "RSA2";

	// 实例化客户端(线程安全类)
	public final static AlipayClient alipayClient = new DefaultAlipayClient(POST_WAY, APP_ID, APP_PRIVATE_KEY, "json",
			CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

	// 支付宝回调通知地址
	public final static String NOTIFY_URL = "http://m.qinziezhan.com/app/payment/mobile/order/asyncNotic";

	// 支付宝跳转地址
	public final static String RETURN_URL = "http://m.qinziezhan.com/#/pay_success";

	public final static String PID = "2088821408624630";
}
