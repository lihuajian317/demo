package com.lihuajian.entity;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2019-05-11 11:01:13
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 933438327716692886L;
    
    private Integer id;
    
    private String name;


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

}