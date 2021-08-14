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
    Member selectMemberById(@Param("id") int id);
    // LoginId, password 를 통해서 회원테이블에서 Member 객체를 가져옵니다.
}
