package com.ablecisi.mapper;

import com.ablecisi.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    /**
     * 插入操作日志
     * @param log 操作日志
     */
    void insert(OperateLog log);

}
