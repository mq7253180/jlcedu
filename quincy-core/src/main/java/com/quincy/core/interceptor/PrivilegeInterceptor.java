package com.quincy.core.interceptor;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.quincy.core.Constants;
import com.quincy.core.common.SupportedLocalesHolder;
import com.quincy.core.entity.Resource;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.AuthorizationService;

public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private SupportedLocalesHolder supportedLocalesHolder;
	private AuthorizationService authorizationService;
	private Map<String, String> resourceMap;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = authorizationService.getUser(request);
		String uri = null;
		if(user!=null) {
			uri = request.getRequestURI();
			uri = uri.substring(uri.substring(1, uri.length()).indexOf("/")+1, uri.length());
			if(this.exists(uri, user.getResources())) {
				return true;
			}
			/*if(this.exists(uri, user.getRootMenus())) {
				return true;
			}*/
		}
		String clientType = CommonHelper.clientType(request, handler);
		if(Constants.CLIENT_TYPE_J.equals(clientType)) {
			RequestContext requestContext = new RequestContext(request);
			String outputContent = "{\"status\":-1, \"msg\":\"["+resourceMap.get(uri)+"]"+requestContext.getMessage("error.deny")+"\"}";
			PrintWriter out = null;
			try {
				response.setContentType("application/json;charset=utf-8");
				out = response.getWriter();
				out.println(outputContent);
				out.flush();
			} finally {
				if(out!=null) {
					out.close();
				}
			}
		} else {
			authorizationService.setDeniedPrivilegeName(request, resourceMap.get(uri));
			uri = "/auth/deny";
			String uriLocale = CommonHelper.getFirstAsUri(request);
			uri = "/"+((uriLocale!=null&&supportedLocalesHolder.isValidLocale(uriLocale))?uriLocale:supportedLocalesHolder.getDefaultLocale())+uri;
			response.sendRedirect(uri);
		}
		return false;
	}

	private boolean exists(String uri, List<Resource> resources) {
		for(Resource r:resources) {
			if(r.getUri()!=null&&r.getUri().length()>0&&!r.getUri().equals("/")&&uri.startsWith(r.getUri())) {
				return true;
			}
		}
		return false;
	}

	/*private boolean exists(String uri, List<Menu> menus) {
		for(Menu m:menus) {
			if(m.getUri()!=null&&m.getUri().trim().startsWith(uri)) {
				return true;
			} else if(m.getChildren()!=null&&m.getChildren().size()>0) {
				boolean exists = this.exists(uri, m.getChildren());
				if(exists)
					return true;
			}
		}
		return false;
	}*/

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}
	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	public Map<String, String> getResourceMap() {
		return resourceMap;
	}
	public void setResourceMap(Map<String, String> resourceMap) {
		this.resourceMap = resourceMap;
	}
}