package com.cdu.community.server.meter.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午10:07
 */
@Data
@TableName("meter_type")
@Schema(description = "表计类别")
public class MeterType {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "类别代码")
    @NotNull(message = "类别代码不能为空")
    @Size(max = 255, message = "类别代码长度不能超过255个字符")
    private String code;

    @Schema(description = "类别名称")
    @NotNull(message = "类别名称不能为空")
    @Size(max = 255, message = "类别名称长度不能超过255个字符")
    private String name;

    @Schema(description = "收费编码")
    @NotNull(message = "收费编码不能为空")
    @Size(max = 255, message = "收费编码长度不能超过255个字符")
    private String chargeCode;

    @Schema(description = "收费名称")
    @NotNull(message = "收费名称不能为空")
    @Size(max = 255, message = "收费名称长度不能超过255个字符")
    private String chargeName;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    @Size(max = 255, message = "更新时间长度不能超过255个字符")
    private String updatedTime;

    @Schema(description = "备注")
    private String remark;
}
