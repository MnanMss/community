package com.cdu.community.server.charge.domain.entity;

import com.cdu.community.server.charge.domain.vo.ReceiveManageVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 此实体仅作业务使用
 *
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ChargeRoomStatistics {

    @Schema(description = "所属大厦/小区id")
    private Long edificeId;

    @Schema(description = "楼栋id")
    private Long buildingId;

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "房间号")
    private String roomCode;

    @Schema(description = "收费项目id")
    private Long chargeProjectId;

    @Schema(description = "业主id")
    private Long proprietorId;

    @Schema(description = "业主姓名")
    private String proprietorName;

    @Schema(description = "收费项目")
    private List<ReceiveManageVO> receiveManages;
}