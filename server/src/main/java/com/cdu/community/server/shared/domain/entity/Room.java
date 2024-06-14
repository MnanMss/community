package com.cdu.community.server.shared.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author mila
 * @date 2024/6/13 上午9:30
 */
@Data
@TableName("room")
@Schema(description = "房间")
public class Room {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "房间编号")
    @NotNull(message = "房间编号不能为空")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String code;

    @Schema(description = "楼顶id")
    @NotNull(message = "楼顶id不能为空")
    private Long buildingId;

    @Schema(description = "所属楼层")
    @NotNull(message = "所属楼层不能为空")
    private Integer floor;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "首次缴费时间")
    private LocalDateTime firstPayTime;

    @Schema(description = "建筑面积")
    @NotNull(message = "建筑面积不能为空")
    private Double area;

    @Schema(description = "使用面积")
    private Double usageArea;

    @Schema(description = "业主Id")
    private Long proprietorId;
}
