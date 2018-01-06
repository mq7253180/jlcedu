{"status":1, "data": {
	"banners":[
		<#list banners as banner>
			{
				"img":"<@property key="prefix.resource"/>${banner.imgPre}_m${banner.imgSuf}", 
				"desc":"<@i18n key="jlcedu.no1.banner.${banner.id}"/>"
			}<#if banner_index lt banners?size-1>,</#if>
		</#list>
	], 
	"navigations": [
		<#list navigations as navigation>
			{
				"linke": "/<@attr key="locale" />/website/navigation/${navigation.id}", 
				"name":"<@i18n key="jlcedu.no1.navigation.${navigation.name}"/>"
			}<#if navigation_index lt navigations?size-1>,</#if>
		</#list>
	]
}}