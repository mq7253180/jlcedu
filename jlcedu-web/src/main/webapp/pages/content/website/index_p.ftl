<#assign jslist=["/content/index_p.js", "/layout/old/jquery.SuperSlide.2.1.1.js", "/layout/old/jquery.SuperSlide.2.1.1.source.js"]/>
<#assign csslist=["/content/index_p.css"]/>
<#assign titlekey="index.title"/>
<#include "/layout_website_p.ftl"/>
<@layout>
							<table border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <td width="600">
										<table width="600" border="0" cellspacing="1" cellpadding="0" bgcolor="#c9c9c9">
										    <tr>
										        <td bgcolor="#FFFFFF">
										            <div id="focus">
										                <div class="hd">
										                    <div class="focus-title" id="focus-title">
										                    <#list banners as banner>
																<a><span class="title"><@i18n key="jlcedu.no1.banner.${banner.id}"/></span></a>
															</#list>
										                    </div>
										                </div>
										                <div class="focus-bar-box" id="focus-bar-box">
										                    <ul class="focus-bar">
										                    <#list banners as banner>
																<li>
																	<a target="_blank" href="/<@attr key="locale" />/website/item/${banner.itemId}">
																		<img id="ctl00_ContentPlaceHolder1_COM_01GDTP1_Repeater1_ctl0${banner_index}_Image1" src="<@property key="prefix.resource"/>${banner.imgPre}_p${banner.imgSuf}" style="border-width:0px;" />
																	</a>
																</li>
															</#list>
										                    </ul>
										                </div>
										                <a class="btn-prev" onclick="return false;" hidefocus="" id="focus-prev"/><a class="btn-next" onclick="return false;" hidefocus="" id="focus-next"/>
										                <div class="ft">
										                    <div class="ftbg"></div>
										                </div>
										            </div>
										        </td>
										    </tr>
										</table>
                        			</td>
			                        <td width="40">
			                            &nbsp;
			                        </td>
                        			<td width="300" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										    <tr>
										        <td background="<@property key="prefix.resource"/>/layout/old/img/index_50.jpg" width="300" height="60" align="right">
										            <a href="NewsList.aspx?ClassID=012001">更多</a> &nbsp; &nbsp; &nbsp; &nbsp;
										        </td>
										    </tr>
										    <tr>
										        <td width="300" height="246" align="center" background="<@property key="prefix.resource"/>/layout/old/img/index_55.jpg">
										            <table width="280" border="0" cellspacing="0" cellpadding="0">
										                <tr>
										                    <td height="226" align="left" valign="top">
																<a title='慎终追远祭英魂——吉林一中开展烈士纪念日祭扫活动' href='News.aspx?NewsID=201704050935541655&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	慎终追远祭英魂——吉林一中开展烈士纪念日...
																</a>
																<br />
																<a title='党员教师利用中午时间义务辅导学生' href='News.aspx?NewsID=201704010907252123&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	党员教师利用中午时间义务辅导学生
																</a>
																<br />
																<a title='录取喜讯！我校2014级刘小瑜同学收到UBC录取通知书！' href='News.aspx?NewsID=201703220831117640&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	录取喜讯！我校2014级刘小瑜同学收到UBC录...
																</a>
																<br />
																<a title='吉林一中获得两奖“三八”红旗奖励' href='News.aspx?NewsID=201703201018560765&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	吉林一中获得两奖“三八”红旗奖励
																</a>
																<br />
																<a title='心理组教师韩鑫桐被评为“江城好人”' href='News.aspx?NewsID=201703201015224202&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	心理组教师韩鑫桐被评为“江城好人”
																</a>
																<br />
																<a title='吉林一中召开“两学一做”学习教育组织生活会和民主评议党员活动' href='News.aspx?NewsID=201703131644037171&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	吉林一中召开“两学一做”学习教育组织生活...
																</a>
																<br />
																<a title='加拿大高中班教学开放日及新生面试活动' href='News.aspx?NewsID=201703091038204358&ClassID=012001' target="_parent" class="news_bt" style="color: #000000">
																	加拿大高中班教学开放日及新生面试活动
																</a>
																<br />
										                    </td>
										                </tr>
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
			                <table width="943" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <td align="right">
			                            <table border="0" cellspacing="0" cellpadding="0">
			                                <tr>
			                                    <td colspan="3">
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_63.jpg" width="609" height="2" />
			                                    </td>
			                                    <td colspan="2"></td>
			                                </tr>
			                                <tr>
			                                    <td width="4">
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_64.jpg" width="4" height="220" />
			                                    </td>
			                                    <td width="599" height="220" align="center" bgcolor="#FFFFFF">
													<table width="578" border="0" cellspacing="0" cellpadding="0">
													    <tr>
													        <td width="220">
													            <table width="200" border="0" cellspacing="0" cellpadding="5" bgcolor="#dfdfdf">
													                <tr>
													                    <td height="200">
													                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_65.jpg" width="190" height="190" />
													                    </td>
													                </tr>
													            </table>
													        </td>
													        <td valign="top">
													            <table width="100%" border="0" cellspacing="0" cellpadding="0">
													                <tr>
													                    <td height="30" colspan="2">
													                        &nbsp;&nbsp;<img src="<@property key="prefix.resource"/>/layout/old/img/index_67.jpg" width="90" height="23" />
													                    </td>
													                </tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='2017年3月好学生' href='News.aspx?NewsID=201704010813504936&ClassID=009005' target="_parent" class="jsyd_bt">
																				2017年3月好学生
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																			2017-04-01<br />
																		</td>
																	</tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='2016年11月份好学生' href='News.aspx?NewsID=201612051512088958&ClassID=009005' target="_parent" class="jsyd_bt">
																				2016年11月份好学生
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																			2016-12-05<br />
																		</td>
																	</tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='2016年10月好学生' href='News.aspx?NewsID=201611021641300657&ClassID=009005' target="_parent" class="jsyd_bt">
																				2016年10月好学生
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																			2016-11-02<br />
																		</td>
																	</tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='2016年8-9月份好学生' href='News.aspx?NewsID=201609301601363283&ClassID=009005' target="_parent" class="jsyd_bt">
																				2016年8-9月份好学生
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																			2016-09-30<br />
																		</td>
																	</tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='吉林一中在吉林市中小学我身边的故事主题教育中获得佳绩' href='News.aspx?NewsID=201609081104116875&ClassID=009004' target="_parent" class="jsyd_bt">
																				吉林一中在吉林市中小学我身边的故事主题教育中...
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																			2016-09-08<br />
																		</td>
																	</tr>
																	<tr>
																		<td width="77%" align="left" valign="top">
																			<a title='2016年6-7月份好学生' href='News.aspx?NewsID=201607121419117758&ClassID=009005' target="_parent" class="jsyd_bt">
																				2016年6-7月份好学生
																			</a>
																			<br />
																		</td>
																		<td width="23%" class="textdate">
																				2016-07-12<br />
																		</td>
																	</tr>
													            </table>
													        </td>
													    </tr>
													</table>
	                                    		</td>
			                                    <td width="5">
			                                        <img src="<@property key="prefix.resource"/>/layout/old/img/index_66.jpg" width="5" height="220" />
			                                    </td>
			                                    <td width="35">
			                                        &nbsp;
			                                    </td>
			                                    <td width="300" valign="top">
													<table width="300" border="0" cellspacing="0" cellpadding="0">
													    <tr>
													        <td>
													            <img src="<@property key="prefix.resource"/>/layout/old/img/index_68.jpg" width="246" height="41" />
													        </td>
													        <td>
													           <a href="NewsList.aspx?ClassID=006000">
													                <img src="<@property key="prefix.resource"/>/layout/old/img/index_69.jpg" width="54" height="41" />
																</a>
													        </td>
													    </tr>
													    <tr>
													        <td colspan="2" align="center" class="jsyd">
													            <table width="280" border="0" cellspacing="0" cellpadding="0">
													                <tr>
													                    <td align="left" valign="top">
																			<a title='2017年3月好教师' href='/<@attr key="locale" />/website/item/xxxx1111' target="_parent" class="jsyd_bt">
																				2017年3月好教师
																			</a>
																			<br />
																			<a title='走访老教师有感' href='News.aspx?NewsID=201701171537021520&ClassID=006006' target="_parent" class="jsyd_bt">
																				走访老教师有感
																			</a>
																			<br />
																			<a title='2016年11月份好教师' href='News.aspx?NewsID=201612060852153529&ClassID=006007' target="_parent" class="jsyd_bt">
																				2016年11月份好教师
																			</a>
																			<br />
																			<a title='飘扬的旗帜' href='News.aspx?NewsID=201611181302162657&ClassID=006006' target="_parent" class="jsyd_bt">
																				飘扬的旗帜
																			</a>
																			<br />
																			<a title='市优秀共产党员标兵杨建华老师先进事迹' href='News.aspx?NewsID=201611140956511361&ClassID=006004' target="_parent" class="jsyd_bt">
																				市优秀共产党员标兵杨建华老师先进事迹
																			</a>
																			<br />
																			<a title='我的“大”师傅' href='News.aspx?NewsID=201611071044371595&ClassID=006006' target="_parent" class="jsyd_bt">
																				我的“大”师傅
																			</a>
																			<br />
																			<a title='吉林市第一中学优秀党务工作者' href='News.aspx?NewsID=201611031034023782&ClassID=006004' target="_parent" class="jsyd_bt">
																				吉林市第一中学优秀党务工作者
																			</a>
																			<br />
													                    </td>
													                </tr>
													            </table>
													        </td>
													    </tr>
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
		                	<table border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <td width="609" align="left">
			                            <img src="<@property key="prefix.resource"/>/layout/old/img/index_76.jpg" width="609" height="45" />
			                        </td>
			                        <td width="33">
			                            &nbsp;
			                        </td>
			                        <td width="305" align="right">
			                            <a href="http://www.jlyzzjb.com/sy.asp" target="_blank">
			                                <img src="<@property key="prefix.resource"/>/layout/old/img/index_74.jpg" width="305" height="47" />
										</a>
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
			                <table width="939" border="0" cellspacing="0" cellpadding="0">
			                    <tr>
			                        <td width="600">
										<table width="600" border="0" cellspacing="0" cellpadding="0">
										    <tr>
										        <td height="41" colspan="2" width="600" style="background-image: url('<@property key="prefix.resource"/>/layout/old/img/index_81.jpg');background-repeat: repeat-x; font-family: 微软雅黑; font-size: 21px;">
													心&nbsp;育&nbsp;美&nbsp;育
										        </td>
										    </tr>
										    <tr>
										        <td width="262">
										            <table width="262" border="0" cellspacing="0" cellpadding="5" bgcolor="#dfdfdf">
										                <tr>
										                    <td height="262">
										                        <div id="demoContent">
										                            <div class="effect">
										                                <div id="slideBox" class="slideBox">
										                                    <div class="hd">
										                                        <ul>
										                                            <li>1</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li>
										                                        </ul>
										                                    </div>
										                                    <div class="bd" id="bd">
										                                        <ul>
										                                        <#list superSlideImg as img>
										                                            <li>
										                                            	<a target="_parent" href='/<@attr key="locale" />/website/menu/m31'>
																							<img id="ctl00_ContentPlaceHolder1_COM_05XYSS1_Repeater2_ctl0${img_index}_Image_News" src="<@property key="prefix.resource"/>${img}" style="height:262px;width:262px;border-width:0px;" />
										                                                </a>
																					</li>
																				</#list>
										                                        </ul>
										                                    </div>
										                                </div>
										                            </div>
										                        </div>
										                    </td>
										                </tr>
										            </table>
										        </td>
										        <td valign="top">
										            <table width="334" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='在基础教育“美育教学”探索的道路上' href='News.aspx?NewsID=201506091108227848&ClassID=007006' target="_parent" class="jsyd_bt">
																	在基础教育“美育教学”探索的道路上
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2015-06-09<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心闻资讯】国家级沙盘游戏治疗中级培训总结' href='News.aspx?NewsID=201505261525283510&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心闻资讯】国家级沙盘游戏治疗中级培训总...
																</a>
																<br />
															</td>
															<td width="23%" class="textdate">
																2015-05-26<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心灵成长】决胜高考要诀' href='News.aspx?NewsID=201505210919354362&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心灵成长】决胜高考要诀</a>
																<br />
															</td>
															<td width="23%" class="textdate">
																2015-05-21<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心闻资讯】参评中小学心育特色校  迎检获省领导高度认可' href='News.aspx?NewsID=201505210914451862&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心闻资讯】参评中小学心育特色校  迎检获...
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2015-05-21<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心育概况】构筑幸福的心灵花园' href='News.aspx?NewsID=201505210910276081&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心育概况】构筑幸福的心灵花园
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2015-05-21<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心育概况】心理健康教育中心照片集锦' href='News.aspx?NewsID=201411191120571853&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心育概况】心理健康教育中心照片集锦
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2014-11-19<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心育概况】心理健康教育中心规章制度' href='News.aspx?NewsID=201411191117472478&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心育概况】心理健康教育中心规章制度
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2014-11-19<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心育概况】峰在前方，路在脚下' href='News.aspx?NewsID=201411191116129041&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心育概况】峰在前方，路在脚下
																</a>
																<br/>
															</td>
															<td width="23%" class="textdate">
																2014-11-19<br/>
															</td>
														</tr>
														<tr>
															<td width="77%" align="left" valign="top">
																<a title='【心灵成长】中学生自己如何维护自身的心理健康' href='News.aspx?NewsID=201411191114457166&ClassID=007006' target="_parent" class="jsyd_bt">
																	【心灵成长】中学生自己如何维护自身的心理...
																</a>
																<br/>
										                    </td>
															<td width="23%" class="textdate">
																2014-11-19<br/>
															</td>
														</tr>
										            </table>
										        </td>
										    </tr>
										</table>
                        			</td>
			                        <td width="33">
			                            &nbsp;
			                        </td>
			                        <td width="305" align="right" valign="top">
										<table width="100" border="0" cellspacing="0" cellpadding="0">
										    <tr>
										        <td>
										            <img src="<@property key="prefix.resource"/>/layout/old/img/index_83.jpg" width="300" height="42" />
										        </td>
										    </tr>
										    <tr>
										        <td>
										            <img src="<@property key="prefix.resource"/>/layout/old/img/index_1401.jpg" width="301" height="156" />
										        </td>
										    </tr>
										    <tr>
										        <td>
										            <img src="<@property key="prefix.resource"/>/layout/old/img/index_1402.jpg" width="301" height="156" />
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