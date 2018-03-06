package com.news.admin.mapper;

import com.news.admin.pojo.SysRolePower;
import com.news.platform.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface SysRolePowerMapper {
	void removeById(Integer id);
    void removeByPowerSym(String powerSym);
    void removeByRoleSym(String roleSym);
    void removeByRoleAndPowerSym(@Param("roleSym") String roleSym, @Param("powerSym") String powerSym);
    void insert(SysRolePower record);
    List<SysRolePower> listByRoleSym(String roleSym);
    /*SysRolePower getByRoleAndPowerSym(String roleSym,String powerSym);*/
}