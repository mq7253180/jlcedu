package com.quincy.core.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.quincy.core.Constant;

public class AuthorizationSessionServiceImpl extends AuthorizationAbstract {
	@Override
	protected Object getUserObject(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session==null?null:session.getAttribute(Constant.ATTR_USER);
	}
}
