package com.grass.module.sys.service;

import com.grass.module.sys.dao.UserDao;
import com.grass.module.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<UserEntity> findAll() {
//        return null;
        return userDao.findAll();
    }
    public UserEntity get(String id) {
//        return null;
        return userDao.get(id);
    }
}
