package com.cdu.community.server.charge.domain.vo;

import com.cdu.community.server.charge.domain.entity.ChargeRoomStatistics;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ChargeRoomStatisticsVO {

    @Schema(description = "房间号")
    @NotNull(message = "房间号不能为空")
    private String roomCode;

    @Schema(description = "业主姓名")
    @NotNull(message = "业主姓名不能为空")
    private String proprietorName;

    @Schema(description = "收费项目")
    @NotNull(message = "收费项目不能为空")
    private List<ReceiveManageVO> receiveManages;

    public static ChargeRoomStatisticsVO of(ChargeRoomStatistics chargeRoomStatistics){
        ChargeRoomStatisticsVO chargeRoomStatisticsVO = new ChargeRoomStatisticsVO();
        BeanUtils.copyProperties(chargeRoomStatistics, chargeRoomStatisticsVO);
        return chargeRoomStatisticsVO;
    }
}