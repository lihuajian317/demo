package com.lihuajian.service;

import com.lihuajian.entity.Classes;
import java.util.List;

/**
 * (Classes)表服务接口
 *
 * @author makejava
 * @since 2019-05-11 11:01:12
 */
public interface ClassesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Classes queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Classes> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    Classes insert(Classes classes);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 实例对象
     */
    Classes update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}