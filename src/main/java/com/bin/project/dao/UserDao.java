package com.bin.project.dao;


import com.bin.project.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * 查询用户是否存在
     * @return
     */
    SysUser findByUsername(@Param("username") String username);

    /**
     * 根据用户名 查询用户角色
     * @param username
     * @return
     */
    List<String> findRoleByUsername(@Param("username") String username);
}
