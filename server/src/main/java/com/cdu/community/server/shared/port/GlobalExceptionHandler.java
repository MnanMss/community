package com.cdu.community.server.shared.port;

import com.cdu.community.server.shared.domain.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author mila
 * @date 2024/6/12 下午10:53
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Resp<Void> handlerBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Resp.fail(e.getErrorCodeEnum());
    }


    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public Resp<Void> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return Resp.error();
    }
}
