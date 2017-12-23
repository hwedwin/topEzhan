package com.topaiebiz.member.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.topaiebiz.member.manage.dto.MemberLabelTypeDto;
import com.topaiebiz.member.manage.entity.MemberLabelEntity;
import com.topaiebiz.member.manage.entity.MemberLabelTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Mapper
public interface MemberLabelTypeDao extends BaseMapper<MemberLabelTypeEntity>{


    /**
     * 获取所有的标签类型列表
     * @return
     */
    List<MemberLabelTypeDto> getLabelTypeList();

    /**
     * 根据typeId获取到标签类型信息
     * @param typeId
     * @return
     */
    MemberLabelTypeDto selectLabelTypeById(@Param("typeId") String typeId);

    /**
     * 根据标签类型名字获取标签信息
     * @param labelTypeName
     * @return
     */
    MemberLabelTypeDto selectLabelTypeByName(@Param("typeName") String typeName);

    /**
     * 获取到标签类型最大的sortNo
     * @return
     */
    Long selectLabelTypeMaxSortNo();
}
