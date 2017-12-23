package com.topaiebiz.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: Nebula软件快速研发平台SSM基础框架的演示项目的启动器。
 * 
 * Author: Joe
 * 
 * Date: 2017年9月15日 下午6:06:50
 * 
 * Copyright: Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class PromotionApplicationStarter {

	/**
	 * Description: 应用的启动入口。
	 * 
	 * Author: Joe
	 * 
	 * @param args
	 *            自变量参数。
	 */
	public static void main(String[] args) {
		SpringApplication.run(PromotionApplicationStarter.class, args);
	}
}
