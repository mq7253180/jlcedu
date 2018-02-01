package com.jlcedu.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quincy.core.entity.User;
import com.quincy.core.service.AuthorizationService;

@Controller
@RequestMapping("")
public class RootController {
	@Resource(name = "authorizationSessionServiceImpl")
	private AuthorizationService authorizationService;

	@RequestMapping("")
	public String index(HttpServletRequest request) throws Exception {
		User user = authorizationService.getUser(request);
		String uri = user==null?"/auth/signin":"/index";
		String locale = request.getLocale().toLanguageTag().replaceAll("Hans-", "");
		return "redirect:/"+locale.toLowerCase().replace("-", "_")+uri;
	}
}