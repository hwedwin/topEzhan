package com.topaiebiz.goods.category.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryAttrDao;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dao.BackendMerchantCategoryDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryAttrDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.dto.BackendMerchantCategoryDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryEntity;
import com.topaiebiz.goods.category.backend.entity.BackendMerchantCategoryEntity;
import com.topaiebiz.goods.category.backend.exception.BackendCategoryExceptionEnum;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;
import com.topaiebiz.goods.category.frontend.dao.FrontBackCategoryDao;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;
import com.topaiebiz.goods.category.frontend.exception.FrontendCategoryExceptionEnum;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.spu.dao.GoodsSpuDao;
import com.topaiebiz.goods.spu.dto.GoodsSpuDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuEntity;

/**
 * Description 商品后台类目实现类
 * 
 * Author Hedda
 * 
 * Date 2017年8月24日 下午4:48:07
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class BackendCategoryServiceImpl implements BackendCategoryService {

	@Autowired
	private BackendCategoryDao backendCategoryDao;

	/** 商品后台类目属性。 */
	@Autowired
	private BackendCategoryAttrDao backendCategoryAttrDao;

	/** 商品spu。 */
	@Autowired
	private GoodsSpuDao goodsSpuDao;

	/** 商品item。 */
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private FrontBackCategoryDao frontBackCategoryDao;

	@Autowired
	private BackendMerchantCategoryDao backendMerchantCategoryDao;

	@Override
	public Integer removeBackendCategory(Long id) throws GlobalException {
		Integer i = null;
		/** 判断id是否为空 */
		if (null == id) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		BackendCategoryEntity backendCategoryById = backendCategoryDao.selectBackendCategoryEntityById(id);
		if (backendCategoryById == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_EXIST);
		}
		/** 通过id查询本级类目 */
		List<BackendCategoryEntity> allBackendCategory = this.getAllBackendCategory(id);

		for (BackendCategoryEntity backendCategory : allBackendCategory) {
			/** 查询叶子类目下的属性 */
			List<BackendCategoryAttrEntity> BackendCategoryAttr = backendCategoryAttrDao
					.selectListBackendCategoryAttr(backendCategory.getId());
			for (BackendCategoryAttrEntity backendCategoryAttrs : BackendCategoryAttr) {
				if (backendCategoryAttrs != null) {
					/** 删除叶子类目下的属性 */
					i = backendCategoryAttrDao.deleteBackendCategoryAttr(backendCategoryAttrs.getId());
				}
			}
			// 查询本类目下面是否有spu商品
			List<GoodsSpuDto> goodsSpuDtos = goodsSpuDao.selectGoodsSpuByBelongCategory(backendCategory.getId());
			if (!(goodsSpuDtos == null || goodsSpuDtos.size() == 0)) {
				for (GoodsSpuDto goodsSpuDto : goodsSpuDtos) {
					GoodsSpuEntity goodsSpu = goodsSpuDao.selectById(goodsSpuDto.getId());
					BeanUtils.copyProperties(goodsSpuDto, goodsSpu);
					goodsSpu.setBelongCategory(3L);
					goodsSpuDao.updateById(goodsSpu);
				}
			}
			// 查询本类目下面是否有item商品
			List<ItemEntity> itemEntities = itemDao.selectItemByBelongCategory(backendCategory.getId());
			if (!(itemEntities == null || itemEntities.size() == 0)) {
				for (ItemEntity itemEntity : itemEntities) {
					itemEntity.setBelongCategory(3L);
					itemDao.updateById(itemEntity);
				}
			}
			i = backendCategoryDao.deleteBackendCategory(backendCategory.getId());
		}
		return i;
	}

	@Override
	public Integer removeBackendCategoryByStoreId(Long storeId) {
		Integer i = null;
		List<BackendCategoryEntity> backendCategoryEntities = backendCategoryDao
				.selectBackendCategoryByStoreId(storeId);
		if (!(backendCategoryEntities == null || backendCategoryEntities.size() == 0)) {
			for (BackendCategoryEntity backendCategoryEntity : backendCategoryEntities) {
				i = backendCategoryDao.deleteBackendCategory(backendCategoryEntity.getId());
			}
		}
		return i;
	}

	/**
	 * 
	 * @Description 查询子类目
	 * 
	 * @Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	private List<BackendCategoryEntity> getAllBackendCategory(Long id) {
		/** 创建一个resultList集合 */
		List<BackendCategoryEntity> resultList = new ArrayList<BackendCategoryEntity>();
		/** 通过上级类目id查询出下级所有类目 */
		BackendCategoryEntity selectBackendCategoryById = backendCategoryDao.selectBackendCategoryEntityById(id);
		resultList.add(selectBackendCategoryById);
		List<BackendCategoryEntity> listBackendCategory = backendCategoryDao.selectAllBackendCategoryById(id);
		if (!(listBackendCategory == null || listBackendCategory.size() == 0)) {
			resultList.addAll(listBackendCategory);
			for (BackendCategoryEntity backendCategory : listBackendCategory) {
				List<BackendCategoryEntity> childList = this.getAllBackendCategory(backendCategory.getId());
				resultList.addAll(childList);
			}
			return resultList;
		} else {
			return resultList;
		}
	}

	@Override
	public Integer modifyBackendCategory(BackendCategoryDto backendCategoryDto) throws GlobalException {
		BackendCategoryDto backendCategoryByName = backendCategoryDao
				.selectBackendCategoryByNameAndId(backendCategoryDto);
		/** 判断商品后台类目名称重复验证 */
		if (backendCategoryByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_NAME_NOT_REPETITION);
		}
		BackendCategoryEntity backendCategory = backendCategoryDao.selectById(backendCategoryDto.getId());
		BeanUtils.copyProperties(backendCategoryDto, backendCategory);
		backendCategory.setLastModifiedTime(new Date());
		backendCategory.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return backendCategoryDao.updateById(backendCategory);
	}

	@Override
	public List<BackendCategoryDto> getListLevelBackendCategory(BackendCategoryDto backendCategoryDto)
			throws GlobalException {
		/** 判断parentId是否为空 */
		if (backendCategoryDto.getParentId() == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_PARENTID_NOT_NULL);
		}
		if (backendCategoryDto.getParentId() == 0) {
			return backendCategoryDao.selectListOneLevelBackendCategory(backendCategoryDto);
		} else {
			return backendCategoryDao.selectListTwoOrThreeLevelBackendCategory(backendCategoryDto);
		}
	}

	@Override
	public List<BackendCategoryAttrEntity> getListBackendCategoryAttr(Long belongCategory) throws GlobalException {
		/** 判断商品后台类目id是否为空 */
		if (belongCategory == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_BELONGCATEGORY_NOT_NULL);
		}
		List<BackendCategoryAttrEntity> selectListBackendCategoryAttr = backendCategoryAttrDao.selectListBackendCategoryAttr(belongCategory);
		if(!(null == selectListBackendCategoryAttr || selectListBackendCategoryAttr.size() == 0)) {
			for (BackendCategoryAttrEntity backendCategoryAttrEntity : selectListBackendCategoryAttr) {
				if(backendCategoryAttrEntity.getDefaultUnit() != null) {
					if(backendCategoryAttrEntity.getDefaultUnit().equals(" ")) {
						backendCategoryAttrEntity.setDefaultUnit(null);
					}
				}
			}
		}
		return selectListBackendCategoryAttr;
	}

	@Override
	public Integer modifyBackendCategoryAttr(BackendCategoryAttrDto backendCategoryAttrDto) throws GlobalException {
		BackendCategoryAttrDto backendCategoryAttrByName = backendCategoryAttrDao
				.selectBackendCategoryAttrByNameAndId(backendCategoryAttrDto);
		/** 判断商品后台类目名称是否重复 */
		if (backendCategoryAttrByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_NAME_NOT_REPETITION);
		}
		BackendCategoryAttrEntity backendCategoryAttr = backendCategoryAttrDao
				.selectById(backendCategoryAttrDto.getId());
		BeanUtils.copyProperties(backendCategoryAttrDto, backendCategoryAttr);
		backendCategoryAttr.setLastModifiedTime(new Date());
		backendCategoryAttr.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return backendCategoryAttrDao.updateById(backendCategoryAttr);
	}

	@Override
	public Integer removeBackendCategoryAttr(Long id) throws GlobalException {
		/** 判断商品后台类目属性id是非为空 */
		if (id == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_ID_NOT_NULL);
		}
		BackendCategoryAttrEntity backendCategoryAttr = backendCategoryAttrDao.selectBackendCategoryAttrEntityById(id);
		/** 判断商品后台类目属性是非存在 */
		if (backendCategoryAttr == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_ID_NOT_EXIST);
		}
		return backendCategoryAttrDao.deleteBackendCategoryAttr(id);
	}

	@Override
	public Integer saveBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr) throws GlobalException {
		BackendCategoryAttrEntity backendCategoryAttrByName = backendCategoryAttrDao
				.selectBackendCategoryAttrByName(backendCategoryAttr);
		/** 对商品后台类目属性名称重复验证 */
		if (backendCategoryAttrByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_NAME_NOT_REPETITION);
		}
		BackendCategoryAttrDto maxSortNoByBackendCategory = backendCategoryAttrDao
				.selectMaxSortNoByBackendCategoryAttr(backendCategoryAttr);
		// 添加排序号时每个类目下面的属性排序号从0 开始
		if (maxSortNoByBackendCategory == null) {
			// 不存在最大值为0
			backendCategoryAttr.setSortNo(0);
		} else {
			// 存在时在最大排序号上加1
			Integer sortNo = maxSortNoByBackendCategory.getSortNo();
			backendCategoryAttr.setSortNo(sortNo + 1);
		}
		backendCategoryAttr.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		backendCategoryAttr.setCreatedTime(new Date());
		return backendCategoryAttrDao.insert(backendCategoryAttr);
	}

	@Override
	public Integer saveBackendCategory(BackendCategoryEntity backendCategory) throws GlobalException {
		Integer i = null;
		BackendCategoryEntity BackendCategoryByName = backendCategoryDao.selectBackendCategoryByName(backendCategory);
		/** 判断商品后台类目名称是否重复 */
		if (BackendCategoryByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_NAME_NOT_REPETITION);
		}
		backendCategory.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		backendCategory.setCreatedTime(new Date());
		i = backendCategoryDao.insert(backendCategory);
		return i;
	}

	@Override
	public Integer saveMerchantBackendCategory(BackendCategoryEntity backendCategory) throws GlobalException {
		Integer i = null;
		BackendCategoryEntity BackendCategoryByName = backendCategoryDao.selectBackendCategoryByName(backendCategory);
		/** 判断商品后台类目名称是否重复 */
		if (BackendCategoryByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_NAME_NOT_REPETITION);
		}
		backendCategory.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		backendCategory.setCreatedTime(new Date());
		i = backendCategoryDao.insert(backendCategory);
		return i;
	}

	@Override
	public BackendCategoryDto findBackendCategoryById(Long id) throws GlobalException {
		/** 判断商品后台类目是否为空 */
		if (id == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		BackendCategoryDto backendCategoryById = backendCategoryDao.selectBackendCategoryById(id);
		/** 判断商品后台类目是否存在 */
		if (backendCategoryById == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_EXIST);
		}
		return backendCategoryById;
	}

	@Override
	public BackendCategoryAttrDto findBackendCategoryAttrById(Long id) throws GlobalException {
		/** 判断商品后台类目属性是否为空 */
		if (id == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_ID_NOT_NULL);
		}
		BackendCategoryAttrDto backendCategoryAttrById = backendCategoryAttrDao.selectBackendCategoryAttrById(id);
		/** 判断商品后台类目属性是否存在 */
		if (backendCategoryAttrById == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_ID_NOT_EXIST);
		}
		if (backendCategoryAttrById.getDefaultUnit() == null) {
			backendCategoryAttrById.setDefaultUnit(" ");
		}
		return backendCategoryAttrById;
	}

	@Override
	public BackendCategoryAttrDto getBackendCategoryAttrByName(BackendCategoryAttrDto backendCategoryAttrDto)
			throws GlobalException {
		BackendCategoryAttrDto backendCategoryAttr = backendCategoryAttrDao
				.selectBackendCategoryAttrByNameAndId(backendCategoryAttrDto);
		/** 判断商品后台类目名称是否重复 */
		if (backendCategoryAttr != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_NAME_NOT_REPETITION);
		}
		return backendCategoryAttr;
	}

	@Override
	public BackendCategoryDto getBackendCategoryByName(BackendCategoryDto backendCategoryDto) throws GlobalException {
		BackendCategoryDto backendCategory = backendCategoryDao.selectBackendCategoryByNameAndId(backendCategoryDto);
		/** 判断商品后台类目名称重复验证 */
		if (backendCategory != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_NAME_NOT_REPETITION);
		}
		return backendCategory;
	}

	@Override
	public Integer modifyBackendCategoryAttrBySortNo(List<BackendCategoryAttrDto> backendCategoryAttrDto) {
		Integer i = 0;
		if (!(backendCategoryAttrDto == null || backendCategoryAttrDto.size() == 0)) {
			for (BackendCategoryAttrDto backendCategoryAttrDto2 : backendCategoryAttrDto) {
				BackendCategoryAttrEntity backendCategoryAttr = backendCategoryAttrDao
						.selectById(backendCategoryAttrDto2.getId());
				BeanUtils.copyProperties(backendCategoryAttrDto, backendCategoryAttr);
				// 将新的排序号更新到商品后台类目属性中
				backendCategoryAttr.setSortNo(backendCategoryAttrDto2.getSortNo());
				i = backendCategoryAttrDao.updateById(backendCategoryAttr);
			}
		}
		return i;
	}

	@Override
	public List<BackendCategorysDto> getBackendCategoryDtoByBelongStore(Long merchantId) {
		// 创建一个BackendCategorysDto集合
		List<BackendCategorysDto> reaultsMsg = new ArrayList<BackendCategorysDto>();
		// 查询出第三级类目的id
		List<BackendCategoryDto> backendCategoryThree = new ArrayList<BackendCategoryDto>();
		// 通过商家id查询商家类目
		List<BackendMerchantCategoryDto> backendCategoryDtos = backendCategoryDao
				.selectThreeBackendCategoryDtoByMerchantId(merchantId);
		if (!(backendCategoryDtos == null || backendCategoryDtos.size() == 0)) {
			for (BackendMerchantCategoryDto backendCategoryDto : backendCategoryDtos) {
				// 通过类目id查询出息类目是否为三级类目
				BackendCategoryDto backendCategory = backendCategoryDao
						.selectBackendCategoryById(backendCategoryDto.getCategoryId());
				if(backendCategory != null) {
					if (backendCategory.getLevel() == 3) {
						backendCategoryThree.add(backendCategory);
					}
				}
			}
		}
		if (!(backendCategoryThree == null || backendCategoryThree.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : backendCategoryThree) {
				List<BackendCategoryDto> reaultMsg = new ArrayList<BackendCategoryDto>();
				// 第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao
						.selectTwoBackendCategoryDtoByParentId(backendCategoryDto.getParentId());
				// 通过商家id和第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao
						.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				// 将一二三级类目添加到BackendCategoryDto集合
				reaultMsg.add(onebackendCategoryDto);
				reaultMsg.add(twobackendCategoryDto);
				reaultMsg.add(backendCategoryDto);
				// 创建backendCategorysDto对象
				BackendCategorysDto backendCategorysDto = new BackendCategorysDto();
				backendCategorysDto.setBackendCategoryDto(reaultMsg);
				reaultsMsg.add(backendCategorysDto);
			}
		}
		return reaultsMsg;
	}

	@Override
	public Integer addBackendCategoryDtoByBelongStore(Long[] ids) {
		Integer i = 0;
		// 获得到店铺的id
		Long merchantId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		if (ids != null && merchantId != null) {
			for (Long id : ids) {
				// 三级类目
				BackendCategoryEntity threeBackendCategory = backendCategoryDao.selectById(id);
				BackendMerchantCategoryEntity backendMerchantCategoryEntityThree = backendCategoryDao
						.selectBackendMerchantCategoryById(threeBackendCategory.getId(), merchantId);
				if (backendMerchantCategoryEntityThree != null) {
					throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ALREADY);
				}
				BackendMerchantCategoryEntity backendMerchantCategoryEntity = new BackendMerchantCategoryEntity();
				backendMerchantCategoryEntity.setMerchantId(merchantId);
				backendMerchantCategoryEntity.setCategoryId(threeBackendCategory.getId());
				backendMerchantCategoryEntity.setCreatedTime(new Date());
				backendMerchantCategoryEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				i = backendMerchantCategoryDao.insert(backendMerchantCategoryEntity);
				// 二级类目
				BackendCategoryEntity twoBackendCategory = backendCategoryDao
						.selectById(threeBackendCategory.getParentId());
				BackendMerchantCategoryEntity backendMerchantCategoryEntityTwo = backendCategoryDao
						.selectBackendMerchantCategoryById(twoBackendCategory.getId(), merchantId);
				if (backendMerchantCategoryEntityTwo == null) {
					BackendMerchantCategoryEntity backendMerchantCategoryEntity2 = new BackendMerchantCategoryEntity();
					backendMerchantCategoryEntity2.setMerchantId(merchantId);
					backendMerchantCategoryEntity2.setCategoryId(twoBackendCategory.getId());
					backendMerchantCategoryEntity2.setCreatedTime(new Date());
					backendMerchantCategoryEntity2.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
					i = backendMerchantCategoryDao.insert(backendMerchantCategoryEntity2);
				}
				// 一级类目
				BackendCategoryEntity oneBackendCategory = backendCategoryDao
						.selectById(twoBackendCategory.getParentId());
				BackendMerchantCategoryEntity backendMerchantCategoryEntityOne = backendCategoryDao
						.selectBackendMerchantCategoryById(oneBackendCategory.getId(), merchantId);
				if (backendMerchantCategoryEntityOne == null) {
					BackendMerchantCategoryEntity backendMerchantCategoryEntity1 = new BackendMerchantCategoryEntity();
					backendMerchantCategoryEntity1.setMerchantId(merchantId);
					backendMerchantCategoryEntity1.setCategoryId(oneBackendCategory.getId());
					backendMerchantCategoryEntity1.setCreatedTime(new Date());
					backendMerchantCategoryEntity1.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
					i = backendMerchantCategoryDao.insert(backendMerchantCategoryEntity1);
				}
			}
		}
		return i;
	}

	@Override
	public List<BackendCategoryDto> getThreeBackendCategoryList() {
		return backendCategoryDao.selectThreeBackendCategoryList();
	}

	@Override
	public List<BackendCategoryDto> getMerchantThreeBackendCategoryList() {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		return backendCategoryDao.selectMerchantThreeBackendCategoryList(storeId);
	}

	@Override
	public List<BackendCategoryAttrDto> getAppListBackendCategoryAttr(Long frontendId) throws GlobalException {
		if (frontendId == null) {
			throw new GlobalException(FrontendCategoryExceptionEnum.FRONTENDCATEGORY_ID_NOT_NULL);
		}
		List<FrontBackCategoryEntity> frontBackCategoryEntity = frontBackCategoryDao
				.selectFrontBackCategoryByFrontId(frontendId);
		List<BackendCategoryAttrDto> backendCategoryAttrDtos = new ArrayList<BackendCategoryAttrDto>();
		if (!(frontBackCategoryEntity == null || frontBackCategoryEntity.size() == 0)) {
			for (FrontBackCategoryEntity frontBackCategoryEntity2 : frontBackCategoryEntity) {
				Long backId = frontBackCategoryEntity2.getBackId();
				List<BackendCategoryAttrDto> selectListBackendCategoryAttr = backendCategoryAttrDao
						.selectListBackendCategoryAttrDto(backId);
				backendCategoryAttrDtos.addAll(selectListBackendCategoryAttr);
			}
		}
		return backendCategoryAttrDtos;
	}

	@Override
	public List<BackendCategoryDto> getOneAndTwoBackendCategoryById(Long backendCategoryId) throws GlobalException {
		if (backendCategoryId == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		BackendCategoryDto backendCategoryDto = backendCategoryDao.selectBackendCategoryById(backendCategoryId);
		if (backendCategoryDto == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_EXIST);
		}
		// 通过第三级parentId查询第二级类目
		BackendCategoryDto twobackendCategoryDto = backendCategoryDao
				.selectTwoBackendCategoryDtoByParentId(backendCategoryDto.getParentId());
		// 通过第二级parentId查询第一级类目
		BackendCategoryDto onebackendCategoryDto = backendCategoryDao
				.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
		List<BackendCategoryDto> backendCategoryDtos = new ArrayList<BackendCategoryDto>();
		backendCategoryDtos.add(backendCategoryDto);
		backendCategoryDtos.add(twobackendCategoryDto);
		backendCategoryDtos.add(onebackendCategoryDto);
		return backendCategoryDtos;
	}

	@Override
	public List<BackendMerchantCategoryDto> getMerchantCategory(BackendMerchantCategoryDto backendMerchantCategoryDto) {
		/** 判断parentId是否为空 */
		if (backendMerchantCategoryDto.getParentId() == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_PARENTID_NOT_NULL);
		}
		Long merchartId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		backendMerchantCategoryDto.setMerchantId(merchartId);
		List<BackendMerchantCategoryDto> backendMerchantCategoryList = backendCategoryDao
				.selectMerchantCategory(backendMerchantCategoryDto);
		return backendMerchantCategoryList;
	}

	@Override
	public List<BackendMerchantCategoryDto> getMerchantThreeCategory() {
		Long merchantId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		List<BackendMerchantCategoryDto> selectMerchantThreeCategory = backendCategoryDao
				.selectMerchantThreeCategory(merchantId);
		return selectMerchantThreeCategory;
	}

	@Override
	public Integer saveMerchantBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr) {
		BackendCategoryAttrEntity backendCategoryAttrByName = backendCategoryAttrDao
				.selectBackendCategoryAttrByName(backendCategoryAttr);
		/** 对商品后台类目属性名称重复验证 */
		if (backendCategoryAttrByName != null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORYATTR_NAME_NOT_REPETITION);
		}
		backendCategoryAttr.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		backendCategoryAttr.setCreatedTime(new Date());
		backendCategoryAttr.setStoreCustom("1");
		return backendCategoryAttrDao.insert(backendCategoryAttr);
	}

	@Override
	public void test() {
		List<BackendCategoryEntity> all = backendCategoryDao.getAll();
		for (BackendCategoryEntity backendCategoryEntity : all) {
			BackendMerchantCategoryEntity selectByCategoryId = backendMerchantCategoryDao
					.selectByCategoryId(backendCategoryEntity.getId(), 1L);
			if (null == selectByCategoryId) {
				BackendMerchantCategoryEntity entity = new BackendMerchantCategoryEntity();
				entity.setMerchantId(1L);
				entity.setCategoryId(backendCategoryEntity.getId());
				entity.setCreatedTime(new Date());
				entity.setCreatorId(11111111L);
				backendMerchantCategoryDao.insert(entity);
			}

		}

	}

	@Override
	public Integer removeBackendMerchantCategoryByStoreId(Long[] categoryId) {
		Integer i = 0;
		// 获得商家的id
		Long merchantId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		/** 判断id是否为空 */
		if (null == categoryId) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		for (Long category : categoryId) {
			i = backendCategoryDao.deleteBackendMerchantCategory(category,merchantId);
		}
		return i;
	}

}
