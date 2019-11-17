package com.jlcedu.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jlcedu.db.mapper.SystemMapper;
import com.jlcedu.entity.CourseSchedule;
import com.jlcedu.service.SystemService;
import com.quincy.core.annotation.ReadOnly;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SystemMapper systemMapper;

	@ReadOnly
	public List<CourseSchedule> getScheduleAsTeacher(String userId) {
		return systemMapper.selectScheduleAsTeacher(userId);
	}

	@ReadOnly
	public List<CourseSchedule> getScheduleAsStudent(String userId) {
		return systemMapper.selectScheduleAsStudent(userId);
	}

	public void importOrdersByExcel(String userId, InputStream in) throws IOException {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(in);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			XSSFCell cell = row.getCell(0);
			System.out.println(cell.getStringCellValue());
		} finally {
			if(wb!=null)
				wb.close();
		}
	}

	@Value("#{'${locales}'.split(',')}")
	private String[] supportedLocales;

	@PostConstruct
	public void init() {
		for(String l:supportedLocales) {
			log.warn("SUPPORTED_LOCALE_SERVICE--------------{}", l);
		}
	}
}
