package com.quincy.core.interceptor;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.quincy.core.Constants;
import com.quincy.core.annotation.WithoutAjax;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.AuthorizationService;

public abstract class AuthorizationInterceptorAbstract extends HandlerInterceptorAdapter {
	private AuthorizationService authorizationService;

	protected boolean doAuth(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)authorizationService.getUser(request);
		if(user==null) {
			String clientType = CommonHelper.clientType(request, handler);
			if(Constants.CLIENT_TYPE_J.equals(clientType)) {
				RequestContext requestContext = new RequestContext(request);
				WithoutAjax annotation = null;
				if(handler!=null) {
					HandlerMethod method = (HandlerMethod)handler;
					annotation = method.getMethod().getDeclaredAnnotation(WithoutAjax.class);
				}
				String outputContent = annotation==null?("{\"status\":0, \"msg\":\""+requestContext.getMessage("auth.timeout")+"\"}"):"Timeout";
				//ServletOutputStream out = null;
				PrintWriter out = null;
				try {
					//out = response.getOutputStream();
					response.setContentType("application/json;charset=utf-8");
					out = response.getWriter();
					out.println(outputContent);
					out.flush();
				} finally {
					if(out!=null) {
						out.close();
					}
				}
			} else {
//				String uri = "/auth/signin";
				String uri = "/auth/timeout";
				String backUri = CommonHelper.trim(request.getRequestURI());
				if(backUri.indexOf("/index")>=0)
					backUri = CommonHelper.trim(request.getParameter("back"));
				if(backUri!=null) {
					uri += "?back=";
					uri += URLEncoder.encode(backUri, "UTF-8");
				}
				String uriLocale = CommonHelper.getFirstAsUri(request);
				uri = "/"+(uriLocale!=null?uriLocale:CommonHelper.getDefaultLocale(request))+uri;
				response.sendRedirect(uri);
			}
			return false;
		} else {
			request.setAttribute(Constants.ATTR_USER, user);
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
