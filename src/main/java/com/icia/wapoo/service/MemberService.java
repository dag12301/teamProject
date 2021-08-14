package com.icia.wapoo.service;

import com.icia.wapoo.model.Member;


import java.util.List;
import java.util.Map;

public interface MemberService {
    Member getMemberByLoginInfo(String loginId, String password);
}
