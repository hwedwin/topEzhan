package com.topaiebiz.member.grade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.grade.dto.MemberGradeDto;
import com.topaiebiz.member.grade.entity.MemberGradeEntity;
import com.topaiebiz.member.grade.entity.MemberGradePrivilegeEntity;
/**
 * 
 * Description： 描述会员等级的接口，并向会员等级控制层提供相关的方法。 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年11月28日 下午1:37:49 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemberGradeDao extends BaseDao<MemberGradeEntity>{
	
	/**
	 * 
	 * Description： 对会员名称进行重复验证 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param name
	 * 		会员名称
	 * @return
	 */
	MemberGradeEntity selectMemberGradeByName(MemberGradeDto memberGrade);
	
	/**
	 * 
	 * Description： 对会员名称进行重复验证 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param name
	 * 		会员名称
	 * @return
	 */
	MemberGradeEntity selectMemberGradeByNames(MemberGradeDto memberGrade);
	
	/**
	 * 
	 * Description： 对会员编号进行重复验证  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param gradeCode
	 * 		会员编号
	 * @return
	 */
	MemberGradeEntity selectMemberGradeByCodes(MemberGradeEntity memberGrade);

	/**
	 * 
	 * Description：会员等级分页加列表加查询   
	 * 
	 * Author Scott.Yang
	 * 
	 * @param page
	 * 		分页参数
	 * @param memberGradeDto
	 * 		会员等级Dto
	 * @return
	 */
	List<MemberGradeDto> selectPageMemberGrade(Page<MemberGradeDto> page, MemberGradeDto memberGradeDto);
	
	/**
	 * 
	 * Description： 通过ID查询对象是否存在
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员等级ID
	 * @return
	 */
	MemberGradeEntity selectMemberGradeById(Long id);
	
	/**
	 * 
	 * Description：  批量删除会员等级 (修改状态)
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员等级ID
	 * @return 
	 */
	void deleteMemberGrades(Long[] id);
	/**
	 * 
	 * Description： 会员等级配置特权
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberGradeRight
	 * 		会员等级权限Entity
	 * @return
	 */
	Integer insertMemberGradePrivilege(MemberGradePrivilegeEntity memberGradePrivilegeEntity);
	/**
	 * 
	 * Description： 获取会员等级列表
	 * 
	 * Author Scott.Yang
	 * 
	 * @return
	 */
	List<MemberGradeEntity> selectListOfMemberGrade();
	/**
	 * 
	 * Description：通过会员编码查询对象成长值
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param gd
	 * @return
	 */
	MemberGradeEntity selectMemberGradeBygradeCode(MemberGradeDto memberGradeDto);
	/**
	 * 
	 * Description： 查询最大ID所对应的编码值  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	MemberGradeEntity selectMemberGradeByMaxGradeCode(MemberGradeDto memberGradeDto);

	







	
}
