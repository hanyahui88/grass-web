package com.grass.module.sys.web;

import com.grass.common.utils.SpringContextHolder;
import com.grass.module.sys.entity.UserEntity;
import com.grass.module.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Controller
@RequestMapping("/${adminPath}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<UserEntity> findAll() {
        Object obj1 = SpringContextHolder.getBean("cacheManager");
        return userService.findAll();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public UserEntity get(@PathVariable("id") String id) {
        return userService.get(id);
    }
}
