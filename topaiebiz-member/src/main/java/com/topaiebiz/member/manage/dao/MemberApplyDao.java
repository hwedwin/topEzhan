package com.topaiebiz.member.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.entity.MemberApplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Mapper
public interface MemberApplyDao extends BaseMapper<MemberApplyEntity>{


    /**
     * 根据memberId 获取到店主信息审核记录列表
     * @param memberId
     * @return
     */
    List<MemberApplyDto> selectByMemberId(@Param("memberId") Long memberId);
}
