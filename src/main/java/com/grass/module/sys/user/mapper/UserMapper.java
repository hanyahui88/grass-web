package com.grass.module.sys.user.mapper;

import com.grass.module.sys.user.entity.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Repository
public interface UserMapper {
    @Select("select name,age from sys_user")
    List<UserEntity> findAll();

    UserEntity get(String id);
}
