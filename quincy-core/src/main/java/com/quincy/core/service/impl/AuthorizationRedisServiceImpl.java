package com.quincy.core.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.quincy.core.Constant;
import com.quincy.core.helper.CommonHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AuthorizationRedisServiceImpl extends AuthorizationAbstract {
	@Autowired
	private JedisPool jedisPool;

	@Override
	protected Object getUserObject(HttpServletRequest request) {
		String token = CommonHelper.getValue(request, Constant.CLIENT_TOKEN);
		if(token!=null) {
			token = token.trim();
			if(token.length()>0) {
				Jedis jedis = null;
				Object obj = null;
				try {
					jedis = jedisPool.getResource();
					obj = jedis.get(token);
				} finally {
					if(jedis!=null)
						jedis.close();
				}
				return obj;
			}
		}
		return null;
	}
}
