package com.cdu.community.server.meter.domain.entity;

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
 * @date 2024/6/13 上午10:17
 */
@Data
@TableName("table_change_record")
@Schema(description = "换表记录")
public class TableChangeRecord {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "房间号")
    @NotNull(message = "房间号不能为空")
    private Long roomId;

    @Schema(description = "表计类别id")
    @NotNull(message = "表计类别id不能为空")
    private Long meterTypeId;

    @Schema(description = "原表号")
    @NotNull(message = "原表号不能为空")
    @Size(max = 255, message = "原表号长度不能超过255个字符")
    private String oldNum;

    @Schema(description = "原表止数")
    @NotNull(message = "原表止数不能为空")
    @DecimalMin(value = "0.00", message = "原表止数不能小于0.00")
    private Double oldEndNum;

    @Schema(description = "原表用量", required = true)
    @NotNull(message = "原表用量不能为空")
    @DecimalMin(value = "0.00", message = "原表用量不能小于0.00")
    private Double oldUsage;

    @Schema(description = "新表号")
    @NotNull(message = "新表号不能为空")
    @Size(max = 255, message = "新表号长度不能超过255个字符")
    private String newNum;

    @Schema(description = "新表起数")
    @NotNull(message = "新表起数不能为空")
    @DecimalMin(value = "0.00", message = "新表起数不能小于0.00")
    private Double newStartNum;

    @Schema(description = "安装人员")
    @NotNull(message = "安装人员不能为空")
    @Size(max = 255, message = "安装人员长度不能超过255个字符")
    private String installStaff = "swh";

    @Schema(description = "登记人id")
    @NotNull(message = "登记人id不能为空")
    private Long recordUserId;
}
