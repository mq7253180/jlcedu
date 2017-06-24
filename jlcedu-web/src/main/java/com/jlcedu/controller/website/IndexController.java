package com.jlcedu.controller.website;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
	@RequestMapping("/{locale}/index")
	public String localIndex(@PathVariable(value = "locale")String locale) {
		return "redirect:/"+locale+"/website/index";
	}

	@RequestMapping("")
	public String index(HttpServletRequest request) {
		return "redirect:/"+request.getLocale().toLanguageTag().toLowerCase().replace("-", "_")+"/website/index";
	}
}
