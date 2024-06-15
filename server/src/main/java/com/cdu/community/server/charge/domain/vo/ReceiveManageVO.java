package com.cdu.community.server.charge.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class ReceiveManageVO {

    @Schema(description = "收费项目名称")
    private String chargeProjectName;

    @Schema(description = "应收金额;0.00")
    private Double receiveAmount;
}