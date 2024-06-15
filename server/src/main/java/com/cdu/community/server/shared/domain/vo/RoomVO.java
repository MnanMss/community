package com.cdu.community.server.shared.domain.vo;

import com.cdu.community.server.shared.domain.entity.Building;
import com.cdu.community.server.shared.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class RoomVO {

    @Schema(description = "楼栋名称")
    @NotNull(message = "楼栋名称不能为空")
    private String buildingName;

    @Schema(description = "单元")
    private String buildingElementName;

    @Schema(description = "房间编号")
    @NotNull(message = "房间编号不能为空")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String roomCode;

    @Schema(description = "业主姓名")
    private String proprietorName;

    @Schema(description = "联系手机")
    private String proprietorPhone;

    @Schema(description = "绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "首次缴费时间")
    private LocalDateTime firstPayTime;

    @Schema(description = "建筑面积")
    private Double area;

    @Schema(description = "使用面积")
    private Double usageArea;

    public static RoomVO of(Room room, Building building){
        RoomVO roomVO = new RoomVO();
        BeanUtils.copyProperties(room, roomVO);
        roomVO.setBuildingName(building.getName());
        return roomVO;
    }
}