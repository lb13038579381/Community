package com.xupt.community.service.impl;

import com.xupt.community.dao.MemberDao;
import com.xupt.community.domain.Member;
import com.xupt.community.dto.MemberDto;
import com.xupt.community.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: lb
 * @time: 2021/1/14 6:14 下午
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;


    @Override
    public Member getById(Long memberid) {
        return memberDao.getById(memberid);
    }

    @Override
    public List<Member> getByRealName(String realName) {
        List<Member> res = memberDao.getByRealName(realName);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public List<Member> getByMajorId(Integer majorId) {
        List<Member> res = memberDao.getByMajorId(majorId);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public List<Member> getByMajorIdAndGradeAndClazz(Integer majorId, Integer grade, Integer clazz) {
        Map<String, Object> params = new HashMap<>();
        params.put("major", majorId);
        params.put("grade", grade);
        params.put("clazz", clazz);
        List<Member> res = memberDao.getByMajorIdAndGradeAndClazz(params);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public Member getByStudentNumber(String studentNumber) {
        return memberDao.getByStudentNumber(studentNumber);
    }

    @Override
    public List<Member> getByCollegeId(Long collegeId) {
        List<Member> res = memberDao.getByCollegeId(collegeId);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public List<Member> getByNickName(String nickName) {
        List<Member> res = memberDao.getByNickName(nickName);
        return CollectionUtils.isEmpty(res) ? new ArrayList<>() : res;
    }

    @Override
    public void addMember(MemberDto memberDto) {
        memberDao.addMember(memberDto);
    }
}
