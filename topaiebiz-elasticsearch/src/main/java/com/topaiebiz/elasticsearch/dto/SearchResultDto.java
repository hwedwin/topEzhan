/**
 * 
 */
package com.topaiebiz.elasticsearch.dto;

/**  
 * Description： 查询结果容器DTO 
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年11月4日 下午4:35:06 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class SearchResultDto {

	/**
	 * 结果数量
	 */
	private int resultCount;
	
	/**
	 * 结果对象
	 */
	private Object resultObj;
	
	/**
	 * 查询耗时
	 */
	private long time;

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	
}
