package com.topaiebiz.payment.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Description Nebula软件快速研发平台SSM基础框架的支付项目的启动器。 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月14日 下午7:24:31 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class PaymentApplicationStarter {

	/**
	 * Description 应用的启动入口  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param args 自定义变量参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplicationStarter.class, args);
	}
}
