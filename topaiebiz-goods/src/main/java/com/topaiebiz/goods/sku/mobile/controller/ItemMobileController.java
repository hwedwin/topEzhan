package com.topaiebiz.goods.sku.mobile.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.sku.dto.ItemAppDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.service.ItemService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description app端商品sku控制层
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午2:35:46
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@RestController
@RequestMapping("/app/goods/item")
public class ItemMobileController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * Description app端根据年龄段,品牌，分类商家查询商品
	 * 
	 * Author Hedda
	 * 
	 * @param ageId
	 *            年龄段id
	 * @param belongStore
	 *            商家id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getItemListByItem", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getItemListByItem(String token,Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if(tokenDetail != null) {
			//店铺id
			Long storeId = tokenDetail.getStoreId();
			itemDto.setBelongStore(storeId);
		}
		Page<ItemDto> itemDtos = itemService.getItemListByItem(page, itemDto);
		return new ResponseInfo(itemDtos);
	}

	/**
	 * Description app根据商品id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findAppItemById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findAppItemById(String token,Long id) throws GlobalException{
		ItemAppDto itemDto = itemService.findAppItemById(token,id);
		return new ResponseInfo(itemDto);
	}
	
	/**
	 * Description 根据商品id查询对应商品评价
	 * 
	 * Author Hedda  
	 * 
	 * @param id 商品id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findAppSkuCommentById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findAppSkuCommentById(Long id,Integer type) throws GlobalException{
		List<GoodsSkuCommentDto> goodsSkuCommentDtos = itemService.findAppSkuCommentById(id,type);
		return new ResponseInfo(goodsSkuCommentDtos);
	}

}
