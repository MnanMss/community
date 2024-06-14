package com.cdu.community.server.charge.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 收费项目视图对象 charge_project
 *
 * @author: Jhc
 * @date: 2024-06-14
 **/

@Data
public class ChargeProjectVo {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "收费编码")
    @NotNull(message = "收费编码不能为空")
    @Size(max = 255, message = "收费编码长度不能超过255个字符")
    private String code;

    @Schema(description = "收费名称")
    @NotNull(message = "收费名称不能为空")
    @Size(max = 255, message = "收费名称长度不能超过255个字符")
    private String name;

    @Schema(description = "收费方式;0-周期性 1-临时性")
    @NotNull(message = "收费方式不能为空")
    private Byte billingType;

    @Schema(description = "计算方式;0-建面(单价*建筑面积) 1-使用(单价*使用面积) 2-定额(单价) 3-公式")
    @NotNull(message = "计算方式不能为空")
    private Byte formulaMode;

    @Schema(description = "收费类型;0-普通 1-押金 2-预收金")
    @NotNull(message = "收费类型不能为空")
    private Byte chargeType;

    public static ChargeProjectVo of(ChargeProject chargeProject){
        ChargeProjectVo chargeProjectVo = new ChargeProjectVo();
        BeanUtils.copyProperties(chargeProject, chargeProjectVo);
        return chargeProjectVo;
    }
}