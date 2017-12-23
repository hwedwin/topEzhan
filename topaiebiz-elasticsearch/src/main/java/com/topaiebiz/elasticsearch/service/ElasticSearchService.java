package com.topaiebiz.elasticsearch.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.elasticsearch.dto.SearchPageDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import java.util.List;

/**
 * 
 * Description： 商品搜索service 接口
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年11月4日 下午2:58:29 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface ElasticSearchService {

	/**
	 * 
	 * Description： 根据查询条件查询商品
	 * 
	 * Author hxpeng   
	 * 
	 * @param queryParams
	 * @return
	 */
	List<ItemDto> searchItemByParams(String queryParams,ItemDto itemDto, SearchPageDto searchPageDto) throws GlobalException;
	
	
}
