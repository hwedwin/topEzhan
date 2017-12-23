package com.topaiebiz.member.manage.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_mem_manager_apply")
public class MemberApplyEntity extends BaseEntity<Long> {
    /**
     * memberId
     * 会员ID
     */
    private Long memberId;

    /***
     * 申请会员类型( 0: 小白用户, 1: 普通会员 2: 店长 )
     * type
     * */
    private String type;

    /**
     * 待绑定的上级会员编号 存在赋值
     * parentMemberId
     */
    private Long parentMemberId;


    /**
     * telephone
     * 手机号
     */
    private String telephone;

    /**
     * realName
     * 真实姓名
     */
    private String realName;

    /**
     * idNum
     * 身份证号
     */
    private String idNum;

    /**
     * idCard1
     * 身份证正面
     */
    private String idCard1;

    /**
     * idCard2
     * 身份证背面
     */
    private String idCard2;

    /**
     * payImage
     * 缴费凭证
     */
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