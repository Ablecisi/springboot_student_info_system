package com.ablecisi.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * springboot_student_info_system
 * com.ablecisi.entity
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/6/22
 * 星期六
 * 上午1:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String username;
    String password;
}
