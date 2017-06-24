package com.jlcedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSchedule {
	private Integer day;
	private Integer seq;
	private String name1;//学生查课表: 课程名称; 老师查课表: 班级名称
	private String name2;//学生查课表: 任课老师姓名; 老师查课表: 课程名称
}
