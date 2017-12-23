package com.topaiebiz.transaction.order.merchant.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * Description 是否是多个指定的值的注解接口  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月14日 下午2:43:15 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {
	
	//flag的有效值多个用","隔开
	String values();
	
	//提示内容
	String message() default "{validation.parameter.error}";
	
	Class<?>[] groups() default {};
	
	Class<?extends Payload>[] payload() default{};

}
