package com.quincy.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReturnedO {
	private int status;
	private String msg;
	private Object data;
}
