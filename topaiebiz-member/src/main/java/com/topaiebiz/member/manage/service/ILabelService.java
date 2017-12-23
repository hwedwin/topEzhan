package com.topaiebiz.member.manage.service;

import com.topaiebiz.member.manage.dto.LabelDto;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/22.
 * desc:
 */
public interface ILabelService {
    /**
     * 获取所有的标签列表
     * @return
     */
    List<LabelDto> getLabelList();

    /**
     * 标签管理---> 调整标签类型排序
     * @param labelTypeId 标签类型id
     * @param optType 操作类型  1: 升序, 2降序
     * @return
     */
    boolean adjustLabelTypeSortNo(String labelTypeId, String optType);

    /**
     * 标签管理---> 调整标签排序
     * @param labelId 标签id
     * @param optType 操作类型  1: 升序, 2降序
     * @return
     */
    boolean adjustLabelSortNo(String labelId, String optType);

    /**
     * 标签管理---> 根据标签类型id获取到下面的所有标签
     * @param labelTypeId
     * @return
     */
    LabelDto getLabelInfoByTypeId(String labelTypeId);


    /**
     * 标签管理---> 新增标签类型
     * @param labelTypeName 标签类型名称
     * @return
     */
    boolean addLabelType(String labelTypeName);

    /**
     * 标签管理---> 新增标签
     * @param labelDto
     * @return
     */
    boolean addLabel(LabelDto labelDto);

    /**
     * 标签管理---> 判断标签类型是否重复
     * @param labelTypeName
     * @return
     */
    boolean judgeLabelTypeName(String labelTypeName);

    /**
     * 标签管理---> 判断标签名称是否重复
     * @param labelName
     * @return
     */
    boolean judgeLabelName(String labelName);

    /**
     * 删除标签
     * @param id
     * @return
     */
    boolean delLabel(Long id);

    boolean delLabelType(Long typeId);

    boolean modifyLabel(LabelDto labelDto);
}
