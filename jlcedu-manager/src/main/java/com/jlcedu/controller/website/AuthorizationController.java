package com.jlcedu.controller.website;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.jlcedu.JlceduConstant;
import com.jlcedu.service.UserService;
import com.quincy.core.annotation.VCodeRequired;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.AuthorizationService;

@Controller
@RequestMapping("/{locale}/auth")
public class AuthorizationController {
	@Resource(name = JlceduConstant.IMPL_AUTHORIZATION)
	private AuthorizationService authorizationService;
	@Autowired
	private UserService userService;

	@RequestMapping("/signin")
	public ModelAndView toLogin(@RequestParam(required = false, value = "back")String _back) {
		ModelAndView mv = new ModelAndView("/content/login");
		String back = CommonHelper.trim(_back);
		if(back!=null)
			mv.addObject("back", back);
		return mv;
	}

	@RequestMapping("/signout")
	public String logout(HttpServletRequest request, @PathVariable(value = "locale")String locale) throws Exception {
		authorizationService.logout(request);
		return "redirect:/"+locale+"/auth/signin";
	}

	@RequestMapping("/vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		authorizationService.vcode(request, response, JlceduConstant.VCODE_LENGTH);
	}

	/**
	 * 登录方法
	 * @param request
	 * @param _username
	 * @param _password
	 * @param _back
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/signin/do")
	@VCodeRequired
	public ModelAndView doLogin(HttpServletRequest request, 
			@RequestParam(required = false, value = "username")String _username, 
			@RequestParam(required = false, value = "password")String _password, 
			@RequestParam(required = false, value = "back")String _back) throws Exception {
		RequestContext requestContext = new RequestContext(request);
		ModelAndView mv = new ModelAndView("/content/login_result");
		String username = CommonHelper.trim(_username);
		if(CommonHelper.trim(username)==null) {
			mv.addObject(JlceduConstant.MV_ATTR_NAME_STATUS, -1);
			mv.addObject(JlceduConstant.MV_ATTR_NAME_MSG, requestContext.getMessage("auth.null.username"));
			return mv;
		}
		String password = CommonHelper.trim(_password);
		if(CommonHelper.trim(password)==null) {
			mv.addObject(JlceduConstant.MV_ATTR_NAME_STATUS, -2);
			mv.addObject(JlceduConstant.MV_ATTR_NAME_MSG, requestContext.getMessage("auth.null.password"));
			return mv;
		}
		User user = userService.selectUserByLoginName(username);
		if(user==null) {
			mv.addObject(JlceduConstant.MV_ATTR_NAME_STATUS, -3);
			mv.addObject(JlceduConstant.MV_ATTR_NAME_MSG, requestContext.getMessage("auth.failure.matched"));
			return mv;
		}
		if(!password.equalsIgnoreCase(user.getPassword())) {
			mv.addObject(JlceduConstant.MV_ATTR_NAME_STATUS, -3);
			mv.addObject(JlceduConstant.MV_ATTR_NAME_MSG, requestContext.getMessage("auth.failure.matched"));
			return mv;
		}
		authorizationService.setUser(request, user);
		mv.addObject(JlceduConstant.MV_ATTR_NAME_STATUS, 1);
		mv.addObject(JlceduConstant.MV_ATTR_NAME_MSG, requestContext.getMessage("auth.success"));
		return mv;
	}

	@RequestMapping("/timeout")
	public ModelAndView timeout(@RequestParam(required = false, value = "back")String _back) {
		ModelAndView mv = new ModelAndView("/timeout");
		String back = CommonHelper.trim(_back);
		if(back!=null)
			mv.addObject("back", back);
		return mv;
	}

	@RequestMapping("/deny")
	public ModelAndView deny(HttpServletRequest request) throws Exception {
		String name = authorizationService.getDeniedPrivilegeName(request);
		ModelAndView mv = new ModelAndView("/deny/deny");
		mv.addObject("name", name);
		return mv;
	}
}