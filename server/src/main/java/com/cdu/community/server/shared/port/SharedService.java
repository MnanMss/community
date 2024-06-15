package com.cdu.community.server.shared.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.shared.domain.dto.RoomDTO;
import com.cdu.community.server.shared.domain.entity.Building;
import com.cdu.community.server.shared.domain.entity.Edifice;
import com.cdu.community.server.shared.domain.entity.Room;
import com.cdu.community.server.shared.infrastructure.orm.BuildingMapper;
import com.cdu.community.server.shared.infrastructure.orm.EdificeMapper;
import com.cdu.community.server.shared.infrastructure.orm.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Service
@RequiredArgsConstructor
public class SharedService {
    private final RoomMapper roomMapper;
    private final BuildingMapper buildingMapper;
    private final EdificeMapper edificeMapper;

    /**
     * 查询大厦/小区信息
     *
     * @param edificeId 大厦/小区ID
     * */
    public Edifice getEdificeById(Long edificeId) {
        return edificeMapper.selectById(edificeId);
    }

    /**
     * 查询楼栋信息
     *
     * @param buildingId 楼栋ID
     * */
    public Building getBuildingById(Long buildingId) {
        return buildingMapper.selectById(buildingId);
    }

    /**
     * 查询房间信息
     *
     * @param roomId 房间ID
     * */
    public Room getRoomById(Long roomId){
        return roomMapper.selectById(roomId);
    }

    /**
     * 查询房间列表
     * */
    public Page<Room> listRoom(RoomDTO condition) {
        LambdaQueryWrapper<Room> query = new LambdaQueryWrapper<>();
        if (condition.getCode() != null && !condition.getCode().isEmpty()){
            query.likeLeft(Room::getCode, condition.getCode());
        }
        Page<Room> page = new Page<>(condition.getPageNum(), condition.getPageSize());
        return roomMapper.selectPage(page, query);
    }

    /**
     * 查询房间信息
     *
     * @param roomCode 房间号
     * */
    public Room getRoomByCode(String roomCode){
        return roomMapper.select
    }
}