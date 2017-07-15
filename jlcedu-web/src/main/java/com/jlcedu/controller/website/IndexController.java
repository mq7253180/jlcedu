package com.jlcedu.controller.website;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

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
	public String test(HttpServletRequest request) throws IOException {
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			log.error(name+"-------"+request.getHeader(name));
		}
		String content = null;

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = null;
		while((line=reader.readLine())!=null) {
			sb.append(line);
			sb.append("\r\n");
		}
		content = sb.toString();
//		InputStream in = null;
//		try {
//			in = request.getInputStream();
//			in = new BufferedInputStream(in);
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			content = new String(buf);
//		} finally {
//			if(in!=null)
//				in.close();
//		}
		log.error("Content: "+content);
		log.error("=============");
		return "ssssssss";
	}
}
