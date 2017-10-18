package com.quincy.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quincy.core.entity.Permission;

@Repository
public interface PermissionMapper {
	public Permission selectPermission(@Param("code")String code);
	public List<Permission> selectPermissions();
	public int updateSecretKeys(@Param("code")String code, @Param("publicKey")String publicKey, @Param("privateKey")String privateKey);
	public int insert(Permission permission);
}