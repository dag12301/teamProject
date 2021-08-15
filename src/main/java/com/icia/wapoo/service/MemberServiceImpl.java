package com.icia.wapoo.service;

import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Member getMemberBymemberId(int memberId) {
        return memberDao.selectMemberById(memberId);
    }

    @Override
    public boolean registerMember(Map<String, Object> userData) {
        // 중복체크 해줘야함 * 중복이 없으면 진행
        System.out.println("중복체크를 진행합니다.");
        Member member = new Member();

        member.setEmail((String) userData.get("email"));
        int result = memberDao.insertMember(member);
        if(result > 0){
            System.out.println("member테이블에 데이터 삽입완료");
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setLoginId((String) userData.get("loginId"));
            loginInfo.setPassword((String) userData.get("password"));
            loginInfo.setMember_id((int) userData.get("id"));
            loginInfoDao.insertLoginInfo(userData);
        }
        return false;
    }
}
