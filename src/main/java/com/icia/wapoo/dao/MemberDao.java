package com.icia.wapoo.dao;

import com.icia.wapoo.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberDao {
    List<MemberVO> selectAllMember();
}
