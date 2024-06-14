package com.cdu.community.server.meter.port;

import com.cdu.community.server.meter.domain.MeterType;
import com.cdu.community.server.meter.domain.dto.MeterTypeDTO;
import com.cdu.community.server.meter.infrastructure.orm.MeterTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author mila
 * @date 2024/6/14 下午2:56
 */
@Service
@RequiredArgsConstructor
public class MeterService {

    private final MeterTypeMapper meterTypeMapper;

    public void addMeterType(MeterTypeDTO meterTypeDTO) {
        MeterType meterType = new MeterType();
        BeanUtils.copyProperties(meterTypeDTO , meterType);
        meterTypeMapper.insert(meterType);
    }
}
