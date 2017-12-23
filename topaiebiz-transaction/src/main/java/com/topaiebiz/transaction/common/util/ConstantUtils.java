package com.topaiebiz.transaction.common.util;

/**
 * 
 * Description： 枚举常量类  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月22日 下午11:14:30 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum ConstantUtils {
	
	/**1:表示平台*/
	RANGETYPE_ONE("platform",1),
	/**2:表示店铺*/
	RANGETYPE_TWO("store",2),
	/**3:表示品牌*/
	RANGETYPE_THREE("brand",3),
	
	/**no_goods:表示当会员收货地址传入时，指定区域中没有符合的地址运费模板，返回没有货的特殊字符标记*/
	NO_GOODS("no_goods",-1);

	private String name;
	
	private Integer index;

	private ConstantUtils(String name, Integer index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public Integer getIndex() {
		return index;
	}
	
}
