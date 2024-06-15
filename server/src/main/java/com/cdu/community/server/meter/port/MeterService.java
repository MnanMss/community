package com.cdu.community.server.meter.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.exception.ChargeProjectNotFound;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import com.cdu.community.server.meter.domain.dto.MeterDto;
import com.cdu.community.server.meter.domain.dto.MeterSearchDTO;
import com.cdu.community.server.meter.domain.dto.MeterTypeSearchDTO;
import com.cdu.community.server.meter.domain.entity.Meter;
import com.cdu.community.server.meter.domain.entity.MeterType;
import com.cdu.community.server.meter.domain.dto.MeterTypeDTO;
import com.cdu.community.server.meter.infrastructure.orm.MeterMapper;
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

    private final MeterMapper meterMapper;

    public void addMeterType(MeterTypeDTO meterTypeDTO) {
        MeterType meterType = new MeterType();
        BeanUtils.copyProperties(meterTypeDTO , meterType);
        // 检查收费项目是否存在
        Optional.ofNullable(chargeProjectMapper.selectById(meterType.getChargeProjectId()))
            .orElseThrow(ChargeProjectNotFound::new);
        meterTypeMapper.insert(meterType);
    }

    public Page<MeterType> listMeterType(MeterTypeSearchDTO condition) {
        LambdaQueryWrapper<MeterType> query = new LambdaQueryWrapper<>();
        if(condition.getName() != null && !condition.getName().isEmpty()) {
            query.like(MeterType::getName , condition.getName());
        }
        if(condition.getCode() != null && !condition.getCode().isEmpty()) {
            query.likeLeft(MeterType::getCode , condition.getCode());
        }
        if(condition.getChargeProjectId() != null) {
            query.eq(MeterType::getChargeProjectId , condition.getChargeProjectId());
        }
        Page<MeterType> page = new Page<>(condition.getPageNum() , condition.getPageSize());
        return meterTypeMapper.selectPage(page, query);
    }
    public void delMeterType(String id) {
        MeterType meterType = new MeterType();
        Optional.ofNullable(chargeProjectMapper.selectById(meterType.getChargeProjectId()))
                .orElseThrow(ChargeProjectNotFound::new);
        meterTypeMapper.deleteById(id);
    }

    public void updateMeterType(MeterType meterType) {
        meterTypeMapper.updateById(meterType);
    }

    public void addMeter(MeterDto meterDto) {
        Meter meter = new Meter();
        BeanUtils.copyProperties(meterDto , meter);
        meterMapper.insert(meter);
    }

    public Page<Meter> listMeter(MeterSearchDTO condition) {
        LambdaQueryWrapper<Meter> query = new LambdaQueryWrapper<>();
        query.eq(Meter::getRoomId , condition.getRoomId());
        Page<Meter> page = new Page<>(condition.getPageNum() , condition.getPageSize());
        return meterMapper.selectPage(page , query);
    }
}
