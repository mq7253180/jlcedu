package com.quincy.core.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.quincy.core.annotation.ReadOnly;
import com.quincy.core.entity.Permission;
import com.quincy.core.helper.DigestHelper;
import com.quincy.core.helper.RSASecurityHelper;
import com.quincy.core.mapper.PermissionMapper;
import com.quincy.core.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	@ReadOnly
	public Permission selectPermission(String code) {
		return permissionMapper.selectPermission(code);
	}

	@Transactional(isolation=Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void createKeyPair(String code) throws NoSuchAlgorithmException {
		String[] keyPair = this.createKeyPair();
		String publicKey = keyPair[0];
		String privateKey = keyPair[1];
		permissionMapper.updateSecretKeys(code, publicKey, privateKey);
	}

	@Transactional(isolation=Isolation.READ_UNCOMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String create(Permission permission, String codePrefix) throws NoSuchAlgorithmException {
		String[] keyPair = this.createKeyPair();
		String publicKey = keyPair[0];
		String privateKey = keyPair[1];
		String uuid = UUID.randomUUID().toString();
		String hash = DigestHelper.getStringSHA1(uuid.getBytes());
		permission.setCode(codePrefix+hash);
		permission.setPublicKey(publicKey);
		permission.setPrivateKey(privateKey);
		permissionMapper.insert(permission);
		return permission.getCode();
	}

	private String[] createKeyPair() throws NoSuchAlgorithmException {
		Map<String, Object> keyMap = RSASecurityHelper.generateKeyPair();
		String publicKey = keyMap.get(RSASecurityHelper.PUBLIC_KEY_BASE64).toString().replaceAll("\r\n", "");
		String privateKey = keyMap.get(RSASecurityHelper.PRIVATE_KEY_BASE64).toString().replaceAll("\r\n", "");
		return new String[]{publicKey, privateKey};
	}
	
	@ReadOnly
	public List<Permission> selectPermissions() {
		return permissionMapper.selectPermissions();
	}
}
