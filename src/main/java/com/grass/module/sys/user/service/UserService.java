package com.grass.module.sys.user.service;

import com.grass.common.persistence.BaseService;
import com.grass.module.sys.user.entity.UserEntity;
import com.grass.module.sys.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
public class UserService extends BaseService<UserEntity,UserMapper> {
    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查找角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        Set<String> result=userMapper.findRoles(username);
        return result;
    }

    /**
     * 根据用户名查找权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        Set<String> result=userMapper.findPermissions(username);
        return result;
    }
}
