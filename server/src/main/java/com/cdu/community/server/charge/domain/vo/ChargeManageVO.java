package com.cdu.community.server.charge.domain.vo;

import com.cdu.community.server.charge.domain.entity.ChargeManage;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
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
public class ChargeManageVO {

    @Schema(description = "收费名称")
    @NotNull(message = "收费名称不能为空")
    @Size(max = 255, message = "收费名称长度不能超过255个字符")
    private String name;

    @Schema(description = "起始日期")
    @NotNull(message = "起始日期不能为空")
    private LocalDateTime startTime;

    @Schema(description = "截至日期")
    @NotNull(message = "截至日期不能为空")
    private LocalDateTime endTime;

    @Schema(description = "数量;0.00")
    @NotNull(message = "数量不能为空")
    @DecimalMin(value = "0.00", message = "数量不能小于0.00")
    private Double amount;

    @Schema(description = "单价;0.00")
    @NotNull(message = "单价不能为空")
    @DecimalMin(value = "0.00", message = "单价不能小于0.00")
    private Double unit;

    @Schema(description = "倍率;0.00")
    @NotNull(message = "倍率不能为空")
    @DecimalMin(value = "0.00", message = "倍率不能小于0.00")
    private Double rate;

    @Schema(description = "应收金额;0.00")
    @NotNull(message = "应收金额不能为空")
    @DecimalMin(value = "0.00", message = "应收金额不能小于0.00")
    private Double receiveAmount;

    @Schema(description = "缓交金额")
    @NotNull(message = "缓交金额不能为空")
    @DecimalMin(value = "0.00", message = "缓交金额不能小于0.00")
    private Double deferredAmount;

    @Schema(description = "减免金额")
    @NotNull(message = "减免金额不能为空")
    @DecimalMin(value = "0.00", message = "减免金额不能小于0.00")
    private Double derateAmount;

    @Schema(description = "违约金额")
    @NotNull(message = "违约金额不能为空")
    @DecimalMin(value = "0.00", message = "违约金额不能小于0.00")
    private Double breachAmount;

    @Schema(description = "优惠金额")
    @NotNull(message = "优惠金额不能为空")
    @DecimalMin(value = "0.00", message = "优惠金额不能小于0.00")
    private Double discountAmount;

    @Schema(description = "实收金额")
    @NotNull(message = "实收金额不能为空")
    @DecimalMin(value = "0.00", message = "实收金额不能小于0.00")
    private Double readIncomeAmount;

    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255个字符")
    private String remark;

    public static ChargeManageVO of(ChargeManage chargeManage){
        ChargeManageVO chargeManageVO = new ChargeManageVO();
        BeanUtils.copyProperties(chargeManage, chargeManageVO);
        return chargeManageVO;
    }
}