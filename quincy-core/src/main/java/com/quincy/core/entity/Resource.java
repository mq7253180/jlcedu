package com.quincy.core.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {
	private static final long serialVersionUID = 3383503262570228600L;
	private String code;
	private String name;
	private String uri;
}