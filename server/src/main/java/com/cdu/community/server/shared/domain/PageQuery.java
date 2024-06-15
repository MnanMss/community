package com.cdu.community.server.shared.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mila
 * @date 2024/6/14 下午4:34
 */
@Data
public class PageQuery {
    @Schema(description = "请求页码")
    private int pageNum = 1;

    @Schema(description = "每页大小")
    private int pageSize = 10;
}
