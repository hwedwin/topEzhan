package com.topaiebiz.goods.category.frontend.mobile.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
import com.topaiebiz.goods.category.frontend.service.FrontendCategoryService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description 商品前台类目控制层
 * 
 * Author Hedda
 * 
 * Date 2017年8月25日 下午3:13:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@RestController
@RequestMapping("/app/frontend/category")
public class FrontendCategoryMobileController {

	@Autowired
	private FrontendCategoryService frontendCategoryService;
	
	/**
	 * Description app端商家前台类目一,二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMerchantAppFrontendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantAppFrontendCategoryList(String token, FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if(tokenDetail != null) {
			//店铺id
			Long storeId = tokenDetail.getStoreId();
			frontendCategoryDto.setBelongStore(storeId);
		}
		List<FrontendCategoryDto> listFrontendCategoryEntity = frontendCategoryService
				.getMerchantAppFrontendCategoryList(frontendCategoryDto);
		return new ResponseInfo(listFrontendCategoryEntity);
	}
	
	/**
	 * Description app端商家前台类目第三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getAppThreeFrontendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAppThreeFrontendCategoryList(String token) throws GlobalException {
		Long storeId = null;
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if(tokenDetail != null) {
			//店铺id
			storeId = tokenDetail.getStoreId();
		}
		List<FrontendCategoryDto> listFrontendCategory = frontendCategoryService
				.getAppThreeFrontendCategoryList(storeId);
		return new ResponseInfo(listFrontendCategory);
	}

}
