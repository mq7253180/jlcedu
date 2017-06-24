package com.jlcedu.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.jlcedu.entity.CourseSchedule;

public interface SystemService {
	public List<CourseSchedule> getScheduleAsTeacher(String userId);
	public List<CourseSchedule> getScheduleAsStudent(String userId);
	public void importOrdersByExcel(String userId, InputStream in) throws IOException;
}
