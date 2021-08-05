package com.icia.wapoo.service;

import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MemberService {
    public List<MemberVO> getMembers();
}
