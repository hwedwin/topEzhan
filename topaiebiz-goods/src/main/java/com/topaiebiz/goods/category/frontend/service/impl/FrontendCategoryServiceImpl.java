package com.topaiebiz.goods.category.frontend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.frontend.dao.FrontBackCategoryDao;
import com.topaiebiz.goods.category.frontend.dao.FrontendCategoryDao;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;
import com.topaiebiz.goods.category.frontend.entity.FrontendCategoryEntity;
import com.topaiebiz.goods.category.frontend.exception.FrontendCategoryExceptionEnum;
import com.topaiebiz.goods.category.frontend.service.FrontendCategoryService;

/**
 * Description 商品前台类目实现类
 * 
 * Author Hedda
 * 
 * Date 2017年8月25日 下午3:14:47
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 *
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class FrontendCategoryServiceImpl implements FrontendCategoryService {

	@Autowired
	private FrontendCategoryDao frontendCategoryDao;
	
	@Autowired
	private FrontBackCategoryDao frontBackCategoryDao;
	
	/** 后台类目。 */
	@Autowired
	private BackendCategoryDao backendCategoryDao;
	
	@Override
	public List<FrontendCategoryDto> getFrontendCategoryList(FrontendCategoryDto frontendCategoryDto)
			throws GlobalException {
		/** 判断parentId是否为空 */
		if (frontendCategoryDto.getParentId() == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_PARENTID_NOT_NULL);
		}
		if (frontendCategoryDto.getParentId() == 0) {
			return frontendCategoryDao.selectListOneLevelFrontendCategory(frontendCategoryDto);
		} else {
			return frontendCategoryDao.selectListTwoOrThreeLevelFrontendCategory(frontendCategoryDto);
		}
	}

	@Override
	public List<FrontendCategoryDto> getMerchantFrontendCategoryList(FrontendCategoryDto frontendCategoryDto)
			throws GlobalException {
		/** 判断parentId是否为空 */
		if (frontendCategoryDto.getParentId() == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_PARENTID_NOT_NULL);
		}
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		frontendCategoryDto.setBelongStore(storeId);
		if (frontendCategoryDto.getParentId() == 0) {
			return frontendCategoryDao.selectListOneLevelFrontendCategory(frontendCategoryDto);
		} else {
			return frontendCategoryDao.selectListTwoOrThreeLevelFrontendCategory(frontendCategoryDto);
		}
	}

	@Override
	public Integer saveFrontendCategory(FrontendCategoryEntity frontendCategory) throws GlobalException {
		FrontendCategoryEntity frontendCategoryByName = frontendCategoryDao
				.selectFrontendCategoryByName(frontendCategory);
		/** 商品前台类目名称重复验证 */
		if (frontendCategoryByName != null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_NAME_NOT_REPETITION);
		}
		frontendCategory.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		frontendCategory.setCreatedTime(new Date());
		return frontendCategoryDao.insert(frontendCategory);
	}

	@Override
	public Integer saveMerchantFrontendCategory(FrontendCategoryEntity frontendCategory) throws GlobalException {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		frontendCategory.setBelongStore(storeId);
		FrontendCategoryEntity frontendCategoryByName = frontendCategoryDao
				.selectFrontendCategoryByName(frontendCategory);
		/** 商品前台类目名称重复验证 */
		if (frontendCategoryByName != null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_NAME_NOT_REPETITION);
		}
		frontendCategory.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		frontendCategory.setCreatedTime(new Date());
		return frontendCategoryDao.insert(frontendCategory);
	}

	@Override
	public Integer modifyFrontendCategory(FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		FrontendCategoryDto frontendCategoryByName = frontendCategoryDao
				.selectFrontendCategoryByNameAndId(frontendCategoryDto);
		/** 商品前台类目名称重复验证 */
		if (frontendCategoryByName != null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_NAME_NOT_REPETITION);
		}
		FrontendCategoryEntity frontendCategoryEntity = frontendCategoryDao.selectById(frontendCategoryDto.getId());
		BeanUtils.copyProperties(frontendCategoryDto, frontendCategoryEntity);
		frontendCategoryEntity.setLastModifiedTime(new Date());
		frontendCategoryEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return frontendCategoryDao.updateById(frontendCategoryEntity);
	}

	@Override
	public void removeFrontendCategory(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		FrontendCategoryEntity frontendCategoryEntity = frontendCategoryDao.selectById(id);
		if (frontendCategoryEntity == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_EXIST);
		}
		/** 查询商品前台类目及子类目 */
		List<FrontendCategoryEntity> allFrontendCategory = this.getAllFrontendCategory(id);
		for (FrontendCategoryEntity frontendCategory : allFrontendCategory) {
			/** 删除商品前台类目及子类目 */
			frontendCategoryDao.deleteFrontendCategory(frontendCategory.getId());
			/** 查询叶子类目下面绑定前后台类目数据 */
			List<FrontBackCategoryEntity> listFrontBackCategory = frontBackCategoryDao
					.selectAllFrontBackCategoryById(frontendCategory.getId());
			for (FrontBackCategoryEntity frontBackCategory : listFrontBackCategory) {
				if (frontBackCategory != null) {
					/** 删除商品前后台绑定类目 */
					frontBackCategoryDao.deleteFrontBackCategory(frontBackCategory.getId());
				}
			}
		}
	}

	/**
	 * Description 查询子类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	private List<FrontendCategoryEntity> getAllFrontendCategory(Long id) {
		List<FrontendCategoryEntity> resultList = new ArrayList<FrontendCategoryEntity>();
		/** 查询本级类目 */
		List<FrontendCategoryEntity> listFrontendCategory = frontendCategoryDao.selectAllFrontendCategoryById(id);
		FrontendCategoryEntity frontendCategoryEntity = frontendCategoryDao.selectFrontendCategoryEntityById(id);
		resultList.add(frontendCategoryEntity);
		if (!(listFrontendCategory == null || listFrontendCategory.size() == 0)) {
			resultList.addAll(listFrontendCategory);
			for (FrontendCategoryEntity frontendCategory : listFrontendCategory) {
				/** 查询下级类目 */
				List<FrontendCategoryEntity> childList = this.getAllFrontendCategory(frontendCategory.getId());
				resultList.addAll(childList);
			}
			return resultList;
		} else {
			return resultList;
		}
	}

	@Override
	public Integer saveFrontBackCategory(FrontBackCategoryEntity frontBackCategory) throws GlobalException {
		BackendCategoryDto backendCategoryById = backendCategoryDao
				.selectBackendCategoryById(frontBackCategory.getBackId());
		/** 判断backId是否存在。 */
		if (backendCategoryById == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTBACKCATEGORY_BACKID_NOT_EXIST);
		}
		/** 判断backId是否重复。 */
