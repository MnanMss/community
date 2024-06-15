package com.cdu.community.server.shared.domain.vo;

import com.cdu.community.server.shared.domain.entity.Building;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class BuildingVO {

    @Schema(description = "楼栋名称")
    @NotNull(message = "楼栋名称不能为空")
    @Size(max = 255, message = "楼栋名称长度不能超过255个字符")
    private String name;

    @Schema(description = "所属大厦/小区id")
    private Long edificeId;

    @Schema(description = "所属大厦/小区名称")
    @NotNull(message = "所属大厦/小区名称不能为空")
    private String edificeName;

    @Schema(description = "所属单元id")
    private Long buildingElementId;

    @Schema(description = "所属单元名称")
    @NotNull(message = "所属单元名称不能为空")
    private String buildingElementName;

    public static BuildingVO of(Building building){
        BuildingVO buildingVO = new BuildingVO();
        BeanUtils.copyProperties(building, buildingVO);
        return buildingVO;
    }
}