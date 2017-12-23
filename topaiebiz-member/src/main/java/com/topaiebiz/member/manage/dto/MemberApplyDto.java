package com.topaiebiz.member.manage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class MemberApplyDto implements Serializable {

    private static final long serialVersionUID = -737742502932830775L;

    // id主键
    private Long id;

    /**
     * memberId
     * 会员ID
     */
    private Long memberId;

    /**
     * 分享链接中包含有上级memberId的情况下, 赋值
     */
    private Long parentMemberId;

    /***
     * 申请会员类型( 0: 小白用户, 1: 普通会员 2: 店长 )
     * type
     * */
    private String type;


    /**
     * telephone
     * 手机号
     */
    @NotNull(message = "{validation.member.telephone}")
    private String telephone;

    /**
     * realName
     * 真实姓名
     */
    @NotNull(message = "{validation.member.realName}")
    private String realName;

    /**
     * realName
     * 真实姓名
     */
    @NotNull(message = "{validation.member.idNum}")
    private String idNum;

    /**
     * idCard1
     * 身份证正面
     */
    @NotNull(message = "{validation.member.Id.pic}")
    private String idCard1;

    /**
     * idCard2
     * 身份证背面
     */
    @NotNull(message = "{validation.member.Id.pic}")
    private String idCard2;

    /**
     * payImage
     * 缴费凭证
     */
    @NotNull(message = "{validation.member.payment.pic}")
    private String payImage;

    /**
     * state
     * 状态(0: 未审核, 1: 审核通过, 2: 未通过)
     */
    private Byte state;

    /**
     * checkId
     * 审核人
     */
    private Long checkId;

    /**
     * checkTime
     * 审核时间
     */
    private Date checkTime;

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

    /**
     * lastModifierId
     * 最后修改人编号。取值为最后修改人的全局唯一主键标识符。
     */
    private Long lastModifierId;

    /**
     * lastModifiedTime
     * 最后修改时间。取值为系统的当前时间。
     */
    private Date lastModifiedTime;

}