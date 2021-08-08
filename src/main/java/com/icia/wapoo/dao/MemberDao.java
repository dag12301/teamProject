package com.icia.wapoo.dao;

import com.icia.wapoo.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberDao {
    List<MemberVO> selectAllMember();
    MemberVO selectMemberByLoginId(@Param("loginId") String loginId);
}
