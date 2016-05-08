package com.grass.module.sys.user.service;

import com.grass.common.persistence.CommonService;
import com.grass.module.sys.user.entity.UserEntity;
import com.grass.module.sys.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
public class UserService extends CommonService<UserEntity,UserMapper>{
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
