package com.cdu.community.server.meter.domain.vo;

import com.cdu.community.server.meter.domain.entity.MeterType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author mila
 * @date 2024/6/15 下午5:00
 */
@Data
@Builder
public class RoomStatisticsVO {
    @Schema(description = "房间编号")
    @NotNull(message = "房间编号不能为空")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String code;

    @Schema(description = "业主姓名")
    private String proprietorName;

    @Schema(description = "表计类别")
    String meters;

    public static String convertMeterType(List<MeterType> meterTypes) {
        StringBuilder stringBuilder = new StringBuilder();
        meterTypes.forEach(m -> stringBuilder.append(m.getName()).append(" "));
        return stringBuilder.toString();
    }
}
