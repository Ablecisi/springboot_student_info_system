package com.ablecisi.service;

import com.ablecisi.entity.Class;

import java.util.List;

/**
 * itheima_web_project
 * com.ablecisi.service.impl
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/28
 * 星期日
 * 下午9:37
 */
public interface ClassService {
    List<Class> list();

    void deleteById(Integer id);

    void insert(Class aClass);

    Class selectById(Integer id);

    void update(Class aClass);
}
