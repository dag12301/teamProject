package com.icia.wapoo.model;


import lombok.Data;


@Data
public class User {
	String loginId;
	String password;
	String lastPasswordChanged;
	String memberId;
}
