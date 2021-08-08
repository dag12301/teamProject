package com.icia.wapoo.dao;

import com.icia.wapoo.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MemberDao {
    List<Member> selectAllMember();
    // 회원정보를 모두 가져옵니다.
    Member selectMemberByLoginId(@Param("loginId") String loginId);
    // LoginId 를 통해서 회원테이블에서 Member 객체를 가져옵니다.
    Member selectMember(@Param("loginId") String loginId,
                        @Param("loginPwd") String loginPwd);
    // LoginId, LoginPwd 를 통해서 회원테이블에서 Member 객체를 가져옵니다.
    int insertMember(Map<String, Object> params);
    // 회원테이블에 Member 객체를 삽입합니다.
}
