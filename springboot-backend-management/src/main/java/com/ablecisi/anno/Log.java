package com.ablecisi.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * itheima_web_project
 * com.ablecisi.anno
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/14
 * 星期二
 * 下午9:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
}
