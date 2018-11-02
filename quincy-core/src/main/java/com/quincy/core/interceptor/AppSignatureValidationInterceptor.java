package com.quincy.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quincy.core.annotation.AppSignatureRequired;
import com.quincy.core.helper.CommonHelper;
import com.quincy.core.helper.MessageDigestHelper;
import com.quincy.core.helper.RSASecurityHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppSignatureValidationInterceptor extends HandlerInterceptorAdapter {
	@Value("${trustedPublicKey}")
	private String trustedPublicKey;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		HandlerMethod method = (HandlerMethod)handler;
		AppSignatureRequired annotation = method.getMethod().getDeclaredAnnotation(AppSignatureRequired.class);
		if(annotation!=null) {
			Error error = this.validate(request);
			if(error!=null) {
				if(CommonHelper.isApp(request)) {
					new OutputOperation() {
						@Override
						protected void run(PrintWriter out) throws IOException {
							out.println("{\"status\":-2, \"msg\":\""+error.getMsg()+"\"}");
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
	private final static String PARAM_SIGNATURE = "signature";

	private Error validate(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		StringBuilder sb = new StringBuilder();
		StringBuilder data = new StringBuilder();
		Set<Entry<String, String[]>> params = request.getParameterMap().entrySet();
		for(Entry<String, String[]> e:params) {
			String key = e.getKey();
			if(!PARAM_SIGNATURE.equals(key)) {
				data.append("&");
				data.append(key);
				data.append("=");
				data.append(e.getValue()[0]);
			}
		}
		String signature = request.getParameter(PARAM_SIGNATURE);
		if(signature==null||signature.trim().length()==0) {
			this.appendError(sb, PARAM_SIGNATURE);
		}
		if(sb.length()>0) {
			return new Error("0x001", sb.substring(0, sb.length()-COMMA_SEPARATOR.length()));
		}
		boolean signatureValidationSuccess = false;
		String dataStr = data.substring(1, data.length());
		try {
			for(String encoding:ENCODINGS) {
				signatureValidationSuccess = RSASecurityHelper.verify(trustedPublicKey, signature, MessageDigestHelper.getStringMD5(dataStr.getBytes(encoding)), null);
				if(signatureValidationSuccess)
					break;
			}
		} catch(SignatureException e) {
			log.error(MessageFormat.format("Signature validation failed.\r\nsignature:{1};\r\ndata:\r\n{2}", signature, data), e);
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