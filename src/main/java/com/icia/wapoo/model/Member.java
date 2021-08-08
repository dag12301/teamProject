package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long id;
    private String regDate;
    private String updateDate;
    private String status;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String addressDetail;
    private String LoginId;
    private String LoginPwd;
    private String nickname;
    private String userType;
    private String userLevel;
    private Long fileGroup;
}
