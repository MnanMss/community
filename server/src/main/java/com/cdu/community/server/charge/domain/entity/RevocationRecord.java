package com.cdu.community.server.charge.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:04
 */
@Data
@TableName("revocation_record")
@Schema(description = "撤销记录")
public class RevocationRecord {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "预收金id")
    @Size(max = 255, message = "预收金id长度不能超过255个字符")
    private String prevChargeId;

    @Schema(description = "撤销金额;0.00")
    @NotNull(message = "撤销金额不能为空")
    @DecimalMin(value = "0.00", message = "撤销金额不能小于0.00")
    private Double revocationAmount;

    @Schema(description = "撤销原因")
    @NotNull(message = "撤销原因不能为空")
    @Size(max = 255, message = "撤销原因长度不能超过255个字符")
    private String revocationReason;

    @Schema(description = "撤销时间")
    @NotNull(message = "撤销时间不能为空")
    private LocalDateTime revocationTime;

    @Schema(description = "撤销人id")
    @NotNull(message = "撤销人id不能为空")
    private Long revocationUserId;
}
