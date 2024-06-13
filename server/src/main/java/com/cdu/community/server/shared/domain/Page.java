package com.cdu.community.server.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author mila
 * @date 2024/6/12 下午11:06
 */
@Getter
@AllArgsConstructor
public class Page<T>{

    /**
     * 总记录数
     */
    private final long total;

    /**
     * 分页数据集
     */
    private final List<? extends T> data;
}
