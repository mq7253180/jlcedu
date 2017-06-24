package com.quincy.core.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.quincy.core.entity.User;
import com.quincy.core.service.AuthorizationService;

public abstract class AuthorizationAbstract implements AuthorizationService {

	protected abstract Object getUserObject(HttpServletRequest request);

	public User getUser(HttpServletRequest request) {
		Object obj = this.getUserObject(request);
		return obj==null?null:(User)obj;
	}

	public User getUser(String id) {
		return null;
	}
}
