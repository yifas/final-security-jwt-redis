package com.bin.project.service;



import com.bin.project.pojo.SysUser;

import java.util.List;

public interface UserService {

    SysUser findByUsername(String username);

    List<String> findRoleByUsername(String username);

    List<SysUser> findUserList();

}
