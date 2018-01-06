package com.jlcedu.controller.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quincy.core.entity.WechatParamO;
import com.quincy.core.service.WechatService;
import com.quincy.thirdparty.wechat.AesException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Value("${wechat.token}")
	private String token;
	@Value("${wechat.EncodingAESKey}")
	private String encodingAESKey;
	@Value("${wechat.CorpID}")
	private String corpID;
	@Autowired
	private WechatService wechatService;

	@RequestMapping("assistant")
    @ResponseBody
	public String assistant(@RequestParam("msg_signature")String signature, @RequestParam("timestamp")String timestamp, 
			@RequestParam("nonce")String nonce, @RequestParam(value="echostr", required=false)String echostr) throws Exception {
		if(echostr!=null&&echostr.trim().length()>0) {
			log.warn("======================verifyURL");
			return this.verifyURL(signature, timestamp, nonce, echostr);
		} else {
			String msg = this.encryptMsg(signature, timestamp, nonce, "xxxxx");
			log.warn("======================"+msg);
			return msg;
		}
	}

	private String encryptMsg(String signature, String timestamp, String nonce, String replyMsg) throws AesException {
		return wechatService.encryptMsg(this.getWechatParamO(signature, timestamp, nonce, replyMsg));
	}

	private String decryptMsg(String signature, String timestamp, String nonce, String echostr) throws AesException {
		return wechatService.decryptMsg(this.getWechatParamO(signature, timestamp, nonce, echostr));
	}

	private String verifyURL(String signature, String timestamp, String nonce, String echostr) throws AesException {
		return wechatService.verifyURL(this.getWechatParamO(signature, timestamp, nonce, echostr));
	}

	private WechatParamO getWechatParamO(String signature, String timestamp, String nonce, String echostr) {
		WechatParamO paramO = new WechatParamO();
		paramO.setToken(token);
		paramO.setEncodingAESKey(encodingAESKey);
		paramO.setCorpID(corpID);
		paramO.setSignature(signature);
		paramO.setTimestamp(timestamp);
		paramO.setNonce(nonce);
		paramO.setEchostr(echostr);
		return paramO;
	}

}
