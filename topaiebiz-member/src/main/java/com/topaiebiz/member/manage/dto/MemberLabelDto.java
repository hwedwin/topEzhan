package com.topaiebiz.member.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemberLabelDto implements Serializable {

    // 主键id
    private Long id;

    /**
     * typeId
     * 所属类型。
     */
    private Long typeId;

    /**
     * typeName
     * 类型名称。
     */
    private String typeName;

    /**
     * typeSortNo
     * 类型排序号。
     */
    private Long typeSortNo;

    /**
     * name
     * 标签名称。
     */
    private String name;

    /**
     * sortNo
     * 标签排序号。
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