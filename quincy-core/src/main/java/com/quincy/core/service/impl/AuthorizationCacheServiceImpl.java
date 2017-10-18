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

@Service("authorizationCacheServiceImpl")
public class AuthorizationCacheServiceImpl extends AuthorizationAbstract {
	@Autowired
	private JedisPool jedisPool;
	@Value("${expire.session}")
	private int expire;
	@Value("${domain}")
	private String domain;

	@Override
	protected Object getUserObject(HttpServletRequest request) throws Exception {
		return new Decoration() {
			@Override
			protected Object run(Jedis jedis, String token) throws Exception {
				byte[] key = (Constant.CLIENT_TOKEN+".USER."+token).getBytes();
				byte[] b = jedis.get(key);
				if(b!=null&&b.length>0) {
					int seconds = expire*60;
					jedis.expire(key, seconds);
					return CommonHelper.unSerialize(b);
				} else 
					return null;
			}
		}.start(request);
	}

	@Override
	public String setUser(HttpServletRequest request, User user) throws IOException {
		String token = this.createOrGetToken(request);
		byte[] key = (Constant.CLIENT_TOKEN+".USER."+token).getBytes();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, CommonHelper.serialize(user));
			int seconds = expire*60;
			jedis.expire(key, seconds);
			return token;
		} finally {
			if(jedis!=null)
				jedis.close();
		}
	}

	public int isLogedIn(HttpServletRequest request) throws Exception {
		return this.getUserObject(request)==null?0:1;
	}

	public void logout(HttpServletRequest request) throws Exception {
		new Decoration() {
			@Override
			protected Object run(Jedis jedis, String token) throws Exception {
				jedis.del((Constant.CLIENT_TOKEN+".USER."+token).getBytes());
				return null;
			}
		}.start(request);
	}

	protected void saveVcode(HttpServletRequest request, String vcode) throws Exception {
		this.setCachedStr(request, "VCODE", vcode);
	}

	public String getCachedVcode(HttpServletRequest request) throws Exception {
		return this.getCachedStr(request, "VCODE");
	}

	private abstract class Decoration {
		protected abstract Object run(Jedis jedis, String token) throws Exception;

		public Object start(HttpServletRequest request) throws Exception {
			String token = CommonHelper.getValue(request, Constant.CLIENT_TOKEN);
			if(token!=null) {
				token = token.trim();
				if(token.length()>0) {
					Jedis jedis = null;
					try {
						jedis = jedisPool.getResource();
						return this.run(jedis, token);
					} finally {
						if(jedis!=null)
							jedis.close();
					}
				}
			}
			return null;
		}
	}

	private String createOrGetToken(HttpServletRequest request) {
		String token = CommonHelper.getValue(request, Constant.CLIENT_TOKEN);
		if(token!=null) {
			token = token.trim();
		} else {
			token = UUID.randomUUID().toString().replaceAll("-", "");
			if(!Constant.CLIENT_TYPE_J.equals(CommonHelper.clientType(request))) {//如果是浏览器写cookie
				Cookie cookie = new Cookie(Constant.CLIENT_TOKEN, token);
				cookie.setDomain(domain);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				HttpServletResponse response = CommonHelper.getResponse();
				response.addCookie(cookie);
			}
		}
		return token;
	}

	@Override
	public void setDeniedPrivilegeName(HttpServletRequest request, String name) {
		this.setCachedStr(request, "DENIED_PRIVILEGE", name);
	}

	@Override
	public String getDeniedPrivilegeName(HttpServletRequest request) throws Exception {
		return this.getCachedStr(request, "DENIED_PRIVILEGE");
	}

	private void setCachedStr(HttpServletRequest request, String flag, String content) {
		String token = this.createOrGetToken(request);
		String key = Constant.CLIENT_TOKEN+"."+flag+"."+token;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, content);
			int seconds = expire*60;
			jedis.expire(key, seconds);
		} finally {
			if(jedis!=null)
				jedis.close();
		}
	}

	public String getCachedStr(HttpServletRequest request, String flag) throws Exception {
		Object retVal = new Decoration() {
			@Override
			protected Object run(Jedis jedis, String token) throws Exception {
				String key = Constant.CLIENT_TOKEN+"."+flag+"."+token;
				String vcode = jedis.get(key);
				return vcode;
			}
		}.start(request);
		return retVal==null?null:String.valueOf(retVal);
	}
}
