package com.jlcedu.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Navigation implements Serializable {
	private static final long serialVersionUID = 3971608161317570568L;
	private Integer id;
	private String name;
	private String icon;
	private List<Integer> menuIds;
	private List<Item> items;
	private Item item;
}
