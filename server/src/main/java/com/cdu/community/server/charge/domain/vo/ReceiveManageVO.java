package com.cdu.community.server.charge.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ReceiveManageVO {

    @Schema(description = "收费名称")
    @NotNull(message = "收费名称不能为空")
    @Size(max = 255, message = "收费名称长度不能超过255个字符")
    private String chargeProjectName;

    @Schema(description = "应收金额;0.00")
    private Double receiveAmount;
}