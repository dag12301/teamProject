package com.icia.wapoo.service;

import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    public MemberDao memberDao;

    @Override
    public List<MemberVO> getMembers() {
        return memberDao.selectAllMember();
    }
}
