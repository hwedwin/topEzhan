package com.topaiebiz.member.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.topaiebiz.member.manage.dto.MemberGradeInfoDto;
import com.topaiebiz.member.manage.entity.MemberGradeInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Mapper
public interface MemberGradeManagerDao extends BaseMapper<MemberGradeInfoEntity> {


    /**
     * 查询店主会员等级列表
     * @return
     */
    List<MemberGradeInfoDto> selectGradeList();


}
