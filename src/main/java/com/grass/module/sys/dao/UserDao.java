package com.grass.module.sys.dao;

import com.grass.module.sys.entity.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Repository
public interface UserDao {
    @Select("select name,age from sys_user")
    List<UserEntity> findAll();
}
