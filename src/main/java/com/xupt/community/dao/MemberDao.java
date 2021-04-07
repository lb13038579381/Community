package com.xupt.community.dao;

import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/14 5:51 下午
 */
@Mapper
public interface MemberDao {
    Member getById(Long memberid);

    List<Member> getByRealName(String realName);

    List<Member> getByMajorId(Integer major);

    List<Member> getByMajorIdAndGradeAndClazz(Map<String, Object> params);

    Member getByStudentNumber(String studentNumber);

    List<Member> getByCollegeId(Long collegeId);

    List<Member> getByNickName(String nickName);

    void addMember(MemberDto memberDto);

}
