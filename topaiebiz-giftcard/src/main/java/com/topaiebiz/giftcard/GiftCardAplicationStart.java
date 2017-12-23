package com.topaiebiz.giftcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.nebulapaas,com.topaiebiz") 
public class GiftCardAplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(GiftCardAplicationStart.class, args);
	}
}
