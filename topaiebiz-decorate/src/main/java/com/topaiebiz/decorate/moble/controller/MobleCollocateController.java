package com.topaiebiz.decorate.moble.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.decorate.dto.CollocateDto;
import com.topaiebiz.decorate.dto.CollocateInfoDto;
import com.topaiebiz.decorate.entity.CollocateGoodsEntity;
import com.topaiebiz.decorate.entity.StoreTemeplateEntity;
import com.topaiebiz.decorate.exception.CollocateExceptionEnum;
import com.topaiebiz.decorate.service.CollocateService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description 装修模版处理器 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年11月2日 上午11:22:52 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/decorate/collocate")
public class MobleCollocateController {

	@Autowired
	private CollocateService collocateService;
	
	/**
	 * Description 根据装修模版类型，店铺Id获取 装修模版
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getListCollocate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListCollocate(String token, @Valid CollocateDto collocateDto, BindingResult result) throws GlobalException{
		if(StringUtils.isEmpty(token)) {
			collocateDto.setStoreId(1L);
		}else {
			TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
			collocateDto.setStoreId(null == tokenDetail ? 1L : tokenDetail.getStoreId());
		}
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		CollocateDto collocate = collocateService.getListCollocate(collocateDto);
		return new ResponseInfo(collocate);
	}
	
	/**
	 * Description 根据装修模版类型，店铺Id获取 装修模版
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/getGiftCollocateDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGiftCollocateDetail(CollocateDto collocateDto) throws GlobalException{
		//不是美礼卡类型
		if(null == collocateDto.getCollocateType() || collocateDto.getCollocateType() != 0) {
			throw new GlobalException(CollocateExceptionEnum.IS_NOT_GIFTCARD_TYPE);
		}
		List<CollocateInfoDto> infoList = collocateService.getGiftCollocateDetail(collocateDto);
		return new ResponseInfo(infoList);
	}
	
	/**
	 * Description： 获取当前店铺适用的模版
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getUsedTemeplate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getUsedTemeplate(String token) throws GlobalException{
		Long sotreId = 1L;
		if(StringUtils.isEmpty(token)) {
			sotreId = 1L;
		}else {
			TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
			sotreId = (null == tokenDetail ? 1L : tokenDetail.getStoreId());
		}
		StoreTemeplateEntity storeTemeplateEntity = collocateService.getUsedTemeplate(sotreId);
		return new ResponseInfo(storeTemeplateEntity.getTemplateId());
	}
	
	@RequestMapping(path = "/getCollocateGoods", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCollocateGoods(Long id) throws GlobalException{
		List<CollocateGoodsEntity> goodsList = collocateService.getCollocateGoods(id);
		return new ResponseInfo(goodsList);
	}
	
	
}
