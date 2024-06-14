package com.cdu.community.server.charge.domain.exception;

import com.cdu.community.server.shared.domain.entity.Code;
import com.cdu.community.server.shared.port.BusinessException;

/**
 * @author mila
 * @date 2024/6/14 下午3:44
 */
public class ChargeProjectNotFound extends BusinessException {
    public ChargeProjectNotFound() {
        super(Code.CHARGE_PROJECT_NOT_FOUND);
    }
}
