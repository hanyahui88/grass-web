package com.grass.module.sys.user.service;

import com.grass.module.sys.user.mapper.UserMapper;
import com.grass.module.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userDao;

    @Cacheable(key ="com.grass.module.sys.user.service.UserService")
    public List<UserEntity> findAll() {
//        return null;
        return userDao.findAll();
    }
    public UserEntity get(String id) {
//        return null;
        return userDao.get(id);
    }
}
