/**
 * 
 */
package com.topaiebiz.elasticsearch.controller;

import com.topaiebiz.elasticsearch.dto.SearchPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.elasticsearch.dto.SearchParamsDto;
import com.topaiebiz.elasticsearch.service.ElasticSearchService;
import com.topaiebiz.goods.sku.dto.ItemDto;

/**  
 * Description： TODO (描述Java类的用途或使用说明。)  
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年11月4日 下午4:19:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@RestController
@RequestMapping("/app/search")
public class SearchController {

	@Autowired
	private ElasticSearchService elasticSearchService;
	
	
	@RequestMapping(path = "/item", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo item(SearchParamsDto searchParamsDto,ItemDto itemDto, SearchPageDto searchPageDto) throws GlobalException {
		return new ResponseInfo(elasticSearchService.searchItemByParams(searchParamsDto.getParams(),itemDto,searchPageDto));
	}
	
	
}
