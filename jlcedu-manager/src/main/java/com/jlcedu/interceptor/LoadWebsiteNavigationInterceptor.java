package com.jlcedu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jlcedu.service.WebsiteService;
import com.quincy.core.helper.CommonHelper;

public class LoadWebsiteNavigationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private WebsiteService jlceduService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null) {
			modelAndView.addObject("navigations", jlceduService.getNavigations(CommonHelper.clientType(request)));
		}
	}
}
