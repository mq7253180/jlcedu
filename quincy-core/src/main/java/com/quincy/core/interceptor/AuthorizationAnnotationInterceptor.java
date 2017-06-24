package com.quincy.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.quincy.core.annotation.LoginRequired;

public class AuthorizationAnnotationInterceptor extends AuthorizationInterceptorAbstract {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		LoginRequired annotation = method.getMethod().getDeclaredAnnotation(LoginRequired.class);
		return annotation==null?true:this.doAuth(request, response, handler);
	}
}
