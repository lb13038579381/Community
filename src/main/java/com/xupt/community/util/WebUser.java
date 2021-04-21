package com.xupt.community.util;

import com.xupt.community.domain.Member;

public class WebUser {
    public static ThreadLocal<Member> memberThreadLocal = new ThreadLocal<>();

    public static void addUser(Member member) {
        memberThreadLocal.set(member);
    }

    public static Member get() {
        return memberThreadLocal.get();
    }
}
