package com.topaiebiz.goods.brand.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.brand.dao.BrandDao;
import com.topaiebiz.goods.brand.dao.SuitableAgeDao;
import com.topaiebiz.goods.brand.dto.BrandDto;
import com.topaiebiz.goods.brand.dto.SuitableAgeDto;
import com.topaiebiz.goods.brand.entity.BrandEntity;
import com.topaiebiz.goods.brand.exception.BrandExceptionEnum;
import com.topaiebiz.goods.brand.service.BrandService;

/**  
 * Description 商品品牌实现类  
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午4:15:52 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandDao brandDao;
	
	/** 年龄段。*/
	@Autowired
	private SuitableAgeDao suitableAgeDao;
	
	public Integer saveBrand (BrandEntity brand) throws GlobalException{
		Integer i = null;
		/**对商品品牌名称进行重复验证*/
		BrandEntity findBrandByName = brandDao.selectBrandByName(brand.getName());
		if (findBrandByName != null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_NAME_NOT_REPETITION);
		}
		Long id = SecurityContextUtils.getCurrentSystemUser().getId();
		brand.setCreatorId(id);
		brand.setCreatedTime(new Date());
		i = brandDao.insert(brand);
		return i;
	}

	public Integer modifyBrand(BrandDto brandDto)throws GlobalException {
		/**对商品品牌名称进行重复验证*/
		BrandDto findBrandByName = brandDao.selectBrandByNameAndId(brandDto);
		if (findBrandByName != null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_NAME_NOT_REPETITION);
		}
		if(brandDto.getBrandImage()=="") {
			brandDto.setBrandImage(" ");
		}
		BrandEntity brand = brandDao.selectById(brandDto.getId());
		BeanUtils.copyProperties(brandDto, brand);
		brand.setLastModifiedTime(new Date());
		brand.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return brandDao.updateById(brand);
	}

	public BrandDto findBrandById(Long id) throws GlobalException {
		/**判断id是否为空*/
		if(id == null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_ID_NOT_NULL);
		}
		/**对id进行查询*/
		BrandDto selectBrandById = brandDao.selectBrandById(id);
		if(selectBrandById == null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_ID_NOT_EXIST);
		}
		return selectBrandById;
	}

	public Page<BrandDto> getBrandList(Page<BrandDto> page, BrandDto brandDto) {
		page.setRecords(brandDao.selectPageBrand(page,brandDto));
		return page;
	}
	
	public Integer removeBrands(Long[] id)throws GlobalException {
		/**判断id是否为空*/
		if(id == null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_ID_NOT_NULL);
		}
		for (Long long1 : id) {
			/**对id进行查询*/
			BrandEntity selectBrandById = brandDao.selectBrandEntityById(long1);
			if(selectBrandById == null) {
				throw new GlobalException(BrandExceptionEnum.BRAND_ID_NOT_EXIST);
			}
		}
		 return brandDao.deleteBrands(id);
	}

	public List<BrandDto> getBrandList(){
		return brandDao.selectListBrand();
	}

	@Override
	public BrandDto getBrandByName(BrandDto brandDto) throws GlobalException{
		BrandDto brand = brandDao.selectBrandByNameAndId(brandDto);
		if(brand != null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_NAME_NOT_REPETITION);
		}
		return brand;
	}

	@Override
	public BrandDto getBrandByCode(BrandDto brandDto) throws GlobalException{
		BrandDto brand = brandDao.selectBrandByBrandCodeAndId(brandDto);
		if(brand != null) {
			throw new GlobalException(BrandExceptionEnum.BRAND_NAME_NOT_REPETITION);
		}
		return brand;
	}

	@Override
	public List<SuitableAgeDto> getListSuitableAge() {
		return suitableAgeDao.selectListSuitableAge();
	}

	@Override
	public List<BrandDto> getAppBrandList() {
		List<BrandDto> selectListBrand = brandDao.selectListBrand();
		return selectListBrand;
	}

	@Override
	public List<SuitableAgeDto> getAppListSuitableAge() {
		List<SuitableAgeDto> selectListSuitableAge = suitableAgeDao.selectAppListSuitableAge();
		return selectListSuitableAge;
	}

}
