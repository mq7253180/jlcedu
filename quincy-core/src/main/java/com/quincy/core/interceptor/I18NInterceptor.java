package com.quincy.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.quincy.core.Constant;
import com.quincy.core.common.SupportedLocalesHolder;
import com.quincy.core.helper.CommonHelper;

public class I18NInterceptor extends HandlerInterceptorAdapter {
	private static Support chainHead;
	private static Object lock = new Object();
	@Autowired
	private SupportedLocalesHolder supportedLocalesHolder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		initChain();
		String locale = chainHead.support(request, response);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
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
				}
			}
		}
	}

	private void initChain() {
		if(chainHead==null) {
			synchronized(lock) {
				if(chainHead==null) {
					Support uriSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							return CommonHelper.getFirstAsUri(request);
						}
					};
					Support domainSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							StringBuffer sb = request.getRequestURL();
							int dotIndexOf = sb.indexOf(".");
							return dotIndexOf==-1?null:sb.delete(dotIndexOf, sb.length()).delete(0, sb.indexOf("://")+3).toString();
						}
					};
					Support headerSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							return CommonHelper.getValueFromHeader(request, Constant.KEY_LOCALE);
						}
					};
					Support parameterSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							return CommonHelper.getValueFromParameter(request, Constant.KEY_LOCALE);
						}
					};
					Support cookieSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							return CommonHelper.getValueFromCookie(request, Constant.KEY_LOCALE);
						}
					};
					Support defaultSupport = new Support() {
						@Override
						protected String resolve(HttpServletRequest request, HttpServletResponse response) {
							return supportedLocalesHolder.getDefaultLocale();
						}
					};
					uriSupport.setNext(domainSupport).setNext(headerSupport).setNext(parameterSupport).setNext(cookieSupport).setNext(defaultSupport);
					chainHead = uriSupport;
				}
			}
		}
	}

	public abstract class Support {
		private Support next;

		protected abstract String resolve(HttpServletRequest request, HttpServletResponse response);

		public Support setNext(Support next) {
			this.next = next;
			return next;
		}

		public final String support(HttpServletRequest request, HttpServletResponse response) {
			String locale = this.resolve(request, response);
			if(locale!=null) {
				locale = locale.trim();
				if((locale.length()==0||!supportedLocalesHolder.isValidLocale(locale))&&this.next!=null) {
					locale = this.next.support(request, response);
				}
			} else if(this.next!=null) {
				locale = this.next.support(request, response);
			}
			return locale;
		}
	}

	/*private void printReqeustInfo(HttpServletRequest request) {
		log.warn("getAuthType---"+request.getAuthType());
		log.warn("getCharacterEncoding---"+request.getCharacterEncoding());
		log.warn("getContentLength---"+request.getContentLength());
		log.warn("getContentLengthLong---"+request.getContentLengthLong());
		log.warn("getContentType---"+request.getContentType());
		log.warn("getLocalAddr---"+request.getLocalAddr());
		log.warn("getLocale---"+request.getLocale().getLanguage());
		log.warn("getLocalName---"+request.getLocalName());
		log.warn("getLocalPort---"+request.getLocalPort());
		log.warn("getMethod---"+request.getMethod());
		log.warn("getPathInfo---"+request.getPathInfo());
		log.warn("getPathTranslated---"+request.getPathTranslated());
		log.warn("getProtocol---"+request.getProtocol());
		log.warn("getQueryString---"+request.getQueryString());
		log.warn("getRemoteAddr---"+request.getRemoteAddr());
		log.warn("getRemoteHost---"+request.getRemoteHost());
		log.warn("getRemotePort---"+request.getRemotePort());
		log.warn("getRemoteUser---"+request.getRemoteUser());
		log.warn("getRequestedSessionId---"+request.getRequestedSessionId());
		log.warn("getScheme---"+request.getScheme());
		log.warn("getServerName---"+request.getServerName());
		log.warn("getServerPort---"+request.getServerPort());
		log.warn("getServletPath---"+request.getServletPath());
		log.warn("getContextPath---"+request.getContextPath());
		log.warn("getRequestURI---"+request.getRequestURI());
		log.warn("getRequestURL---"+request.getRequestURL());
		log.warn("getUserPrincipal---"+(request.getUserPrincipal()!=null?request.getUserPrincipal().getName():"null"));
		
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String name = headers.nextElement();
			log.warn(name+"="+request.getHeader(name));
		}
		Enumeration<Locale> locales = request.getLocales();
		while(locales.hasMoreElements()) {
			Locale locale = locales.nextElement();
			log.warn("---"+locale.getLanguage());
		}
	}*/

}
