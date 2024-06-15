package com.cdu.community.server.shared.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class BuildingDTO extends PageQuery {

    @Schema(description = "楼栋名称")
    @NotNull(message = "楼栋名称不能为空")
    @Size(max = 255, message = "楼栋名称长度不能超过255个字符")
    private String name;

    @Schema(description = "所属大厦/小区id")
    @NotNull(message = "所属大厦/小区id不能为空")
    private Long edificeId;

    @Schema(description = "所属单元id")
    private Long buildingElementId;
}