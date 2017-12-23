package com.topaiebiz.merchant.grade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.grade.dto.MerchantGradeDto;
import com.topaiebiz.merchant.grade.entity.MerchantGradeEntity;

/**
 * Description: 商家等级管理数据持久层
 * 
 * Author : Anthony
 * 
 * Date :2017年9月28日 下午7:15:45
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MerchantGradeDao extends BaseDao<MerchantGradeEntity> {

	/**
	 * Description： 商家等级名称下拉框展示
	 * 
	 * Author: Anthony
	 * 
	 * return merchantGrade 商家等级的id和Name数据信息对象
	 */
	List<MerchantGradeDto> selectMerchantGradeByName();

	/**
	 * Description：查看商家等级信息(根据Id查看商家等级信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 *            商家等级id
	 * @return 商家等级信息实体类对象
	 * 
	 */
	MerchantGradeDto selectMerchantGradeById(Long id);

	/**
	 * Description： 商家等级信息分页检索
	 * 
	 * Author: Anthony
	 * 
	 * @param page
	 *            分页参数
	 * @param merchantGradeDto
	 *            商家等级信息dto
	 * @return
	 */
	List<MerchantGradeDto> selectMerchantGradeList(Page<MerchantGradeDto> page, MerchantGradeDto merchantGradeDto);

	/**
	 * Description：对id进行查询
	 * 
	 * Author: Anthony
	 * 
	 * @param long1
	 * @return
	 */
	MerchantGradeEntity selectMerchantGradeByIds(Long long1);

	/**
	 * Description：进行批量删除操作
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteMerchantGradeByIds(Long[] id);

	/**
	 * Description： 等级 名称进行重复验证
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantGradeEntity
	 * @return
	 */
	MerchantGradeEntity selectMerchantGradeInfoByName(MerchantGradeEntity merchantGradeEntity);
    
	/**
	 * Description： 检验商家等级表的id是否已经被商家所占用。
	 * 
	 * Author: Anthony   
	 * 
	 * @param long2
	 * @return
	 */
	MerchantGradeEntity selectMerchantInfoById(Long long2);

}
