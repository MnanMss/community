package com.cdu.community.server.meter.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:12
 */
@Data
@TableName("room_statistics")
@Schema(description = "房间统计")
public class RoomStatistics {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "业主id")
    private Long proprietorId;
}
