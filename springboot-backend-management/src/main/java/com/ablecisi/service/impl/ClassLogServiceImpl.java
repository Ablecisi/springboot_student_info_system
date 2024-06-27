package com.ablecisi.service.impl;

import com.ablecisi.entity.ClassLog;
import com.ablecisi.mapper.ClassLogMapper;
import com.ablecisi.service.ClassLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassLogServiceImpl implements ClassLogService {

    @Autowired
    private ClassLogMapper classLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(ClassLog classLog) {
        classLogMapper.insert(classLog);
    }
}
