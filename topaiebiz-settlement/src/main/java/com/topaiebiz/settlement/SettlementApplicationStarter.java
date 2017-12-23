package com.topaiebiz.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Description： Nebula软件快速研发平台SSM基础框架的演示项目的启动器。 
 * 
 * Author Harry 
 *    
 * Date 2017年10月2日 下午8:25:33 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
@EnableScheduling
public class SettlementApplicationStarter {

	/**
	 * Description： 应用的启动入口 。 
	 * 
	 * Author Harry  
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SettlementApplicationStarter.class, args);
	}
}
