package com.lihuajian.controller;

import com.lihuajian.entity.Student;
import com.lihuajian.service.StudentService;
import com.lihuajian.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Student)表控制层
 *
 * @author makejava
 * @since 2019-05-09 16:08:39
 */
@RestController
@RequestMapping("student")
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public String selectOne(Integer id) {
        Student student=this.studentService.queryById(id);
        return JsonUtils.toJson(student);
    }

}