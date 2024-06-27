package com.ablecisi.service.impl;

import com.ablecisi.entity.User;
import com.ablecisi.mapper.UserMapper;
import com.ablecisi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * springboot_student_info_system
 * com.ablecisi.service.impl
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/6/22
 * 星期六
 * 上午2:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
