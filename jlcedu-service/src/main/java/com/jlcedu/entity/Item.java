package com.jlcedu.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {
	private static final long serialVersionUID = 67456189461139154L;
	private Integer id;
	private Integer menuId;
	private String title;
	private String content;
	private String origin;
	private Integer clicks;
	private Date createdDate;
	private String publishDate;
}
