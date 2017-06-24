package com.quincy.core.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import com.quincy.core.helper.CommonHelper;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class AttributeTemplateDirectiveModelBean implements TemplateDirectiveModel {
	private static Map<String, Attribute> map = new HashMap<String, Attribute>(5);

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		env.getOut().write(map.get(params.get("key").toString()).getAttribute());
	}

	private static interface Attribute {
		public String getAttribute();
	}

	static {
		map.put("locale", new Attribute() {
			@Override
			public String getAttribute() {
				return WebUtils.getSessionAttribute(CommonHelper.getRequest(), SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME).toString().toLowerCase();
			}
		});
		map.put("uri", new Attribute() {
			@Override
			public String getAttribute() {
				return CommonHelper.getRequest().getRequestURI();
			}
		});
		map.put("uri_without_first", new Attribute() {
			@Override
			public String getAttribute() {
				String uri = CommonHelper.getRequest().getRequestURI();
				String[] ss = uri.split("/");
				return ss.length==0?uri:uri.substring(ss[1].length()+1, uri.length());
			}
		});
		map.put("url", new Attribute() {
			@Override
			public String getAttribute() {
				return CommonHelper.getRequest().getRequestURL().toString();
			}
		});
		map.put("context_path", new Attribute() {
			@Override
			public String getAttribute() {
				return CommonHelper.getRequest().getContextPath();
			}
		});
	}

}
