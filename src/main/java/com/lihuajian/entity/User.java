package com.lihuajian.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2019-05-11 11:01:13
 */
public class User implements Serializable {
    private static final long serialVersionUID = 419769012924289527L;
    
    private Integer id;
    
    private String name;
    
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}