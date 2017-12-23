package com.topaiebiz.timetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description 定时任务项目启动类
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/22 16:39
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz")
public class TimeTaskApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(TimeTaskApplicationStarter.class, args);
	}
}
