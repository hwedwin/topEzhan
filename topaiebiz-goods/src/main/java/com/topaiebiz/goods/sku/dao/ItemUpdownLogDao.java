package com.topaiebiz.goods.sku.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.sku.entity.ItemUpdownLogEntity;

/**
 * Description 商品上下架记录dao  
 * 
 * Author Hedda 
 *    
 * Date 2017年10月3日 下午7:16:54 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface ItemUpdownLogDao extends BaseDao<ItemUpdownLogEntity>{

}
