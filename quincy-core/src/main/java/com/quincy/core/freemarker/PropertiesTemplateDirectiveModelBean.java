package com.quincy.core.freemarker;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PropertiesTemplateDirectiveModelBean implements TemplateDirectiveModel {
	private Properties properties;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		env.getOut().write(properties.getProperty(params.get("key").toString()));
	}

	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
