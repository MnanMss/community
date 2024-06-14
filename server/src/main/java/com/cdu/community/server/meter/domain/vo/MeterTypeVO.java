package com.cdu.community.server.meter.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cdu.community.server.meter.domain.entity.MeterType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author mila
 * @date 2024/6/14 下午4:16
 */
@Data
public class MeterTypeVO {
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "类别代码")
    @NotNull(message = "类别代码不能为空")
    @Size(max = 255, message = "类别代码长度不能超过255个字符")
    private String code;

    @Schema(description = "类别名称")
    @NotNull(message = "类别名称不能为空")
    @Size(max = 255, message = "类别名称长度不能超过255个字符")
    private String name;

    @Schema(description = "收费项目id")
    private Long chargeProjectId;

    @Schema(description = "备注")
    private String remark;

    public static MeterTypeVO of(MeterType meterType) {
        MeterTypeVO meterTypeVO = new MeterTypeVO();
        BeanUtils.copyProperties(meterType , meterTypeVO);
        return meterTypeVO;
    }
}
