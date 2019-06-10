package com.quincy.core.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.quincy.core.Constants;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.helper.HttpClientHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {
	private String path;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		log.error(HttpClientHelper.reqInfo(request), e);
		String clientType = CommonHelper.clientType(request, handler);
		String exception = Constants.CLIENT_TYPE_J.equals(clientType)?e.toString():this.getExceptionStackTrace(e, "<br/>", "&nbsp;");
		ModelAndView mv = new ModelAndView(path+"_"+clientType);
		mv.addObject("exception", exception);
		return mv;
	}

	private String getExceptionStackTrace(Exception e, String lineBreak, String spaceSymbol) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder msg = new StringBuilder("*************");
		msg.append(df.format(new Date()));
		msg.append("*************");
		msg.append(lineBreak);
		msg.append(e.toString());
		StackTraceElement[] elements = e.getStackTrace();
		for(int i=0;i<elements.length;i++) {
			msg.append(lineBreak);
			for(int j=0;j<10;j++) {
				msg.append(spaceSymbol);
			}
			msg.append("at");
			msg.append(spaceSymbol);
			msg.append(elements[i].toString());
		}
		return msg.toString();
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
