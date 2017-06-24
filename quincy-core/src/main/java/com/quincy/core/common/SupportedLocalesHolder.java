package com.quincy.core.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("supportedLocalesHolder")
public class SupportedLocalesHolder {
	private static String[] SUPPORTED_LOCALES;
	private static String defaultLocale;
	private static Object lock = new Object();
	@Value("${supportedLocales}")
	private String supportedLocales;

	public boolean isValidLocale(String locale) {
		this.init();
		for(String ls:SUPPORTED_LOCALES) {
			if(ls.equalsIgnoreCase(locale)) {
				return true;
			}
		}
		return false;
	}

	private void init() {
		if(SUPPORTED_LOCALES==null) {
			synchronized(lock) {
				if(SUPPORTED_LOCALES==null) {
					SUPPORTED_LOCALES = supportedLocales.split(",");
					defaultLocale = SUPPORTED_LOCALES[0];
				}
			}
		}
	}

	public String getDefaultLocale() {
		this.init();
		return defaultLocale;
	}

	public String getSupportedLocales() {
		return supportedLocales;
	}
	public void setSupportedLocales(String supportedLocales) {
		this.supportedLocales = supportedLocales;
	}

}
