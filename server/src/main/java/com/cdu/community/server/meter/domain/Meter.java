package com.cdu.community.server.meter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:09
 */
@Data
@TableName("meter")
@Schema(description = "表计")
public class Meter {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

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

    @Schema(description = "装表读数")
    @NotNull(message = "装表读数不能为空")
    @DecimalMin(value = "0.00", message = "装表读数不能小于0.00")
    private Double readNum;

    @Schema(description = "倍率")
    @NotNull(message = "倍率不能为空")
    @DecimalMin(value = "0.00", message = "倍率不能小于0.00")
    private Double rate;

    @Schema(description = "量程")
    @NotNull(message = "量程不能为空")
    @DecimalMin(value = "0.00", message = "量程不能小于0.00")
    private Double range;

    @Schema(description = "表计运行状态;1运行/0报停")
    @NotNull(message = "表计运行状态不能为空")
    @Size(max = 255, message = "表计运行状态长度不能超过255个字符")
    private String status;

    @Schema(description = "安装日期")
    @NotNull(message = "安装日期不能为空")
    private LocalDateTime installTime;

    @Schema(description = "报停日期")
    private LocalDateTime stopTime;

    @Schema(description = "抄表员")
    @Size(max = 255, message = "抄表员长度不能超过255个字符")
    private String meterReader = "swh";

    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;
}
