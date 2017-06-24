package com.quincy.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WechatParamO {
	private String token;
	private String encodingAESKey;
	private String corpID;
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
}
