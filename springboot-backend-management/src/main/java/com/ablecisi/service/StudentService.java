package com.ablecisi.service;

import com.ablecisi.entity.ZhanBi;
import com.ablecisi.entity.Student;
import com.ablecisi.entity.PageBean;

import java.time.LocalDate;
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
public interface StudentService {
    PageBean<Student> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate birthday);

    PageBean<Student> pageConvient(Integer page, Integer pageSize, String name, Integer gender, LocalDate birthday);

    void deleteByNumbers(List<String> numbers);

    void insertStudent(Student student);

    Student selectByNumber(String number);

    void updateStudent(Student student);

    List<Student> report();

    ZhanBi ZhanBi();
}
