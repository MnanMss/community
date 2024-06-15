package com.cdu.community.server.shared.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Data
public class RoomProprietorDTO extends PageQuery {
    @Schema(description = "房间编号")
    @NotNull(message = "房间编号不能为空")
    @Size(max = 255, message = "房间编号长度不能超过255个字符")
    private String code;

    @Schema(description = "业主名称")
    @NotNull(message = "业主名称不能为空")
    @Size(max = 255, message = "业主名称长度不能超过255个字符")
    private String name;

    @Schema(description = "联系手机号")
    @NotNull(message = "联系手机号不能为空")
    @Size(max = 255, message = "联系手机号长度不能超过255个字符")
    private String phone;
}