//		FrontBackCategoryDto frontBackCategoryDto = frontBackCategoryDao
//				.selectFrontBackCategoryByBackId(frontBackCategory.getBackId());
//		if (frontBackCategoryDto != null) {
//			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTBACKCATEGORY_BACKID_NOT_REPETITION);
//		}
		/** 判断frontId是否存在。 */
		FrontendCategoryDto frontendCategoryById = frontendCategoryDao
				.selectFrontendCategoryById(frontBackCategory.getFrontId());
		if (frontendCategoryById == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTBACKCATEGORY_FRONTID_NOT_EXIST);
		}
		frontBackCategory.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		frontBackCategory.setCreatedTime(new Date());
		return frontBackCategoryDao.insert(frontBackCategory);
	}

	@Override
	public void removeFrontBackCategory(FrontBackCategoryDto frontBackCategoryDto) throws GlobalException {
		/** 判断backId是否存在。 */
		BackendCategoryDto backendCategoryById = backendCategoryDao
				.selectBackendCategoryById(frontBackCategoryDto.getBackId());
		if (backendCategoryById == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTBACKCATEGORY_BACKID_NOT_EXIST);
		}
		/** 判断frontId是否存在。 */
		FrontendCategoryDto frontendCategoryById = frontendCategoryDao
				.selectFrontendCategoryById(frontBackCategoryDto.getFrontId());
		if (frontendCategoryById == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTBACKCATEGORY_FRONTID_NOT_EXIST);
		}
		frontBackCategoryDao.deleteFrontBackCategory(frontBackCategoryDto.getId());
	}

	@Override
	public FrontendCategoryDto getFrontendCategoryById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_NULL);
		}
		FrontendCategoryDto frontendCategoryDto = frontendCategoryDao.selectFrontendCategoryById(id);
		/** 判断id是否存在 */
		if (frontendCategoryDto == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_EXIST);
		}
		return frontendCategoryDto;
	}

	@Override
	public Integer addFrontendCategoryById(Long id, String image) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_NULL);
		}
		FrontendCategoryEntity frontendCategoryEntity = frontendCategoryDao.selectById(id);
		/** 判断id是否存在 */
		if (frontendCategoryEntity == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_EXIST);
		}
		frontendCategoryEntity.setImage(image);
		return frontendCategoryDao.updateById(frontendCategoryEntity);
	}

	@Override
	public List<BackendCategoryDto> getBackendCategoryList(Long id) throws GlobalException {
		/** 创建一个resultList集合 */
		List<BackendCategoryDto> resultList = new ArrayList<BackendCategoryDto>();
		List<FrontBackCategoryEntity> allFrontBackCategoryById = frontBackCategoryDao
				.selectAllFrontBackCategoryById(id);
		if (!(allFrontBackCategoryById == null || allFrontBackCategoryById.size() == 0)) {
			for (FrontBackCategoryEntity frontBackCategory : allFrontBackCategoryById) {
				BackendCategoryDto allBackendCategoryById = backendCategoryDao
						.selectBackendCategoryByBackId(frontBackCategory.getBackId());
				allBackendCategoryById.setFrontBackId(frontBackCategory.getId());
				resultList.add(allBackendCategoryById);
			}
		}
		return resultList;
	}

	@Override
	public FrontendCategoryDto getFrontendCategoryByName(FrontendCategoryDto frontendCategoryDto)
			throws GlobalException {
		FrontendCategoryDto frontendCategory = frontendCategoryDao
				.selectFrontendCategoryByNameAndId(frontendCategoryDto);
		/** 商品前台类目名称重复验证 */
		if (frontendCategory != null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_NAME_NOT_REPETITION);
		}
		return frontendCategory;
	}

	@Override
	public Integer cancelFrontendCategoryImage(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_NULL);
		}
		FrontendCategoryEntity frontendCategoryEntity = frontendCategoryDao.selectById(id);
		/** 判断id是否存在 */
		if (frontendCategoryEntity == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_EXIST);
		}
		if (frontendCategoryEntity.getImage() != null) {
			frontendCategoryEntity.setImage(" ");
		}
		return frontendCategoryDao.updateById(frontendCategoryEntity);
	}

	@Override
	public List<FrontendCategoryDto> getMerchantAppFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		/** 判断parentId是否为空 */
		if (frontendCategoryDto.getParentId() == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_PARENTID_NOT_NULL);
		}
		if (frontendCategoryDto.getParentId() == 0) {
			return frontendCategoryDao.selectAppListOneLevelFrontendCategory(frontendCategoryDto);
		} else {
			return frontendCategoryDao.selectAppListTwoOrThreeLevelFrontendCategory(frontendCategoryDto);
		}
	}

	@Override
	public List<FrontendCategoryDto> getAppThreeFrontendCategoryList(Long storeId) {
		return frontendCategoryDao.selectAppThreeFrontendCategoryList(storeId);
	}

	@Override
	public Page<FrontBackCategoryDto> getThreeFrontendCategoryList(Page<FrontBackCategoryDto> page) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		page.setRecords(frontendCategoryDao.selectThreeFrontendCategoryList(page, storeId));
		return page;
	}

}
