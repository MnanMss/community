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
 * @date 2024/6/13 上午10:00
 */
@Data
@TableName("prev_charge")
@Schema(description = "预收金/押金管理")
public class PrevCharge {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "收费流水号")
    @NotNull(message = "收费流水号不能为空")
    @Size(max = 255, message = "收费流水号长度不能超过255个字符")
    private String code;

    @Schema(description = "房间id")
    @NotNull(message = "房间id不能为空")
    private Long roomId;

    @Schema(description = "业主id")
    @NotNull(message = "业主id不能为空")
    private Long proprietorId;

    @Schema(description = "收费项目_id")
    @NotNull(message = "收费项目id不能为空")
    private Long chargeProjectId;

    @Schema(description = "实收金额;0.00")
    @NotNull(message = "实收金额不能为空")
    @DecimalMin(value = "0.00", message = "实收金额不能小于0.00")
    private Double readIncomeAmount;

    @Schema(description = "收款方式;0-现金 1-银行卡 2-微信 3-支付宝")
    @NotNull(message = "收款方式不能为空")
    private Byte paymentMethod;

    @Schema(description = "收支状态;0-收入 1-支出")
    @NotNull(message = "收支状态不能为空")
    private Byte budgetStatus;

    @Schema(description = "收款备注")
    @Size(max = 255, message = "收款备注长度不能超过255个字符")
    private String chargeRemark;

    @Schema(description = "收费日期")
    private LocalDateTime chargeTime;

    @Schema(description = "收款人_id")
    private Long chargeUserId;

    @Schema(description = "归档状态;0-未归档 1-已归档")
    @NotNull(message = "归档状态不能为空")
    private Byte status;

    @Schema(description = "收费状态;0-未收费 1-已收费 2-已退费")
    @NotNull(message = "收费状态不能为空")
    private Byte paymentStatus;

    @Schema(description = "归档人id")
    private Long archiveUserId;

    @Schema(description = "归档时间")
    private LocalDateTime archiveTime;
}
