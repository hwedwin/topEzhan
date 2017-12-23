package com.topaiebiz.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * Description SpringBoot的启动类  
 * 
 * SpringBootApplication 指定类为应用启动类，自动扫描于当前类同级以及子包下的响应注解注册为spring beans，
 * 在类中main方法中通过
 * 
 * Author zhushuyong 
 *    
 * Date 2017年8月31日 下午1:31:00 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class TransactionApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplicationStarter.class, args);
	}

}
