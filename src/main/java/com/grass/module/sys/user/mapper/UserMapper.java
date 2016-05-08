package com.grass.module.sys.user.mapper;

import com.grass.common.persistence.CommonMapper;
import com.grass.module.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
@Repository
public interface UserMapper extends CommonMapper<UserEntity> {

    Set<String> findPermissions(String username);

    Set<String> findRoles(String username);
}
