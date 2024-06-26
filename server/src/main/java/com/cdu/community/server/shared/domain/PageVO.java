package com.cdu.community.server.shared.domain;

import java.util.List;

/**
 * @param total 总记录数
 * @param data  分页数据集
 * @author mila
 * @date 2024/6/12 下午11:06
 */
public record PageVO<T>(long total, List<? extends T> data) {

}
