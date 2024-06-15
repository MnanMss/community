package com.cdu.community.server.charge.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ChargeRoomStatisticsDTO extends PageQuery {

    @Schema(description = "所属大厦/小区id")
    @NotNull(message = "所属大厦/小区id不能为空")
    private Long edificeId;

    @Schema(description = "楼栋id")
    @NotNull(message = "楼栋id不能为空")
    private Long buildingId;

    @Schema(description = "房间号")
    @NotNull(message = "房间号不能为空")
    private String roomCode;

    @Schema(description = "收费项目id")
    @NotNull(message = "收费项目id不能为空")
    private Long chargeProjectId;

    @Schema(description = "业主姓名")
    @NotNull(message = "业主姓名不能为空")
    private String proprietorName;

}