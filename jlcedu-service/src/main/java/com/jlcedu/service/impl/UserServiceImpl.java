package com.jlcedu.service.impl;

import org.springframework.stereotype.Service;

import com.jlcedu.service.UserService;
import com.quincy.core.annotation.ReadOnly;
import com.quincy.core.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	@ReadOnly
	public User selectUserByLoginName(String loginName) {
		return null;
	}	
}