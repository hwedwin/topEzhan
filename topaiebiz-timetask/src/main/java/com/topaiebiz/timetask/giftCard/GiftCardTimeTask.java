package com.topaiebiz.timetask.giftCard;

import com.topaiebiz.giftcard.manage.dao.GiftCardDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/22 15:40
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GiftCardTimeTask {

	@Autowired
	private GiftCardDetailDao giftCardDetailDao;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateCardByDate() {
		/** 将过期礼卡改为不可用 */
		giftCardDetailDao.updateCardByDate();
		/** 将过期礼卡信息改为下架 */
		giftCardDetailDao.updateCardInfoByDate();
	}


}
