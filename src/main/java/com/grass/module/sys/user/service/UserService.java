package com.grass.module.sys.user.service;

import com.grass.module.sys.user.mapper.UserMapper;
import com.grass.module.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {
    @Autowired
    private UserMapper userDao;

    @Cacheable(key ="com.grass.module.sys.user.service.UserService")
    public List<UserEntity> findAll() {
        System.out.println("findall");
        return userDao.findAll();
    }
    @Cacheable("usre")
    public UserEntity get(String id) {
        System.out.println("findall");
        return userDao.get(id);
    }
}
