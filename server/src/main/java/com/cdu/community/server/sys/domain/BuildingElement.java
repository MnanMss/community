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
 * @date 2024/6/13 上午9:35
 */
@Data
@TableName("building_element")
@Schema(description = "单元")
public class BuildingElement {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "单元名称")
    @NotNull(message = "单元名称不能为空")
    @Size(max = 255, message = "单元名称长度不能超过255个字符")
    private String name;

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "所属小区id")
    private Long edificeId;
}
