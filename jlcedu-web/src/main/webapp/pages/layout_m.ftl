<#import "/spring.ftl" as spring>
<#macro layout>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><@i18n key="common.name"/>-<@i18n key="${titlekey}"/></title>
	</head>
	<body>
		<div style="">
			<div class="header-wrapper">
				<div style="float:right;font-size:35px">
					<@i18n key="layout.switchLanguage"/>: 
					<select id="switchLanguageSelect" style="font-size:30px;width:250px">
						<option value="zh_cn"><@i18n key="layout.locale.zh_cn"/></option>
						<option value="zh_tw"><@i18n key="layout.locale.zh_tw"/></option>
						<option value="en_us"><@i18n key="layout.locale.en_us"/></option>
					</select>
					<!--input type="button" id="testIndexBtn" value="Test"/-->
				</div>
			</div>
			<#nested/>
			<div class="footer">
				<@i18n key="layout.icp">property:index.icp.no</@i18n>
			</div>
		</div>
	</body>
	<input type="hidden" id="locale" value="<@attr key="locale" />"/>
	<input type="hidden" id="uri" value="<@attr key="uri_without_first" />"/>
	<input type="hidden" id="resourcePrefix" value="<@property key="prefix.resource"/>"/>
	<script src="<@property key="prefix.resource"/>/jquery-3.2.0.min.js"></script>
	<script src="<@property key="prefix.resource"/>/quincy.js"></script>
	<script src="<@property key="prefix.resource"/>/layout/layout.js"></script>
<#list jslist as js>
	<script src="<@property key="prefix.resource"/>${js}"></script>
</#list>
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>/layout/layout_m.css" />
<#list csslist as css>
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>${css}" />
</#list>
</html>
</#macro>