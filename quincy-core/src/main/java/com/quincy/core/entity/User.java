package com.quincy.core.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String id;
	private String citizenId;
	private String surName;
	private String givenName;
	private String gender;
	private String mobilePhoneNo;
	private String email;
	private String password;
	private String loginName;
	private String nickName;
	private List<Resource> resources;
}
