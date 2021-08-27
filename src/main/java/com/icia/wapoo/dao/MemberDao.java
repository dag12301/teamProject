package com.icia.wapoo.dao;

import com.icia.wapoo.model.Coupon;
import com.icia.wapoo.model.Member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface MemberDao {
    // id 를 통해서 Member객체를 반환합니다.
    Member selectMemberById(@Param("id") int id);
    int insertMember(Member member);

    int checkEmail(@Param("email") String email);
    int checkNickname(@Param("nickname") String nickname);
    int checkPhone(@Param("phone") String phone);
    
    
    List<Map<String, Object>> selectMemberList(@Param("startLimit") int startLimit, @Param("endLimit") int endLimit, @Param("option") String option);

    int selectMemberListCount(@Param("option") String option);
    
    void updateMemberStatus(@Param("memberId") int memberId, @Param("status") String status);
}
