package com.jlcedu.entity;

import com.quincy.core.entity.User;

public class Student extends User {
	private Klass klass;//班级
	private School graduationSchool;//毕业学校
	private School matriculationSchool;//录取中学
	private University university;//录取大学
	private Major major;//专业
	private UniversityDuration universityDuration;//学制
}
