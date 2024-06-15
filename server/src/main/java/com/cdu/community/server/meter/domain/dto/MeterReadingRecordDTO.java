package com.cdu.community.server.meter.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @author mila
 * @date 2024/6/15 下午7:05
 */
@Data
public class MeterReadingRecordDTO {
    @Schema(description = "房间号")
    private String roomCode;

    @Schema(description = "表计类别id")
    @NotNull(message = "表计类别id不能为空")
    private Long meterTypeId;

    @Schema(description = "表号")
    @NotNull(message = "表号不能为空")
    @Size(max = 255, message = "表号长度不能超过255个字符")
    private String num;

    @Schema(description = "起数")
    @NotNull(message = "起数不能为空")
    @DecimalMin(value = "0.00", message = "起数不能小于0.00")
    @DecimalMax(value = "999999999999999999.99", message = "起数不能超过999999999999999999.99")
    private Double startNum;

    @Schema(description = "止数")
    @NotNull(message = "止数不能为空")
    @DecimalMin(value = "0.00", message = "止数不能小于0.00")
    private Double endNum;


    @Schema(description = "数量")
    @NotNull(message = "数量不能为空")
    @DecimalMin(value = "0.00", message = "数量不能小于0.00")
    private Double amount;


    @Schema(description = "录单时间")
    @NotNull(message = "录单时间不能为空")
    private Date recordTime;


    @Schema(description = "抄表人id")
    private Long recordUserId;

    @Schema(description = "录入方式;0-按止数录入 1-按用了录入")
    private Long inputMode;
}
