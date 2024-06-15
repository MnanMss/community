package com.cdu.community.server.meter.port;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.meter.domain.dto.*;
import com.cdu.community.server.meter.domain.entity.Meter;
import com.cdu.community.server.meter.domain.entity.MeterReadingRecord;
import com.cdu.community.server.meter.domain.entity.MeterType;
import com.cdu.community.server.meter.domain.entity.RoomStatistics;
import com.cdu.community.server.meter.domain.vo.MeterTypeVO;
import com.cdu.community.server.meter.domain.vo.MeterVO;
import com.cdu.community.server.meter.domain.vo.RoomStatisticsVO;
import com.cdu.community.server.shared.domain.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cdu.community.server.shared.domain.PageVO;

import java.util.List;

/**
 * @author mila
 * @date 2024/6/14 下午2:55
 */
@RestController
@RequestMapping("/api/meter")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name="MeterController" , description = "抄表管理")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("/type")
    @Operation(description = "添加表计类别")
    public Resp<Void> addMeterType(@RequestBody @Valid MeterTypeDTO meterTypeDTO) {
        log.info("添加表计类别：{}" , meterTypeDTO);
        meterService.addMeterType(meterTypeDTO);
        return Resp.ok();
    }

    @GetMapping("/type/list")
    @Operation(description = "查询表计类别列表")
    public Resp<PageVO<MeterTypeVO>> listMeterType(MeterTypeSearchDTO condition) {
        log.info("查询表计类别列表：{}" , condition);
        Page<MeterType> list = meterService.listMeterType(condition);
        log.info(list.getRecords().toString());
        List<MeterTypeVO> voList = list.getRecords().stream().map(MeterTypeVO::of).toList();
        return Resp.ok(new PageVO<>(list.getTotal() , voList));
    }
    @PutMapping("/type")
    @Operation(description = "修改表计类别")
    public Resp<Void> updateMeterType( @RequestBody @Valid MeterType meterType) {
        log.info("修改表计类别：{}" ,meterType);
        meterService.updateMeterType(meterType);
        return Resp.ok();
    }
    @DeleteMapping("/type/{id}")
    @Operation(description = "删除表计类别")
    public Resp<Void> delMeterType(@PathVariable Long id) {
        log.info("删除表计类别：{}" , id);
        meterService.delMeterType(id);
        return Resp.ok();
    }

    @PostMapping
    @Operation(description = "新增表计")
    public Resp<Void> addMeter(@RequestBody @Valid MeterDto meterDto) {
        log.info("新增表计：{}",  meterDto);
        meterService.addMeter(meterDto);
        return Resp.ok();
    }

    @GetMapping("/list")
    @Operation(description = "表计列表")
    public Resp<PageVO<MeterVO>> listMeter(MeterSearchDTO condition) {
        log.info("表计列表：{}" , condition);
        Page<Meter> page = meterService.listMeter(condition);
        List<MeterVO> voList = page.getRecords().stream().map(MeterVO::of).toList();
        return Resp.ok(new PageVO<>(page.getTotal() , voList));
    }


    @PutMapping
    @Operation(description = "修改表计")
    public Resp<Void> modifyMeter(@RequestBody @Valid Meter meter) {
        log.info("修改表计：{}" , meter);
        meterService.modifyMeter(meter);
        return Resp.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "删除表计")
    public Resp<Void> delMeter(@PathVariable Long id) {
        log.info("删除表计：{}" , id);
        meterService.delMeter(id);
        return Resp.ok();
    }

    @GetMapping("/room/statistics")
    @Operation(description = "房间表计统计")
    public Resp<PageVO<RoomStatisticsVO>> listRoomStatistics(RoomStatisticsSearchDTO condition) {
        log.info("房间表计统计：{}" , condition);
        Page<RoomStatistics> page =  meterService.listRoomStatistics(condition);
        List<RoomStatistics> roomStatistics = page.getRecords();
        List<RoomStatisticsVO> list = roomStatistics.stream().map(r -> {
            RoomStatisticsVO roomStatisticsVO = RoomStatisticsVO.builder()
                .code(r.getRoomCode())
                .proprietorName(r.getProprietorName())
                .build();
            List<MeterType> meterTypes = meterService.listMeterTypeByRoomId(r.getRoomId());
            roomStatisticsVO.setMeters(RoomStatisticsVO.convertMeterType(meterTypes));
            return roomStatisticsVO;
        }).toList();
        return Resp.ok(new PageVO<>(page.getTotal() , list));
    }

    @PostMapping("/reading/record")
    @Operation(description = "新增抄表记录")
    public Resp<Void> addMeterRecord(@RequestBody @Valid MeterReadingRecordDTO meterReadingRecordDTO) {
        log.info("新增抄表记录：{}" , meterReadingRecordDTO);
        meterService.addMeterRecord(meterReadingRecordDTO);
        return Resp.ok();
    }


    @PutMapping("/reading/record")
    @Operation(description = "修改抄表记录")
    public Resp<Void> modifyMeterRecord(@RequestBody @Valid MeterReadingRecord meterReadingRecord) {
        log.info("修改抄表记录：{}" , meterReadingRecord);
        meterService.modifyMeterRecord(meterReadingRecord);
        return Resp.ok();
    }

    @DeleteMapping("/reading/record/{id}")
    @Operation(description = "删除抄表记录")
    public Resp<Void> delMeterRecord(@PathVariable Long id) {
        log.info("删除抄表记录：{}" , id);
        meterService.delMeterRecord(id);
        return Resp.ok();
    }
}
