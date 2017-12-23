/**
 * 
 */
package com.topaiebiz.transaction.order.total.dto;

/**  
 * Description： 总订单价格区间
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月30日 下午3:07:17 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class TotalOrderPriceRangeDto {

	/**
	 * 总价区间字符串
	 */
	private String rangeStr;
	
	/**
	 * 总价区间数量
	 */
	private Integer count;

	public String getRangeStr() {
		return rangeStr;
	}

	public void setRangeStr(String rangeStr) {
		this.rangeStr = rangeStr;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
