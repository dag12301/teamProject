package com.icia.wapoo.service;

import com.icia.wapoo.dao.MemberDao;
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

    @Override
    public List<Member> getMembers() {
        return memberDao.selectAllMember();
    }

    @Override
    public Member getMemberByLoginId(String loginId) {
        return memberDao.selectMemberByLoginId(loginId);
    }

    @Override
    public int joinMember(Map<String, Object> memberData) {
        int result = memberDao.insertMember(memberData);
        BigInteger id = (BigInteger) memberData.get("id"); // 오류처리 해줄것
        System.out.println(result + " 건의 데이터 삽입 성공 : id = "+ id);
        return result;
    }

    @Override
    public Member getMember(String loginId, String loginPwd) {
        return memberDao.selectMember(loginId, loginPwd);
    }
}
