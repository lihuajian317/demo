package com.lihuajian.controller;

import com.lihuajian.entity.Classes;
import com.lihuajian.service.ClassesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Classes)表控制层
 *
 * @author makejava
 * @since 2019-05-11 11:01:12
 */
@RestController
@RequestMapping("classes")
public class ClassesController {
    /**
     * 服务对象
     */
    @Resource
    private ClassesService classesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Classes selectOne(Integer id) {
        return this.classesService.queryById(id);
    }

}