package com.cdu.community.server.charge.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:05
 */
@Data
@TableName("mitigate_record")
@Schema(description = "减免登记")
public class MitigateRecord {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "应收管理id")
    @NotNull(message = "应收管理id不能为空")
    private Long receiveManageId;

    @Schema(description = "减免金额;0.00")
    @NotNull(message = "减免金额不能为空")
    @DecimalMin(value = "0.00", message = "减免金额不能小于0.00")
    private Double mitigateAmount;

    @Schema(description = "登记状态;0-未登记 1-已登记")
    @NotNull(message = "登记状态不能为空")
    private Byte recordStatus;

    @Schema(description = "登记时间")
    @NotNull(message = "登记时间不能为空")
    private LocalDateTime recordTime;

    @Schema(description = "登记人id")
    @NotNull(message = "登记人id不能为空")
    private Long recordUserId;
}
