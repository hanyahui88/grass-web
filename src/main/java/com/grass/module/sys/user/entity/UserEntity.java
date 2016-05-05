package com.grass.module.sys.user.entity;

import com.grass.common.persistence.CommonEntiry;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
public class UserEntity extends CommonEntiry {
    private String name;
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
