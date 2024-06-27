package com.ablecisi.service.impl;

import com.ablecisi.entity.ZhanBi;
import com.ablecisi.entity.Student;
import com.ablecisi.entity.PageBean;
import com.ablecisi.mapper.StudentMapper;
import com.ablecisi.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 下午9:39
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询员工
     * @param page 页码
     * @param pageSize 每页大小
     * @return 页面对象
     */
    @Override
    public PageBean<Student> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate birthday) {
        int start = (page - 1) * pageSize;
        return new PageBean<>(studentMapper.count(), studentMapper.page(start, pageSize, name, gender, birthday)) ;
    }

    /**
     * 分页查询员工(<b>利用pageHelper插件实现分页查询</b>)
     * @param page 页码
     * @param pageSize 每页大小
     * @return 页面对象
     */
    @Override
    public PageBean<Student> pageConvient(Integer page, Integer pageSize, String name, Integer gender, LocalDate birthday) {
        // 使用PageHelper插件，设置分页
        PageHelper.startPage(page, pageSize);

        // 执行查询
        List<Student> students = studentMapper.list(name, gender, birthday);
        Page<Student> pageInfo = (Page<Student>) students;

        // 返回分页数据
        return new PageBean<>(pageInfo.getTotal(), pageInfo.getResult());
    }

    /**
     * 删除员工
     * @param numbers 员工ID
     */
    @Override
    public void deleteByNumbers(List<String> numbers) {
        studentMapper.deleteByNumbers(numbers);
    }

    /**
     *  添加员工
     * @param student 员工对象
     */
    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    /**
     * 更新员工
     * @param number 员工ID
     */
    @Override
    public Student selectByNumber(String number) {
        return studentMapper.selectByNumber(number);
    }

    /**
     * 更新员工
     * @param student 员工对象
     */
    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public List<Student> report() {
        return studentMapper.report();
    }

    @Override
    public ZhanBi ZhanBi() {
        return new ZhanBi(studentMapper.count(), studentMapper.man());
    }
}
