package com.topaiebiz.distribution.merchant.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionAndItemDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionLogDto;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionEntity;
import com.topaiebiz.distribution.merchant.service.GoodsDistributionAllocationService;

/**
 * Description： 商品分销控制层。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:04:11
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/distribution/goods")
public class GoodsDistributionController {

	@Autowired
	private GoodsDistributionAllocationService goodsDistributionAllocationService;

	/**
	 * Description： 添加商品分销比例。
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * @param goodsDistributionAllocationDtos
	 *            商品分销信息
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addGoodsDistributionAllocation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGoodsDistributionAllocation(
			@RequestBody GoodsDistributionAndItemDto goodsDistributionAndItemDtos, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 获取参数itemId */
		Long[] itemId = goodsDistributionAndItemDtos.getItemId();
		List<GoodsDistributionEntity> goodsDistributionAllocationEntitis = new ArrayList<GoodsDistributionEntity>();
		/** 获取该会员分销dto */
		List<GoodsDistributionDto> goodsDistributionAllocationDtos = goodsDistributionAndItemDtos
				.getGoodsDistributionAllocationDtos();
		for (GoodsDistributionDto goodsDistributionDto : goodsDistributionAllocationDtos) {
			GoodsDistributionEntity goodsDistributionEntity = new GoodsDistributionEntity();
			BeanUtils.copyProperties(goodsDistributionDto, goodsDistributionEntity);
			goodsDistributionAllocationEntitis.add(goodsDistributionEntity);
		}
		return new ResponseInfo(goodsDistributionAllocationService.saveGoodsDistributionAllocation(itemId,
				goodsDistributionAllocationEntitis));
	}

	/**
	 * Description： 根据itemId商品分销比例(回显数据)。
	 * 
	 * Author Harry
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findGoodsDistributionAllocationByItemId", method = RequestMethod.GET)
	@ResponseBody
	private ResponseInfo findGoodsDistributionAllocationByItemId(Long itemId) throws GlobalException {
		List<GoodsDistributionDto> goodsDistributionAllocationDtos = goodsDistributionAllocationService
				.findGoodsDistributionAllocationByItemId(itemId);
		return new ResponseInfo(goodsDistributionAllocationDtos);
	}

	/**
	 * Description：定时计算店铺分润记录总金额/分润金额。
	 * 
	 * Author Harry
	 * 
	 * @param goodsDistributionLogs
	 * @return
	 * @throws GlobalException
	 */
	/*@Scheduled(cron = "00 10 10 ? * * ")
	@RequestMapping(path = "/addGoodsDistributionLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGoodsDistributionLog() throws GlobalException {
		return new ResponseInfo(goodsDistributionAllocationService.saveGoodsDistributionLog());
	}*/

	/**
	 * Description： 平台店铺分销记录分页列表加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto
	 * @return
	 */
	@RequestMapping(path = "getPlatformGoodsDistributionLogList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatformGoodsDistributionLogList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		Page<GoodsDistributionLogDto> goodsdistributionallocationlist = goodsDistributionAllocationService
				.getPlatformGoodsDistributionList(page, goodsDistributionLogDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}

	/**
	 * Description： 通过StoreID查看会员记录分销金额列表加分页。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto
	 * @return
	 */
	@RequestMapping(path = "/findMemberDistributionListByStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findMemberDistributionListByStoreId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionallocationlists = goodsDistributionAllocationService
				.findMemberDistributionListByStoreId(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionallocationlists);
	}

	/**
	 * Description： 通过会员id查看会员购买的商品列表。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsDistributionLogDto
	 *            店铺分润分销记录Dto
	 * @return
	 */
	@RequestMapping(path = "/findGoodsMemberDistributionListByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findGoodsMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionallocationlists = goodsDistributionAllocationService
				.findGoodsMemberDistributionListByMemberId(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionallocationlists);
	}

	/**
	 * Description： 商家端店铺分销记录分页列表加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param goodsDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantGoodsDistributionLogList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantGoodsDistributionLogList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		Page<GoodsDistributionLogDto> goodsdistributionallocationlist = goodsDistributionAllocationService
				.getMerchantGoodsDistributionLogList(page, goodsDistributionLogDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}

	/**
	 * Description： 通过skuId查询会员购买的分销记录。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param goodsDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "/findMemberDistributionListByskuId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findMemberDistributionListByskuId(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		Page<GoodsDistributionLogDto> goodsdistributionallocationlists = goodsDistributionAllocationService
				.findMemberDistributionListByskuId(page, goodsDistributionLogDto);
		return new ResponseInfo(goodsdistributionallocationlists);
	}

	/**
	 * Description： 商家端选售商品。
	 * 
	 * Author Harry
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/addStoreDistributionGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addStoreDistributionGoods(Long[] id) throws GlobalException {
		return new ResponseInfo(goodsDistributionAllocationService.saveStoreDistributionGoods(id));
	}
	
	/**
	 * Description： 商家端取消选售商品。
	 * 
	 * Author Harry
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(path = "/cancelStoreDistributionGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelStoreDistributionGoods(Long id) throws GlobalException {
		return new ResponseInfo(goodsDistributionAllocationService.removeStoreDistributionGoods(id));
	}

}
