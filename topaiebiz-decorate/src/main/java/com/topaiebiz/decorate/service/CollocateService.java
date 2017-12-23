package com.topaiebiz.decorate.service;

import java.util.List;

import com.topaiebiz.decorate.dto.CollocateDetailDto;
import com.topaiebiz.decorate.dto.CollocateDto;
import com.topaiebiz.decorate.dto.CollocateGoodsDto;
import com.topaiebiz.decorate.dto.CollocateInfoDto;
import com.topaiebiz.decorate.dto.TemeplateInfoDto;
import com.topaiebiz.decorate.entity.CollocateGoodsEntity;
import com.topaiebiz.decorate.entity.StoreTemeplateEntity;

public interface CollocateService {

	/**
	 * Description 展示前台装修图片
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @return
	 */
	CollocateDto getListCollocate(CollocateDto collocateDto);

	/**
	 * Description 添加装修类型
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @return
	 */
	void addCollocateType(CollocateDto collocateDto);

	/**
	 * Description 添加装修图片
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateInfoDto
	 */
	void addCollocateInfo(CollocateInfoDto collocateInfoDto);

	/**
	 * Description 装修回显详情
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param collocateDto
	 * @return
	 */
	CollocateDetailDto getCollocateDetail(CollocateDto collocateDto);

	/**
	 * Description：启用禁用装修模版
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param temeplateInfoDto
	 */
	void modifyTemeplateInfo(TemeplateInfoDto temeplateInfoDto);

	/**
	 * Description： 店铺选用装修模版 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param temeplateId
	 */
	void saveStoreTemeplate(Long temeplateId);

	/**
	 * Description： 获取店铺使用的装修模版
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param sotreId
	 * @return
	 */
	StoreTemeplateEntity getUsedTemeplate(Long sotreId);

	/**
	 * Description： 根据类型ID查询装修商品
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<CollocateGoodsEntity> getCollocateGoods(Long id);

	/**
	 * Description： 给装修模版添加商品  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param itemIdList
	 */
	void addCollocateGoods(List<CollocateGoodsDto> collocateGoodsList);

	/**
	 * Description： 平台添加美礼卡轮播图
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param collocateInfoDto
	 */
	void addGiftCardCollocateInfo(CollocateInfoDto collocateInfoDto);

	/**
	 * Description： 查询美礼卡轮播图 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param collocateDto
	 * @return
	 */
	List<CollocateInfoDto> getGiftCollocateDetail(CollocateDto collocateDto);


}
