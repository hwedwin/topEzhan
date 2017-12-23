package com.topaiebiz.goods.spu.controller;

import java.util.ArrayList;
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
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuPictureDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuAttrEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuPictureEntity;
import com.topaiebiz.goods.spu.exception.GoodsSpuExceptionEnum;
import com.topaiebiz.goods.spu.service.GoodsSpuService;

/**
 * Description 商品SPU管理控制层
 * 
 * Author Hedda
 * 
 * Date 2017年9月29日 下午8:01:27
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@RestController
@RequestMapping("/goods/spu")
public class GoodsSpuController {

	@Autowired
	private GoodsSpuService goodsSpuService;

	/**
	 * Description 商品spu列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSpuDto
	 *            商品spudto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getListGoodsSpu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListProductSpuInfoDto(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto)
			throws GlobalException {
		Page<GoodsSpuDto> listgoodsSpu = goodsSpuService.getListGoodsSpuDto(page, goodsSpuDto);
		return new ResponseInfo(listgoodsSpu);
	}
	
	/**
	 * Description 商家与平台根据商品模板发布商品列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSpuDto
	 *            商品spudto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getGoodsSpuListByBelongCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsSpuListByStoreId(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto)
			throws GlobalException {
		Page<GoodsSpuDto> listgoodsSpu = goodsSpuService.getGoodsSpuListByBelongCategory(page, goodsSpuDto);
		return new ResponseInfo(listgoodsSpu);
	}

	/**
	 * Description 商品spu批量逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param ids
	 *            商品spu的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/cancelGoodsSpu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGoodsSpu(Long[] id) throws GlobalException {
		goodsSpuService.removeGoodsSpu(id);
		return new ResponseInfo("删除成功！");
	}

	/**
	 * Description 查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getRecentlyCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getRecentlyCategoryList() throws GlobalException {
		List<BackendCategorysDto> backendCategorysDto = goodsSpuService.getRecentlyCategoryList();
		return new ResponseInfo(backendCategorysDto);
	}

	/**
	 * Description 商品spu添加
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品spu
	 * @param goodsSpuAttrDtos
	 *            商品spu属性
	 * @param goodsSpuPictureDtos
	 *            商品spu图片
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addGoodsSpu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGoodsSpu(@Valid @RequestBody GoodsSpuDto goodsSpuDto, BindingResult result)
			throws GlobalException {
		/** 对商品spu字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		List<GoodsSpuAttrDto> goodsSpuAttrDtos = goodsSpuDto.getGoodsSpuAttrDtos();
		List<GoodsSpuAttrEntity> goodsSpuAttrEntities = new ArrayList<GoodsSpuAttrEntity>();
		List<GoodsSpuPictureDto> goodsSpuPictureDtos = goodsSpuDto.getGoodsSpuPictureDtos();
		List<GoodsSpuPictureEntity> goodsSpuPictureEntities = new ArrayList<GoodsSpuPictureEntity>();
		GoodsSpuEntity goodsSpu = new GoodsSpuEntity();
		BeanUtils.copyProperties(goodsSpuDto, goodsSpu);
		// 对商品spu属性进行copy
		if (!(goodsSpuAttrDtos == null || goodsSpuAttrDtos.size() == 0)) {
			for (GoodsSpuAttrDto goodsSpuAttrDto : goodsSpuAttrDtos) {
				if(goodsSpuAttrDto.getPrice() == null) {
					throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ATTR_PRICE_NOT_NULL);
				}
				GoodsSpuAttrEntity goodsSpuAttr = new GoodsSpuAttrEntity();
				BeanUtils.copyProperties(goodsSpuAttrDto, goodsSpuAttr);
				goodsSpuAttrEntities.add(goodsSpuAttr);

			}
		}
		// 对商品spu图片进行copy
		if (!(goodsSpuPictureDtos == null || goodsSpuPictureDtos.size() == 0)) {
			for (GoodsSpuPictureDto goodsSpuPictureDto : goodsSpuPictureDtos) {
				if(goodsSpuPictureDto.getName() == null) {
					throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_PICTURE_NAME_NOT_NULL);
				}
				GoodsSpuPictureEntity goodsSpuPicture = new GoodsSpuPictureEntity();
				BeanUtils.copyProperties(goodsSpuPictureDto, goodsSpuPicture);
				goodsSpuPictureEntities.add(goodsSpuPicture);
			}
		}
		return new ResponseInfo(goodsSpuService.saveGoodsSpu(goodsSpu, goodsSpuAttrEntities, goodsSpuPictureEntities));
	}

	/**
	 * Description 商品spu修改
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品spu
	 * @param goodsSpuAttrDtos
	 *            商品spu属性
	 * @param goodsSpuPictureDtos
	 *            商品spu图片
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editGoodsSpu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editGoodsSpu(@Valid @RequestBody GoodsSpuDto goodsSpuDto, BindingResult result)
			throws GlobalException {
		/** 对商品spu字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		List<GoodsSpuAttrDto> goodsSpuAttrDtos = goodsSpuDto.getGoodsSpuAttrDtos();
		List<GoodsSpuPictureDto> goodsSpuPictureDtos = goodsSpuDto.getGoodsSpuPictureDtos();
		return new ResponseInfo(goodsSpuService.modifyGoodsSpu(goodsSpuDto, goodsSpuAttrDtos, goodsSpuPictureDtos));
	}

	/**
	 * Description 根据id查询商品spu信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品spu的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findGoodsSpuById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findGoodsSpuById(Long id) throws GlobalException {
		// 商品spu对象
		GoodsSpuDto goodsSpuDto = goodsSpuService.findGoodsSpuById(id);
		// 商品spu属性
		List<GoodsSpuAttrDto> goodsSpuAttrDtos = goodsSpuService.findGoodsSpuAttrById(id);
		// 商品spu图片
		List<GoodsSpuPictureDto> goodsSpuPictureDtos = goodsSpuService.findGoodsSpuPictureById(id);
		if (!(goodsSpuAttrDtos == null || goodsSpuAttrDtos.size() == 0)) {
			goodsSpuDto.setGoodsSpuAttrDtos(goodsSpuAttrDtos);
		}
		if (!(goodsSpuPictureDtos == null || goodsSpuPictureDtos.size() == 0)) {
			goodsSpuDto.setGoodsSpuPictureDtos(goodsSpuPictureDtos);
		}
		return new ResponseInfo(goodsSpuDto);
	}
	
	/**
	 * Description 修改商品spu的类目
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品的id
	 * @param belongCategory
	 *            商品后台第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editGoodsSpuCcategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editGoodsSpuCategory(Long spuId, Long belongCategory) throws GlobalException {
		return new ResponseInfo(goodsSpuService.modifyGoodsSpuCategory(spuId, belongCategory));
	}

	/**
	 * Description 查看商品编码验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getGoodsSpuByCode", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsSpuByCode(GoodsSpuDto goodsSpuDto) throws GlobalException {
		GoodsSpuDto goodsSpu = goodsSpuService.getGoodsSpuByCode(goodsSpuDto);
		return new ResponseInfo(goodsSpu);

	}

	/**
	 * Description 查看商品名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getGoodsSpuByName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getGoodsSpuByName(GoodsSpuDto goodsSpuDto) throws GlobalException {
		GoodsSpuDto goodsSpu = goodsSpuService.getGoodsSpuByName(goodsSpuDto);
		return new ResponseInfo(goodsSpu);

	}

}
