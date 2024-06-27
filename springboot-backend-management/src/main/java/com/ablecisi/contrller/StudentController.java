package com.ablecisi.contrller;

import com.ablecisi.anno.Log;
import com.ablecisi.entity.Student;
import com.ablecisi.entity.Result;
import com.ablecisi.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.List;


/**
 * itheima_web_project
 * com.ablecisi.contrller
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/28
 * 星期日
 * 下午9:33
 */
@Slf4j
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生
     * @return 学生列表
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1" )Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name,  Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday
                       ){
        log.info("查询学生, 参数: page = {}, pageSize = {}, name = {}, birthday = {}", page, pageSize, name, birthday);
        return Result.success(studentService.pageConvient(page, pageSize, name, gender, birthday));
    }

    /**
     * 删除学生
     * @param numbers 学生ID
     * @return 删除结果
     */
    @Log
    @DeleteMapping("/{numbers}")
    public Result deleteByIds(@PathVariable List<String> numbers){
        log.info("删除学生, 参数: numbers = {}", numbers.toArray());
        studentService.deleteByNumbers(numbers);
        return Result.success();
    }

    /**
     * 添加学生
     * @param student 学生对象
     * @return 添加结果
     */
    @Log
    @PostMapping
    public Result insertStudent(@RequestBody Student student){
        log.info("添加学生, 参数: student = {}", student);
        studentService.insertStudent(student);
        return Result.success();
    }

    /**
     * 查询学生
     * @param number 学生ID
     * @return 学生对象
     */
    @GetMapping("/{number}")
    public Result selectById(@PathVariable String number){
        log.info("查询学生, 参数: number = {}", number);
        return Result.success(studentService.selectByNumber(number));
    }

    /**
     * 更新学生
     * @param student 学生对象
     * @return 更新结果
     */
    @Log
    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        log.info("更新学生, 参数: student = {}", student);
        studentService.updateStudent(student);
        return Result.success(student);
    }

    @GetMapping("/report")
    public Result export(){
        log.info("信息统计");
        return Result.success(studentService.report());
    }

    @GetMapping("/ZhanBi")
    public Result ZhanBi(){
        log.info("男女占比");
        return Result.success(studentService.ZhanBi());
    }

}
