package com.topaiebiz.member.manage.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Data
public class ShopKeeperInfoDto extends MemberInfoDto {

    // 直属人数
    private Long firstPerson;

    // 二级人数
    private Long secondPerson;

    // 培训奖励
    private BigDecimal trainSumNum;

    // 待结算金额
    private BigDecimal settlementMoney;

    // 可提现金额
    private BigDecimal availableMoney;

    // 已提现金额
    private BigDecimal presentMoney;

    // 状态(0: 未审核, 1: 审核通过, 2: 未通过)
    private Integer status;

}
