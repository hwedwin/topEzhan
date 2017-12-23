package com.topaiebiz.goods.category.backend.moblie.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryAttrDto;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;

/**
 * Description 商品后台类目控制层
 * 
 * Author Hedda
 * 
 * Date 2017年8月24日 下午4:46:22
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/backend/category")
public class BackendCategoryMoblieController {

	@Autowired
	private BackendCategoryService backendCategoryService;

	/**
	 * Description app端根据商品前台第三级类目中所对应的规格属性
	 * 
	 * Author Hedda
	 * 
	 * @param frontendId
	 *            商品前台第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	@GetMapping(path = "/getAppListBackendCategoryAttr")
	@ResponseBody
	public ResponseInfo getAppListBackendCategoryAttr(Long frontendId) throws GlobalException {
		List<BackendCategoryAttrDto> listBackendCategoryAttr = backendCategoryService
				.getAppListBackendCategoryAttr(frontendId);
		return new ResponseInfo(listBackendCategoryAttr);
	}
	
	

}
