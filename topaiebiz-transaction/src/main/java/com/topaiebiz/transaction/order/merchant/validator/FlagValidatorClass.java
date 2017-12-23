package com.topaiebiz.transaction.order.merchant.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {
	
	/**
	 *临时变量保存flag值列表 
	 */
	private String values;
	
	/**
	 * 
	 * Description 初始化values的值
	 * 
	 * Author zhushuyong   
	 * 
	 * @param arg0
	 */
	public void initialize(FlagValidator flagValidator) {
		//将注解内配置的值赋值给临时变量
		this.values=flagValidator.values();
	}

	/**
	 * 
	 * Description 实现验证的方法  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param value
	 * @param constraintValidatorContext
	 * @return
	 */
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		//分割定义的有效值
		String[] value_array=values.split(",");
		boolean isFlag = false;
		//遍历比对有效值
		for(int i=0;i<value_array.length;i++) {
			//存在一致跳出循环，赋值isFlag=true
			if(value_array[i].equals(value)) {
				isFlag=true;
				break;
			}
		}
		//返回是否存在boolean
		return isFlag;
	}

}
