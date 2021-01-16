package com.xupt.community.controller;

import com.xupt.community.dto.MemberDto;
import com.xupt.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Resource
public class MemberController {
    @Autowired
    MemberService memberService;

    public void addMember(MemberDto memberDto) {

    }
}
