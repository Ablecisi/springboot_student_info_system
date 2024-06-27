package com.ablecisi.mapper;

import com.ablecisi.entity.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * itheima_web_project
 * com.ablecisi.mapper
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/28
 * 星期日
 * 下午9:35
 */
@Mapper
public interface ClassMapper {
    /**
     * 查询所有部门
     * @return List<Class>
     */
    List<Class> list();

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    void deleteById(Integer id);

    /**
     * 添加部门
     * @param aClass 部门对象
     */
    void insert(Class aClass);

    /**
     * 根据id查询部门
     * @param id 部门id
     */
    Class selectById(Integer id);

    /**
     * 更新部门
     * @param aClass 部门对象
     */
    void update(Class aClass);
}
