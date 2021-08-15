package com.icia.wapoo.dao;

import com.icia.wapoo.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface MemberDao {
    // id 를 통해서 Member객체를 반환합니다.
    Member selectMemberById(@Param("id") int id);
    int insertMember(Member member);
}
