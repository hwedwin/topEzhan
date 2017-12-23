package com.topaiebiz.transaction.cart.mobile.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.cart.dto.GoodsCartNextDto;
import com.topaiebiz.transaction.cart.dto.GoodsFavoriteDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartEntityDto;
import com.topaiebiz.transaction.cart.dto.ModifyShoppingCartDto;
import com.topaiebiz.transaction.cart.dto.ShoppingCartAppDto;
import com.topaiebiz.transaction.cart.exception.ShoppingCartExceptionEnum;
import com.topaiebiz.transaction.cart.service.ShoppingCartService;

/**
 * 
 * Description 购物车&收藏夹的控制层
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月8日 下午4:11:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/transaction/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 
	 * Description app端商品加入到购物车
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartEntityDto(goodsId:商品sku表的id;memberId:会员id)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addShoppingCart(String token, @Valid ShoppingCartEntityDto shoppingCartEntityDto,
			BindingResult result) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		// 会员id
		Long memberId = tokenDetail.getMemberId();
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		shoppingCartEntityDto.setMemberId(memberId);
		Integer callback = shoppingCartService.saveGoodsCart(shoppingCartEntityDto);
		return new ResponseInfo(callback);
	}

	/**
	 * 
	 * Description app端根据会员id查询购物车中的列表详细信息
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartDto(会员id:memberId)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getShoppingCartByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getShoppingCartByMemberId(String token) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		// 会员id
		Long memberId = tokenDetail.getMemberId();
		Long storeId = tokenDetail.getStoreId();
		ShoppingCartAppDto itemDto = shoppingCartService.getShoppingCartByMemberId(memberId, storeId);
		return new ResponseInfo(itemDto);
	}

	/**
	 * 
	 * Description 购物车的下一步
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartDto(会员id:memberId;会员收货地址的地址区域:districtId)
	 * @return
	 */
	@RequestMapping(value = "/shoppingCartStatistics", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo shoppingCartStatistics(ShoppingCartDto shoppingCartDto) {
		GoodsCartNextDto goodsCartNextDto = shoppingCartService.shoppingCartStatistics(shoppingCartDto);
		return new ResponseInfo(goodsCartNextDto);
	}

	/**
	 * 
	 * Description app端购物车中修改商品的数量
	 * 
	 * Author zhushuyong
	 * 
	 * @param shoppingCartEntityDto(goodsNum:购物车中对应商品的对应数量;id:购物车的id)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/modifyShoppingCartNum", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo modifyShoppingCartNum(String token, @Valid ModifyShoppingCartDto modifyShoppingCartDto,
			BindingResult result) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		return new ResponseInfo(shoppingCartService.modifyGoodsCartNum(modifyShoppingCartDto));
	}

	/**
	 * 
	 * Description app端根据id删除购物车中指定的商品
	 * 
	 * Author zhushuyong
	 * 
	 * @param (ids传入的购物车对应的id，可以为数组)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/deleteShoppingCart", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelShoppingCart(String token, Long[] id) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		if (StringUtils.isEmpty(id) || id.length < 0) {
			throw new GlobalException(ShoppingCartExceptionEnum.GOODSCART_ID_NOT_NULL);
		}
		return new ResponseInfo(shoppingCartService.removeGoodsCart(id));
	}

	/**
	 * Description app端将商品从购物车中移除到收藏夹
	 * 
	 * Author zhushuyong
	 * 
	 * @param goodsFavoriteDto(goodsId:商品sku表的id;memberId:会员id)
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/saveGoodsFavorite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo saveGoodsFavorite(String token, Long[] itemIds) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		return new ResponseInfo(shoppingCartService.addGoodsFavorite(memberId, itemIds));
	}

	/**
	 * Description app端我的足迹添加
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param itemIds
	 *            商品id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/saveGoodsFootprint", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo saveGoodsFootprint(String token, Long[] itemIds) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		return new ResponseInfo(shoppingCartService.addsaveGoodsFootprint(memberId, itemIds));
	}

	/**
	 * Description 我的足迹列表
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getGoodsFootprintList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsFootprintList(String token, Page<ItemDto> page, ItemDto itemDto)
			throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		Page<ItemDto> itemDtos = shoppingCartService.getGoodsFootprintListByMemberId(page, itemDto, memberId);
		return new ResponseInfo(itemDtos);
	}

	/**
	 * Description 删除我的足迹
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param id
	 *            商品id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/cancelGoodsFootprint", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGoodsFootprint(String token, Long[] id) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		return new ResponseInfo(shoppingCartService.removelGoodsFootprint(id));
	}

	/**
	 * Description app端收藏夹列表
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getGoodsFavoriteList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsFavoriteList(String token, Page<ItemDto> page) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		Page<ItemDto> itemDtos = shoppingCartService.getGoodsFavoriteListByMemberId(page, memberId);
		return new ResponseInfo(itemDtos);
	}

	/**
	 * Description app端删除收藏夹
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param id
	 *            收藏夹的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/cancelGoodsFavorite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGoodsFavorite(String token, Long[] id) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		return new ResponseInfo(shoppingCartService.removelGoodsFavorite(id));
	}

	/**
	 * Description app端购物车中的编辑中的确定
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param oldSkuId
	 *            旧的skuId
	 * @param newSkuId
	 *            新的skuId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editAppShoppingCart", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editAppShoppingCart(String token, Long oldSkuId, Long newSkuId) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		return new ResponseInfo(shoppingCartService.modfiyAppShoppingCart(memberId, oldSkuId, newSkuId));
	}

	/**
	 * Description app端将商品分享
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param itemIds
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/saveGoodssharing", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo saveGoodssharing(String token, Long[] itemIds) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(ShoppingCartExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		Long memberId = tokenDetail.getMemberId();
		return new ResponseInfo(shoppingCartService.saveGoodssharing(memberId, itemIds));
	}

	/**
	 * Description 查询此商品是否被选为收藏
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param itemId
	 *            商品id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/findGoodsFavorite", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findGoodsFavorite(String token, Long itemId) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = null;
		if (tokenDetail != null) {
			memberId = tokenDetail.getMemberId();
		}
		GoodsFavoriteDto goodsFavorite = shoppingCartService.findGoodsFavorite(memberId, itemId);
		return new ResponseInfo(goodsFavorite);
	}

}
