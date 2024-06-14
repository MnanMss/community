package com.cdu.community.server.meter.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author mila
 * @date 2024/6/14 下午4:19
 */
@Data
public class MeterTypeSearchDTO extends PageQuery {
    @Schema(description = "类别代码")
    @Size(max = 255, message = "类别代码长度不能超过255个字符")
    private String code;

    @Schema(description = "类别名称")
    @Size(max = 255, message = "类别名称长度不能超过255个字符")
    private String name;

    @Schema(description = "收费项目id")
    private Long chargeProjectId;
}
