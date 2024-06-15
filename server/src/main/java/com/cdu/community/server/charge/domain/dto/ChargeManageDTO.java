package com.cdu.community.server.charge.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ChargeManageDTO extends PageQuery {

    @Schema(description = "房间id")
    private Long roomId;

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