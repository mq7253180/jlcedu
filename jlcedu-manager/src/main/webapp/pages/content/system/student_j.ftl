{"status":1, "msg":"Success", "data": {
	schedules: [<#list schedules as schedule>
		{
			"day": "${schedule.day}", 
			"seq":"${schedule.seq}", 
			"c_name":"<@i18n key="jlcedu.no1.system.course.${schedule.name1}"/>", 
			"t_name":"${schedule.name2}"
		}<#if schedule_index lt schedules?size-1>,</#if>
	</#list>],
	"trand_chart": "<@property key="prefix.resource"/>/upload/exam/exam-s-zhaorq-mock2-<@attr key="locale" />.jpg"
}}