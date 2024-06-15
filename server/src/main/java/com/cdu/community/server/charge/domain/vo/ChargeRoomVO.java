package com.cdu.community.server.charge.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
import com.cdu.community.server.charge.domain.entity.ChargeRoom;
import com.cdu.community.server.shared.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Jhc
 * @date: 2024-06-14
 **/

@Data
public class ChargeRoomVO {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "房间id")
    private Long roomId;

    @Schema(description = "房间号")
    @NotNull(message = "房间号不能为空")
    private String roomCode;

    @Schema(description = "收费项目id")
    private Long chargeProjectId;

    @Schema(description = "收费编码")
    private String chargeProjectCode;

    @Schema(description = "收费名称")
    private String chargeProjectName;

    @Schema(description = "收费方式")
    private Byte billingType;

    @Schema(description = "计费方式")
    private Byte formulaMode;

    @Schema(description = "计费开始日期")
    private LocalDateTime chargeStartTime;

    @Schema(description = "计费结束日期")
    private LocalDateTime chargeEndTime;

    public static ChargeRoomVO of(ChargeRoom chargeRoom, Room room, ChargeProject chargeProject){
        ChargeRoomVO chargeRoomVO = new ChargeRoomVO();
        BeanUtils.copyProperties(chargeRoom , chargeRoomVO);
        chargeRoomVO.setRoomCode(room.getCode());
        chargeRoomVO.setChargeProjectCode(chargeProject.getCode());
        chargeRoomVO.setChargeProjectName(chargeProject.getName());
        chargeRoomVO.setBillingType(chargeProject.getBillingType());
        chargeRoomVO.setFormulaMode(chargeProject.getFormulaMode());

        return chargeRoomVO;
    }
}