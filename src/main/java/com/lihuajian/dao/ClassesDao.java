package com.lihuajian.dao;

import com.lihuajian.entity.Classes;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Classes)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-11 11:01:12
 */
public interface ClassesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Classes queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Classes> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param classes 实例对象
     * @return 对象列表
     */
    List<Classes> queryAll(Classes classes);

    /**
     * 新增数据
     *
     * @param classes 实例对象
     * @return 影响行数
     */
    int insert(Classes classes);

    /**
     * 修改数据
     *
     * @param classes 实例对象
     * @return 影响行数
     */
    int update(Classes classes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}