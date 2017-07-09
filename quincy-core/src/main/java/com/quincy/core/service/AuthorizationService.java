package com.quincy.core.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quincy.core.entity.User;

public interface AuthorizationService {
	public User getUser(HttpServletRequest request) throws Exception;
	/**
	 * @param request
	 * @param user
	 * @return: 返回cookie值
	 * @throws Exception
	 */
	public String setUser(HttpServletRequest request, User user) throws Exception;
	public void vcode(HttpServletRequest request, HttpServletResponse response, int length) throws IOException;
	public void logout(HttpServletRequest request);
	/**
	 * @param request
	 * @return: 1已登录; 0未登录
	 */
	public int isLogedIn(HttpServletRequest request);
	public String getCachedVcode(HttpServletRequest request);
}
