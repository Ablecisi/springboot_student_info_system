package com.ablecisi.mapper;


import com.ablecisi.entity.ClassLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassLogMapper {

    /**
     * 添加部门日志
     * @param log 部门日志对象
     */
    void insert(ClassLog log);

}
