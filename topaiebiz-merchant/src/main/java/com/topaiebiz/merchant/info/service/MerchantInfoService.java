package com.topaiebiz.merchant.info.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.merchant.info.dto.MerchantInfoDto;
import com.topaiebiz.merchant.info.dto.MerchantInfoListDto;
import com.topaiebiz.merchant.info.dto.StoreInfoDetailDto;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;

/**
 * Description: 商家管理业务层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月27日 下午1:26:24
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MerchantInfoService {

	/**
	 * Description： 添加商家信息
	 * 
	 * Author: Anthony
	 * 
	 * param : entity 商家信息实体类对象
	 * 
	 * return : 执行成功或失败的提示信息
	 */
	Integer saveMerchantInfo(MerchantInfoEntity entity) throws GlobalException;

	/**
	 * Description：删除商家信息(修改状态)。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return 返回结果参数集
	 */
	Integer removeMerchantInfoById(Long id);

	/**
	 * Description：编辑(修改)商家信息。
	 * 
	 * Author: Anthony
	 * 
	 * param : dto 商家信息dto对象
	 * 
	 * return : 返回执行结果成功参数
	 */
	Integer modifyMerchantInfoById(MerchantInfoDto dto) throws GlobalException;

	/**
	 * Description： 商家信息列表分页检索
	 * 
	 * Author: Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merchantInfoDto 商家信息Dto
	 * 
	 * return : list 商家信息列表数据
	 */
	Page<MerchantInfoListDto> getMerchantInfoList(Page<MerchantInfoListDto> page, MerchantInfoListDto merchantInfoListDto);

	/**
	 * Description：查看商家详情(根据Id查看商家详情数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 * 
	 * return : 商家详情 dto对象
	 */
	MerchantInfoDto getMerchantParticularsById(Long id);

	/**
	 * Description：查看门店信息(根据Id查看门店信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 门店信息id
	 * 
	 * return : 门店信息实体类对象
	 */
	MerchantInfoDto getStoreInfoById(Long merchantId);

	/**
	 * Description： 商户类型下拉框展示
	 * 
	 * Author: Anthony
	 * 
	 * return : MerchantType 商户类型的名称和对应的id
	 */
	List<MerchantInfoDto> getMerchantType();

	/**
	 * Description： 获取店铺id和name
	 * 
	 * Author: Anthony
	 * 
	 * return : StoreInfoByName 店铺的name和id
	 * 
	 * @param name
	 */
	List<StoreInfoEntity> getStoreInfoByName(MerchantInfoDto dto);

	/**
	 * Description：商家等级设置
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家id
	 * 
	 * param : merchantGradeId 等级id
	 * 
	 * return : 返回成功结果参数
	 */
	Integer modifyMerchantInfoByMerchantGradeId(Long id, Long merchantGradeId);

	/**
	 * Description： 商户类型下拉框
	 * 
	 * Author: Anthony
	 * 
	 * return 商家信息Dto
	 * 
	 * throws GlobalException
	 */
	List<MerchantInfoDto> getMerchantInfoByMerchantType();
    
	/**
	 * Description： 所属商家。
	 * 
	 * Author: Anthony   
	 * 
	 * @return
	 */
	List<MerchantInfoDto> getMerchantInfoByName();
    
	
	/**
	 * Description：所属店铺
	 * 
	 * Author: Anthony   
	 * 
	 * @return
	 */
	List<StoreInfoEntity> getStoreInfoList();

	/**
	 * Description： 根据登录用户查询所有店铺
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param userName
	 * @return
	 */
	StoreInfoDetailDto getAllStoreByLoginName();

}
