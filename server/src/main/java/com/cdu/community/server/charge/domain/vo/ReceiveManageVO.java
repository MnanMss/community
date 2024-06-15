package com.cdu.community.server.charge.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cdu.community.server.charge.domain.entity.ReceiveManage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
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
public class ReceiveManageVO {

    @Schema(description = "房间编号")
    private String roomCode;

    @Schema(description = "业主名称")
    private String proprietorName;

    @Schema(description = "收费名称")
    private String chargeProjectName;

    @Schema(description = "应收金额;0.00")
    private Double receiveAmount;

    @Schema(description = "数量;0.00")
    private Double amount;

    @Schema(description = "单价;0.00")
    private Double unit;

    @Schema(description = "倍率;0.00")
    private Double rate;

    @Schema(description = "计费开始日期")
    private LocalDateTime startTime;

    @Schema(description = "计费结束日期")
    private LocalDateTime endTime;

    @Schema(description = "录入时间")
    @NotNull(message = "录入时间不能为空")
    private LocalDateTime recordTime;

    @Schema(description = "录入人")
    private Long recordUserName;

    @Schema(description = "审核状态")
    private String status;

    public static ReceiveManageVO of(ReceiveManage receiveManage){
        ReceiveManageVO receiveManageVO = new ReceiveManageVO();
        BeanUtils.copyProperties(receiveManage, receiveManageVO);
        return receiveManageVO;
    }
}