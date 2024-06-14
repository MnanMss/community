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
 * @date 2024/6/13 上午9:45
 */
@Data
@TableName("charge_manage")
@Schema(description = "收费管理")
public class ChargeManage {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "应收管理id")
    private Long receiveManageId;

    @Schema(description = "缓交金额")
    @NotNull(message = "缓交金额不能为空")
    @DecimalMin(value = "0.00", message = "缓交金额不能小于0.00")
    private Double deferredAmount;

    @Schema(description = "减免金额")
    @NotNull(message = "减免金额不能为空")
    @DecimalMin(value = "0.00", message = "减免金额不能小于0.00")
   private Double derateAmount;

    @Schema(description = "违约金额")
    @NotNull(message = "违约金额不能为空")
    @DecimalMin(value = "0.00", message = "违约金额不能小于0.00")
    private Double breachAmount;

    @Schema(description = "优惠金额")
    @NotNull(message = "优惠金额不能为空")
    @DecimalMin(value = "0.00", message = "优惠金额不能小于0.00")
    private Double discountAmount;

    @Schema(description = "实收金额")
    @NotNull(message = "实收金额不能为空")
    @DecimalMin(value = "0.00", message = "实收金额不能小于0.00")
    private Double readIncomeAmount;

    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;
}
