package com.cdu.community.server.meter.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @author mila
 * @date 2024/6/13 上午10:14
 */
@Data
@TableName("meter_reading_record")
@Schema(description = "抄表记录")
public class MeterReadingRecord {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @Schema(description = "房间id")
    @NotNull(message = "房间id不能为空")
    private Long roomId;

    @Schema(description = "表计类别id")
    @NotNull(message = "表计类别id不能为空")
    private Long meterTypeId;

    @Schema(description = "表号")
    @NotNull(message = "表号不能为空")
    @Size(max = 255, message = "表号长度不能超过255个字符")
    private String num;

    @Schema(description = "计费起始")
    @NotNull(message = "计费起始不能为空")
    private Date startChargeTime;

    @Schema(description = "计费截止")
    @NotNull(message = "计费截止不能为空")
    private Date endChargeTime;

    @Schema(description = "起数")
    @NotNull(message = "起数不能为空")
    @DecimalMin(value = "0.00", message = "起数不能小于0.00")
    @DecimalMax(value = "999999999999999999.99", message = "起数不能超过999999999999999999.99")
    private Double startNum;

    @Schema(description = "止数")
    @NotNull(message = "止数不能为空")
    @DecimalMin(value = "0.00", message = "止数不能小于0.00")
    private Double endNum;

    @Schema(description = "倍率")
    @NotNull(message = "倍率不能为空")
    @DecimalMin(value = "0.00", message = "倍率不能小于0.00")
    private Double rate = 1.00;

    @Schema(description = "数量")
    @NotNull(message = "数量不能为空")
    @DecimalMin(value = "0.00", message = "数量不能小于0.00")
    private Double amount;

    @Schema(description = "抄表状态;0-已录入 1-待收费 2-已收费 3-已录入")
    @NotNull(message = "抄表状态不能为空")
    private Integer status = 0;

    @Schema(description = "录单时间")
    @NotNull(message = "录单时间不能为空")
    private Date recordTime;

    @Schema(description = "抄表方式;0-手工 1-其它")
    @NotNull(message = "抄表方式不能为空")
    private Integer recordType = 0;

    @Schema(description = "抄表流水号")
    @Size(max = 255, message = "抄表流水号长度不能超过255个字符")
    private String serialNumber;

    @Schema(description = "抄表人id")
    private Long recordUserId;

    @Schema(description = "录入方式;0-按止数录入 1-按用了录入")
    private Integer inputMode = 0;
}
