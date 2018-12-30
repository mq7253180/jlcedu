package com.quincy.core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.quincy.core.Constants;
import com.quincy.core.annotation.VCodeRequired;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.AuthorizationService;

public class VCodeInterceptor extends HandlerInterceptorAdapter {
	private AuthorizationService authorizationService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		VCodeRequired annotation = method.getMethod().getDeclaredAnnotation(VCodeRequired.class);
		if(annotation!=null) {
			RequestContext requestContext = new RequestContext(request);
			int status = 0;
			String msgKey = null;
			String cachedVCode = CommonHelper.trim(authorizationService.getCachedVcode(request));
			if(cachedVCode!=null&&cachedVCode.length()>0) {
				String vcode = CommonHelper.trim(request.getParameter(Constants.ATTR_VCODE));
				if(vcode!=null&&vcode.equalsIgnoreCase(cachedVCode)) {
					return true;
				}
				status = -4;
				msgKey = "auth.failure.vcode";
			} else {
				status = -3;
				msgKey = "auth.timeout.vcode";
			}
			String clientType = CommonHelper.clientType(request, handler);
			if(clientType.equals(Constants.CLIENT_TYPE_J)) {
				PrintWriter out = null;
				try {
					//out = response.getOutputStream();
					response.setContentType("application/json;charset=utf-8");
					out = response.getWriter();
					out.println("{\"status\":"+status+", \"msg\":\""+requestContext.getMessage(msgKey)+"\"}");
					out.flush();
				} finally {
					if(out!=null)
						out.close();
				}
			} else {
				//Redirect
			}
			return false;
		}
		return true;
	}

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}
	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
}
