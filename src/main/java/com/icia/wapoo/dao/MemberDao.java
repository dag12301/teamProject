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
    Member selectMemberByLoginId(@Param("loginId") String loginId);
    Member selectMember(@Param("loginId") String loginId,
                        @Param("loginPwd") String loginPwd);
    int insertMember(Map<String, Object> params);
}
