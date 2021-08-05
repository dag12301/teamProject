package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private long id;
    private String regDate;
    private String updateDate;
    private String status;
    private String name;
    private String phone;
    private String address;
    private String LoginId;
    private String LoginPwd;
    private String nickname;
    private String userType;
    private String userLevel;
}
