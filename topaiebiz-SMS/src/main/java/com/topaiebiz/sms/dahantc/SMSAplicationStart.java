package com.topaiebiz.sms.dahantc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@ComponentScan(basePackages = "com.nebulapaas, com.topaiebiz")
public class SMSAplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(SMSAplicationStart.class, args);
	}
}
