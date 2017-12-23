package com.topaiebiz.member.manage.dto;

import com.nebulapaas.data.mybatis.common.BaseBizEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemberLabelTypeDto implements Serializable {
    /**
     * id
     * 全局唯一标识符。
     */
    private Long id;

    /**
     * typeName
     * 类型名称。
     */
    private String typeName;

    /**
     * sortNo
     * 排序号。
     */
    private Long sortNo;

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