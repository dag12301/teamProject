package com.icia.wapoo.service;

import com.icia.wapoo.model.Member;


import java.util.List;
import java.util.Map;

public interface MemberService {
    List<Member> getMembers();
    Member getMemberByLoginId(String loginId);
    int joinMember(Map<String, Object> memberData);
    Member getMember(String loginId, String loginPwd);
}
