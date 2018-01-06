{"status":1, "msg":"Success", "data": {
	"schedules": [<#list schedules as schedule>
		{
			"day": "${schedule.day}", 
			"seq":"${schedule.seq}", 
			"class_name":"${schedule.name1}", 
			"course_name":"<@i18n key="jlcedu.no1.system.course.${schedule.name2}"/>"
		}<#if schedule_index lt schedules?size-1>,</#if>
	</#list>],
	"trand_chart": "<@property key="prefix.resource"/>/upload/exam/exam-t-wangym-mock2-<@attr key="locale" />.jpg"
}}