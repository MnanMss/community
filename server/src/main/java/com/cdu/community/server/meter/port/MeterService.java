package com.cdu.community.server.meter.port;

import com.cdu.community.server.charge.domain.exception.ChargeProjectNotFound;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import com.cdu.community.server.meter.domain.entity.MeterType;
import com.cdu.community.server.meter.domain.dto.MeterTypeDTO;
import com.cdu.community.server.meter.infrastructure.orm.MeterTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author mila
 * @date 2024/6/14 下午2:56
 */
@Service
@RequiredArgsConstructor
public class MeterService {

    private final MeterTypeMapper meterTypeMapper;

    private final ChargeProjectMapper chargeProjectMapper;

    public void addMeterType(MeterTypeDTO meterTypeDTO) {
        MeterType meterType = new MeterType();
        BeanUtils.copyProperties(meterTypeDTO , meterType);
        // 检查收费项目是否存在
        Optional.ofNullable(chargeProjectMapper.selectById(meterType.getChargeProjectId()))
            .orElseThrow(ChargeProjectNotFound::new);
        meterTypeMapper.insert(meterType);
    }
}
