package com.quincy.core.interceptor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ResourceDebugModeFilter implements Filter {
	private String[] excludes;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean last = true;
		if(excludes!=null&&excludes.length>0) {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			String uri = httpServletRequest.getRequestURI();
			for(String exclude:excludes) {
				if(uri.startsWith(exclude)) {
					String path = httpServletRequest.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/")+uri.substring(1, uri.length());
					BufferedInputStream in = null;
					BufferedOutputStream out = null;
					try {
						in = new BufferedInputStream(new FileInputStream(path));
						out = new BufferedOutputStream(response.getOutputStream());
						byte[] buf = new byte[in.available()];
						in.read(buf);
						out.write(buf);
						out.flush();
					} finally {
						if(in!=null)
							in.close();
						if(out!=null)
							out.close();
					}
					last = false;
					break;
				}
			}
		}
		if(last)
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		String excludes = cfg.getInitParameter("excludes");
		if(excludes!=null) {
			this.excludes = excludes.trim().split(",");
		}
	}

	@Override
	public void destroy() {
		
	}
}
