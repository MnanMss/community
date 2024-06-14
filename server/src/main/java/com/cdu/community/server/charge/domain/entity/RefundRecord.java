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
 * @date 2024/6/13 上午10:02
 */
@Data
@TableName("refund_record")
@Schema(description = "退款记录")
public class RefundRecord {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "预收金id")
    @NotNull(message = "预收金id不能为空")
    private Long prevChargeId;

    @Schema(description = "退款金额;0.00")
    @NotNull(message = "退款金额不能为空")
    @DecimalMin(value = "0.00", message = "退款金额不能小于0.00")
    private Double refundAmount;

    @Schema(description = "退款方式;0-现金 1-银行卡 2-微信 3-支付宝")
    @NotNull(message = "退款方式不能为空")
    private Byte refundMethod;

    @Schema(description = "退款原因")
    @NotNull(message = "退款原因不能为空")
    @Size(max = 255, message = "退款原因长度不能超过255个字符")
    private String refundReason;

    @Schema(description = "退款日期")
    private LocalDateTime refundTime;
}