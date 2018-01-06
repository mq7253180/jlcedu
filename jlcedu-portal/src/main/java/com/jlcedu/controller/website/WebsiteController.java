package com.jlcedu.controller.website;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.jlcedu.entity.Navigation;
import com.jlcedu.service.WebsiteService;
import com.quincy.core.annotation.Synchronized;
import com.quincy.core.entity.ReturnedO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/{locale}/website")
public class WebsiteController {
	@Autowired
	private WebsiteService websiteService;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/content/website/index");
		mv.addObject("banners", websiteService.getBanners());
		mv.addObject("superSlideImg", websiteService.getSuperSlideImg());
		return mv;
	}

	@RequestMapping("/navigation/{id}")
	public ModelAndView navigation(@PathVariable(value = "id")Integer navigationId) {
		ModelAndView mv = new ModelAndView("/content/website/menu");
		mv.addObject("navigation", websiteService.getNavigationById(navigationId));
		return mv;
	}

	@RequestMapping("/menu/{id}")
	public ModelAndView menu(@PathVariable(value = "id")Integer menuId) {
		ModelAndView mv = new ModelAndView("/content/website/menu");
		mv.addObject("navigation", websiteService.getNavigationByMenuId(menuId));
		return mv;
	}

	@RequestMapping("/item/{id}")
	public ModelAndView item(HttpServletRequest request, @PathVariable(value = "id")String itemId) {
		ModelAndView mv = new ModelAndView("/content/website/item");
		Navigation navigation = websiteService.getNavigationByItemId(itemId);
		RequestContext requestContext = new RequestContext(request);
		DateFormat df = new SimpleDateFormat(requestContext.getMessage("date_format"));
		navigation.getItem().setPublishDate(df.format(navigation.getItem().getCreatedDate()));
		mv.addObject("navigation", navigation);
		return mv;
	}

	@Synchronized("xxx")
	@RequestMapping("/demo")
	public String demo(String arg) throws Exception {
		long l = System.currentTimeMillis();
		log.error("TEST_DEMO------------------"+l);
		Thread.sleep(10000);
		if(arg!=null&&arg.trim().length()>0)
			throw new Exception("xxx");
		log.error("TEST_DEMO=================="+l);
		return "/content/website/index";
	}

	@RequestMapping("/test")
	@ResponseBody
	public ReturnedO test(String arg) throws Exception {
		if(arg!=null&&arg.trim().length()>0)
			throw new Exception("xxx");
		ReturnedO o = new ReturnedO();
		o.setStatus(1);
		o.setMsg("Success");
		o.setData("Test");
		return o;
	}
}
