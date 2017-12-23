package com.topaiebiz.merchant.info.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.merchant.info.dto.MerchantInfoDto;
import com.topaiebiz.merchant.info.dto.MerchantInfoListDto;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;

/**
 * Description: 商家管理数据持久层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年9月27日 下午1:28:39
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MerchantInfoDao extends BaseMapper<MerchantInfoEntity> {

	/**
	 * Description： 联系人手机号进行重复验证
	 * 
	 * Author: Anthony
	 * 
	 * param: contactTele 商家联系人手机号
	 * 
	 * return: 商家信息实体类对象
	 */
	MerchantInfoEntity selectMerchantInfoBycontactTele(String contactTele);

	/**
	 * Description： 删除商家信息（修改状态）
	 * 
	 * Author: Anthony
	 * 
	 * param :id 商家信息id
	 * 
	 * return: 执行结果信息
	 */
	Integer deleteMerchantInfoById(Long id);

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
	List<MerchantInfoListDto> selectMerchantInfoList(Page<MerchantInfoListDto> page, MerchantInfoListDto merchantInfoListDto);

	/**
	 * Description：查看商家详情(根据Id查看商家详情数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 * 
	 * return : 商家详情 dto对象
	 */
	MerchantInfoDto selectMerchantParticularsById(Long id);

	/**
	 * Description：查看门店信息(根据Id查看门店信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 门店信息id
	 * 
	 * return : 门店信息实体类对象
	 */
	MerchantInfoDto selectStoreInfoById(Long merchantId);

	/**
	 * Description：商户类型下拉框展示。
	 * 
	 * Author: Anthony
	 * 
	 * return : MerchantType 商户类型的名称和对应的id
	 */
	List<MerchantInfoDto> selectMerchantType();

	/**
	 * Description： 获取店铺id和name
	 * 
	 * Author: Anthony
	 * 
	 * return : StoreInfoByName 店铺的name和id
	 * 
	 * @param name
	 */
	List<StoreInfoEntity> selectStoreInfoByName(MerchantInfoDto dto);

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
	Integer updateMerchantInfoByMerchantGradeId(@Param("id") Long id, @Param("merchantGradeId") Long merchantGradeId);

	/**
	 * Description： 商户类型下拉框
	 * 
	 * Author: Anthony
	 * 
	 * return 商家信息Dto
	 * 
	 * throws GlobalException
	 */
	List<MerchantInfoDto> selectMerchantInfoByMerchantType();

	/**
	 * Description： 所属商家
	 * 
	 * Author: Anthony
	 * 
	 * @return
	 */
	List<MerchantInfoDto> selectMerchantInfoByName();

	/***
	 * 
	 * Description： 根据店铺ID获取店铺信息
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */

	StoreInfoEntity selectByStoreId(Long storeId);
    
	/**
	 * Description： 获取店铺名称
	 * 
	 * Author: Anthony   
	 * 
	 * @return
	 */
	List<StoreInfoEntity> selectStoreInfoList(); 



}
