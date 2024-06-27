package com.ablecisi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * itheima_web_project
 * com.ablecisi.entity
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/4/30
 * 星期二
 * 下午2:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<E> {
    private Long total;
    private List<E> rows;
}
