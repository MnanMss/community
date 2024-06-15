package com.cdu.community.server.shared.port;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.shared.domain.PageVO;
import com.cdu.community.server.shared.domain.Resp;
import com.cdu.community.server.shared.domain.dto.RoomDTO;
import com.cdu.community.server.shared.domain.dto.RoomProprietorDTO;
import com.cdu.community.server.shared.domain.entity.Building;
import com.cdu.community.server.shared.domain.entity.Edifice;
import com.cdu.community.server.shared.domain.entity.Room;
import com.cdu.community.server.shared.domain.entity.RoomProprietor;
import com.cdu.community.server.shared.domain.vo.RoomProprietorVO;
import com.cdu.community.server.shared.domain.vo.RoomVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@RestController
@RequestMapping("/api/shared")
@Validated
@RequiredArgsConstructor
@Slf4j
@Tag(name = "SharedController", description = "公共功能管理")
public class SharedController {
    private final SharedService sharedService;

    /**
     * @param edificeId 大厦/小区ID
     * */
    @GetMapping("/edifice/{edificeId}")
    @Operation(description = "查询大厦/小区信息")
    public Resp<Edifice> getEdifice(@PathVariable Long edificeId){
        return Resp.ok(sharedService.getEdificeById(edificeId));
    }

    /**
     * 查询房间列表
     * */
    @GetMapping("/room/list")
    @Operation(description = "查询房间列表")
    public Resp<PageVO<RoomVO>> listRoom(RoomDTO condition){
        log.info("查询房间列表：{}", condition);
        Page<Room> list = sharedService.listRoom(condition);
        log.info(list.getRecords().toString());
        List<RoomVO> voList = list.getRecords().stream()
                .map(room -> {
                    Building building = sharedService.getBuildingById(room.getBuildingId());
                    return RoomVO.of(room, building);
                })
                .toList();
        return Resp.ok(new PageVO<>(list.getTotal(), voList));
    }

    @GetMapping("/manage/proprietor/list")
    @Operation(description = "查询房间业主信息列表")
    public Resp<PageVO<RoomProprietorVO>> listRoomProprietor(RoomProprietorDTO condition){
        log.info("查询房间业主信息列表：{}", condition);
        Page<RoomProprietor> list = sharedService.listRoomProprietor(condition);
        log.info(list.getRecords().toString());
        List<RoomProprietorVO> voList = list.getRecords().stream()
                .map(RoomProprietorVO::of)
                .toList();
        return Resp.ok(new PageVO<>(list.getTotal(), voList));
    }
}