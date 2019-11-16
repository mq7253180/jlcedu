package com.quincy.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.quincy.core.helper.CommonHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class I18NInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		String locale = CommonHelper.getLocale(request);
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
		log.warn("LOCALE============={}", locale);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null) {
			String viewName = modelAndView.getViewName();
			if(viewName!=null) {
				viewName = viewName.trim();
				if(!viewName.startsWith("redirect")&&!viewName.startsWith("forward")) {
					modelAndView.setViewName(modelAndView.getViewName()+"_"+CommonHelper.clientType(request, handler));
					/*Object result = modelAndView.getModelMap().get(RESULT);
					if(result==null) {
						modelAndView.addObject(RESULT, -1);
					}*/
				}
			}
		}
	}
}
