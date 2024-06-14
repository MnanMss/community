package com.cdu.community.server.meter.port;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.meter.domain.dto.MeterTypeDTO;
import com.cdu.community.server.meter.domain.dto.MeterTypeSearchDTO;
import com.cdu.community.server.meter.domain.entity.MeterType;
import com.cdu.community.server.meter.domain.vo.MeterTypeVO;
import com.cdu.community.server.shared.domain.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.cdu.community.server.shared.domain.PageDTO;

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

    @GetMapping("/list")
    @Operation(description = "查询表计类别列表")
    public Resp<PageDTO<MeterTypeVO>> listMeterType(MeterTypeSearchDTO condition) {
        log.info("查询表计类别列表：{}" , condition);
        Page<MeterType> list = meterService.listMeterType(condition);
        log.info(list.getRecords().toString());
        List<MeterTypeVO> voList = list.getRecords().stream().map(MeterTypeVO::of).toList();
        return Resp.ok(new PageDTO<>(list.getTotal() , voList));
    }
    @PutMapping
    @Operation(description = "修改表计类别")
    public Resp<Void> updateMeterType( @RequestBody @Valid MeterType meterType) {
        log.info("修改表计类别：{}" ,meterType);
        meterService.updateMeterType(meterType);
        return Resp.ok();
    }
    @DeleteMapping("/type/{id}")
    @Operation(description = "删除表计类别")
    public Resp<Void> delMeterType( @PathVariable String id) {
        log.info("删除表计类别：{}" , id);
        meterService.delMeterType(id);
        return Resp.ok();
    }

}
