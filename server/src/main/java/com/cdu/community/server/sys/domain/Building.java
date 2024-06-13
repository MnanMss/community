package com.cdu.community.server.sys.domain;

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
 * @date 2024/6/13 上午9:32
 */
@Data
@TableName("building")
@Schema(description = "楼栋")
public class Building {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "楼栋名称")
    @NotNull(message = "楼栋名称不能为空")
    @Size(max = 255, message = "楼栋名称长度不能超过255个字符")
    private String name;

    @Schema(description = "所属大厦/小区id")
    @NotNull(message = "所属大厦/小区id不能为空")
    private Long edificeId;
}
