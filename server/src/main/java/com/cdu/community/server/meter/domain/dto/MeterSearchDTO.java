package com.cdu.community.server.meter.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mila
 * @date 2024/6/15 下午4:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MeterSearchDTO extends PageQuery {
    @Schema(description = "房间id")
    @NotNull(message = "房间id不能为空")
    private Long roomId;
}
