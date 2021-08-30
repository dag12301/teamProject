package com.icia.wapoo.service;

import com.icia.wapoo.dao.CouponDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.Member;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface MemberService {
    Member getMemberByLoginInfo(String loginId, String password);
    Member getMemberBymemberId(int memberId);
    boolean registerMember(Map<String, Object> userData);

    boolean checkLoginId(String loginId);
    boolean checkEmail(String loginId);
    boolean checkNickname(String loginId);
    boolean checkPhone(String loginId);
    
	int getMemberListCount(String option);
	List<Map<String, Object>> getMemberList(int listPerPage, int currentPage, String option);
	void updateMemberStatus(int memberId, String status);
    
}
