package com.icia.wapoo.service;

import com.icia.wapoo.model.Member;

import java.util.Map;

public interface MemberService {
    Member getMemberByLoginInfo(String loginId, String password);
    Member getMemberBymemberId(int memberId);
    boolean registerMember(Map<String, Object> userData);
}
