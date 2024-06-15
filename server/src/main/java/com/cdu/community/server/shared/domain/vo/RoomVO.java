package com.cdu.community.server.shared.domain.vo;

import com.cdu.community.server.shared.domain.entity.Building;
import com.cdu.community.server.shared.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class RoomVO {

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "楼栋名称")
    @NotNull(message = "楼栋名称不能为空")
    private String buildingName;

    @Schema(description = "所属楼层")
    private Integer floor;

    @Schema(description = "房间编号")
    @NotNull(message = "房间编号不能为空")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String code;

    public static RoomVO of(Room room, Building building){
        RoomVO roomVO = new RoomVO();
        BeanUtils.copyProperties(room, roomVO);
        roomVO.setBuildingName(building.getName());
        return roomVO;
    }
}