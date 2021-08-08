package com.icia.wapoo.service;

import com.icia.wapoo.model.Member;
import com.icia.wapoo.model.Result;


import java.util.List;
import java.util.Map;

public interface MemberService {
    List<Member> getMembers();
    Member getMemberByLoginId(String loginId);
    Result joinMember(Map<String, Object> memberData);
    Member getMember(String loginId, String loginPwd);
}
