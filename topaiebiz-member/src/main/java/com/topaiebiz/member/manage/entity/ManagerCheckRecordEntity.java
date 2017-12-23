package com.topaiebiz.member.manage.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_mem_manager_check_record")
public class ManagerCheckRecordEntity extends BaseEntity<Long> {

    /**
     * applyId
     * 申请ID。
     */
    private Long applyId;

    /**
     * resultState
     * 审核结果( 1: 审核通过, 2: 未通过)
     */
    private Byte resultState;

    /**
     * checkId
     * 审核人。
     */
    private Long checkId;

    /**
     * checkTime
     * 审核时间。
     */
    private Date checkTime;

    /**
     * memo
     * 备注。
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