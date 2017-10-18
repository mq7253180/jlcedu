package com.jlcedu;

public class JlceduConstant {
	public final static int MENUS_OR_RESOURCES_HAD = 1;
	public final static int MENUS_OR_RESOURCES_NOT_HAD = 0;
//	public final static String IMPL_AUTHORIZATION = "authorizationSessionServiceImpl";
	public final static String IMPL_AUTHORIZATION = "authorizationCacheServiceImpl";
	public final static String VIEW_NAME_SUCCESS = "/success/success";//成功页的ftl
	public final static String VIEW_NAME_SUCCESS_RESULT = "/success/success_result";//成功页的ftl
	public final static String VIEW_NAME_ERROR = "/error/error";//错误页的ftl
	public final static String VIEW_ATTR_NAME_EXECUTION = "execution";
	public final static String VIEW_ATTR_NAME_EFFECTION = "effection";
	public final static int VCODE_LENGTH = 4;//验证码长度
	public final static int DEFAULT_ITEMS_PER_PAGE = 10;//分页默认每页记录数
	public final static String MV_ATTR_NAME_STATUS = "status";
	public final static String MV_ATTR_NAME_MSG = "msg";
	public final static int MAX_STATUS = 6;
	public final static int EXPIRE_CACHE = 30;//单位: 分钟
}