package com.ablecisi.exception;

import com.ablecisi.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * itheima_web_project
 * com.ablecisi.exception
 * 全局异常处理器
 * @author Ablecisi
 * @version 1.0
 * 2024/5/13
 * 星期一
 * 下午5:20
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // 拦截所有异常
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 返回500 状态码
    public final Result handleAllExceptions(Exception ex) { // 处理异常
        // 这里可以添加你自己的异常处理逻辑，例如记录日志、发送通知等
        System.out.println("错误 === " + ex.getMessage());
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
