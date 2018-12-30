package com.quincy.core.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.quincy.core.Constants;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;

@Service("authorizationSessionServiceImpl")
public class AuthorizationSessionServiceImpl extends AuthorizationAbstract {
	@Override
	protected Object getUserObject(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session==null?null:session.getAttribute(Constants.ATTR_USER);
	}

	@Override
	public String setUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constants.ATTR_USER, user);
		return CommonHelper.getValueFromCookie(request, "jsessionid");
	}

	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
	}

	public int isLogedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) {
			return 0;
		} else {
			boolean exists = session.getAttribute(Constants.ATTR_USER)!=null;
			return exists?1:0;
		}
	}

	@Override
	protected void saveVcode(HttpServletRequest request, String vcode) {
		request.getSession(true).setAttribute(Constants.ATTR_VCODE, vcode);
	}

	@Override
	public String getCachedVcode(HttpServletRequest request) {
		return this.getCachedStr(request, Constants.ATTR_VCODE);
	}

	@Override
	public void setDeniedPrivilegeName(HttpServletRequest request, String name) {
		request.getSession().setAttribute(Constants.ATTR_DENIED_PRIVILEGE, name);
	}

	@Override
	public String getDeniedPrivilegeName(HttpServletRequest request) {
		return this.getCachedStr(request, Constants.ATTR_DENIED_PRIVILEGE);
	}

	private String getCachedStr(HttpServletRequest request, String attrName) {
		HttpSession session = request.getSession();
		if(session!=null) {
			Object obj = session.getAttribute(attrName);
			return obj==null?null:obj.toString();
		}
		return null;
	}
}