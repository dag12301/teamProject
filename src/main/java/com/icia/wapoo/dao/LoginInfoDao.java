package com.icia.wapoo.dao;

import com.icia.wapoo.model.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface LoginInfoDao {
    LoginInfo selectloginInfo(@Param("loginId") String loginId,
                              @Param("password") String password);
    int insertLoginInfo(LoginInfo loginInfo);
    int checkLoginId(@Param("loginId") String loginId);
}
