package com.topaiebiz.member.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.dto.MemberLabelDto;
import com.topaiebiz.member.manage.entity.MemberApplyEntity;
import com.topaiebiz.member.manage.entity.MemberLabelEntity;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Mapper
public interface MemberLabelDao extends BaseMapper<MemberLabelEntity>{

    /**
     * 获取到标签信息列表
     * @return
     */
    List<MemberLabelDto> getLabelList();

    /**
     * 根据类型id查询标签列表
     * @param typeId
     * @return
     */
    List<MemberLabelDto> selectLabelListByTypeId(@Param("typeId") Long typeId);

    /**
     * 根据标签名获取标签信息
     * @param name
     * @return
     */
    MemberLabelDto selectLabelByName(@Param("name") String name);

    /**
     * 根据标签名获取
     * @param name
     * @return
     */
    Long selectLabelSortNoByName(@Param("name") String name);

    /**
     * 根据标签类型id获取标签的最大排序
     * @param typeId
     * @return
     */
    Long selectLabelMaxSortNoByTypeId(@Param("typeId") Long typeId);

    Long deleteLabelByTypeId(@Param("typeId") Long typeId);
}
