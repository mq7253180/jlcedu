<#assign titlekey="jlcedu.no1.system.schedule"/>
<#include "/layout_system_p.ftl"/>
<@layout>
						<table style="border:1px solid #0027FF">
							<tr>
								<td style="border:1px solid #0027FF"></td>
							<#list 1..6 as day>
								<td style="border:1px solid #0027FF;font-size:20px;color:#D81CB5" align="center"><b><@i18n key="day.${day}"/></b></td>
							</#list>
								<td rowspan="13" style="border:1px solid #0027FF">
									<img width="580" src="<@property key="prefix.resource"/>/upload/exam/exam-s-zhaorq-mock2-<@attr key="locale" />.jpg"/>
								</td>
                    		</tr>
				<#assign index=0/>
				<#list 0..11 as seq>
                    		<tr>
                    			<td style="border:1px solid #0027FF;font-size:20px;color:#D81CB5" align="center"><b>${seq}</b></td>
                    <#list 1..6 as day>
                    			<td style="font-size:15px;border:1px solid #0027FF">
                    	<#if index lt schedules?size>
                    		<#list schedules[index..(schedules?size-1)] as schedule>
                    			<#if schedule.day==day&&schedule.seq==seq>
                    				<@i18n key="jlcedu.no1.system.course.${schedule.name1}"/>-${schedule.name2}
                    				<#assign index=index+1/>
                    			<#else>
                    				<#break>
                    			</#if>
                    		</#list>
                    	</#if>
								</td>
					</#list>
                    		</tr>
				</#list>
						</table>
</@layout>