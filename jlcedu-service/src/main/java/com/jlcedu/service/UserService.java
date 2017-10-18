package com.jlcedu.service;

import com.quincy.core.entity.User;

public interface UserService {
	public User selectUserByLoginName(String loginName);
}