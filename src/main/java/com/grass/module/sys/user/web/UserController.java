package com.grass.module.sys.user.web;

import com.grass.common.result.ResultDTO;
import com.grass.common.web.BaseController;
import com.grass.module.sys.user.entity.UserEntity;
import com.grass.module.sys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Controller
@RequestMapping("/${adminPath}/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll() {
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = userService.findList(userEntity);
        return ResultDTO.toJson(200, "成功", list);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public UserEntity get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Boolean insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(12);
        userEntity.setName("fsdf");
        userEntity.setId(UUID.randomUUID() + "");
        userService.save(userEntity);
        return false;
    }

}
