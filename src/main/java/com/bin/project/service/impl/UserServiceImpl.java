package com.bin.project.service.impl;


import com.bin.project.dao.UserDao;
import com.bin.project.pojo.SysUser;
import com.bin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<String> findRoleByUsername(String username) {
        return userDao.findRoleByUsername(username);
    }
}
