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

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Service
public class UserService extends CommonService<UserEntity,UserMapper>{
}
