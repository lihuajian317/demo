package com.lihuajian.entity;

import java.io.Serializable;

/**
 * (Classes)实体类
 *
 * @author makejava
 * @since 2019-05-09 16:28:19
 */
public class Classes implements Serializable {
    private static final long serialVersionUID = -68614873034389320L;
    
    private Integer id;
    
    private String classname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

}