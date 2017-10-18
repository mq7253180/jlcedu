package com.quincy.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Permission {
	private Integer id;
	private String code;
	private String publicKey;
	private String privateKey;
	private String name;
	private String mobile;
	private String email;
	private String callbackImplName;
}
