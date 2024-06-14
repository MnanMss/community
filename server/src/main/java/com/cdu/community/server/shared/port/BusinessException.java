package com.cdu.community.server.shared.port;

import com.cdu.community.server.shared.domain.entity.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常，用于处理用户请求时，业务错误时抛出
 *
 * @author mila
 * @date 2024/4/25
 */
@EqualsAndHashCode(callSuper = true)  // 自动生成 equals 和 hashcode
@Data
public class BusinessException extends RuntimeException {

    private final Code errorCodeEnum;

    public BusinessException(Code errorCodeEnum) {
        // 不调用父类 Throwable的fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        // 构造器之间的调用必须在第一行
        super(errorCodeEnum.getMessage(), null, false, false);
        this.errorCodeEnum = errorCodeEnum;
    }

}
