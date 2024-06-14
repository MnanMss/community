package com.cdu.community.server.meter.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:07
 */
@Data
@TableName("meter_type")
@Schema(description = "表计类别")
public class MeterType {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "类别代码")
    @NotNull(message = "类别代码不能为空")
    @Size(max = 255, message = "类别代码长度不能超过255个字符")
    private String code;

    @Schema(description = "类别名称")
    @NotNull(message = "类别名称不能为空")
    @Size(max = 255, message = "类别名称长度不能超过255个字符")
    private String name;

    @Schema(description = "收费项目id")
    private Long chargeProjectId;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @Schema(description = "备注")
    private String remark;
}
