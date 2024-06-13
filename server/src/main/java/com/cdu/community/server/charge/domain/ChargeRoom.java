package com.cdu.community.server.charge.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author mila
 * @date 2024/6/13 上午9:43
 */
@Data
@TableName("charge_room")
@Schema(description = "房间收费")
public class ChargeRoom {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "房间id")
    @NotNull(message = "房间id不能为空")
    private Long roomId;

    @Schema(description = "收费项目id")
    @NotNull(message = "收费项目id不能为空")
    private Long chargeProjectId;

    @Schema(description = "计费开始日期")
    private LocalDateTime chargeStartTime;

    @Schema(description = "计费结束日期")
    private LocalDateTime chargeEndTime;
}
