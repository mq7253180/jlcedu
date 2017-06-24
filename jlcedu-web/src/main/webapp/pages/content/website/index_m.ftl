<#assign jslist=["/content/index_m.js", "/jquery.nivo.slider.pack.js"]/>
<#assign csslist=["/nivo-slider.css", "/default.css"]/>
<#assign titlekey="index.title"/>
<#include "/layout_m.ftl"/>
<@layout>
			<div id="slider" class="nivoSlider">
			<#list banners as banner>
				<img src="<@property key="prefix.resource"/>${banner.imgPre}_m${banner.imgSuf}" data-thumb="<@property key="prefix.resource"/>${banner.imgPre}_m${banner.imgSuf}" alt="" title="<@i18n key="jlcedu.no1.banner.${banner.id}"/>" />
			</#list>
			</div>
			<table width="100%">
			<#assign c=4/>
			<#assign loops=navigations?size/c/>
			<#if (navigations?size%c==0)>
				<#assign loops=(loops-1)/>
			</#if>
			<#list 0..loops as i>
				<#assign lower=c*i/>
				<#assign upper=c*(i+1)-1/>
				<#if (upper>(navigations?size-1))>
					<#assign upper=(navigations?size-1)/>
				</#if>
				<tr>
				<#list navigations[lower..upper] as navigation>
					<td>
						<div style="text-align:center;width:100%">
							<a><img style="width=150px;height:150px" src="<@property key="prefix.resource"/>${navigation.icon}"/></a>
							<div style="text-align:center;font-size:45px"><@i18n key="jlcedu.no1.navigation.${navigation.name}"/></div>
						</div>
					</td>
				</#list>
				</tr>
			</#list>
			</table>
</@layout>