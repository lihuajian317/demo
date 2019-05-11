package com.lihuajian.service.impl;

import com.lihuajian.entity.Classes;
import com.lihuajian.dao.ClassesDao;
import com.lihuajian.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classes)表服务实现类
 *
 * @author makejava
 * @since 2019-05-11 11:01:12
 */
@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesDao classesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Classes queryById(Integer id) {
        return this.classesDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Classes> queryAllByLimit(int offset, int limit) {
        return this.classesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public Classes insert(Classes classes) {
        this.classesDao.insert(classes);
        return classes;
    }

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    @Override
    public Classes update(Classes classes) {
        this.classesDao.update(classes);
        return this.queryById(classes.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.classesDao.deleteById(id) > 0;
    }
}