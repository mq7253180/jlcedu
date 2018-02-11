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
    	<form name="aspnetForm" method="post" action="index.aspx" id="aspnetForm">
			<div>
				<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="" />
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
		        <tr>
		            <td align="center">
						<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
		        			<tr>
			            		<td width="610">
			                		<img src="<@property key="prefix.resource"/>/layout/header.jpg" width="610" height="124" />
			            		</td>
            					<td width="350" valign="bottom">
                					<table width="350" border="0" cellspacing="0" cellpadding="0">
					                    <tr>
					                        <td height="51" colspan="4" align="center" valign="top" background="<@property key="prefix.resource"/>/layout/old/img/index_03.jpg">
					                            <table width="<@i18n key="layout.p.top.opt.width"/>" border="0" cellspacing="0" cellpadding="0">
					                                <tr>
					                                    <td align="center">
					                                         <a href="xiaoyoulu/Login.aspx"   target="_blank" class="alumni" ><@i18n key="jlcedu.no1.top.alumni"/></a>
					                                    </td>
					                                    <td width="1" align="center">
					                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_04.jpg" width="1" height="6" />
					                                    </td>
					                                    <td align="center">
					                                        <a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.maqiangcgq.com/<@attr key="locale" />/website/index');" href="javascript:;" target="_parent" class="topmenu"><@i18n key="jlcedu.no1.top.index"/></a>
					                                    </td>
					                                    <td align="center">
					                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_04.jpg" width="1" height="6" />
					                                    </td>
					                                    <td align="center">
					                                        <a href="javascript:window.external.AddFavorite('http://www.maqiangcgq.com/<@attr key="locale" />/website/index', '<@i18n key="common.name"/>')" target="_parent" class="topmenu"><@i18n key="jlcedu.no1.top.favorite"/></a>
					                                    </td>
					                                    <td align="center">
					                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_04.jpg" width="1" height="6" />
					                                    </td>
					                                    <td align="center">
					                                    	<div style="font-size:12px;color:#99ffb6">
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
                    					<tr>
					                        <td width="5">
					                            <img src="<@property key="prefix.resource"/>/layout/old/img/index_05.jpg" width="5" height="73" />
					                        </td>
					                        <td width="269" background="<@property key="prefix.resource"/>/layout/old/img/index_07.jpg">
					                            <table width="269" border="0" cellspacing="0" cellpadding="0">
					                                <tr>
					                                    <td height="36" align="center" class="h12a" colspan="2">
					                                        <h1><@i18n key="jlcedu.no1.top.alumni"/></h1>
					                                    </td>
					                                </tr>
					                                <tr>
					                                    <td align="center">
															<a href="xiaoyoulu/Login.aspx" target="_blank">
					                                            <img alt="" src="<@property key="prefix.resource"/>/layout/old/img/dl.png" />
															</a>
					                                    </td>
					                                    <td align="center">
					                                        <a href="xiaoyoulu/Register.aspx" target="_blank">
					                                            <img alt="" src="<@property key="prefix.resource"/>/layout/old/img/zc.png" />
															</a>
					                                    </td>
					                                </tr>
					                            </table>
					                        </td>
					                        <td width="5">
					                            <img src="<@property key="prefix.resource"/>/layout/old/img/index_11.jpg" width="5" height="73" />
					                        </td>
					                        <td width="72" align="right">
					                            <img src="<@property key="prefix.resource"/>/layout/old/img/index_12.jpg" width="70" height="73" />
					                        </td>
					                    </tr>
					                </table>
					            </td>
					        </tr>
					    </table>
					    <table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
					        <tr>
					            <td width="14">
					                <img src="<@property key="prefix.resource"/>/layout/old/img/index_23.jpg" width="14" height="42" />
					            </td>
					            <td width="931" align="center" background="<@property key="prefix.resource"/>/layout/old/img/index_25.jpg">
					                <table width="921" border="0" cellspacing="0" cellpadding="0">
					                    <tr>
					                    	<td align="center">
					                            <a href="/<@attr key="locale" />/website/index" onmouseout="topDivOUT()" onmouseover="JavaScript:topDiv('0', 'topDiv_', 11)" class="menu_f"><@i18n key="jlcedu.no1.navigation.index"/></a>
					                        </td>
					                    <#list navigations as navigation>
					                        <td align="center">
					                            <a href="/<@attr key="locale" />/website/navigation/${navigation.id}" onmouseout="topDivOUT()" onmouseover="JavaScript:topDiv('${navigation_index+1}', 'topDiv_', 11)" class="menu_f"><@i18n key="jlcedu.no1.navigation.${navigation.name}"/></a>
					                        </td>
										</#list>
					                    </tr>
					                </table>
					            </td>
					            <td width="15">
					                <img src="<@property key="prefix.resource"/>/layout/old/img/index_35.jpg" width="15" height="42" />
					            </td>
					        </tr>
					    </table>
					    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					        <tr>
					            <td height="46" align="center" background="<@property key="prefix.resource"/>/layout/old/img/index_38.jpg">
					            	<div style="display:none" id="topDiv_0">
										<table width="940" height="30" border="0" cellpadding="0" cellspacing="0" class="white16">
											<tr>
												<td style="text-align: center">
													<a href="/<@attr key="locale" />/website/index" target="_parent" class="menu_s"><@i18n key="jlcedu.no1.navigation.menu.0"/></a>
												</td>
											</tr>
										</table>
									</div>
								<#list navigations as navigation>
					                <div style="display:none" id="topDiv_${navigation_index+1}">
										<table width="940" height="30" border="0" cellpadding="0" cellspacing="0" class="white16">
											<tr>
												<td style="text-align: center">
												<#list navigation.menuIds as menuId>
													<a href="/<@attr key="locale" />/website/menu/${menuId}" target="_parent" class="menu_s"><@i18n key="jlcedu.no1.navigation.menu.${menuId}"/></a>
												</#list>
												</td>
											</tr>
										</table>
									</div>
								</#list>
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
	    </form>
	</body>
	<input type="hidden" id="locale" value="<@attr key="locale" />"/>
	<input type="hidden" id="uri" value="<@attr key="uri_without_first" />"/>
	<input type="hidden" id="resourcePrefix" value="<@property key="prefix.resource"/>"/>
	<script src="<@property key="prefix.resource"/>/layout/old/jquery-1.9.1.min.js"></script>
	<script src="<@property key="prefix.resource"/>/jquery.i18n.properties.min.js"></script>
	<script src="<@property key="prefix.resource"/>/quincy.js"></script>
	<script src="<@property key="prefix.resource"/>/layout/layout.js"></script>
	<script src="<@property key="prefix.resource"/>/layout/layout_p.js"></script>
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