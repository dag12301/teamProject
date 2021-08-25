package com.icia.wapoo.service;

import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    @Autowired
    private final MemberDao memberDao;

    @Autowired
    private final LoginInfoDao loginInfoDao;

    @Override
    public Member getMemberByLoginInfo(String loginId, String password) {
        System.out.println("아이디 " + loginId +" 비번 "+ password + " 으로 로그인시도");
        LoginInfo loginInfo = loginInfoDao.selectloginInfo(loginId, password);


        if(loginInfo == null) {
            System.out.println("아이디, 비밀번호로 로그인 가능한 정보가 없습니다." + loginInfo);
            // 아이디, 비밀번호로 조회한 로그인가능한 정보가 없음.
            return null;
        }
        System.out.println("loginInfoDao : "+loginInfo);
        return memberDao.selectMemberById(loginInfo.getMember_id());
    }

    @Override
    public Member getMemberBymemberId(int memberId) {
        return memberDao.selectMemberById(memberId);
    }

    /**
     * Member 테이블을 먼저 삽입하고, 성공적( return 1 ) 이면, loginInfo 테이블에 로그인아이디 비밀번호를 저장한다.
     * @param userData
     * @return
     */
    @Override
    public boolean registerMember(Map<String, Object> userData) {
        Member member = new Member();
        member.setPhone((String) userData.get("phone"));
        member.setName((String) userData.get("name"));
        member.setEmail((String) userData.get("email"));
        member.setNickname((String) userData.get("nickname"));
        member.setRole((String) userData.get("role"));

        System.out.println("Member 테이블에 데이터를 삽입합니다.");

        int result = memberDao.insertMember(member);
        if(result > 0){
            System.out.println("결과값 : " + member.getMemberId());
            System.out.println("member테이블에 데이터 삽입완료 생성된 id -> " + member.getMemberId());
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setLoginId((String) userData.get("loginId"));
            loginInfo.setPassword((String) userData.get("password"));
            loginInfo.setMember_id(member.getMemberId());
            System.out.println("LoginInfo 에 들어갈 값 "+ loginInfo);
            result = loginInfoDao.insertLoginInfo(loginInfo);
            if(result > 0) {
                System.out.println("LoginInfo 데이터 삽입 완료");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLoginId(String loginId) {
        int result = loginInfoDao.checkLoginId(loginId);
        System.out.println("loginId 중복체크 .. 중복된 아이디 : " + result);
        if(result > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        int result = memberDao.checkEmail(email);
        System.out.println("email 중복체크 .. 중복된 이메일 : " + result);
        if(result > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkNickname(String nickname) {
        int result = memberDao.checkNickname(nickname);
        System.out.println("nickname 중복체크 .. 중복된 닉네임 : " + result);
        if(result > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPhone(String phone) {
        int result = memberDao.checkPhone(phone);
        System.out.println("phone 중복체크 .. 중복된 폰 : " + result);
        if(result > 0) {
            return false;
        }
        return true;
    }
}
