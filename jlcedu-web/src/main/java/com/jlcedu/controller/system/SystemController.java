package com.jlcedu.controller.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jlcedu.service.SystemService;

@Controller
@RequestMapping("/{locale}/system")
public class SystemController {
	@Autowired
	private SystemService systemService;

	@RequestMapping("/teacher")
	public ModelAndView teacher() {
		ModelAndView mv = new ModelAndView("/content/system/teacher");
		mv.addObject("schedules", systemService.getScheduleAsTeacher("1"));
		return mv;
	}

	@RequestMapping("/student")
	public ModelAndView student() {
		ModelAndView mv = new ModelAndView("/content/system/student");
		mv.addObject("schedules", systemService.getScheduleAsStudent("101"));
		return mv;
	}

	@RequestMapping("/upload")
	public String toUpload() {
		return "/content/system/upload_page";
	}

	@RequestMapping("/upload/do")
	public String doUpload(HttpServletRequest request) throws FileUploadException, IOException {
		this.printlnHeaders(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem item:list) {
			InputStream in = null;
			try {
				in = item.getInputStream();
				systemService.importOrdersByExcel(null, item.getInputStream());
			} finally {
				if(in!=null)
					in.close();
			}
		}
		return "/content/system/upload_result";
	}

	private void printlnHeaders(HttpServletRequest request) {
		Enumeration<String> e = request.getHeaderNames();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println(key+"-------"+request.getHeader(key));
		}
	}
}
