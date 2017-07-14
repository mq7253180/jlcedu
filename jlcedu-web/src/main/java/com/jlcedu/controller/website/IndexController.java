package com.jlcedu.controller.website;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {
	@RequestMapping("/{locale}/index")
	public String localIndex(@PathVariable(value = "locale")String locale) {
		return "redirect:/"+locale+"/website/index";
	}

	@RequestMapping("")
	public String index(HttpServletRequest request) {
		return "redirect:/"+request.getLocale().toLanguageTag().toLowerCase().replace("-", "_")+"/website/index";
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpResponse response) throws IOException {
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			log.error(name+"-------"+request.getHeader(name));
		}
		InputStream in = null;
		try {
			in = request.getInputStream();
			in = new BufferedInputStream(in);
			byte[] buf = new byte[in.available()];
			in.read(buf);
			log.error("Content: "+new String(buf));
		} finally {
			if(in!=null)
				in.close();
		}
		log.error("=============");
		return "ssssssss";
	}
}
