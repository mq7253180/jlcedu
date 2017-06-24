package com.jlcedu.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jlcedu.entity.CourseSchedule;

@Repository
public interface SystemMapper {
	public List<CourseSchedule> selectScheduleAsTeacher(@Param("user_id")String userId);
	public List<CourseSchedule> selectScheduleAsStudent(@Param("user_id")String userId);
}
