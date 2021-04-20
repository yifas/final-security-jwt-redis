package com.bin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FinalSecurityJwtRedisApplicationTests {


    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //注：底层已经加盐了！！
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}
