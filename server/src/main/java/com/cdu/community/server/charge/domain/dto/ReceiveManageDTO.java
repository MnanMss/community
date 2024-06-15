package com.cdu.community.server.charge.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ReceiveManageDTO extends PageQuery {

    @Schema(description = "房间号id")
    @NotNull(message = "房间号id不能为空")
    private Long roomId;

    @Schema(description = "收费方式")
    @NotNull(message = "收费方式不能为空")
    private Byte billingType;

    @Schema(description = "收费项目id")
    @NotNull(message = "收费项目id不能为空")
    private Long chargeProjectId;

    @Schema(description = "起始日期")
    @NotNull(message = "起始日期不能为空")
    private LocalDateTime startTime;

    @Schema(description = "截至日期")
    @NotNull(message = "截至日期不能为空")
    private LocalDateTime endTime;

    @Schema(description = "数量;0.00")
    @NotNull(message = "数量不能为空")
    @DecimalMin(value = "0.00", message = "数量不能小于0.00")
    private Double amount;

    @Schema(description = "单价;0.00")
    @NotNull(message = "单价不能为空")
    @DecimalMin(value = "0.00", message = "单价不能小于0.00")
    private Double unit;

    @Schema(description = "倍率;0.00")
    @NotNull(message = "倍率不能为空")
    @DecimalMin(value = "0.00", message = "倍率不能小于0.00")
    private Double rate;

    @Schema(description = "应收金额;0.00")
    @NotNull(message = "应收金额不能为空")
    @DecimalMin(value = "0.00", message = "应收金额不能小于0.00")
    private Double receiveAmount;

    @Schema(description = "录入时间")
    @NotNull(message = "录入时间不能为空")
    private LocalDateTime recordTime;

    @Schema(description = "录入人id")
    @NotNull(message = "录入人id不能为空")
    private Long recordUserId;

    @Schema(description = "审核状态;0-待审核 1-已审核")
    @NotNull(message = "审核状态不能为空")
    private Byte status;

    @Schema(description = "审核人")
    private String judgeUser;

    @Schema(description = "备注")
    private String remark;
}