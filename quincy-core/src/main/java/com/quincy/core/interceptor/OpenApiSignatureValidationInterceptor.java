package com.quincy.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quincy.core.Constant;
import com.quincy.core.annotation.OpenApiSignatureRequired;
import com.quincy.core.entity.Permission;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.helper.MessageDigestHelper;
import com.quincy.core.helper.RSASecurityHelper;
import com.quincy.core.service.PermissionService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenApiSignatureValidationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		HandlerMethod method = (HandlerMethod)handler;
		OpenApiSignatureRequired annotation = method.getMethod().getDeclaredAnnotation(OpenApiSignatureRequired.class);
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

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private class Error {
		private String code;
		private String msg;
	}

	private final static String[] ENCODINGS = {"GBK", "UTF-8", "ISO8859-1"};
	private final static String COMMA_SEPARATOR = ", ";
	private final static String PARAM_AUTH = "auth";
	private final static String PARAM_SIGNATURE = "signature";
	private final static String PARAM_DATA = "data";

	private Error validate(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		StringBuilder sb = new StringBuilder();
		String authCode = request.getParameter(PARAM_AUTH);
		if(authCode==null||authCode.trim().length()==0) {
			this.appendError(sb, PARAM_AUTH);
		}
		String data = request.getParameter(PARAM_DATA);
		if(data==null||data.trim().length()==0) {
			this.appendError(sb, PARAM_DATA);
		}
		String signature = request.getParameter(PARAM_SIGNATURE);
		if(signature==null||signature.trim().length()==0) {
			this.appendError(sb, PARAM_SIGNATURE);
		}
		if(sb.length()>0) {
			return new Error("0x001", sb.substring(0, sb.length()-COMMA_SEPARATOR.length()));
		}
		Permission permission = permissionService.selectPermission(authCode);
		if(permission==null)
			return new Error("0x002", authCode+", that's not a match.");
		String publicKey = permission.getPublicKey();
		if(publicKey==null)
			return new Error("0x003", "Secret key pair has not been created.");
		publicKey = publicKey.trim();
		if(publicKey.length()==0)
			return new Error("0x003", "Secret key pair has not been created.");
		boolean signatureValidationSuccess = false;
		try {
			for(String encoding:ENCODINGS) {
				signatureValidationSuccess = RSASecurityHelper.verify(publicKey, signature, MessageDigestHelper.getStringMD5(data.getBytes(encoding)), null);
				if(signatureValidationSuccess)
					break;
			}
		} catch(SignatureException e) {
			log.error(MessageFormat.format("Signature validation failed. \r\nauth: {0};\r\nsignature:{1};\r\ndata:\r\n{2}", authCode, signature, data), e);
			return new Error("0x004", "Signature validation failed.");
		}
		if(!signatureValidationSuccess)
			return new Error("0x004", "Signature validation failed.");
		return null;
	}

	private void appendError(StringBuilder sb, String param) {
		sb.append("Required String parameter '");
		sb.append(param);
		sb.append("' is not present");
		sb.append(COMMA_SEPARATOR);
	}
}