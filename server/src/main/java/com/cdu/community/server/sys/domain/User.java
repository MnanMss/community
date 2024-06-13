package com.cdu.community.server.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author mila
 * @date 2024/6/13 上午9:27
 */
@Data
@TableName("user")
@Schema(description = "用户")
public class User {

    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    @Size(max = 255, message = "用户名长度不能超过255个字符")
    private String name;

    @Schema(description = "密码")
    @NotNull(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过255个字符")
    private String password;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;
}
