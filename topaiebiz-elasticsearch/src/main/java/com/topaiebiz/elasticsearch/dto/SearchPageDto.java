package com.topaiebiz.elasticsearch.dto;

/**
 * Description 搜索工具分页参数DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/29 11:18
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class SearchPageDto {

	private int pageSize = 30;

	private int currentPage = 1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
