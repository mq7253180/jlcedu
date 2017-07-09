package com.quincy.core.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.quincy.core.Constant;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;

@Service("authorizationSessionServiceImpl")
public class AuthorizationSessionServiceImpl extends AuthorizationAbstract {
	@Override
	protected Object getUserObject(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session==null?null:session.getAttribute(Constant.ATTR_USER);
	}

	@Override
	public String setUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(Constant.ATTR_USER, user);
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
			boolean exists = session.getAttribute(Constant.ATTR_USER)!=null;
			return exists?1:0;
		}
	}

	@Override
	protected void saveVcode(HttpServletRequest request, String vcode) {
		request.getSession(true).setAttribute(Constant.ATTR_VCODE, vcode);
	}

	@Override
	public String getCachedVcode(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session!=null) {
			Object obj = session.getAttribute(Constant.ATTR_VCODE);
			return obj==null?null:obj.toString();
		}
		return null;
	}
}
