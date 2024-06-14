package com.cdu.community.server.charge.port;

import com.cdu.community.server.charge.domain.entity.ChargeProject;
import com.cdu.community.server.charge.domain.dto.ChargeProjectDTO;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author mila
 * @date 2024/6/14 下午3:19
 */
@Service
@RequiredArgsConstructor
public class ChargeService {
    private final ChargeProjectMapper chargeProjectMapper;


    public void addChargeProject(ChargeProjectDTO chargeProjectDTO) {
        ChargeProject chargeProject = new ChargeProject();
        BeanUtils.copyProperties(chargeProjectDTO , chargeProject);
        chargeProjectMapper.insert(chargeProject);
    }
}
