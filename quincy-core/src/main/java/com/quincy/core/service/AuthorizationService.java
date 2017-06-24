package com.quincy.core.service;

import javax.servlet.http.HttpServletRequest;

import com.quincy.core.entity.User;

public interface AuthorizationService {
	public User getUser(HttpServletRequest request);
	public User getUser(String id);
}
