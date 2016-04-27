package com.grass.module.sys.user.service;

import com.grass.module.sys.user.entity.UserEntity;
import com.grass.module.sys.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("findAll")
    public List<UserEntity> findAll() {
        System.out.println("findall");
        return userDao.findAll();
    }

    @Cacheable()
    public UserEntity get(String id) {
        System.out.println("get");
        return userDao.get(id);
    }
}
