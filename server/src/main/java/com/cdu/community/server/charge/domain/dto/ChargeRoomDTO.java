package com.cdu.community.server.charge.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Jhc
 * @date: 2024-06-14
 **/

@Data
public class ChargeRoomDTO extends PageQuery {

    @Schema(description = "大厦/小区id")
    @NotNull(message = "大厦/小区id不能为空")
    private Long edificeId;

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "房间号")
    private String roomCode;

    @Schema(description = "收费项目id")
    @NotNull(message = "收费项目id不能为空")
    private Long chargeProjectId;

    @Schema(description = "计费开始日期")
    private LocalDateTime chargeStartTime;

    @Schema(description = "计费结束日期")
    private LocalDateTime chargeEndTime;

    @Schema(description = "违约金率")
    private Double breachRate;

    @Schema(description = "违约开始天数")
    private Integer breachStart;

    @Schema(description = "业主姓名")
    private Integer proprietorName;
}