package com.topaiebiz.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * Description：Nebula软件快速研发平台SSM基础框架的会员项目的启动器。   
 * 
 * 
 * Author Scott
 *    
 * Date 2017年9月22日 下午8:20:00 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class MemberApplicationStarter {
   
	/**
	 * Description: 应用的启动入口。
	 * 
	 * Author: Scott
	 * 
	 * @param args
	 *            自变量参数。
	 */
	public static void main(String[] args) {
		SpringApplication.run(MemberApplicationStarter.class, args);
	}
}
