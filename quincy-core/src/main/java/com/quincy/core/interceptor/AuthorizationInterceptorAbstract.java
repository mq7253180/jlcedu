package com.quincy.core.interceptor;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.quincy.core.Constant;
import com.quincy.core.common.SupportedLocalesHolder;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.AuthorizationService;

public abstract class AuthorizationInterceptorAbstract extends HandlerInterceptorAdapter {
	@Autowired
	private SupportedLocalesHolder supportedLocalesHolder;
	private AuthorizationService authorizationService;

	protected boolean doAuth(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		User user = authorizationService.getUser(request);
		if(user==null) {
			String clientType = CommonHelper.clientType(request, handler);
			if(Constant.CLIENT_TYPE_J.equals(clientType)) {
				RequestContext requestContext = new RequestContext(request);
				ServletOutputStream out = null;
				try {
					out = response.getOutputStream();
					out.println("{\"status\":0, \"data\":\""+requestContext.getMessage("login.timeout")+"\"}");
				} finally {
					if(out!=null) {
						out.close();
					}
				}
			} else {
				String uri = "/login?back="+request.getRequestURI();
				String uriLocale = CommonHelper.getFirstAsUri(request);
				if(uriLocale!=null&&supportedLocalesHolder.isValidLocale(uriLocale)) {
					uri = "/"+uriLocale+uri;
				}
				response.sendRedirect(uri);
			}
			return false;
		} else {
			request.setAttribute(Constant.ATTR_USER, user);
			return true;
		}
	}

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}
	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
}
