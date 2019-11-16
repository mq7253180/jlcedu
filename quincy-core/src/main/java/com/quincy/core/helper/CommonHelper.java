package com.quincy.core.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import com.quincy.core.Constants;
import com.quincy.core.annotation.WithoutAjax;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonHelper {
	private static I18NSupport i18nChainHead;
	private static String[] MOBILE_USER_AGENT_FLAGS = {"iPhone", "iPad", "Android"};

	public static String getLocale(HttpServletRequest request) {
		return i18nChainHead.support(request);
	}

	static {
		I18NSupport headerSupport = new I18NSupport() {
			@Override
			protected String resolve(HttpServletRequest request) {
				return CommonHelper.getValueFromHeader(request, Constants.KEY_LOCALE);
			}
		};
		I18NSupport cookieSupport = new I18NSupport() {
			@Override
			protected String resolve(HttpServletRequest request) {
				return CommonHelper.getValueFromCookie(request, Constants.KEY_LOCALE);
			}
		};
		I18NSupport parameterSupport = new I18NSupport() {
			@Override
			protected String resolve(HttpServletRequest request) {
				return CommonHelper.getValueFromParameter(request, Constants.KEY_LOCALE);
			}
		};
		I18NSupport uriSupport = new I18NSupport() {
			@Override
			protected String resolve(HttpServletRequest request) {
				String locale = CommonHelper.getFirstAsUri(request);
				log.warn("FIRST_URI_AS_LOCALE==================={}", locale);
				return Constants.KEY_LOCALE.equalsIgnoreCase(locale)?locale:null;
			}
		};
		I18NSupport defaultSupport = new I18NSupport() {
			@Override
			protected String resolve(HttpServletRequest request) {
				return getDefaultLocale(request);
			}
		};
		headerSupport.setNext(headerSupport).setNext(parameterSupport).setNext(cookieSupport).setNext(uriSupport).setNext(defaultSupport);
		i18nChainHead = headerSupport;
	}

	public static abstract class I18NSupport {
		private I18NSupport next;

		protected abstract String resolve(HttpServletRequest request);

		public I18NSupport setNext(I18NSupport next) {
			this.next = next;
			return next;
		}

		public final String support(HttpServletRequest request) {
			String locale = this.resolve(request);
			if(locale!=null) {
				locale = locale.trim();
				if(locale.length()==0&&this.next!=null) {
					locale = this.next.support(request);
				}
			} else if(this.next!=null) {
				locale = this.next.support(request);
			}
			return locale;
		}
	}

	public static String getDefaultLocale(HttpServletRequest request) {
		Locale locale = request.getLocale();
		return locale.getLanguage()+"_"+locale.getCountry();
	}

	public static String trim(String s) {
		if(s!=null) {
			String _s = s.trim();
			if(_s.length()>0) {
				return _s;
			}
		}
		return null;
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static String clientType() {
		return clientType(getRequest(), null);
	}

	public static String clientType(Object handler) {
		return clientType(getRequest(), handler);
	}

	public static String clientType(HttpServletRequest request) {
		return clientType(request, null);
	}

	public static String clientType(HttpServletRequest request, Object handler) {
		ResponseBody annotation = null;
		if(handler!=null) {
			HandlerMethod method = (HandlerMethod)handler;
			annotation = method.getMethod().getDeclaredAnnotation(ResponseBody.class);
		}
		if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))||isApp(request)||annotation!=null) {
			return Constants.CLIENT_TYPE_J;
		} else {
			String userAgent = request.getHeader("user-agent");
			if(userAgent!=null) {
				for(String flag:MOBILE_USER_AGENT_FLAGS) {
					if(userAgent.contains(flag)) {
						return Constants.CLIENT_TYPE_M;
					}
				}
			}
			return Constants.CLIENT_TYPE_P;
		}
	}

	public static String clientTypeExceptAjax(HttpServletRequest request, Object handler) {
		ResponseBody annotation = null;
		WithoutAjax withoutAnnotation = null;
		if(handler!=null) {
			HandlerMethod method = (HandlerMethod)handler;
			annotation = method.getMethod().getDeclaredAnnotation(ResponseBody.class);
			withoutAnnotation = method.getMethod().getDeclaredAnnotation(WithoutAjax.class);
		}
		if((withoutAnnotation!=null?false:"XMLHttpRequest".equals(request.getHeader("x-requested-with")))||isApp(request)||annotation!=null) {
			return Constants.CLIENT_TYPE_J;
		} else {
			String userAgent = request.getHeader("user-agent");
			if(userAgent!=null) {
				for(String flag:MOBILE_USER_AGENT_FLAGS) {
					if(userAgent.contains(flag)) {
						return Constants.CLIENT_TYPE_M;
					}
				}
			}
			return Constants.CLIENT_TYPE_P;
		}
	}

	public static String getValueFromHeader(HttpServletRequest request, String key) {
		String value = request.getHeader(key);
		if(value!=null) {
			value = value.trim();
			if(value.length()>0) {
				return value;
			}
		}
		return null;
	}

	public static String getValueFromParameter(HttpServletRequest request, String key) {
		String value = request.getParameter(key);
		if(value!=null) {
			value = value.trim();
			if(value.length()>0) {
				return value;
			}
		}
		return null;
	}

	public static String getValueFromCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				String name = cookie.getName();
				if(name!=null) {
					name = name.trim();
					if(key.equals(name)) {
						String value = cookie.getValue();
						if(value!=null) {
							value = value.trim();
							if(value.length()>0) {
								return value;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static String getValue(HttpServletRequest request, String key) {
		String value = getValueFromHeader(request, key);
		if(value!=null) {
			return value;
		}
		value = getValueFromParameter(request, key);
		if(value!=null) {
			return value;
		}
		value = getValueFromCookie(request, key);
		if(value!=null) {
			return value;
		}
		return null;
	}

	public static String getApp(HttpServletRequest request) {
		return getValue(request, Constants.CLIENT_APP);
	}

	public static boolean isApp(HttpServletRequest request) {
		String app = getApp(request);
		return app!=null;
	}

	public static String getFirstAsUri(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String[] ss = uri.split("/");
		return ss.length<2?null:ss[1];
	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
    		oos = new ObjectOutputStream(bos);
    		oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
		} finally {
			if(bos!=null)
				bos.close();
			if(oos!=null)
				oos.close();
		}
	}

	public static Object unSerialize(byte[] byteArray) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
        	bis = new ByteArrayInputStream (byteArray);
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} finally {
			if(bis!=null)
				bis.close();
			if(ois!=null)
				ois.close();
		}
	}

}
