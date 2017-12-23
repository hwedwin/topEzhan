/**
 * 
 */
package com.topaiebiz.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**  
 * Description： elasticsearch 项目启动类
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年11月4日 下午4:14:52 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class ElasticSearchApplicationStarter {
	
	
	/**
	 * 
	 * Description： 启动方法
	 * 
	 * Author hxpeng   
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplicationStarter.class, args);
	}
}
