<#assign titlekey="jlcedu.no1.navigation.menu.${navigation.item.menuId}"/>
<#include "/layout_website_p.ftl"/>
<@layout>
							<table width="943" border="0" cellspacing="0" cellpadding="0">
                    			<tr>
                        			<td width="210" valign="top">
										<table width="220" border="0" cellspacing="0" cellpadding="0">
										    <tr>
										        <td align="center" style="background-image: url('<@property key="prefix.resource"/>/layout/old/img/index_00.jpg'); background-repeat: no-repeat;color: #999999; font-weight: normal; font-family: 微软雅黑; font-size: 16px;" height="41" width="220"><@i18n key="jlcedu.no1.navigation.${navigation.name}"/></td>
										    </tr>
										    <tr>
										        <td class="jsyd">
										            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="secmenu_bg">
										                <tr>
										                    <td align="center">
										                        <table width="170" border="0" cellspacing="0" cellpadding="0">
										                        <#list navigation.menuIds as menuId>
																	<tr>
																		<td align="left">
																			<img src="<@property key="prefix.resource"/>/layout/old/img/zy_11.jpg" width="4" height="4" />&nbsp;<a href='/<@attr key="locale" />/website/menu/${menuId}' target="_parent" class="secmenu"><@i18n key="jlcedu.no1.navigation.menu.${menuId}"/></a>
																		</td>
																	</tr>
																</#list>
										                        </table>
										                    </td>
										                </tr>
										            </table>
										        </td>
										    </tr>
										</table>
			                            <table width="100" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td height="24">
			                                        &nbsp;
			                                    </td>
			                                </tr>
			                            </table>
			                            <table width="220" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td>
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/zy_14.jpg" width="220" height="42" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td class="jsyd">
			                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                                            <tr>
			                                                <td align="center">
			                                                    <img src='<@property key="prefix.resource"/>/layout/old/img/zy_17.jpg' width="130" height="131" />
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
			                                                    <img src="<@property key="prefix.resource"/>/layout/old/img/zy_20.jpg" width="218" height="52" />
			                                                </td>
			                                            </tr>
			                                        </table>
			                                    </td>
			                                </tr>
			                            </table>
			                            <table width="220" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td>
			                                         <img src='<@property key="prefix.resource"/>/layout/old/img/zy_23.jpg' width="220" height="39" />
			                                    </td>
			                                </tr>
			                            </table>
			                            <table width="100" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td height="24">
			                                        &nbsp;
			                                    </td>
			                                </tr>
			                            </table>
			                            <table width="220" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td>
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/zy_26.jpg" width="221" height="38" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td align="center" class="zy_lxwm">
			                                        <table width="90%" border="0" cellspacing="0" cellpadding="0">
			                                            <tr>
			                                                <td>
																传真：0432-64826007<br />
																邮编：132012
			                                                </td>
			                                            </tr>
			                                        </table>
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td>
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/zy_29.jpg" width="219" height="59" />
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td>
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/zy_32.jpg" width="221" height="107" />
			                                    </td>
			                                </tr>
			                            </table>
									</td>
									<td>
										&nbsp;
									</td>
			                        <td valign="top">
			                            <table width="100" border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td align="center" background="<@property key="prefix.resource"/>/layout/old/img/zy_06.jpg">
			                                        <table width="650" border="0" cellspacing="0" cellpadding="0">
			                                            <tr>
			                                                <td align="center">
			                                                    &nbsp;
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
			                                                    <h2>${navigation.item.title}</h2>
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
			                                                    &nbsp;
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
																发布时间:${navigation.item.publishDate}
			                                                    &nbsp;&nbsp;来源：${navigation.item.origin}
			                                                    &nbsp;&nbsp;点击次数：${navigation.item.clicks}
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
			                                                    &nbsp;
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td align="center">
			                                                    
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td>
			                                                    &nbsp;
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td class="zw" align="left">
			                                                    ${navigation.item.content}
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <td>
			                                                    &nbsp;
			                                                </td>
			                                            </tr>
			                                        </table>
			                                    </td>
			                                </tr>
			                                <tr>
			                                    <td>
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/zy_33.jpg" width="702" height="5" />
			                                    </td>
			                                </tr>
			                            </table>
			                        </td>
			                    </tr>
			                </table>
			                <table width="100" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <td height="24">
			                            &nbsp;
			                        </td>
			                    </tr>
			                </table>
</@layout>
