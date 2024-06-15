package com.cdu.community.server.meter.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mila
 * @date 2024/6/15 下午4:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoomStatisticsSearchDTO extends PageQuery {

    @Schema(description = "房间编号")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String roomCode;

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "业主姓名")
    private String proprietorName;
}
