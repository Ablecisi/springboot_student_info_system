package com.ablecisi.service.impl;

import com.ablecisi.entity.Class;
import com.ablecisi.entity.ClassLog;
import com.ablecisi.mapper.ClassLogMapper;
import com.ablecisi.mapper.ClassMapper;
import com.ablecisi.mapper.StudentMapper;
import com.ablecisi.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * itheima_web_project
 * com.ablecisi.service.impl
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/28
 * 星期日
 * 下午9:38
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassLogMapper classLogMapper;


    @Override
    public List<Class> list() {
        return classMapper.list();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    // spring事务管理 rollbackFor可以控制发生何种异常时回滚
    @Override
    public void deleteById(Integer id){
        try {
            classMapper.deleteById(id); // 根据ID删除班级
            studentMapper.deleteByClassId(id); // 根据班级ID删除员工
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            // 记录日志 务必执行
            ClassLog classLog = new ClassLog();
            classLog.setCreateTime(LocalDateTime.now());
            classLog.setDescription("执行了解散班级操作，此次操作解散的是班级ID为"+id+"的班级");
            classLogMapper.insert(classLog); // 插入日志
        }

    }

    @Override
    public void insert(Class aClass) {
        classMapper.insert(aClass);
    }

    @Override
    public Class selectById(Integer id) {
        return classMapper.selectById(id);
    }

    @Override
    public void update(Class aClass) {
        classMapper.update(aClass);
    }
}
