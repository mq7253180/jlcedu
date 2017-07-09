package com.quincy.core.service.impl;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quincy.core.Constant;
import com.quincy.core.entity.User;
import com.quincy.core.helper.CommonHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("authorizationRedisServiceImpl")
public class AuthorizationRedisServiceImpl extends AuthorizationAbstract {
	@Autowired
	private JedisPool jedisPool;
	@Value("${system}")
	private String system;
	@Value("${expire.session}")
	private Integer expire;

	@Override
	protected Object getUserObject(HttpServletRequest request) throws ClassNotFoundException, IOException {
		String token = CommonHelper.getValue(request, Constant.CLIENT_TOKEN);
		if(token!=null) {
			token = token.trim();
			if(token.length()>0) {
				Jedis jedis = null;
				try {
					jedis = jedisPool.getResource();
					byte[] b = jedis.get(token.getBytes());
					if(b!=null&&b.length>0)
						return CommonHelper.unSerialize(b);
				} finally {
					if(jedis!=null)
						jedis.close();
				}
			}
		}
		return null;
	}

	@Override
	public String setUser(HttpServletRequest request, User user) throws IOException {
		String uuid = UUID.randomUUID().toString();
		if(!Constant.CLIENT_TYPE_J.equals(CommonHelper.clientType(request))) {
			Cookie cookie = new Cookie(Constant.CLIENT_TOKEN, uuid);
			HttpServletResponse response = CommonHelper.getResponse();
			response.addCookie(cookie);
		}
		byte[] key = (Constant.CLIENT_TOKEN+"."+system+"USER."+uuid).getBytes();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, CommonHelper.serialize(user));
			jedis.expire(key, expire);
			return uuid;
		} finally {
			if(jedis!=null)
				jedis.close();
		}
	}

	@Override
	public int isLogedIn(HttpServletRequest request) {
		return 0;
	}

	@Override
	public void logout(HttpServletRequest request) {
		
	}

	@Override
	protected void saveVcode(HttpServletRequest request, String vcode) {
		
	}

	@Override
	public String getCachedVcode(HttpServletRequest request) {
		return null;
	}
}
