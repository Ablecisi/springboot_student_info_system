package com.ablecisi.mapper;

import com.ablecisi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * springboot_student_info_system
 * com.ablecisi.mapper
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/6/22
 * 星期六
 * 上午2:09
 */
@Mapper
public interface UserMapper {
    User login(User user);
}
