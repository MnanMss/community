package com.cdu.community.server.meter.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.exception.ChargeProjectNotFound;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import com.cdu.community.server.meter.domain.dto.*;
import com.cdu.community.server.meter.domain.entity.Meter;
import com.cdu.community.server.meter.domain.entity.MeterReadingRecord;
import com.cdu.community.server.meter.domain.entity.MeterType;
import com.cdu.community.server.meter.domain.entity.RoomStatistics;
import com.cdu.community.server.meter.infrastructure.orm.MeterMapper;
import com.cdu.community.server.meter.infrastructure.orm.MeterReadingRecordMapper;
import com.cdu.community.server.meter.infrastructure.orm.MeterTypeMapper;
import com.cdu.community.server.meter.infrastructure.orm.RoomStatisticsMapper;
import com.cdu.community.server.shared.port.SharedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private final RoomStatisticsMapper roomStatisticsMapper;

    private final MeterReadingRecordMapper meterReadingRecordMapper;

    private final SharedService sharedService;
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
        if(condition.getChargeProjectId() != null && condition.getChargeProjectId() > 0) {
            query.eq(MeterType::getChargeProjectId , condition.getChargeProjectId());
        }
        Page<MeterType> page = new Page<>(condition.getPageNum() , condition.getPageSize());
        return meterTypeMapper.selectPage(page, query);
    }
    public void delMeterType(Long id) {
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

    public void modifyMeter(Meter meter) {
        meterMapper.updateById(meter);
    }

    public void delMeter(Long id) {
        meterMapper.deleteById(id);
    }

    public Page<RoomStatistics> listRoomStatistics(RoomStatisticsSearchDTO condition) {
        LambdaQueryWrapper<RoomStatistics> query = new LambdaQueryWrapper<>();
        if(condition.getBuildingId() != 0) {
            query.eq(RoomStatistics::getBuildingId , condition.getBuildingId());
        }
        if(condition.getRoomCode() != null  && !condition.getRoomCode().isEmpty()) {
            query.eq(RoomStatistics::getRoomCode , condition.getRoomCode());
        }
        if(condition.getProprietorName() != null && !condition.getProprietorName().isEmpty()) {
            query.eq(RoomStatistics::getProprietorName , condition.getProprietorName());
        }

        Page<RoomStatistics> page = new Page<>(condition.getPageNum() , condition.getPageSize());
        return roomStatisticsMapper.selectPage(page , query);
    }

    public List<MeterType> listMeterTypeByRoomId(Long roomId) {
        LambdaQueryWrapper<Meter> query = new LambdaQueryWrapper<>();
        query.eq(Meter::getRoomId , roomId);
        List<Long> meterTypeIdList = meterMapper.selectList(query).stream().map(Meter::getMeterTypeId).toList();
        List<MeterType> meterTypes = new ArrayList<>();
        if(!meterTypeIdList.isEmpty()) {
            meterTypes = meterTypeMapper.selectBatchIds(meterTypeIdList);
        }
        return meterTypes;
    }

    public void addMeterRecord(MeterReadingRecordDTO meterReadingRecordDTO) {
        MeterReadingRecord meterReadingRecord = new MeterReadingRecord();
        BeanUtils.copyProperties(meterReadingRecordDTO , meterReadingRecord);
        meterReadingRecord.setSerialNumber(String.valueOf(System.nanoTime()));
        Long roomId = sharedService.getRoomByCode(meterReadingRecordDTO.getRoomCode()).getId();
        meterReadingRecord.setRoomId(roomId);
        meterReadingRecordMapper.insert(meterReadingRecord);
    }

    public void modifyMeterRecord(MeterReadingRecord meterReadingRecord) {
        meterReadingRecordMapper.updateById(meterReadingRecord);
    }

    public void delMeterRecord(Long id) {
        meterReadingRecordMapper.deleteById(id);
    }

    public Page<MeterReadingRecord> listMeterRecord(MeterReadingRecordSearchDTO condition) {
        LambdaQueryWrapper<MeterReadingRecord> query = new LambdaQueryWrapper<>();
        if(condition.getNum() != null && !condition.getNum().isEmpty()) {
            query.eq(MeterReadingRecord::getNum , condition.getNum());
        }
        if(condition.getStartChargeTime() != null) {
            query.ge(MeterReadingRecord::getStartChargeTime , condition.getStartChargeTime());
        }
        if(condition.getEndChargeTime() != null) {
            query.le(MeterReadingRecord::getEndChargeTime , condition.getEndChargeTime());
        }
        Page<MeterReadingRecord> page = new Page<>(condition.getPageNum() , condition.getPageSize());
        return meterReadingRecordMapper.selectPage(page , query);
    }
}
