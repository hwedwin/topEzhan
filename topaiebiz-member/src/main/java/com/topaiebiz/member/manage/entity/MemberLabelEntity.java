package com.topaiebiz.member.manage.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_mem_label")
public class MemberLabelEntity extends BaseBizEntity<Long> {

    /**
     * typeId
     * 所属类型。
     */
    private Long typeId;

    /**
     * name
     * 标签名称。
     */
    private String name;

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