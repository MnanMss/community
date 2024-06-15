package com.cdu.community.server.charge.port;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.dto.*;
import com.cdu.community.server.charge.domain.entity.*;
import com.cdu.community.server.charge.domain.vo.ChargeManageVO;
import com.cdu.community.server.charge.domain.vo.ChargeProjectVo;
import com.cdu.community.server.charge.domain.vo.ChargeRoomStatisticsVO;
import com.cdu.community.server.charge.domain.vo.ChargeRoomVO;
import com.cdu.community.server.charge.infrastructure.orm.ChargeManageMapper;
import com.cdu.community.server.shared.domain.PageVO;
import com.cdu.community.server.shared.domain.Resp;
import com.cdu.community.server.shared.domain.entity.Room;
import com.cdu.community.server.shared.port.SharedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mila
 * @date 2024/6/14 下午3:18
 */
@RestController
@RequestMapping("/api/charge")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name="ChargeController" , description = "收费管理")
public class ChargeController {
    private final ChargeService chargeService;
    private final SharedService sharedService;

    @PostMapping("/project")
    @Operation(description = "新增收费项目")
    public Resp<Void> addChargeProject(@RequestBody ChargeProjectDTO chargeProjectDTO) {
        log.info("新增收费项目：{}" , chargeProjectDTO);
        chargeService.addChargeProject(chargeProjectDTO);
        return Resp.ok();
    }

    @PutMapping("/project")
    @Operation(description = "修改收费项目")
    public Resp<Void> editChargeProject(Long id, @RequestBody ChargeProjectDTO chargeProjectDTO){
        log.info("修改收费项目：{}", chargeProjectDTO);
        chargeService.editChargeProject(id, chargeProjectDTO);
        return Resp.ok();
    }

    @DeleteMapping("/project/{id}")
    @Operation(description = "删除收费项目")
    public Resp<Void> deleteChargeProject(@PathVariable("id") Long id){
        log.info("删除收费项目：{}", id);
        chargeService.deleteChargeProject(id);
        return Resp.ok();
    }

    @GetMapping("/project/list")
    @Operation(description = "查询收费项目列表")
    public Resp<PageVO<ChargeProjectVo>> listChargeProject(ChargeProjectDTO condition){
        log.info("查询收费项目列表：{}", condition);
        Page<ChargeProject> list = chargeService.listChargeProject(condition);
        log.info(list.getRecords().toString());
        List<ChargeProjectVo> voList = list.getRecords().stream().
                map(ChargeProjectVo::of)
                .toList();
        return Resp.ok(new PageVO<>(list.getTotal(), voList));
    }

    @PostMapping("/room/project")
    @Operation(description = "新增房间收费项目")
    public Resp<Void> addChargeRoom(@RequestBody ChargeRoomDTO chargeRoomDTO){
        log.info("新增房间收费项目：{}", chargeRoomDTO);
        chargeService.addChargeRoom(chargeRoomDTO);
        return Resp.ok();
    }

    @PutMapping("/room/project")
    @Operation(description = "修改房间收费项目")
    public Resp<Void> editChargeRoom(Long id, @RequestBody ChargeRoomDTO chargeRoomDTO){
        log.info("修改房间收费项目：{}", chargeRoomDTO);
        chargeService.editChargeRoom(id, chargeRoomDTO);
        return Resp.ok();
    }

    @DeleteMapping("/room/project/{id}")
    @Operation(description = "修改房间收费项目")
    public Resp<Void> deleteChargeRoom(@PathVariable("id")Long id){
        log.info("删除房间收费项目：{}", id);
        chargeService.deleteChargeRoom(id);
        return Resp.ok();
    }

    @GetMapping("/room/project/list")
    @Operation(description = "查询房间收费项目列表")
    public Resp<PageVO<ChargeRoomVO>> listChargeRoom(ChargeRoomDTO condition){
        log.info("查询房间收费项目列表：{}", condition);
        Page<ChargeRoom> list = chargeService.listChargeRoom(condition);
        log.info(list.getRecords().toString());
        List<ChargeRoomVO> voList = list.getRecords().stream()
                .map(chargeRoom -> {
                    Room room = sharedService.getRoomById(chargeRoom.getRoomId());
                    ChargeProject chargeProject = chargeService.getChargeProjectById(chargeRoom.getChargeProjectId());
                    return ChargeRoomVO.of(chargeRoom, room, chargeProject);
                })
                .toList();
        return Resp.ok(new PageVO<>(list.getTotal(), voList));
    }

    /**
     * 暂定
     * */
    @GetMapping("/room/project/schedule")
    @Operation(description = "房间收费项目一览表")
    public Resp<PageVO<ChargeRoomStatisticsVO>> scheduleChargeRoom(ChargeRoomStatisticsDTO condition){
        log.info("房间收费项目一览表：{}", condition);
        PageVO<ChargeRoomStatisticsVO> list = chargeService.scheduleChargeRoom(condition);
        return Resp.ok(list);
    }

    /**
     * 收费管理表、收费项目表、应收管理表
     * */
    @GetMapping("/manage/{roomId}")
    @Operation(description = "获取收费选项-应收明细列表")
    public Resp<PageVO<ChargeManageVO>> listChargeManage(ChargeManageDTO condition){
        log.info("获取收费选项-应收明细列表：{}", condition);
        PageVO<ChargeManageVO> list = chargeService.listChargeManage(condition);
        log.info(list.toString());
        return Resp.ok(list);
    }

    @PostMapping("/receive/manage")
    @Operation(description = "添加应收记录")
    public Resp<Void> addReceiveManage(@RequestBody ReceiveManageDTO receiveManageDTO){
        log.info("添加应收记录：{}", receiveManageDTO);
        chargeService.addReceiveManage(receiveManageDTO);
        return Resp.ok();
    }


}
