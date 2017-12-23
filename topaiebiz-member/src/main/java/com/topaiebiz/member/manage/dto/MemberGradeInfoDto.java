package com.topaiebiz.member.manage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MemberGradeInfoDto implements Serializable {

    private static final long serialVersionUID = 264915995470358053L;

    @NotNull(message = "{validation.membermanage.grade.id}")
    private Long id;

    /**
     * gradeCode
     * 会员等级的唯一编码。本字段是从业务角度考虑的，相当于全局的唯一业务主键。
     */
    @NotNull(message = "{validation.membermanage.gradeCode}")
    private String gradeCode;

    /**
     * name
     * 会员等级名称。
     */
    @NotNull(message = "{validation.membermanage.grade.name}")
    private String name;

    /**
     * smallIcon
     * 会员等级的小图标。
     */
    private String smallIcon;

    /**
     * bigIcon
     * 会员等级的大图标。
     */
    private String bigIcon;

    /**
     * firstRatio
     * 一级培训奖励
     */
    private BigDecimal firstRatio;

    /**
     * secondRatio
     * 二级培训奖励
     */
    private BigDecimal secondRatio;

    /**
     * firstAttain
     * 单月销售奖励金额-一档
     */
    private BigDecimal firstAttain;

    /**
     * firstAttainRatio
     * 达标奖励比例-一档
     */
    private BigDecimal firstAttainRatio;

    /**
     * secondAttain
     * 单月销售奖励金额-二档
     */
    private BigDecimal secondAttain;

    /**
     * secondAttainRatio
     * 达标奖励比例-二档
     */
    private BigDecimal secondAttainRatio;

    /**
     * thirdAttain
     * 单月销售奖励金额-三档
     */
    private BigDecimal thirdAttain;

    /**
     * thirdAttainRatio
     * 达标奖励比例-三档
     */
    private BigDecimal thirdAttainRatio;

    /**
     * firstNumber
     * 所需直属人数.
     */
    @NotNull(message = "{validation.membermanage.firstNumber}")
    private Long firstNumber;

    /**
     * secondNumber
     * 所需二级人数.
     */
    @NotNull(message = "{validation.membermanage.secondNumber}")
    private Long secondNumber;

    /**
     * trainSum
     * 所需培训奖励.
     */
    @NotNull(message = "{validation.membermanage.trainSum}")
    private BigDecimal trainSum;

    /**
     * description
     * 会员等级说明。
     */
    private String description;

    /**
     * memo
     * 备注
     */
    private String memo;

    /**
     * creatorId
     * 创建人编号。取值为创建人的全局唯一主键标识符。
     */
    private Long creatorId;

    /**
     * createdTime
     * 创建时间。取值为系统的当前时间。
     */
    private Date createdTime;



}