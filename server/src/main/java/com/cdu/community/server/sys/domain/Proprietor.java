package com.cdu.community.server.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author mila
 * @date 2024/6/13 上午9:28
 */
@Data
@TableName("proprietor")
@Schema(description = "业主")
public class Proprietor {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "业主名称")
    @NotNull(message = "业主名称不能为空")
    @Size(max = 255, message = "业主名称长度不能超过255个字符")
    private String name;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "联系手机号")
    @NotNull(message = "联系手机号不能为空")
    @Size(max = 255, message = "联系手机号长度不能超过255个字符")
    private String phone;

    @Schema(description = "预收金金额")
    private BigDecimal amount;
}