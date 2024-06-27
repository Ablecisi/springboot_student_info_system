package com.ablecisi.mapper;

import com.ablecisi.entity.Student;
import com.ablecisi.entity.ZhanBi;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
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
public interface StudentMapper {

    /**
     * 查询所有员工(利用pageHelper插件实现分页查询)
     * @return 员工列表
     */
    List<Student> list(String name, Integer gender, LocalDate birthday);

    /**
     * 查询所有员工
     * @return 数据总数
     */
    Long count();

    /**
     *  分页查询员工
     * @param page 开始位置
     * @param pageSize 每页大小
     * @return 员工列表
     */
    List<Student> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate birthday);

    /**
     * 删除员工
     * @param numbers 员工ID
     */
    void deleteByNumbers(List<String> numbers);

    /**
     * 添加员工
     * @param student 员工对象
     */
    void insertStudent(Student student);

    /**
     * 根据ID查询员工
     * @param number 员工ID
     * @return 员工对象
     */
    Student selectByNumber(String number);

    /**
     * 更新员工
     * @param student 员工对象
     */
    void updateStudent(Student student);

    /**
     * 根据部门ID删除员工
     * @param id 部门ID
     */
    void deleteByClassId(Integer id);

    List<Student> report(); // 信息统计

    int man();
}
