package com.ablecisi.contrller;

import com.ablecisi.entity.Class;
import com.ablecisi.entity.Student;
import com.ablecisi.entity.Result;
import com.ablecisi.entity.User;
import com.ablecisi.service.StudentService;
import com.ablecisi.service.UserService;
import com.ablecisi.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * itheima_web_project
 * com.ablecisi.contrller
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/1
 * 星期三
 * 下午4:35
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("登录员工, 参数: student = {}", user);
        User users = userService.login(user);
        // 登陆成功,生成JWt令牌
        if (users != null) {
            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", users.getId());
            claims.put("username", users.getUsername());
            claims.put("avatar", null);
            String jwt = JwtUtils.generateJwt(claims); // 生成JWT令牌
            return Result.success(jwt);
        } else {
            // 尝试学生登陆
            Student student = new Student();
            student.setNumber(user.getUsername());
            student.setPassword(user.getPassword());
            student = studentService.login(student);
            if (student != null) {
                // 生成JWT令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", student.getNumber());
                claims.put("username", student.getName());
                claims.put("avatar",student.getImage());
                String jwt = JwtUtils.generateJwt(claims); // 生成JWT令牌
                return Result.success(jwt);
            }
            return Result.error("用户名或密码错误");
        }
    }
}
