package com.jlcedu.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner implements Serializable {
	private static final long serialVersionUID = 7769815192396559001L;
	private Integer id;
	private String imgPre;
	private String imgSuf;
	private String itemId;
}
