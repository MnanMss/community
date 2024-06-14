package com.cdu.community.server.charge.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author mila
 * @date 2024/6/14 下午3:23
 */
@Data
public class ChargeProjectDTO extends PageQuery {
    @Schema(description = "收费编码")
    @NotNull(message = "收费编码不能为空")
    @Size(max = 255, message = "收费编码长度不能超过255个字符")
    private String code;

    @Schema(description = "收费方式;0-周期性 1-临时性")
    @NotNull(message = "收费方式不能为空")
    private Byte billingType;

    @Schema(description = "收费类型;0-普通 1-押金 2-预收金")
    @NotNull(message = "收费类型不能为空")
    private Byte chargeType;

    @Schema(description = "收费名称")
    @NotNull(message = "收费名称不能为空")
    @Size(max = 255, message = "收费名称长度不能超过255个字符")
    private String name;

    @Schema(description = "计算单价")
    @NotNull(message = "计算单价不能为空")
    @DecimalMin(value = "0.00", message = "计算单价不能小于0.00")
    private Double unitCost;

    @Schema(description = "周期单位;0-日 1-月")
    @NotNull(message = "周期单位不能为空")
    private Byte unitPeriod;

    @Schema(description = "计算精度;0-元(0.00) 1-角(0.10) 2-分(0.01)")
    @NotNull(message = "计算精度不能为空")
    private Byte computingAccuracy;

    @Schema(description = "取舍方式;0-四舍五入 1-直接舍弃 2-直接进位")
    @NotNull(message = "取舍方式不能为空")
    private Byte tradeOff;

    @Schema(description = "计算方式;0-建面(单价*建筑面积) 1-使用(单价*使用面积) 2-定额(单价) 3-公式")
    @NotNull(message = "计算方式不能为空")
    private Byte formulaMode;

    @Schema(description = "违约金额;违约金额占比")
    @DecimalMin(value = "0.00", message = "违约金额不能小于0.00")
    private Double breachAmount;

    @Schema(description = "违约开始;违约金开始天数")
    private Integer breachStart;
}
