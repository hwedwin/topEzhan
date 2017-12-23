package com.topaiebiz.merchant.info.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.merchant.enter.service.MerchantEnterService;
import com.topaiebiz.merchant.info.dto.StoreInfoDetailDto;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.merchant.info.exception.MerchantInfoException;
import com.topaiebiz.merchant.info.service.MerchantInfoService;
import com.topaiebiz.system.security.util.SecurityContextUtils;

/**
 * Description: 店铺管理
 * 
 * Author : Aaron
 * 
 * Date :2017年11月24日 下午1:25:19
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/merchant/storeInfo")
public class StoreInfoController {
	
	@Autowired
	private MerchantInfoService merchantInfoService;
	
	@Autowired
	private MerchantEnterService merchantEnterService;

	/**
	 * Description：商家登录回显所有店铺
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 * 
	 * return : 商家详情 dto对象
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getAllStoreByLoginName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAllStoreByLoginName() throws GlobalException {
		StoreInfoDetailDto storeInfo = merchantInfoService.getAllStoreByLoginName();
		return new ResponseInfo(storeInfo);
	}
	
	/**
	 * Description：创建店铺
	 * 
	 * Author: Anthony
	 * 
	 * param : storeInfoDto 店铺信息storeInfoDto对象
	 * 
	 * param : result 绑定结果集
	 * 
	 * return : 执行结果参数提示
	 * 
	 * throws GlobalException : 全局异常类
	 */
	@RequestMapping(path = "/addStoreInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo insertStoreInfo(@Valid StoreInfoDto storeInfoDto, BindingResult result)
			throws GlobalException, ParseException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		StoreInfoEntity storeInfoEntity = new StoreInfoEntity();
		BeanUtils.copyProperties(storeInfoDto, storeInfoEntity);
		return new ResponseInfo(merchantEnterService.saveStoreInfo(storeInfoEntity));
	}
	
	
	@RequestMapping(path = "/adminStoreInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo adminStoreInfo(HttpSession session, Long storeId) throws GlobalException, ParseException {
		if (null == storeId) {
			throw new GlobalException(MerchantInfoException.MERCHANTINFO_ID_NOT_NULL);
		}
		SecurityContextUtils.getCurrentSystemUser().setStoreId(storeId);
//		SystemUserEntity user = (SystemUserEntity)session.getAttribute("user");
//		user.setStoreId(storeId);
//		session.removeAttribute("user");
//		session.setAttribute("user", user);
		return new ResponseInfo();
	}

	
}
