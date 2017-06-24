package com.quincy.core.service.impl;

import org.springframework.stereotype.Service;

import com.quincy.core.entity.WechatParamO;
import com.quincy.core.service.WechatService;
import com.quincy.thirdparty.wechat.AesException;
import com.quincy.thirdparty.wechat.WXBizMsgCrypt;
@Service
public class WechatServiceImpl implements WechatService {

	public String encryptMsg(WechatParamO paramO) throws AesException {
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(paramO.getToken(), paramO.getEncodingAESKey(), paramO.getCorpID());
		return wxcpt.EncryptMsg(paramO.getEchostr(), paramO.getTimestamp(), paramO.getNonce());
	}

	public String decryptMsg(WechatParamO paramO) throws AesException {
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(paramO.getToken(), paramO.getEncodingAESKey(), paramO.getCorpID());
		return wxcpt.DecryptMsg(paramO.getSignature(), paramO.getTimestamp(), paramO.getNonce(), paramO.getEchostr());
	}
	
	public String verifyURL(WechatParamO paramO) throws AesException {
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(paramO.getToken(), paramO.getEncodingAESKey(), paramO.getCorpID());
		return wxcpt.VerifyURL(paramO.getSignature(), paramO.getTimestamp(), paramO.getNonce(), paramO.getEchostr());
	}
}
