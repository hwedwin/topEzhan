package com.topaiebiz.promotion.mgmt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEnrolDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionEntryDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionSingleDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.promotion.mgmt.exception.PromotionExceptionEnum;
import com.topaiebiz.promotion.mgmt.service.PromotionGoodsService;
import com.topaiebiz.promotion.mgmt.service.PromotionService;

/**
 * 
 * Description： 营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午1:20:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping(path = "/promotion/mgmt")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private PromotionGoodsService promotionGoodsService;

	/**
	 * 
	 * Description 获取营销活动列表
	 * 
	 * Author Joe
	 * 
	 * @param name
	 * @param marketState
	 * @return
	 */
	@RequestMapping(path = "/getPromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) throws GlobalException {

		Page<PromotionDto> promotionDtoList = null;

		if (null != promotionDto.getTypeId()) {

			if (promotionDto.getTypeId() == 1) {

				// 单品折扣
				promotionDtoList = promotionService.getPromotionListSingle(page, promotionDto);

			} else if (promotionDto.getTypeId() == 2) {

				// 一口价
				promotionDtoList = promotionService.getPromotionListSingle(page, promotionDto);

			} else if (promotionDto.getTypeId() == 3) {

				// 满减
				promotionDtoList = promotionService.getPromotionListReducePrice(page, promotionDto);

			} else if (promotionDto.getTypeId() == 4) {

				// 平台优惠券
				promotionDtoList = promotionService.getPromotionListCoupon(page, promotionDto);

			} else if (promotionDto.getTypeId() == 5) {

				// 平台优惠码
				promotionDtoList = promotionService.getPromotionListCouponCode(page, promotionDto);

			} else if (promotionDto.getTypeId() == 6) {

				// 秒杀活动
				promotionDtoList = promotionService.getPromotionListSingle(page, promotionDto);

			} else if (promotionDto.getTypeId() == 7) {

				// 店铺优惠券
				promotionDtoList = promotionService.getPromotionListStoreCoupon(page, promotionDto);

			} else if (promotionDto.getTypeId() == 8) {

				// 包邮
				promotionDtoList = promotionService.getPromotionListFreeShipping(page, promotionDto);

			}

		} else {

			// 查询所有活动
			promotionDtoList = promotionService.getPromotionList(page, promotionDto);

		}

		return new ResponseInfo(promotionDtoList);

	}

	
	/**
	 * 
	 * Description 添加单品折扣
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionSingle", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionSingle(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		Long promotionId = promotionService.addPromotionSingle(promotion);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 添加/修改单品折扣商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editPromotionSingleGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionSingleGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.modifyPromotionSingleGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 保存/发布单品折扣活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotionSingleGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionSingleGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotionSingle(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 添加一口价活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionPrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionPrice(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);
		
		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		Long promotionId = promotionService.savePromotionPrice(promotion);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 添加/修改一口价商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editPromotionPriceGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionPriceGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.modifyPromotionPriceGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 保存/发布一口价活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotionPriceGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionPriceGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotionPriceGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 添加秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionSeckill", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionSeckill(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		Long promotionId = promotionService.savePromotionSeckill(promotion);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 修改秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editPromotionSeckill", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionSeckill(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		/** 判断id是否为空 */
		if (null == promotionSingleDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);
		Long promotionId = promotionService.modifyPromotionSeckill(promotionSingleDto);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 添加/修改秒杀活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editPromotionSeckillGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionSeckillGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.modifyPromotionSeckillGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 保存/发布秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotionSeckillGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionSeckillGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		// 商品列表不得为空
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotionSeckillGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 修改单品级活动(单品折扣,一口价,秒杀)
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editPromotionSingle", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionSingle(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 判断id是否为空 */
		if (null == promotionSingleDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());

		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);
		Long promotionId = promotionService.modifyPromotionSingle(promotionSingleDto);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 添加满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionSingleDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionReducePrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionReducePrice(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);
		
		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionDto, promotion);
		Long promotionId = promotionService.savePromotionReducePrice(promotion);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 修改满减活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editPromotionReducePrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionReducePrice(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		/** 判断id是否为空 */
		if (null == promotionDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		Long promotionId = promotionService.modifyPromotionReducePrice(promotionDto);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 添加店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionStoreCoupon", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionStoreCoupon(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionDto, promotion);
		Long promotionId = promotionService.savePromotionStoreCoupon(promotion);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 修改店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editPromotionStoreCoupon", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionStoreCoupon(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 判断id是否为空 */
		if (null == promotionDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		Long promotionId = promotionService.modifyPromotionStoreCoupon(promotionDto);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 添加/修改活动商品(满减,包邮,店铺优惠券,平台优惠券,平台优惠码)
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editPromotionGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.modifyPromotionGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 保存/发布活动(满减,包邮,店铺优惠券,平台优惠券)
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotion(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		/** 商品列表不得为空*/
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotion(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 添加包邮活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionFreeShipping", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionFreeShipping(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 包邮条件值不得为空*/
		if (null == promotionSingleDto.getCondValue()) {
			throw new GlobalException(PromotionExceptionEnum.CONDVALUE_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionSingleDto, promotion);
		Long promotionId = promotionService.savePromotionFreeShipping(promotion);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 修改包邮活动
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editPromotionFreeShipping", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionFreeShipping(@Valid PromotionSingleDto promotionSingleDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 判断id是否为空 */
		if (null == promotionSingleDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		/** 包邮条件值不得为空*/
		if (null == promotionSingleDto.getCondValue()) {
			throw new GlobalException(PromotionExceptionEnum.CONDVALUE_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionSingleDto.getPromotionStart());
		Date endDate = sdf.parse(promotionSingleDto.getPromotionEnd());
		promotionSingleDto.setStartTime(startDate);
		promotionSingleDto.setEndTime(endDate);

		Long promotionId = promotionService.modifyPromotionFreeShipping(promotionSingleDto);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description： 添加平台优惠劵
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionCoupon", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionCoupon(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionDto, promotion);
		Long promotionId = promotionService.savePromotionCoupon(promotion);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description： 修改平台优惠券
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/editCoupon", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionCoupon(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		/** 对营销活动字段进行校验 */
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 判断id是否为空 */
		if (null == promotionDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		Long promotionId = promotionService.modifyPromotionCoupon(promotionDto);
		return new ResponseInfo(promotionId);

	}

	/**
	 * 
	 * Description 添加平台优惠码
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/addPromotionCouponCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionCouponCode(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException, ParseException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		if (null == promotionDto.getAmount()) {// 验证发放数量
			throw new GlobalException(PromotionExceptionEnum.QUANTITY_ISSUED_SHALL_NOT_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(promotionDto.getPromotionStart());
		Date endDate = sdf.parse(promotionDto.getPromotionEnd());
		promotionDto.setStartTime(startDate);
		promotionDto.setEndTime(endDate);

		PromotionEntity promotion = new PromotionEntity();
		BeanUtils.copyProperties(promotionDto, promotion);
		Long promotionId = promotionService.savePromotionCouponCode(promotion);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 修改平台优惠码
	 * 
	 * Author Joe
	 * 
	 * @param promotionDto
	 * @param result
	 * @param promotionGoodsList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editPromotionCouponCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editPromotionCouponCode(@Valid PromotionDto promotionDto, BindingResult result) throws GlobalException {

		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}

		if (null == promotionDto.getAmount()) {// 验证发放数量
			throw new GlobalException(PromotionExceptionEnum.QUANTITY_ISSUED_SHALL_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		Long promotionId = promotionService.modifyPromotionCouponCode(promotionDto);
		return new ResponseInfo(promotionId);
	}

	/**
	 * 
	 * Description 保存/发布平台优惠码活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotionCouponCodeGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionCouponCodeGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {

		// 商品列表不得为空
		if (promotionGoodsList.size() == 0) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotionCouponCodeGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 停止优惠活动
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editStop", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editStopPromotion(Long id) throws GlobalException {

		/** 判断id是否为空 */
		if (null == id) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		Integer modifyStopPromotionCouponCode = promotionService.modifyStopPromotion(id);
		return new ResponseInfo(modifyStopPromotionCouponCode);
	}

	/**
	 * 
	 * Description： 删除营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelPromotion(String ids) throws GlobalException {

		/** 判断id是否为空 */
		if (null == ids) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.removePromotion(ids);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 修改营销活动回显
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findPromotionById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findPromotionById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (null == id) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		PromotionDto findPromotionById = promotionService.findPromotionById(id);
		return new ResponseInfo(findPromotionById);
	}

	/**
	 * 
	 * Description 获取平台商品列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getPlatformGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatformGoodsList(Page<ItemDto> page, ItemDto itemDto) {
		Page<ItemDto> itemDtoList = promotionService.getPlatformGoodsList(page, itemDto);
		return new ResponseInfo(itemDtoList);
	}

	/**
	 * 
	 * Description 获取商家商品列表
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsList(Page<ItemDto> page, ItemDto itemDto) {
		Page<ItemDto> itemDtoList = promotionService.getGoodsList(page, itemDto);
		return new ResponseInfo(itemDtoList);

	}

	/**
	 * 
	 * Description 活动选择保存商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionGoodsDtoList
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addPromotionGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPromotionGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) throws GlobalException {
		/** 判断id是否为空 */
		if (null == promotionGoodsList.get(0).getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.savePromotionGoods(promotionGoodsList);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 获取营销活动所选商品
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getPromotionGoods", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionGoods(Page<PromotionGoodsDto> page, ItemDto itemDto) throws GlobalException {

		/** 判断id是否为空 */
		if (null == itemDto.getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		Page<PromotionGoodsDto> promotionGoodsDtoList = promotionService.getPromotionGoods(page, itemDto);
		return new ResponseInfo(promotionGoodsDtoList);
	}

	/**
	 * 
	 * Description 根据商品查询所能使用的营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(path = "/findPromotionByGoods", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findPromotionByGoods(List<Long> goodsSkuDtoList) {

		// List<PromotionDto> findPromotionByGoods =
		// promotionService.findPromotionByGoods(goodsSkuDtoList);

		return new ResponseInfo();

	}

	/**
	 * 
	 * Description 发布活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/releasePromotion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo releasePromotion(Long id, Long typeId) throws GlobalException {

		/** 判断id是否为空 */
		if (null == id) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		/** 判断活动类型是否为空 */
		if (null == typeId) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_TYPE_NOT_NULL);
		}
		promotionService.releasePromotion(id, typeId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 取消活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGoods(Long promotionId, Long itemId) throws GlobalException {

		/** 判断id是否为空 */
		if (null == promotionId) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.removeGoods(promotionId, itemId);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 根据活动id和itmId回显商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	@RequestMapping(path = "/getPromotionGoodsByPromotionIdAndItemid", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionGoodsByPromotionIdAndItemid(Long promotionId, Long itemId) {

		/** 判断id是否为空 */
		if (null == promotionId) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		List<PromotionGoodsDto> promotionGoodsList = promotionService.getPromotionGoodsByPromotionIdAndItemid(promotionId, itemId);
		return new ResponseInfo(promotionGoodsList);
	}
	
	/**
	 * 
	 * Description 平台报名活动列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/getPlatformEnrolPromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatformEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) throws ParseException {
		
		Page<PromotionDto> promotionDtoList = promotionService.getPlatformEnrolPromotionList(page,promotionDto);
		
		return new ResponseInfo(promotionDtoList);
	}
	
	/**
	 * 
	 * Description 发起报名
	 * 
	 * Author Joe   
	 * 
	 * @param promotionEnrolDto
	 * @param result
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/editInitiateEnrol", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editInitiateEnrol(@Valid PromotionEnrolDto promotionEnrolDto, BindingResult result) throws ParseException {
		/** 判断id是否为空 */
		if (null == promotionEnrolDto.getId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		promotionService.modifyInitiateEnrol(promotionEnrolDto);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description 发起报名获取活动列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@RequestMapping(path = "/getEnrolPromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		Page<PromotionDto> promotionList = promotionService.getEnrolPromotionList(page, promotionDto);
		return new ResponseInfo(promotionList);
	}
	
	/**
	 * 
	 * Description 营销活动报名商家列表
	 * 
	 * Author Joe    
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(path = "/getPromotionEnrolStoreList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionEnrolStoreList(Page<PromotionEntryDto> page, PromotionEntryDto promotionEntryDto) throws ParseException {
		Page<PromotionEntryDto> promotionEntryList = promotionService.getPromotionEnrolStoreList(page, promotionEntryDto);
		return new ResponseInfo(promotionEntryList);
	}
	
	/**
	 * 
	 * Description 查看店铺报名详情
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/getPromotionEnrolStore", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionEnrolStore(Long id) {
		if(null == id) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		PromotionEntryDto promotionEntryDto = promotionService.getPromotionEnrolStore(id);
		return new ResponseInfo(promotionEntryDto);
	}
	
	/**
	 * 
	 * Description 报名活动商家不通过  
	 * 
	 * Author Joe   
	 * 
	 * @return
	 */
	@RequestMapping(path = "/editStoreAuditNonconformity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editStoreAuditNonconformity(Long id) {
		if(null == id) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		promotionService.modifyStoreAuditNonconformity(id);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description 商家报名商品
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	@RequestMapping(path = "/getStoreEnrolGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreEnrolGoodsList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto) {
		if(promotionGoodsDto.getPromotionId() == null || promotionGoodsDto.getStoreId() == null) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		Page<PromotionGoodsDto> promotionGoodsList = promotionGoodsService.getStoreEnrolGoodsList(page,promotionGoodsDto);
		return new ResponseInfo(promotionGoodsList);
	}
	
	
	/**
	 * 
	 * Description 商家营销活动报名列表(全部活动)
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@RequestMapping(path = "/getStoreAllPromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreAllPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		Page<PromotionDto> promotionDtoList = promotionService.selectStoreAllPromotionList(page,promotionDto);
		return new ResponseInfo(promotionDtoList);
	}
	
	/**
	 * 
	 * Description 商家营销活动报名列表(已报名活动)
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionDto
	 * @return
	 */
	@RequestMapping(path = "/getStorePromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreEnrolPromotionList(Page<PromotionDto> page, PromotionDto promotionDto) {
		Page<PromotionDto> promotionDtoList = promotionService.getStoreEnrolPromotionList(page,promotionDto);
		return new ResponseInfo(promotionDtoList);
	}

	/**
	 * 
	 * Description 报名商家商品审核列表  
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	@RequestMapping(path = "/getStoreGoodsAuditList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreGoodsAuditList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto) {
		/** 活动id不可为空*/
		if(null == promotionGoodsDto.getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		/** 店铺id不可为空*/
		if(null == promotionGoodsDto.getStoreId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_STORE_OWNED_NOT_NULL);
		}
		Page<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsService.getStoreGoodsAuditList(page, promotionGoodsDto);
		return new ResponseInfo(promotionGoodsDtoList);
	}
	
	/**
	 * 
	 * Description 审核单个sku商品 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsId
	 * @param state
	 * @return
	 */
	@RequestMapping(path = "/editAuditSingleSkuGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAuditSingleSkuGoods(Long promotionGoodsId, Integer state) {
		if(null == promotionGoodsId) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		if(null == state) {
			throw new GlobalException(PromotionExceptionEnum.PRODUCT_AUDIT_STATE_CANNOT_BE_EMPTY);
		}
		promotionGoodsService.modifyAuditSingleSkuGoods(promotionGoodsId,state);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description sku商品审核完成
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsList
	 * @return
	 */
	@RequestMapping(path = "/editAuditSkuGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAuditSkuGoods(@RequestBody List<PromotionGoodsDto> promotionGoodsList) {
		promotionGoodsService.modifyAuditSkuGoods(promotionGoodsList);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description 审核item商品通过/不通过  
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsDto
	 * @return
	 */
	@RequestMapping(path = "/editAuditItemGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAuditItemGoods(PromotionGoodsDto promotionGoodsDto) {
		if(null == promotionGoodsDto.getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		if(null == promotionGoodsDto.getState()) {
			throw new GlobalException(PromotionExceptionEnum.PRODUCT_AUDIT_STATE_CANNOT_BE_EMPTY);
		}
		if(null == promotionGoodsDto.getStoreId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_STORE_OWNED_NOT_NULL);
		}
		if(null == promotionGoodsDto.getItemId()) {
			throw new GlobalException(PromotionExceptionEnum.PRODUCT_ITEM_ID_NOT_NULL);
		}
		promotionGoodsService.modifyAuditItemGoods(promotionGoodsDto);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description 商家商品审核完成 
	 * 
	 * Author Joe   
	 * 
	 * @return
	 */
	@RequestMapping(path = "/editAuditGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAuditGoods(PromotionGoodsDto promotionGoodsDto) {
		if(null == promotionGoodsDto.getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_NOT_NULL);
		}
		if(null == promotionGoodsDto.getStoreId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTIONGOODS_STORE_OWNED_NOT_NULL);
		}
		promotionGoodsService.modifyAuditGoods(promotionGoodsDto);
		return new ResponseInfo();
	}
	
}
