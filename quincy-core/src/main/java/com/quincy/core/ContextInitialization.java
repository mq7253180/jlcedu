package com.quincy.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@PropertySource("classpath:core.properties")
//@Configuration
@Component
@Slf4j
public class ContextInitialization {
	@Value("#{'${locales}'.split(',')}")
	private String[] supportedLocales;

	@PostConstruct
	public void init() {
		for(String l:supportedLocales) {
			log.warn("SUPPORTED_LOCALE--------------{}", l);
		}
	}
}
