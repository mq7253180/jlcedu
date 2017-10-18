package com.quincy.core.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quincy.core.entity.Permission;

@Repository
public interface PermissionService {
	public Permission selectPermission(String code);
	public void createKeyPair(String code) throws NoSuchAlgorithmException;
	public String create(Permission permission, String codePrefix) throws NoSuchAlgorithmException;
	public List<Permission> selectPermissions();
}
