<#import "/spring.ftl" as spring>
<#macro layout>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><@i18n key="common.name"/>-<@i18n key="${titlekey}"/></title>
		<link rel="shortcut icon" type="image/x-icon" href="<@property key="prefix.resource"/>/layout/favicon.ico" mce_href="<@property key="prefix.resource"/>/layout/favicon.ico">
		<link rel="icon" type="image/x-icon" href="<@property key="prefix.resource"/>/layout/favicon.ico" mce_href="<@property key="prefix.resource"/>/layout/favicon.ico">
	</head>
	<body>
		<div>
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="" />
		</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">
					<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
		       			<tr>
		            		<td width="610" height="124" background="<@property key="prefix.resource"/>/layout/header.jpg">
		                		
		            		</td>
           					<td valign="top">
           						<div style="font-size:20px;color:#99ffb6">
									<@i18n key="layout.switchLanguage"/>
									<select id="switchLanguageSelect" style="font-size:12px">
										<option value="zh_cn"><@i18n key="layout.locale.zh_cn"/></option>
										<option value="zh_tw"><@i18n key="layout.locale.zh_tw"/></option>
										<option value="en_us"><@i18n key="layout.locale.en_us"/></option>
									</select>
								</div>
				            </td>
				        </tr>
				    </table>
				</td>
			</tr>
		</table>
    	<div>
		    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="<@property key="prefix.resource"/>/layout/old/img/index_46.jpg">
		        <tr>
		            <td align="center">
		                <table width="100" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                        <td height="24">
		                            &nbsp;
		                        </td>
		                    </tr>
		                </table>
						<#nested/>
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
		                        <td height="52" align="center" background="<@property key="prefix.resource"/>/layout/old/img/index_97.jpg">
		                            <table width="960" border="0" cellspacing="0" cellpadding="0">
		                                <tr>
		                                    <td width="10">
		                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_99.jpg" width="10" height="52" />
		                                    </td>
		                                    <td align="center" background="<@property key="prefix.resource"/>/layout/old/img/index_101.jpg">
		                                        <table width="900" border="0" cellspacing="0" cellpadding="0">
		                                            <tr>
		                                                <td class="bottom" align="center">
		                                                   	<@i18n key="jlcedu.no1.bottom"/>
		                                                </td>
		                                            </tr>
		                                        </table>
		                                    </td>
		                                    <td width="9">
		                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_104.jpg" width="9" height="52" />
		                                    </td>
		                                </tr>
		                            </table>
		                        </td>
		                    </tr>
		                </table>
		            </td>
		        </tr>
		    </table>
	    </div>
		<div id="rbbox"></div>
	</body>
	<input type="hidden" id="locale" value="<@attr key="locale" />"/>
	<input type="hidden" id="uri" value="<@attr key="uri_without_first" />"/>
	<input type="hidden" id="resourcePrefix" value="<@property key="prefix.resource"/>"/>
	<script src="<@property key="prefix.resource"/>/jquery-3.2.0.min.js"></script>
	<script src="<@property key="prefix.resource"/>/layout/layout.js"></script>
<#list jslist as js>
	<script src="<@property key="prefix.resource"/>${js}"></script>
</#list>
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>/layout/old/style.css" />
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>/layout/old/slide.css" />
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>/layout/layout_p.css" />
<#list csslist as css>
	<link rel="stylesheet" type="text/css" href="<@property key="prefix.resource"/>${css}" />
</#list>
</html>
</#macro>