package com.icia.wapoo.service;

import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    public MemberDao memberDao;

    @Autowired
    public LoginInfoDao loginInfoDao;


    @Override
    public Member getMemberByLoginInfo(String loginId, String password) {
        System.out.println(loginId +" : "+ password);
        LoginInfo loginInfo = loginInfoDao.selectloginInfo(loginId, password);
        System.out.println("loginInfoDao -> "+loginInfo);
        if(loginInfo == null) {
            System.out.println("아이디, 비밀번호로 로그인 가능한 정보가 없습니다." + loginInfo);
            // 아이디, 비밀번호로 조회한 로그인가능한 정보가 없음.
            return null;
        }
        return memberDao.selectMemberById(loginInfo.getMember_id());
    }
}
