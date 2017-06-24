package com.quincy.core.service;

import com.quincy.core.entity.WechatParamO;
import com.quincy.thirdparty.wechat.AesException;

public interface WechatService {
	public String encryptMsg(WechatParamO paramO) throws AesException;
	public String decryptMsg(WechatParamO paramO) throws AesException;
	public String verifyURL(WechatParamO paramO) throws AesException;
}
