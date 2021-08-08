package com.icia.wapoo.service;

import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result joinMember(Map<String, Object> memberData) {
        int result = memberDao.insertMember(memberData);
        BigInteger id = (BigInteger) memberData.get("id"); // 오류처리 해줄것
        if(id == null || result == 0) {
            return new Result(500, "데이터 삽입에 실패했습니다", memberData);
        }
        return new Result(0, "Success", memberData);
    }

    @Override
    public Member getMember(String loginId, String loginPwd) {
        return memberDao.selectMember(loginId, loginPwd);
    }
}
