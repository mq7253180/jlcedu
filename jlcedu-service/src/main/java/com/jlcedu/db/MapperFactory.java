package com.jlcedu.db;

import java.util.Date;
import java.util.Map;

import com.jlcedu.db.mapper.WebsiteMapper;

public class MapperFactory {
	private Map<String, WebsiteMapper> userMapperMap;

	public WebsiteMapper getUserMapper(Date createTime) {
		org.apache.commons.dbcp2.BasicDataSource ds;
		return userMapperMap.get("");
	}
}
