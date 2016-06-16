package com.grass.module.sys.user.entity;

import com.grass.common.persistence.BaseEntiry;

/**
 * Created by 韩亚辉 on 2016/4/18.
 */
public class UserEntity extends BaseEntiry {
    private String name;
    private Integer age;
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
