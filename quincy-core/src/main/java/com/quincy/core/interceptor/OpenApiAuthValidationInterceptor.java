package com.quincy.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quincy.core.Constant;
import com.quincy.core.annotation.OpenApiAuthRequired;
import com.quincy.core.entity.Permission;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.service.PermissionService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OpenApiAuthValidationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		HandlerMethod method = (HandlerMethod)handler;
		OpenApiAuthRequired annotation = method.getMethod().getDeclaredAnnotation(OpenApiAuthRequired.class);
		if(annotation!=null) {
			Error error = this.validate(request);
			if(error!=null) {
				if(Constant.CLIENT_TYPE_J.equals(CommonHelper.clientType(request))) {
					new OutputOperation() {
						@Override
						protected void run(PrintWriter out) throws IOException {
							out.println("{\"status\":\""+error.getCode()+"\", \"msg\":\""+error.getMsg()+"\"}");
						}
					}.start(response);
				} else {
					Document doc = new Document(new Element("result")
						.addContent(new Element("code").setText(error.getCode()))
						.addContent(new Element("message").setText(error.getMsg()))
					);
					new OutputOperation() {
						@Override
						protected void run(PrintWriter out) throws IOException {
							new XMLOutputter().output(doc, out);
						}
					}.start(response);
				}
				return false;
			}
		}
		return true;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class Error {
		private String code;
		private String msg;
	}

	private abstract class OutputOperation {
		protected abstract void run(PrintWriter out) throws IOException;

		public void start(HttpServletResponse response) throws IOException {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				this.run(out);
				out.flush();
			} finally {
				if(out!=null) {
					out.close();
				}
			}
		}
	}

	private static String COMMA_SEPARATOR = ", ";
	private static String PARAM_AUTH = "auth";

	private Error validate(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String authCode = request.getParameter(PARAM_AUTH);
		if(authCode==null||authCode.trim().length()==0) {
			this.appendError(sb, PARAM_AUTH);
		}
		if(sb.length()>0) {
			return new Error("0x001", sb.substring(0, sb.length()-COMMA_SEPARATOR.length()));
		}
		Permission permission = permissionService.selectPermission(authCode);
		if(permission==null)
			return new Error("0x002", authCode+", that's not a match.");
		return null;
	}

	private void appendError(StringBuilder sb, String param) {
		sb.append("Required String parameter '");
		sb.append(param);
		sb.append("' is not present");
		sb.append(COMMA_SEPARATOR);
	}
}