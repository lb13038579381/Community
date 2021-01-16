package com.xupt.community.service;

import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/14 6:15 下午
 */
public interface MemberService {
    Member getById(Long memberid);

    List<Member> getByRealName(String realName);

    List<Member> getByMajorId(Integer majorId);

    List<Member> getByMajorIdAndGradeAndClazz(Integer majorId, Integer grade, Integer clazz);

    Member getByStudentNumber(Long studentNumber);

    List<Member> getByCollegeId(Long collegeId);

    List<Member> getByNickName(String nickName);

    void addMember(MemberDto memberDto);
}
