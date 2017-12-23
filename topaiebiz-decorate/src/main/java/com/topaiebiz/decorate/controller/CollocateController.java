package com.topaiebiz.decorate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.decorate.dto.CollocateDetailDto;
import com.topaiebiz.decorate.dto.CollocateDto;
import com.topaiebiz.decorate.dto.CollocateGoodsDto;
import com.topaiebiz.decorate.dto.CollocateInfoDto;
import com.topaiebiz.decorate.dto.TemeplateInfoDto;
import com.topaiebiz.decorate.exception.CollocateExceptionEnum;
import com.topaiebiz.decorate.service.CollocateService;

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
@RequestMapping("/decorate/collocate")
public class CollocateController {
	
	@Autowired
	private CollocateService collocateService;
	
	/**
	 * Description：店铺选用装修模版
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param temeplateId
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addStoreTemeplate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addStoreTemeplate(Long temeplateId) throws GlobalException{
		collocateService.saveStoreTemeplate(temeplateId);
		return new ResponseInfo();
	}
	
	/**
	 * Description：平台禁用启用装修模版
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editTemeplateInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editTemeplateInfo(@Valid TemeplateInfoDto temeplateInfoDto, BindingResult result) throws GlobalException{
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		collocateService.modifyTemeplateInfo(temeplateInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description 添加店铺装修模版类型
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addCollocateType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addCollocateType(@Valid CollocateDto collocateDto, BindingResult result) throws GlobalException{
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		//校验标题
		if(StringUtils.isEmpty(collocateDto.getTitle())) {
			throw new GlobalException(CollocateExceptionEnum.TITLE_ID_NOT_NULL);
		}
		//保存数据
		collocateService.addCollocateType(collocateDto);
		return new ResponseInfo();
	}
	
	
	/**
	 * Description 添加装修模版信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/addCollocateInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addCollocateInfo(@Valid CollocateInfoDto collocateInfoDto, BindingResult result) throws GlobalException{
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		//1商品 2类目 3品牌 4 自定义url
//		if(collocateInfoDto.getJumpType() == 4) {
//			if(StringUtils.isEmpty(collocateInfoDto.getJumpUrl())) {
//				throw new GlobalException(CollocateExceptionEnum.JUMP_URL_NOT_NULL);
//			}
//		}else {
//			if(null == collocateInfoDto.getJumpId()) {
//				throw new GlobalException(CollocateExceptionEnum.JUMP_ID_NOT_NULL);
//			}
//		}
		collocateService.addCollocateInfo(collocateInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description 添加装修模版信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/addGiftCardCollocateInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGiftCardCollocateInfo(CollocateInfoDto collocateInfoDto) throws GlobalException{
		//不是美礼卡类型
		if(null == collocateInfoDto.getCollocateType() || collocateInfoDto.getCollocateType() != 0) {
			throw new GlobalException(CollocateExceptionEnum.IS_NOT_GIFTCARD_TYPE);
		}
		if(null == collocateInfoDto.getSortNo()) {
			throw new GlobalException(CollocateExceptionEnum.SORTNO_NOT_NULL);
		}
		if(null == collocateInfoDto.getImage() || "".equals(collocateInfoDto.getImage())) {
			throw new GlobalException(CollocateExceptionEnum.IMAGE_NOT_NULL);
		}
		if(null == collocateInfoDto.getJumpType()) {
			throw new GlobalException(CollocateExceptionEnum.JUMP_TYPE_NOT_NULL );
		}
		if(null == collocateInfoDto.getJumpId()) {
			throw new GlobalException(CollocateExceptionEnum.JUMP_ID_NOT_NULL);
		}
		collocateService.addGiftCardCollocateInfo(collocateInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description 添加装修模版商品信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @param result
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/addCollocateGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addCollocateGoods(@RequestBody List<CollocateGoodsDto> collocateGoodsList) throws GlobalException{
		if(null == collocateGoodsList || collocateGoodsList.size() ==0 ) {
			throw new GlobalException(CollocateExceptionEnum.GOODS_ID_NOT_NULL);
		}
		collocateService.addCollocateGoods(collocateGoodsList);
		return new ResponseInfo();
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
	@RequestMapping(path = "/getCollocateDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCollocateDetail(@Valid CollocateDto collocateDto, BindingResult result) throws GlobalException{
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		CollocateDetailDto collocateDetailDto = collocateService.getCollocateDetail(collocateDto);
		return new ResponseInfo(collocateDetailDto);
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
	
}
