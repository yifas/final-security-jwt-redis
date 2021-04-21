package com.bin;

import com.bin.project.dao.UserDao;
import com.bin.project.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@SpringBootTest
class FinalSecurityJwtRedisApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //注：底层已经加盐了！！
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    void testFindRole(){
        /*Set<Role> admin = userDao.findRole("admin");
        for (Role role : admin) {
            System.out.println(role.getRoleName());
        }*/

        Collection<Role> list = userDao.findRole("admin");
    }
}
