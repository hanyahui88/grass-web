package com.grass.module;

import com.grass.config.Application;
import com.grass.module.sys.user.entity.UserEntity;
import com.grass.module.sys.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=8888", "management.port=0"})
public class TestUser {

    @Autowired
    private UserService userService;
    @Test
    public void findAll(){
        List<UserEntity> userEntityList=userService.findAll();
        System.out.print(userEntityList.size());
    }
    @Test
    public void get(){
        UserEntity userEntity=userService.get("1");
        System.out.print(userEntity.getAge());
        System.out.print(userEntity.getName());
    }
}
