package com.ablecisi.contrller;

import com.ablecisi.anno.Log;
import com.ablecisi.entity.Class;
import com.ablecisi.entity.Result;
import com.ablecisi.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Slf4j // lombok提供的注解，可以直接使用log对象
@RequestMapping("/class")
@RestController()
public class ClassController {

    /**
     * 注入DeptService
     */
    @Autowired
    private ClassService classService;

    /**
     * 查询所有部门
     * 只能是get请求
     */

    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        List<Class> classlist =  classService.list();
        return Result.success(classlist);
    }

    /**
     * 根据id删除部门
     * @param id 部门id
     * @return Result
     * */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据id删除部门:{}",id);
        classService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     * @param aClass 部门对象
     * @return Result
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Class aClass) {
        log.info("添加部门:{}", aClass);
        classService.insert(aClass);
        return Result.success();
    }


    /**
     * 根据id查询部门
     * @param id 部门id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据id查询部门:{}",id);
        return Result.success(classService.selectById(id));
    }

    /**
     * 修改部门
     * @return Result
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Class aClass) {
        log.info("修改部门:{}", aClass);
        classService.update(aClass);
        return Result.success();
    }


}
