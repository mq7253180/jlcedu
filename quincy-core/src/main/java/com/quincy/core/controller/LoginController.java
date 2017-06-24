package com.quincy.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/*/login")
public class LoginController {
	@RequestMapping("")
	public ModelAndView toLoginPage() {
		ModelAndView mv = new ModelAndView("/login");
		return mv;
	}
	
	@RequestMapping("/express")
	public ModelAndView toExpressLoginPage() {
		ModelAndView mv = new ModelAndView("/expressLogin");
		return mv;
	}

	@RequestMapping("/doLogin")
	public ModelAndView doLogin() {
		ModelAndView mv = new ModelAndView("/login");
		return mv;
	}

}
