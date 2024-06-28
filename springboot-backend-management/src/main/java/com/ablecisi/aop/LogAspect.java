package com.ablecisi.aop;

import com.ablecisi.entity.OperateLog;
import com.ablecisi.entity.Result;
import com.ablecisi.mapper.OperateLogMapper;
import com.ablecisi.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * itheima_web_project
 * com.ablecisi.aop
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/14
 * 星期二
 * 下午8:57
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    HttpServletRequest request;

    @Around("@annotation( com.ablecisi.anno.Log )") // 切入点表达式，表示对所有被@Log注解的方法进行切面操作
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人的ID - 当前登陆人员的ID
        // 获取请求头中的JWT令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        String operateUser = claims.get("id").toString();

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 调用原始方法
        long start = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed(); // 执行原始方法
        } catch (Exception e) {
            result = Result.error("操作失败，请联系管理员");
        }

        long end = System.currentTimeMillis();

        // 操作方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        long costTime = end - start;

        // 记录日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);

        // 调用mapper层方法，插入日志
        operateLogMapper.insert(operateLog);

        log.info("AOP记录日志：{}", operateLog);

        return result;
    }
}
